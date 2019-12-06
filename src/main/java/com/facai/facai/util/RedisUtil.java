package com.facai.facai.util;

import com.alibaba.fastjson.JSON;
import com.facai.facai.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

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
        redisTemplate.opsForValue().set(userInfo.getuId().toString(),JsonUtil.beanToJson(userInfo),3600, TimeUnit.SECONDS);
    }

    public void setOrder(String key, Map<String,String> map){
        redisTemplate.opsForValue().set(key, JSON.toJSONString(map),1800, TimeUnit.SECONDS);
    }

    public Map<String,String> getOrder(String serialnum,Integer uid){
        String str = redisTemplate.opsForValue().get("order:"+serialnum+"-"+uid).toString();
        Map<String,String> map = (Map<String,String>)JSON.parse(str);
        return map;
    }

    public void removeOrder(String serialnum,Integer uid){
        redisTemplate.delete("order:"+serialnum+"-"+uid);
    }
}
