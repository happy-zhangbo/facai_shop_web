package com.facai.facai.dao;

import com.facai.facai.entity.Product;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer pId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer pId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}