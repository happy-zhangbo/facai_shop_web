package com.facai.facai.service;

import com.facai.facai.entity.Cart;
import com.facai.facai.entity.ProductSpecs;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    //加入购物车
    public int addToCart(Integer userId,Integer count, ProductSpecs productSpecs);

    //查询购物车
    public List<Cart> selectAllCartByUserId(Integer userId);

    //删除购物车的产品
    public int deleteCartByIdAndUserId(Integer cid,Integer userId);


}
