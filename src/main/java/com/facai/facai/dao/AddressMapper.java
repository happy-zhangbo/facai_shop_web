package com.facai.facai.dao;

import com.facai.facai.entity.Address;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AddressMapper {
    int deleteByPrimaryKey(Integer aId);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer aId);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    //查看所有地址
    List<Address> selectAllAddress(Integer userId);

    //更新地址
    int updateByDefault(Address record);

    @Delete("delete from address where a_id = #{aId} and a_userId = #{userId}")
    int deleteByAidAndUserid(Integer aId,Integer userId);

    Address selectDefaultAddressByUserId(Integer userId);
}