package com.facai.facai.service.impl;

import com.facai.facai.dao.CartMapper;
import com.facai.facai.entity.Cart;
import com.facai.facai.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public int addToCart(Cart cart) {

        return cartMapper.insert(cart);
    }

    @Override
    public List<Cart> selectAllCartByUserId(Integer userId) {

        return null;
    }

    @Override
    public int deleteCartByIdAndUserId(Integer cid, Integer userId) {
        return 0;
    }


}
