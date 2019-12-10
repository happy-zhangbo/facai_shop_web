package com.facai.facai;

import com.facai.facai.dao.*;
import com.facai.facai.entity.OrderDetail;
import com.facai.facai.entity.ProductSpecs;
import com.facai.facai.entity.UserInfo;
import com.facai.facai.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


@SpringBootTest
class FacaiApplicationTests {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductSpecsMapper productSpecsMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        System.out.println(JsonUtil.beanToJson(orderMapper.selectOrderByOidAndUserId(47,5)));

    }

}
