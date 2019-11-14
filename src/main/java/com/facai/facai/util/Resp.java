package com.facai.facai.util;

public class Resp {
    private int code;

    private String msg;

    private Object data;

    private long count;

    private Object other;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Object getOther() {
        return other;
    }

    public void setOther(Object other) {
        this.other = other;
    }



    public Resp(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Resp(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Resp(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Resp(int code, String msg, Object data, long count) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }

    public Resp(int code, String msg, Object data, long count, Object other) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
        this.other = other;
    }

    //成功

    public static Resp success(){
        return new Resp(0,"成功");
    }
    public static Resp success(String msg){
        return new Resp(0,msg);
    }
    public static Resp success(Object data){
        return new Resp(0,data);
    }
    public static Resp success(String msg,Object data){
        return new Resp(0,msg,data);
    }
    public static Resp success(String msg,Object data,long count){
        return new Resp(0,msg,data,count);
    }

    //失败

    public static Resp error(){
        return new Resp(1,"失败");
    }
    public static Resp error(String msg){
        return new Resp(1,msg);
    }
    public static Resp error(String msg,Object data){
        return new Resp(1,msg,data);
    }

    //警告
    public static Resp warning(String msg){
        return new Resp(2,msg);
    }
    public static Resp warning(String msg,Object data){
        return new Resp(2,msg,data);
    }
    public static Resp warning(String msg,Object data,long count){
        return new Resp(2,msg,data,count);
    }
}
