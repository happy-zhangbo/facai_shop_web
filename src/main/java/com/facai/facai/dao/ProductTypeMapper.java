package com.facai.facai.dao;

import com.facai.facai.entity.ProductType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductTypeMapper {
    int deleteByPrimaryKey(Integer ptId);

    int insert(ProductType record);

    int insertSelective(ProductType record);

    ProductType selectByPrimaryKey(Integer ptId);

    int updateByPrimaryKeySelective(ProductType record);

    int updateByPrimaryKey(ProductType record);

    //查询所有产品类型
    List<ProductType> selectAllProductType();
}