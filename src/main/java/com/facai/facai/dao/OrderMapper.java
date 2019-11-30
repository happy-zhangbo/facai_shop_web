package com.facai.facai.dao;

import com.facai.facai.entity.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderMapper {
    int deleteByPrimaryKey(Integer oId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer oId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> selectAllOrderByUserId(Integer userId);

    Order selectOrderByOidAndUserId(Integer oId,Integer userId);


}