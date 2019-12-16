package com.facai.facai.service.impl;

import com.facai.facai.dao.CartMapper;
import com.facai.facai.dao.OrderDetailMapper;
import com.facai.facai.dao.OrderMapper;
import com.facai.facai.dao.ProductSpecsMapper;
import com.facai.facai.entity.Order;
import com.facai.facai.entity.OrderDetail;
import com.facai.facai.entity.ProductSpecs;
import com.facai.facai.entity.UserInfo;
import com.facai.facai.service.OrderService;
import com.facai.facai.util.RedisUtil;
import com.facai.facai.util.Tool;
import com.facai.facai.weixin.WXPayUtil;
import com.facai.facai.weixin.WeXinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.*;

/**
 * @auth Auth :zhangbo
 * @date Date : 2019年11月28日 9:16
 */
@Service
public class OrderServiceImpl implements OrderService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ProductSpecsMapper productSpecsMapper;

    @Autowired
    private WeXinUtil weXinUtil;

    @Autowired
    private RedisUtil redisUtil;


    @Transactional(propagation = Propagation.REQUIRED , readOnly = false)
    @Override
    public Map<String,String> commitOrder(Order order, UserInfo userInfo) {
        Map<String,String> map = null;
        //生成订单编号
        order.setoSerialnum(Tool.generateOrderNum());
        order.setoState(0);
        order.setoCreatetime(new Date());
        order.setoUserid(userInfo.getuId());
        //计算价格
        order.setoTotalamount(addendTotal(order.getOrderDetail()));
        if(orderMapper.insert(order) > 0){
            // 清空购物车
            List<Integer> cIdList = new ArrayList<Integer>();
            for (OrderDetail orderDetail : order.getOrderDetail()) {
                orderDetail.setOdOid(order.getoId());
                cIdList.add(orderDetail.getOcId());
            }

            if(orderDetailMapper.insertBatchOrderDetail(order.getOrderDetail()) > 0){
                if(cartMapper.deleteBatchByCIdAndUserId(cIdList,userInfo.getuId()) <= 0){
                    return null;
                }
                //订单提交数据库成功后，根据支付方式调用统一下单接口

                if(order.getoType() == 1){ //微信支付
                    map = weXinUtil.wxPayUnifiedorder(order,userInfo.getuOpenid());
                    if(null == map){
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    }
                }else{
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
                redisUtil.setOrder("order:"+order.getoSerialnum()+"-"+userInfo.getuId(),map);
                return map;
            }else{
                logger.error("订单详情添加失败");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return null;
            }
        }else{
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return null;
    }

    public BigDecimal addendTotal(List<OrderDetail> list){

        List<ProductSpecs> psList = productSpecsMapper.selectOrderProductSpecs(list);
        BigDecimal res = new BigDecimal(0);
        int i = 0;
        for (OrderDetail orderDetail:list) {
            for (ProductSpecs productSpecs:psList){
                if(orderDetail.getOdPsid() == productSpecs.getsId()){
                    BigDecimal total = productSpecs.getsPrice().multiply(new BigDecimal(orderDetail.getOdCount()));
                    orderDetail.setOdTotal(total);
                    res = res.add(total);
                }
            }
            list.set(i,orderDetail);
            i++;

        }
        return res;
    }

    @Override
    public int payOrder() {

        return 0;
    }

    @Override
    public List<Order> selectAllOrderByUserId(Integer userId,Integer oState) {
        return orderMapper.selectAllOrderByUserId(userId,oState);
    }

    @Override
    public Order selectOrderByOidAndUserId(Integer oId, Integer userId) {
        return orderMapper.selectOrderByOidAndUserId(oId,userId);
    }

    @Override
    public int cancelOrderBySerialNum(String serialNum,Integer userId) {
        return orderMapper.cancelOrderBySerialNum(serialNum,userId);
    }

    @Override
    public int confirmOrder(String serialNum, Integer userId) {
        return orderMapper.confirmOrderBySerialNum(serialNum,userId);
    }


    @Override
    public int wxnotifyResult(boolean result, String serialNum,String transactionNum) {
        if(result){
            return orderMapper.wxnotifyResult(1,serialNum,transactionNum);
        }else{
            //支付失败
            return orderMapper.wxnotifyResult(-2,serialNum,transactionNum);
        }

    }
}
