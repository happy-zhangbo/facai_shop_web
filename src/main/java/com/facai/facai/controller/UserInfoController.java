package com.facai.facai.controller;

import com.facai.facai.entity.UserInfo;
import com.facai.facai.service.UserInfoService;
import com.facai.facai.util.JsonUtil;
import com.facai.facai.util.JwtUtil;
import com.facai.facai.util.RedisUtil;
import com.facai.facai.util.Resp;
import com.facai.facai.weixin.WeXinUtil;
import com.facai.facai.weixin.WxLoginResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "userinfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    RedisTemplate redisTemplate;

    //微信小程序登录
    @RequestMapping(value = "login_wxLogin")
    public Resp wxLogin(String code,String nickName,String avatar){
        if(null == code||"".equals(code)){
            return Resp.error("code为空");
        }
        Map<String,Object> map = new HashMap<>();
        //登录
        WxLoginResp wxLoginResp = WeXinUtil.wxLogin(code); //返回openid
        //调用登录注册服务接口
        UserInfo userInfo = userInfoService.selectLoginByWeiXin(wxLoginResp.getOpenid(),nickName,avatar);
        if(null == userInfo){
            return Resp.error("新用户注册失败");
        }
        String token = JwtUtil.createToken(userInfo,1000*60*30);
        map.put("nickName",userInfo.getuNickname());
        map.put("avatar",userInfo.getuAvatar());
        map.put("token",token);

        redisTemplate.opsForValue().set("u", JsonUtil.beanToJson(userInfo));

        return Resp.success("登录成功",map);
    }

}
