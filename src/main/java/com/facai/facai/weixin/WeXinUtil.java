package com.facai.facai.weixin;

import com.facai.facai.constant.Constant;
import com.facai.facai.entity.Order;
import com.facai.facai.util.JsonUtil;
import com.facai.facai.util.OkHttp;
import com.facai.facai.util.Resp;
import okhttp3.Response;

import java.io.IOException;
import java.util.Date;
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
    public static Map<String,String> wxPayUnifiedorder(Order order,String openid){
        Map<String,String> map = WXPayIntegrated.dounifiedorder(order,openid);
        Map<String,String> resMap = new HashMap<String,String>();
        if(null == map){
            return null;
        }
        if("SUCCESS".equals(map.get("return_code"))){
            try {
                resMap.put("appId",Constant.appid);
                resMap.put("timeStamp",new Date().getTime()+"");
                resMap.put("nonceStr",WXPayIntegrated.getRandomString(32));
                resMap.put("package","prepay_id="+map.get("prepay_id"));
                resMap.put("signType","MD5");
                resMap.put("paySign",WXPayUtil.generateSignature(resMap,Constant.payKey));
                resMap.put("return_code","SUCCESS");
                resMap.remove("appId");
            }catch (Exception e){
                e.printStackTrace();
            }

        }else{
            resMap.put("return_code","FAIL");
            resMap.put("return_msg",map.get("return_msg"));
        }
        return resMap;
    }


    public static void main(String[] args) throws IOException {
        WeXinUtil w = new WeXinUtil();
        w.wxLogin("").getOpenid();
    }
}
