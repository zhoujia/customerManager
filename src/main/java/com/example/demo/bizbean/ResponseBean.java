package com.example.demo.bizbean;

import com.example.demo.utils.DataErrorCode;


/****
 * 返回值定义
 */
public class ResponseBean<T>{

    private String retCode=DataErrorCode.SUCCESS.getCode();//返回码
    private String retMsg= DataErrorCode.SUCCESS.getMsg();//返回消息
    private T data;//返回数据


    public ResponseBean() {
    }

    public ResponseBean(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public ResponseBean(Integer retCode, String retMsg) {
        this.retCode = String.valueOf(retCode);
        this.retMsg = retMsg;
    }

    public ResponseBean(T t) {
        this.setData(t);
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setCodeAndMsg(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public void setCodeAndMsg(Integer retCode, String retMsg) {
        this.retCode = String.valueOf(retCode);
        this.retMsg = retMsg;
    }

    @Override
    public String toString() {
        return "ResponseBean [retCode=" + retCode + ", retMsg=" + retMsg + ", data=" + data + "]";
    }
}
