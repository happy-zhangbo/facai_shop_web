package com.facai.facai.service.impl;

import com.facai.facai.dao.UserInfoMapper;
import com.facai.facai.entity.UserInfo;
import com.facai.facai.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

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
}
