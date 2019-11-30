package com.facai.facai.weixin;


import com.facai.facai.constant.Constant;
import com.facai.facai.entity.Order;

import java.util.HashMap;
import java.util.Map;

public class WXPayIntegrated {


    /**
     * 统一下单
     * @param order
     */
    public static void dounifiedorder(Order order){
        try {
            Map<String,String> map = new HashMap<String,String>();
            map.put("appid", Constant.appid);
            map.put("mch_id","123456789");
            map.put("nonce_str",WXPayUtil.generateNonceStr());
            map.put("body","宝平顺发服务");
            map.put("sign",WXPayUtil.generateSignature(map,"123123123"));
            map.put("out_trade_no",order.getoSerialnum());
            map.put("total_fee","1");
            map.put("spbill_create_ip","47.94.143.161");
            map.put("notify_url","47.94.143.161");
            map.put("trade_type","JSAPI");

        }catch (Exception e){
            e.printStackTrace();
        }


    }



    public static void main(String[] args) throws Exception {

    }
}
