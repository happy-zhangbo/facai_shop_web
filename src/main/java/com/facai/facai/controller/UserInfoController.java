package com.facai.facai.controller;

import com.facai.facai.entity.Address;
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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "userinfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RedisUtil redisUtil;

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
        redisUtil.setUserInfo(userInfo);

        return Resp.success("登录成功",map);
    }


    //查询所有地址
    @RequestMapping(value = "address/select_address")
    public Resp selectAllAddress(@RequestHeader String token){
        UserInfo userInfo = redisUtil.getUserInfo(token);
        List<Address> list = userInfoService.selectAllAddress(userInfo.getuId());
        if(null != list && 0 != list.size()){
            return Resp.success("查询完成",list);
        }else{
            return Resp.error("暂无地址");
        }
    }

    //添加地址
    @RequestMapping(value = "address/insert_insertAddress")
    public Resp insertAddress(@RequestHeader String token,Address address){
        UserInfo userInfo = redisUtil.getUserInfo(token);
        address.setaUserid(userInfo.getuId());
        int num = userInfoService.insertAddress(address);
        if(num > 0){
            return Resp.success("添加完成");
        }

        return Resp.error("添加失败");
    }

    //更新地址
    @RequestMapping(value = "address/update_updateAddress")
    public Resp updateAddress(@RequestHeader String token,Address address){
        UserInfo userInfo = redisUtil.getUserInfo(token);
        address.setaUserid(userInfo.getuId());
        int num = userInfoService.updateAddress(address);
        if(num > 0){
            return Resp.success("更新完成");
        }

        return Resp.error("更新失败");
    }
    //删除地址
    @RequestMapping(value = "address/delete_deleteAllAddress")
    public Resp deleteAllAddress(@RequestHeader String token,Integer aId){
        UserInfo userInfo = redisUtil.getUserInfo(token);
        int num = userInfoService.deleteAddress(aId,userInfo.getuId());
        if(num > 0){
            return Resp.success("删除完成");
        }
        return Resp.error("删除失败");
    }

    //获取用户的默认地址
    @RequestMapping(value = "address/select_defaultAddress")
    public Resp getDefaultAddressByUserInfo(@RequestHeader String token){
        UserInfo userInfo = redisUtil.getUserInfo(token);
        Address address = userInfoService.selectDefaultAddressByUserId(userInfo.getuId());
        if(null != address){
            return Resp.success("查询完成",address);
        }else{
            return Resp.error("暂无默认地址");
        }

    }
}
