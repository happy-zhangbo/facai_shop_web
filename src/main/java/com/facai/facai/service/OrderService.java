package com.facai.facai.service;

import com.facai.facai.entity.Order;
import com.facai.facai.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @auth Auth :zhangbo
 * @date Date : 2019年11月28日 9:02
 */
@Service
public interface OrderService {

    //提交订单
    public Map<String,String> commitOrder(Order order, UserInfo userInfo);

    //支付订单
    public int payOrder();

    //查询订单
    public List<Order> selectAllOrderByUserId(Integer userId,Integer oState);

    //查询订单详情
    public Order selectOrderByOidAndUserId(Integer oId,Integer userId);

    //取消订单
    public int cancelOrderBySerialNum(String serialNum,Integer userId);

    public int confirmOrder(String serialNum,Integer userId);

    //处理支付订单
    public int wxnotifyResult(boolean result,String serialNum,String transactionNum);


}
