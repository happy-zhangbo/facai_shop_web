package com.facai.facai.entity;

import java.math.BigDecimal;

public class OrderDetail {
    private Integer odId;

    private Integer odOid;

    private Integer odPsid;

    private Integer odCount;

    private BigDecimal odTotal;

    private Integer  ocId;

    public OrderDetail(Integer odId, Integer odOid, Integer odPsid, Integer odCount, BigDecimal odTotal) {
        this.odId = odId;
        this.odOid = odOid;
        this.odPsid = odPsid;
        this.odCount = odCount;
        this.odTotal = odTotal;
    }

    public OrderDetail() {
        super();
    }

    public Integer getOdId() {
        return odId;
    }

    public void setOdId(Integer odId) {
        this.odId = odId;
    }

    public Integer getOdOid() {
        return odOid;
    }

    public void setOdOid(Integer odOid) {
        this.odOid = odOid;
    }

    public Integer getOdPsid() {
        return odPsid;
    }

    public void setOdPsid(Integer odPsid) {
        this.odPsid = odPsid;
    }

    public Integer getOdCount() {
        return odCount;
    }

    public void setOdCount(Integer odCount) {
        this.odCount = odCount;
    }

    public BigDecimal getOdTotal() {
        return odTotal;
    }

    public void setOdTotal(BigDecimal odTotal) {
        this.odTotal = odTotal;
    }

    public Integer getOcId() {
        return ocId;
    }

    public void setOcId(Integer ocId) {
        this.ocId = ocId;
    }
}