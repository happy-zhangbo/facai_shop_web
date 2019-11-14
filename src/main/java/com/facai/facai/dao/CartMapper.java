package com.facai.facai.dao;

import com.facai.facai.entity.Cart;
import org.springframework.stereotype.Component;

@Component
public interface CartMapper {
    int deleteByPrimaryKey(Integer cId);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer cId);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
}