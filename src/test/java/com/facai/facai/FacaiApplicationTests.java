package com.facai.facai;

import com.facai.facai.dao.UserInfoMapper;
import com.facai.facai.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;


@SpringBootTest
class FacaiApplicationTests {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        UserInfo u = new UserInfo();
        u.setuNickname("张发财");
        u.setuPassword("zhangbo");
        u.setuState(1);
        u.setuType(1);
        u.setuOpenid("123");
        u.setuCreatetime(new Date());
        u.setuRegtype(1);
        u.setuLogintime(new Date());
        u.setuEmail("854008949@qq.com");
        u.setuPhone("1801009101612543");
        redisTemplate.opsForValue().set("u",u);


//        System.out.println(userInfoMapper.insert(u));
    }

}
