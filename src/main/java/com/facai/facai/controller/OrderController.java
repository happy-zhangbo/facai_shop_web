package com.facai.facai.controller;

import com.facai.facai.entity.Order;
import com.facai.facai.entity.UserInfo;
import com.facai.facai.service.OrderService;
import com.facai.facai.util.RedisUtil;
import com.facai.facai.util.Resp;
import com.facai.facai.weixin.WXPayConstants;
import com.facai.facai.weixin.WXPayUtil;
import com.facai.facai.weixin.WeXinUtil;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auth Auth :zhangbo
 * @date Date : 2019年11月28日 10:53
 */
@RestController
@RequestMapping(value = "order")
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisUtil redisUtil;

    //提交订单 （访问统一下单接口）
    @RequestMapping(value = "commit_unifiedorder")
    public Resp commit_unifiedorder(@RequestHeader String token, @RequestBody Order order){
        UserInfo userInfo = redisUtil.getUserInfo(token);
        Map<String,String> map = orderService.commitOrder(order,userInfo);
        if(null != map){
            if("SUCCESS".equals(map.get("return_code"))){
                return Resp.success("订单提交成功",map);
            }else{
                return Resp.error(map.get("return_msg"));
            }
        }else{
            return Resp.error("服务错误");
        }
    }

    //查询订单
    @RequestMapping(value = "select_orders")
    public Resp selectAllOrder(@RequestHeader String token){
        UserInfo userInfo = redisUtil.getUserInfo(token);
        List<Order> list = orderService.selectAllOrderByUserId(userInfo.getuId());
        if(null != list && 0 != list.size()){
            return Resp.success("查询完成",list);
        }else{
            return Resp.error("订单为空");
        }
    }

    //查询订单详情
    @RequestMapping(value = "select_selectAllOrderDetail")
    public Resp selectAllOrderDetail(@RequestHeader String token,Integer oid){
        UserInfo userInfo = redisUtil.getUserInfo(token);
        Order order = orderService.selectOrderByOidAndUserId(oid,userInfo.getuId());
        if(null != order){
            return Resp.success("查询完成",order);
        }else{
            return Resp.error("没有此订单信息");
        }
    }

    //取消订单
    @RequestMapping(value = "update_cancelOrder")
    public Resp cancelOrderBySerialNum(@RequestHeader String token,String serialNum){
        UserInfo userInfo = redisUtil.getUserInfo(token);
        int num = orderService.cancelOrderBySerialNum(serialNum,userInfo.getuId());
        if(num > 0){
            redisUtil.removeOrder(serialNum,userInfo.getuId());
            return Resp.success("订单已取消");
        }else{
            return Resp.error("订单取消失败");
        }
    }

    //支付订单
    @RequestMapping(value = "pay_order")
    public Resp payOrder(@RequestHeader String token,String serialNum){
        UserInfo userInfo = redisUtil.getUserInfo(token);
        Map<String,String> map = redisUtil.getOrder(serialNum,userInfo.getuId());
        if(null != map){
            return Resp.success("ok",map);
        }else{
            return Resp.error("订单已取消，或不存在");
        }


    }
    /**
     * 返回成功xml
     */
    private String resSuccessXml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";

    /**
     * 返回失败xml
     */
    private String resFailXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml>";

    //支付回调
    @RequestMapping(value = "notify_orderConfirm")
    public void notify_orderConfirm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String resXml = "";
        String xml = WeXinUtil.readNotifyContent(request);
        try {
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            boolean isSuccess = false;
            if (WXPayConstants.SUCCESS.equalsIgnoreCase(resultMap.get(WXPayConstants.RESULT_CODE))) {
                WXPayUtil.getLogger().info("wxnotify:微信支付----返回成功");
                if (WXPayUtil.isSignatureValid(resultMap, WXPayConstants.API_KEY)) {
                    // 订单处理 操作 orderconroller 的回写操作?
                    WXPayUtil.getLogger().info("wxnotify:微信支付----验证签名成功");

                    // 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
                    resXml = resSuccessXml;
                    isSuccess = true;
                }
            }else{
                WXPayUtil.getLogger().error("wxnotify:支付失败,错误信息：" + resultMap.get(WXPayConstants.ERR_CODE_DES));
                resXml = resFailXml;
            }
            // 回调方法，处理业务 - 修改订单状态
            WXPayUtil.getLogger().info("wxnotify:微信支付回调：修改的订单===>" + resultMap.get("out_trade_no"));
            if(orderService.wxnotifyResult(isSuccess,resultMap.get("out_trade_no"),resultMap.get("transaction_id")) > 0){
                //删除redis缓存订单
                redisUtil.removeOrder(resultMap.get("out_trade_no"),Integer.parseInt(resultMap.get("attach")));
                WXPayUtil.getLogger().info("wxnotify:微信支付回调：修改订单支付状态成功");
            } else {
                WXPayUtil.getLogger().error("wxnotify:微信支付回调：修改订单支付状态失败");
            }

        }catch (Exception e){
            WXPayUtil.getLogger().error("wxnotify:支付回调发布异常:out：", e);
        }finally {
            try {
                // 处理业务完毕
                BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
                out.write(resXml.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                WXPayUtil.getLogger().error("wxnotify:支付回调发布异常:out：", e);
            } catch (Exception e) {
                WXPayUtil.getLogger().error("wxnotify:支付回调发布异常:out：", e);
            }
        }




    }



    //
}
