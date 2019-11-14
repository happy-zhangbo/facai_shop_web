package com.facai.facai.dao;

import com.facai.facai.entity.ProductType;
import org.springframework.stereotype.Component;

@Component
public interface ProductTypeMapper {
    int deleteByPrimaryKey(Integer ptId);

    int insert(ProductType record);

    int insertSelective(ProductType record);

    ProductType selectByPrimaryKey(Integer ptId);

    int updateByPrimaryKeySelective(ProductType record);

    int updateByPrimaryKey(ProductType record);
}