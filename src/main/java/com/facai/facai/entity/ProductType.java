package com.facai.facai.entity;

import java.util.Date;

public class ProductType {
    private Integer ptId;

    private String ptName;

    private Integer ptState;

    private Date ptCreatetime;

    public ProductType(Integer ptId, String ptName, Integer ptState, Date ptCreatetime) {
        this.ptId = ptId;
        this.ptName = ptName;
        this.ptState = ptState;
        this.ptCreatetime = ptCreatetime;
    }

    public ProductType() {
        super();
    }

    public Integer getPtId() {
        return ptId;
    }

    public void setPtId(Integer ptId) {
        this.ptId = ptId;
    }

    public String getPtName() {
        return ptName;
    }

    public void setPtName(String ptName) {
        this.ptName = ptName == null ? null : ptName.trim();
    }

    public Integer getPtState() {
        return ptState;
    }

    public void setPtState(Integer ptState) {
        this.ptState = ptState;
    }

    public Date getPtCreatetime() {
        return ptCreatetime;
    }

    public void setPtCreatetime(Date ptCreatetime) {
        this.ptCreatetime = ptCreatetime;
    }
}