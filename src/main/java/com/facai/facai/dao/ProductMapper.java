package com.facai.facai.dao;

import com.facai.facai.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductMapper {
    int deleteByPrimaryKey(Integer pId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer pId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    //根据分类ID查询所有产品
    List<Product> selectAllProduct(Integer typeId);
}