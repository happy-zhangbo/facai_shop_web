package com.facai.facai.service.impl;

import com.facai.facai.dao.CartMapper;
import com.facai.facai.dao.OrderDetailMapper;
import com.facai.facai.dao.OrderMapper;
import com.facai.facai.entity.Order;
import com.facai.facai.entity.OrderDetail;
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
    private RedisUtil redisUtil;


    @Transactional(propagation = Propagation.REQUIRED , readOnly = false)
    @Override
    public Map<String,String> commitOrder(Order order, UserInfo userInfo) {
        Map<String,String> map = null;
        //生成订单编号
        order.setoSerialnum(Tool.generateOrderNum());
        order.setoState(0);
        order.setoCreatetime(new Date());
        if(orderMapper.insert(order) > 0){
            for (OrderDetail orderDetail : order.getOrderDetail()) {
                orderDetail.setOdOid(order.getoId());
            }

            if(orderDetailMapper.insertBatchOrderDetail(order.getOrderDetail()) > 0){
                // 清空购物车
                List<Integer> cIdList = new ArrayList<Integer>();
                for (OrderDetail orderDetail : order.getOrderDetail()){
                    cIdList.add(orderDetail.getOcId());
                }
                if(cartMapper.deleteBatchByCIdAndUserId(cIdList,userInfo.getuId()) <= 0){
                    return null;
                }

                //订单提交数据库成功后，根据支付方式调用统一下单接口

                if(order.getoType() == 1){ //微信支付
                    map = WeXinUtil.wxPayUnifiedorder(order,userInfo.getuOpenid());
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

    @Override
    public int payOrder() {

        return 0;
    }

    @Override
    public List<Order> selectAllOrderByUserId(Integer userId) {
        return orderMapper.selectAllOrderByUserId(userId);
    }

    @Override
    public Order selectOrderByOidAndUserId(Integer oId, Integer userId) {
        return orderMapper.selectOrderByOidAndUserId(oId,userId);
    }

    @Override
    public int cancelOrderBySerialNum(String serialNum) {
        return 0;
    }
}
