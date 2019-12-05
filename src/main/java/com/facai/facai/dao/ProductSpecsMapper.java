package com.facai.facai.dao;

import com.facai.facai.entity.OrderDetail;
import com.facai.facai.entity.ProductSpecs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductSpecsMapper {
    int deleteByPrimaryKey(Integer sId);

    int insert(ProductSpecs record);

    int insertSelective(ProductSpecs record);

    ProductSpecs selectByPrimaryKey(Integer sId);

    int updateByPrimaryKeySelective(ProductSpecs record);

    int updateByPrimaryKey(ProductSpecs record);

    List<ProductSpecs> selectOrderProductSpecs(List<OrderDetail> odList);

}