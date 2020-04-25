package com.facai.facai.service.impl;

import com.facai.facai.dao.ProductMapper;
import com.facai.facai.dao.ProductTypeMapper;
import com.facai.facai.entity.Product;
import com.facai.facai.entity.ProductType;
import com.facai.facai.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> selectAllProductType() {
        return productTypeMapper.selectAllProductType();
    }

    @Override
    public List<Product> selectAllProductByTypeId(Integer typeId) {
        return productMapper.selectAllProductByTypeId(typeId);
    }

    @Override
    public List<Product> selectAllProduct(Product product) {
        return productMapper.selectAllProduct(product);
    }

    @Override
    public Product selectByProductId(Integer pid) {
        return productMapper.selectByPrimaryKey(pid);
    }
}
