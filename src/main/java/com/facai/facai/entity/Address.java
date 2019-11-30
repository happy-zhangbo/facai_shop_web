package com.facai.facai.entity;

public class Address {
    private Integer aId;

    private Integer aUserid;

    private String aCity;

    private String aAddress;

    private Integer aDefatult;

    private String aLink;

    private String aTel;

    public Address(Integer aId, Integer aUserid, String aCity, String aAddress, Integer aDefatult, String aLink, String aTel) {
        this.aId = aId;
        this.aUserid = aUserid;
        this.aCity = aCity;
        this.aAddress = aAddress;
        this.aDefatult = aDefatult;
        this.aLink = aLink;
        this.aTel = aTel;
    }

    public Address() {
        super();
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public Integer getaUserid() {
        return aUserid;
    }

    public void setaUserid(Integer aUserid) {
        this.aUserid = aUserid;
    }

    public String getaCity() {
        return aCity;
    }

    public void setaCity(String aCity) {
        this.aCity = aCity == null ? null : aCity.trim();
    }

    public String getaAddress() {
        return aAddress;
    }

    public void setaAddress(String aAddress) {
        this.aAddress = aAddress == null ? null : aAddress.trim();
    }

    public Integer getaDefatult() {
        return aDefatult;
    }

    public void setaDefatult(Integer aDefatult) {
        this.aDefatult = aDefatult;
    }

    public String getaLink() {
        return aLink;
    }

    public void setaLink(String aLink) {
        this.aLink = aLink == null ? null : aLink.trim();
    }

    public String getaTel() {
        return aTel;
    }

    public void setaTel(String aTel) {
        this.aTel = aTel == null ? null : aTel.trim();
    }
}