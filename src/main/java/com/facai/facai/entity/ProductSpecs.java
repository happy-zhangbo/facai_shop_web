package com.facai.facai.entity;

import java.math.BigDecimal;

public class ProductSpecs {
    private Integer sId;

    private String sName;

    private Integer sState;

    private BigDecimal sPrice;

    private String sBrief;

    private Integer sStock;

    private Integer sProductid;

    private Product product;

    public ProductSpecs(Integer sId, String sName, Integer sState, BigDecimal sPrice, String sBrief, Integer sStock, Integer sProductid) {
        this.sId = sId;
        this.sName = sName;
        this.sState = sState;
        this.sPrice = sPrice;
        this.sBrief = sBrief;
        this.sStock = sStock;
        this.sProductid = sProductid;
    }

    public ProductSpecs() {
        super();
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName == null ? null : sName.trim();
    }

    public Integer getsState() {
        return sState;
    }

    public void setsState(Integer sState) {
        this.sState = sState;
    }

    public BigDecimal getsPrice() {
        return sPrice;
    }

    public void setsPrice(BigDecimal sPrice) {
        this.sPrice = sPrice;
    }

    public String getsBrief() {
        return sBrief;
    }

    public void setsBrief(String sBrief) {
        this.sBrief = sBrief == null ? null : sBrief.trim();
    }

    public Integer getsStock() {
        return sStock;
    }

    public void setsStock(Integer sStock) {
        this.sStock = sStock;
    }

    public Integer getsProductid() {
        return sProductid;
    }

    public void setsProductid(Integer sProductid) {
        this.sProductid = sProductid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}