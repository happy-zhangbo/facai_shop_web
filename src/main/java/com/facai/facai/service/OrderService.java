package com.facai.facai.service;

import com.facai.facai.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auth Auth :zhangbo
 * @date Date : 2019年11月28日 9:02
 */
@Service
public interface OrderService {

    //提交订单
    public int commitOrder(Order order) throws Exception;

    //支付订单
    public int payOrder();

    //查询订单
    public List<Order> selectAllOrderByUserId(Integer userId);

    //查询订单详情
    public Order selectOrderByOidAndUserId(Integer oId,Integer userId);

}
