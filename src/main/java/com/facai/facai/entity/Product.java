package com.facai.facai.entity;

import java.util.Date;

public class Product {
    private Integer pId;

    private String pTitle;

    private Integer pState;

    private Integer pTypeid;

    private Date pCreatetime;

    private String pDetail;

    private String pBrief;

    private String pBrand;

    private String pSource;

    private String pOrigin;

    private String pCover;

    private String pImgarray;

    public Product(Integer pId, String pTitle, Integer pState, Integer pTypeid, Date pCreatetime, String pDetail, String pBrief, String pBrand, String pSource, String pOrigin, String pCover, String pImgarray) {
        this.pId = pId;
        this.pTitle = pTitle;
        this.pState = pState;
        this.pTypeid = pTypeid;
        this.pCreatetime = pCreatetime;
        this.pDetail = pDetail;
        this.pBrief = pBrief;
        this.pBrand = pBrand;
        this.pSource = pSource;
        this.pOrigin = pOrigin;
        this.pCover = pCover;
        this.pImgarray = pImgarray;
    }

    public Product() {
        super();
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle == null ? null : pTitle.trim();
    }

    public Integer getpState() {
        return pState;
    }

    public void setpState(Integer pState) {
        this.pState = pState;
    }

    public Integer getpTypeid() {
        return pTypeid;
    }

    public void setpTypeid(Integer pTypeid) {
        this.pTypeid = pTypeid;
    }

    public Date getpCreatetime() {
        return pCreatetime;
    }

    public void setpCreatetime(Date pCreatetime) {
        this.pCreatetime = pCreatetime;
    }

    public String getpDetail() {
        return pDetail;
    }

    public void setpDetail(String pDetail) {
        this.pDetail = pDetail == null ? null : pDetail.trim();
    }

    public String getpBrief() {
        return pBrief;
    }

    public void setpBrief(String pBrief) {
        this.pBrief = pBrief == null ? null : pBrief.trim();
    }

    public String getpBrand() {
        return pBrand;
    }

    public void setpBrand(String pBrand) {
        this.pBrand = pBrand == null ? null : pBrand.trim();
    }

    public String getpSource() {
        return pSource;
    }

    public void setpSource(String pSource) {
        this.pSource = pSource == null ? null : pSource.trim();
    }

    public String getpOrigin() {
        return pOrigin;
    }

    public void setpOrigin(String pOrigin) {
        this.pOrigin = pOrigin == null ? null : pOrigin.trim();
    }

    public String getpCover() {
        return pCover;
    }

    public void setpCover(String pCover) {
        this.pCover = pCover == null ? null : pCover.trim();
    }

    public String getpImgarray() {
        return pImgarray;
    }

    public void setpImgarray(String pImgarray) {
        this.pImgarray = pImgarray == null ? null : pImgarray.trim();
    }
}