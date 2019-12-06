package com.facai.facai.weixin;


import com.alibaba.fastjson.JSON;
import com.facai.facai.constant.Constant;
import com.facai.facai.entity.Order;
import com.facai.facai.entity.UserInfo;
import com.facai.facai.util.JsonUtil;
import com.facai.facai.util.OkHttp;
import okhttp3.Response;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WXPayIntegrated {


    /**
     * 统一下单
     * @param order
     */
    public static Map<String,String> dounifiedorder(Order order, String openid){
        try {
            Map<String,String> map = new HashMap<String,String>();
            map.put("appid", WXPayConstants.APPID);
            map.put("mch_id","1566079381");
            map.put("nonce_str",WXPayUtil.generateNonceStr());
            map.put("device_info","01");
            map.put("body", "材料购买-在线支付".getBytes("UTF-8").toString());
            map.put("out_trade_no",order.getoSerialnum());
            map.put("total_fee",order.getoTotalamount().multiply(new BigDecimal(100)).intValue()+"");
            map.put("spbill_create_ip","47.94.143.161");
            map.put("notify_url","http://nzdx2t.natappfree.cc/order/notify_orderConfirm");
            map.put("trade_type","JSAPI");
            map.put("openid",openid);
            map.put("attach",order.getoUserid().toString());
            map.put("sign",WXPayUtil.generateSignature(map,WXPayConstants.API_KEY));
            String res = OkHttp.doPostString("https://api.mch.weixin.qq.com/pay/unifiedorder",WXPayUtil.mapToXml(map));


            return WXPayUtil.xmlToMap(res);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static String getRandomString(int length){
        //1.  定义一个字符串（A-Z，a-z，0-9）即62个数字字母；
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        //2.  由Random生成随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //3.  长度为几就循环几次
        for(int i=0; i<length; ++i){
            //从62个的数字或字母中选择
            int number=random.nextInt(62);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }


    public static void main(String[] args) throws Exception {
        Order order = new Order();
        order.setoSerialnum("20191203143112345");
        order.setoTotalamount(new BigDecimal(0.01));
        order.setoUserid(5);
        System.out.println(JSON.toJSONString(dounifiedorder(order,"oK1Yd0SnDm0flgHDtz3fj4qWFMIw")));
    }
}
