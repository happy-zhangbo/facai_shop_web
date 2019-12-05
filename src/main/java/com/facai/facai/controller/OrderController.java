package com.facai.facai.controller;

import com.facai.facai.entity.Order;
import com.facai.facai.entity.UserInfo;
import com.facai.facai.service.OrderService;
import com.facai.facai.util.RedisUtil;
import com.facai.facai.util.Resp;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @auth Auth :zhangbo
 * @date Date : 2019年11月28日 10:53
 */
@RestController
@RequestMapping(value = "order")
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisUtil redisUtil;

    //提交订单 （访问统一下单接口）
    @RequestMapping(value = "commit_unifiedorder")
    public Resp commit_unifiedorder(@RequestHeader String token, @RequestBody Order order){
        UserInfo userInfo = redisUtil.getUserInfo(token);
        Map<String,String> map = orderService.commitOrder(order,userInfo);
        if(null != map){
            if("SUCCESS".equals(map.get("return_code"))){
                return Resp.success("订单提交成功",map);
            }else{
                return Resp.error(map.get("return_msg"));
            }
        }else{
            return Resp.error("服务错误");
        }
    }

    //查询订单
    @RequestMapping(value = "select_orders")
    public Resp selectAllOrder(@RequestHeader String token){
        UserInfo userInfo = redisUtil.getUserInfo(token);
        List<Order> list = orderService.selectAllOrderByUserId(userInfo.getuId());
        if(null != list && 0 != list.size()){
            return Resp.success("查询完成",list);
        }else{
            return Resp.error("订单为空");
        }
    }

    //查询订单详情
    @RequestMapping(value = "select_selectAllOrderDetail")
    public Resp selectAllOrderDetail(@RequestHeader String token,Integer oid){
        UserInfo userInfo = redisUtil.getUserInfo(token);
        Order order = orderService.selectOrderByOidAndUserId(oid,userInfo.getuId());
        if(null != order){
            return Resp.success("查询完成",order);
        }else{
            return Resp.error("没有此订单信息");
        }
    }

    //取消订单
    @RequestMapping(value = "update_cancelOrder")
    public Resp cancelOrderBySerialNum(@RequestHeader String token,String serialNum){
        UserInfo userInfo = redisUtil.getUserInfo(token);
        int num = orderService.cancelOrderBySerialNum(serialNum,userInfo.getuId());
        if(num > 0){
            return Resp.success("订单已取消");
        }else{
            return Resp.error("订单取消失败");
        }
    }

    //支付订单
    @RequestMapping(value = "pay_order")
    public Resp payOrder(@RequestHeader String token,String serialNum){
        UserInfo userInfo = redisUtil.getUserInfo(token);
        Map<String,String> map = redisUtil.getOrder(serialNum,userInfo.getuId());
        if(null != map){
            return Resp.success("ok",map);
        }else{
            return Resp.error("订单已取消，或不存在");
        }


    }

    //支付回调




    //
}
