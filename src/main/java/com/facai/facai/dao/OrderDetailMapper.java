package com.facai.facai.dao;

import com.facai.facai.entity.OrderDetail;
import org.springframework.stereotype.Component;

@Component
public interface OrderDetailMapper {
    int deleteByPrimaryKey(Integer odId);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(Integer odId);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);
}