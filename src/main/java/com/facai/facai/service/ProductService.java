package com.facai.facai.service;

import com.facai.facai.entity.Product;
import com.facai.facai.entity.ProductType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    //查询全部产品分类
    public List<ProductType> selectAllProductType();

    //根据分类ID查询产品
    public List<Product> selectAllProductByTypeId(Integer typeId);

    //查询产品
    public List<Product> selectAllProduct(Product product);

    //根据产品ID查询详情
    public Product selectByProductId(Integer pid);

}
