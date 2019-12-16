package com.facai.facai.weixin;

import com.facai.facai.constant.Constant;
import com.facai.facai.entity.Order;
import com.facai.facai.util.JsonUtil;
import com.facai.facai.util.OkHttp;
import com.facai.facai.util.Resp;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class WeXinUtil {


    @Autowired
    private WXPayIntegrated wxPayIntegrated;


    //访问登录接口
    public static WxLoginResp wxLogin(String code) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("appid", WXPayConstants.APPID);
        map.put("secret", WXPayConstants.APP_SECRET);
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
    public Map<String,String> wxPayUnifiedorder(Order order,String openid){
        Map<String,String> map = wxPayIntegrated.dounifiedorder(order,openid);
        Map<String,String> resMap = new HashMap<String,String>();
        if(null == map){
            return null;
        }
        if("SUCCESS".equals(map.get("return_code"))){
            try {
                resMap.put("appId",WXPayConstants.APPID);
                resMap.put("timeStamp",new Date().getTime()+"");
                resMap.put("nonceStr",WXPayIntegrated.getRandomString(32));
                resMap.put("package","prepay_id="+map.get("prepay_id"));
                resMap.put("signType","MD5");
                resMap.put("paySign",WXPayUtil.generateSignature(resMap,WXPayConstants.API_KEY));
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

    //读取回调内容
    public static String readNotifyContent(HttpServletRequest servletRequest){
        String resXml = "";
        InputStream inStream;
        try {
            inStream = servletRequest.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            resXml = new String(outSteam.toByteArray(), "utf-8");
            outSteam.close();
            inStream.close();
        }catch (Exception e){
            WXPayUtil.getLogger().error("wxnotify:支付回调发布异常：", e);
        }

        return resXml;
    }


    public static void main(String[] args) throws IOException {
        WeXinUtil w = new WeXinUtil();
        w.wxLogin("").getOpenid();
    }
}
