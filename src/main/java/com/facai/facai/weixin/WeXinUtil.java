package com.facai.facai.weixin;

import com.facai.facai.constant.Constant;
import com.facai.facai.entity.Order;
import com.facai.facai.util.JsonUtil;
import com.facai.facai.util.OkHttp;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WeXinUtil {

    //访问登录接口
    public static WxLoginResp wxLogin(String code) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("appid", Constant.appid);
        map.put("secret", Constant.appSecret);
        map.put("js_code",code);
        map.put("grant_type","authorization_code");

        Response res = OkHttp.doGet("https://api.weixin.qq.com/sns/jscode2session",map);
        String resStr = "";
        try {
            resStr = res.body().string();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(resStr);
        return JsonUtil.jsonToBean(resStr, WxLoginResp.class);
    }

    //访问微信支付统一下单接口
    public static String wxPayUnifiedorder(Order order){


        return "";
    }


    public static void main(String[] args) throws IOException {
        WeXinUtil w = new WeXinUtil();
        w.wxLogin("").getOpenid();
    }
}
