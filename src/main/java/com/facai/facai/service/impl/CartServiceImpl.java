package com.facai.facai.service.impl;

import com.facai.facai.dao.CartMapper;
import com.facai.facai.entity.Cart;
import com.facai.facai.entity.ProductSpecs;
import com.facai.facai.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public int addToCart(Integer userId,Integer count, ProductSpecs productSpecs) {
        Cart cart = new Cart();
        cart.setcUserid(userId);
        cart.setcCount(count);
        BigDecimal countBig = new BigDecimal(count.toString());
        cart.setcTotal(countBig.multiply(productSpecs.getsPrice()));
        cart.setcPsid(productSpecs.getsId());

        return cartMapper.insert(cart);
    }

    @Override
    public List<Cart> selectAllCartByUserId(Integer userId) {

        return cartMapper.selectAllCartByUserId(userId);
    }

    @Override
    public int deleteCartByIdAndUserId(Integer cid, Integer userId) {
        return cartMapper.deleteByCIdAndUserId(cid,userId);
    }


}
