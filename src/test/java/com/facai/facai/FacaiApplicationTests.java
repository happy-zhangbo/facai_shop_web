package com.facai.facai;

import com.facai.facai.dao.CartMapper;
import com.facai.facai.dao.ProductMapper;
import com.facai.facai.dao.UserInfoMapper;
import com.facai.facai.entity.UserInfo;
import com.facai.facai.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.math.BigDecimal;
import java.util.Date;


@SpringBootTest
class FacaiApplicationTests {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        System.out.println(JsonUtil.listToJson(cartMapper.selectAllCartByUserId(5)));
    }

}
