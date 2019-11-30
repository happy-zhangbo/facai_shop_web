package com.facai.facai.service.impl;

import com.facai.facai.dao.AddressMapper;
import com.facai.facai.dao.UserInfoMapper;
import com.facai.facai.entity.Address;
import com.facai.facai.entity.UserInfo;
import com.facai.facai.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public UserInfo selectLoginByWeiXin(String openid,String nickName,String avatar) {
        UserInfo u = userInfoMapper.selectByOpenId(openid);
        if(null != u){
            return u;
        }else{
            u = new UserInfo();
            u.setuNickname(nickName);
            u.setuAvatar(avatar);
            u.setuOpenid(openid);
            if(userInfoMapper.insert(u) > 0){
                return u;
            }else{
                return null;
            }
        }
    }

    @Override
    public List<Address> selectAllAddress(Integer userId) {
        List<Address> list = addressMapper.selectAllAddress(userId);

        return list;
    }

    @Override
    public int insertAddress(Address address) {
        if(address.getaDefatult() == 1){
            addressMapper.updateByDefault(address);
        }
        return addressMapper.insert(address);
    }

    @Override
    public int updateAddress(Address address) {
        if(address.getaDefatult() == 1){
            addressMapper.updateByDefault(address);
        }
        return addressMapper.updateByPrimaryKeySelective(address);
    }

    @Override
    public int deleteAddress(Integer aId,Integer uid) {

        return addressMapper.deleteByAidAndUserid(aId,uid);
    }

    @Override
    public Address selectDefaultAddressByUserId(Integer uid) {

        return addressMapper.selectDefaultAddressByUserId(uid);
    }


}
