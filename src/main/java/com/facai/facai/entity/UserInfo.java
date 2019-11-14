package com.facai.facai.entity;

import java.util.Date;

public class UserInfo {
    private Integer uId;

    private String uNickname;

    private String uPassword;

    private Integer uState;

    private Integer uType;

    private String uOpenid;

    private Integer uRegtype;

    private Date uCreatetime;

    private String uPhone;

    private String uEmail;

    private Date uLogintime;

    private String uAvatar;

    public UserInfo(Integer uId, String uNickname, String uPassword, Integer uState, Integer uType, String uOpenid, Integer uRegtype, Date uCreatetime, String uPhone, String uEmail, Date uLogintime, String uAvatar) {
        this.uId = uId;
        this.uNickname = uNickname;
        this.uPassword = uPassword;
        this.uState = uState;
        this.uType = uType;
        this.uOpenid = uOpenid;
        this.uRegtype = uRegtype;
        this.uCreatetime = uCreatetime;
        this.uPhone = uPhone;
        this.uEmail = uEmail;
        this.uLogintime = uLogintime;
        this.uAvatar = uAvatar;
    }

    public UserInfo() {
        super();
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getuNickname() {
        return uNickname;
    }

    public void setuNickname(String uNickname) {
        this.uNickname = uNickname == null ? null : uNickname.trim();
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword == null ? null : uPassword.trim();
    }

    public Integer getuState() {
        return uState;
    }

    public void setuState(Integer uState) {
        this.uState = uState;
    }

    public Integer getuType() {
        return uType;
    }

    public void setuType(Integer uType) {
        this.uType = uType;
    }

    public String getuOpenid() {
        return uOpenid;
    }

    public void setuOpenid(String uOpenid) {
        this.uOpenid = uOpenid == null ? null : uOpenid.trim();
    }

    public Integer getuRegtype() {
        return uRegtype;
    }

    public void setuRegtype(Integer uRegtype) {
        this.uRegtype = uRegtype;
    }

    public Date getuCreatetime() {
        return uCreatetime;
    }

    public void setuCreatetime(Date uCreatetime) {
        this.uCreatetime = uCreatetime;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone == null ? null : uPhone.trim();
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail == null ? null : uEmail.trim();
    }

    public Date getuLogintime() {
        return uLogintime;
    }

    public void setuLogintime(Date uLogintime) {
        this.uLogintime = uLogintime;
    }

    public String getuAvatar() {
        return uAvatar;
    }

    public void setuAvatar(String uAvatar) {
        this.uAvatar = uAvatar == null ? null : uAvatar.trim();
    }
}