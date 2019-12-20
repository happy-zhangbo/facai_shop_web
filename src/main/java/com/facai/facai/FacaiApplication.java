package com.facai.facai;

import com.facai.facai.weixin.WXPayConstants;
import com.facai.facai.weixin.WXPayIntegrated;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.facai.facai.dao")
public class FacaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacaiApplication.class, args);
    }
}