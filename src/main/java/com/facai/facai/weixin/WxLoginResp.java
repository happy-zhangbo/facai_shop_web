package com.facai.facai.weixin;

import lombok.Data;

@Data
public class WxLoginResp {

    private long expires_in;

    private String openid;

    private String session_key;


}
