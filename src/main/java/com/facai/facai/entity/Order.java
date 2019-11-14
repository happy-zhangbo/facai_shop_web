package com.facai.facai.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Integer oId;

    private String oSerialnum;

    private Integer oPaymethod;

    private Date oCreatetime;

    private Integer oState;

    private Integer oType;

    private String oTransactionnum;

    private BigDecimal oTotalamount;

    private Integer oUserid;

    private String oRemarks;

    public Order(Integer oId, String oSerialnum, Integer oPaymethod, Date oCreatetime, Integer oState, Integer oType, String oTransactionnum, BigDecimal oTotalamount, Integer oUserid, String oRemarks) {
        this.oId = oId;
        this.oSerialnum = oSerialnum;
        this.oPaymethod = oPaymethod;
        this.oCreatetime = oCreatetime;
        this.oState = oState;
        this.oType = oType;
        this.oTransactionnum = oTransactionnum;
        this.oTotalamount = oTotalamount;
        this.oUserid = oUserid;
        this.oRemarks = oRemarks;
    }

    public Order() {
        super();
    }

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public String getoSerialnum() {
        return oSerialnum;
    }

    public void setoSerialnum(String oSerialnum) {
        this.oSerialnum = oSerialnum == null ? null : oSerialnum.trim();
    }

    public Integer getoPaymethod() {
        return oPaymethod;
    }

    public void setoPaymethod(Integer oPaymethod) {
        this.oPaymethod = oPaymethod;
    }

    public Date getoCreatetime() {
        return oCreatetime;
    }

    public void setoCreatetime(Date oCreatetime) {
        this.oCreatetime = oCreatetime;
    }

    public Integer getoState() {
        return oState;
    }

    public void setoState(Integer oState) {
        this.oState = oState;
    }

    public Integer getoType() {
        return oType;
    }

    public void setoType(Integer oType) {
        this.oType = oType;
    }

    public String getoTransactionnum() {
        return oTransactionnum;
    }

    public void setoTransactionnum(String oTransactionnum) {
        this.oTransactionnum = oTransactionnum == null ? null : oTransactionnum.trim();
    }

    public BigDecimal getoTotalamount() {
        return oTotalamount;
    }

    public void setoTotalamount(BigDecimal oTotalamount) {
        this.oTotalamount = oTotalamount;
    }

    public Integer getoUserid() {
        return oUserid;
    }

    public void setoUserid(Integer oUserid) {
        this.oUserid = oUserid;
    }

    public String getoRemarks() {
        return oRemarks;
    }

    public void setoRemarks(String oRemarks) {
        this.oRemarks = oRemarks == null ? null : oRemarks.trim();
    }
}