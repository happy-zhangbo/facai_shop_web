package com.facai.facai.controller;

import com.facai.facai.entity.Cart;
import com.facai.facai.entity.ProductSpecs;
import com.facai.facai.entity.UserInfo;
import com.facai.facai.service.CartService;
import com.facai.facai.util.JsonUtil;
import com.facai.facai.util.JwtUtil;
import com.facai.facai.util.RedisUtil;
import com.facai.facai.util.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "add_carts")
    public Resp addToCarts(@RequestHeader String token,@RequestBody Map<String,Object> map){

        Integer count = Integer.parseInt(map.get("count").toString());

        ProductSpecs productSpecs = JsonUtil.jsonToBean(JsonUtil.mapToJson((Map<String, Object>) map.get("productSpecs")),ProductSpecs.class);

        UserInfo userInfo = redisUtil.getUserInfo(token);
        if(cartService.addToCart(userInfo.getuId(),count,productSpecs) > 0){
            return Resp.success("成功加入购物车");
        }else{
            return Resp.success("加入购物车失败");
        }
    }

    @RequestMapping(value = "select_carts")
    public Resp addToCarts(@RequestHeader String token){

        UserInfo userInfo = redisUtil.getUserInfo(token);
        List<Cart> list = cartService.selectAllCartByUserId(userInfo.getuId());
        return Resp.success("购物车查询完成",list);
    }

    @RequestMapping(value = "delete_carts")
    public Resp delToCarts(@RequestHeader String token,Integer cId){

        UserInfo userInfo = redisUtil.getUserInfo(token);
        int flag = cartService.deleteCartByIdAndUserId(cId,userInfo.getuId());
        if(flag > 0){
            return Resp.success("购物车删除完成");
        }else{
            return Resp.error("购物车删除失败");
        }

    }

}
