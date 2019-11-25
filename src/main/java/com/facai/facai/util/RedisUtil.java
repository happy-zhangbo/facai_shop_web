package com.facai.facai.util;

import com.facai.facai.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {

    @Autowired
    public RedisTemplate redisTemplate;

    public UserInfo getUserInfo(String token){
        String uid = JwtUtil.getCurUserId(token);
        String u = redisTemplate.opsForValue().get(uid).toString();

        return JsonUtil.jsonToBean(u,UserInfo.class);
    }

    public void setUserInfo(UserInfo userInfo){
        redisTemplate.opsForValue().set(userInfo.getuId().toString(),JsonUtil.beanToJson(userInfo));
    }
}
