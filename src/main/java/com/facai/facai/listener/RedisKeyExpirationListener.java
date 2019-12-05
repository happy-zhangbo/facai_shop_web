package com.facai.facai.listener;

import com.facai.facai.service.OrderService;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @auth Auth :zhangbo
 * @date Date : 2019年12月05日 17:04
 */
@Log4j2
@Configuration
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {


    @Autowired
    private OrderService orderService;


    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对redis数据失效事件，进行数据处理
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 用户做自己的业务处理即可,注意message.toString()可以获取失效的key
        String expiredKey = message.toString();
        log.info(expiredKey+"已失效");
        if(expiredKey.startsWith("order:")){
            //如果是Order:开头的key，进行处理
            //获取订单号
            String serialNum = expiredKey.substring(expiredKey.indexOf(":")+1,expiredKey.lastIndexOf("-"));
            String userId = expiredKey.substring(expiredKey.lastIndexOf("-")+1,expiredKey.length());
            orderService.cancelOrderBySerialNum(serialNum,Integer.parseInt(userId));
        }
    }

}
