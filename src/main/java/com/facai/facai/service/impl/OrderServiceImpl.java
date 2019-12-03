package com.facai.facai.service.impl;

import com.facai.facai.dao.OrderDetailMapper;
import com.facai.facai.dao.OrderMapper;
import com.facai.facai.entity.Order;
import com.facai.facai.service.OrderService;
import com.facai.facai.util.Tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

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
    private OrderDetailMapper orderDetailMapper;

    @Transactional(propagation = Propagation.REQUIRED , readOnly = false)
    @Override
    public int commitOrder(Order order) throws Exception {
        //生成订单编号
        order.setoSerialnum(Tool.generateOrderNum());
        order.setoState(0);
        order.setoCreatetime(new Date());
        if(orderMapper.insert(order) > 0){
            if(orderDetailMapper.insertBatchOrderDetail(order.getOrderDetail()) > 0){
                //订单提交数据库成功后，根据支付方式调用统一下单接口


                return 1;
            }else{
                logger.error("订单详情添加失败");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
        return 0;
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
}
