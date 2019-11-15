package com.facai.facai.controller;

import com.facai.facai.service.ProductService;
import com.facai.facai.util.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //查询所有分类
    @RequestMapping(value = "find_selectAllProductType")
    public Resp selectAllProductType(){
        return Resp.success("查询完成",productService.selectAllProductType());
    }

    //根据分类ID查询所有产品
    @RequestMapping(value = "find_selectAllProductByTypeId")
    public Resp selectAllProductByTypeId(Integer typeid){
        return Resp.success("查询完成",productService.selectAllProduct(typeid));
    }

    //根据产品ID查询产品详细信息
    @RequestMapping(value = "find_selectProductDetail")
    public Resp selectProductDetail(Integer pid){
        return Resp.success("查询完成",productService.selectByProductId(pid));
    }
}
