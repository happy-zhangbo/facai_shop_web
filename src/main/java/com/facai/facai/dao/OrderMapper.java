package com.facai.facai.dao;

import com.facai.facai.entity.Order;
import org.apache.ibatis.annotations.Param;
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

    List<Order> selectAllOrderByUserId(Integer userId,Integer oState);

    Order selectOrderByOidAndUserId(Integer oId,Integer userId);

    int cancelOrderBySerialNum(String serialNum,Integer userId);

    int wxnotifyResult(@Param("state") Integer state,@Param("serialNum") String serialNum,@Param("transactionNum") String transactionNum);
}