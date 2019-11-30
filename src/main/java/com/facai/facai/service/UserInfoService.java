package com.facai.facai.service;

import com.facai.facai.entity.Address;
import com.facai.facai.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserInfoService {

    //注册登录
    public UserInfo selectLoginByWeiXin(String openid,String nickName,String avatar);

    //查询所有地址
    public List<Address> selectAllAddress(Integer userId);

    //添加地址
    public int insertAddress(Address address);

    //更新地址
    public int updateAddress(Address address);

    //删除地址
    public int deleteAddress(Integer aId,Integer uid);

    //获取默认地址
    public Address selectDefaultAddressByUserId(Integer uid);

}
