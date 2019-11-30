package com.facai.facai.dao;

import com.facai.facai.entity.UserInfo;
import org.springframework.stereotype.Component;

@Component
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer uId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer uId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    //根据openid
    UserInfo selectByOpenId(String openid);


}