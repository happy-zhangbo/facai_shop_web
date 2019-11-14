package com.facai.facai.entity;

import java.math.BigDecimal;

public class Cart {
    private Integer cId;

    private Integer cPsid;

    private Integer cCount;

    private Integer cUserid;

    private BigDecimal cTotal;

    public Cart(Integer cId, Integer cPsid, Integer cCount, Integer cUserid, BigDecimal cTotal) {
        this.cId = cId;
        this.cPsid = cPsid;
        this.cCount = cCount;
        this.cUserid = cUserid;
        this.cTotal = cTotal;
    }

    public Cart() {
        super();
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getcPsid() {
        return cPsid;
    }

    public void setcPsid(Integer cPsid) {
        this.cPsid = cPsid;
    }

    public Integer getcCount() {
        return cCount;
    }

    public void setcCount(Integer cCount) {
        this.cCount = cCount;
    }

    public Integer getcUserid() {
        return cUserid;
    }

    public void setcUserid(Integer cUserid) {
        this.cUserid = cUserid;
    }

    public BigDecimal getcTotal() {
        return cTotal;
    }

    public void setcTotal(BigDecimal cTotal) {
        this.cTotal = cTotal;
    }
}