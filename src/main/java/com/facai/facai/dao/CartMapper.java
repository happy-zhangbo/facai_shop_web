package com.facai.facai.dao;

import com.facai.facai.entity.Cart;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CartMapper {
    int deleteByPrimaryKey(Integer cId);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer cId);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    //根据用户ID查询购物车内容
    List<Cart> selectAllCartByUserId(Integer userId);

    @Delete("delete from cart where c_id = #{cId} and c_userid = #{userId}")
    int deleteByCIdAndUserId(Integer cId,Integer userId);
}