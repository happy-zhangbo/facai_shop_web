package com.facai.facai.service;

import com.facai.facai.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface UserInfoService {

    //注册登录
    public UserInfo selectLoginByWeiXin(String openid,String nickName,String avatar);
    //修改密码

}
