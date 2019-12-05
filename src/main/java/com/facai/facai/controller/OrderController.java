package com.facai.facai.controller;

import com.facai.facai.entity.Order;
import com.facai.facai.entity.UserInfo;
import com.facai.facai.service.OrderService;
import com.facai.facai.util.RedisUtil;
import com.facai.facai.util.Resp;
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



    //
}
