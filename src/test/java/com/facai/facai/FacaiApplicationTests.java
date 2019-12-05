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
        List<OrderDetail> list = new ArrayList<OrderDetail>();
        OrderDetail o1 = new OrderDetail();
        o1.setOdPsid(1);
        list.add(o1);
        OrderDetail o2 = new OrderDetail();
        o2.setOdPsid(2);
        list.add(o2);
        OrderDetail o3 = new OrderDetail();
        o3.setOdPsid(3);
        list.add(o3);
        List<ProductSpecs> psList = productSpecsMapper.selectOrderProductSpecs(list);
        BigDecimal res = new BigDecimal(0);
        int i = 0;
        for (ProductSpecs productSpecs:psList) {
            res = res.add(productSpecs.getsPrice());
            OrderDetail od = list.get(i);
            od.setOdTotal(res);
            list.set(i,od);
            i++;
        }

        System.out.println(res.intValue()+"");

    }

}
