package com.facai.facai.util;

import com.facai.facai.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisUtil {

    @Autowired
    public RedisTemplate redisTemplate;

    public UserInfo getUserInfo(String key){
        UserInfo u = (UserInfo) redisTemplate.opsForValue().get("u");
        return u;
    }

    public void setUserInfo(UserInfo userInfo){
        redisTemplate.opsForValue().set("u",userInfo);
    }
}
