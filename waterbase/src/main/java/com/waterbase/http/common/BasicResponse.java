package com.waterbase.http.common;

/**
 * 服务器返回数据的基础类
 * Created by Water on 2018/3/29.
 */
public class BasicResponse<T> {

    private int code;       //返回 1 表示成功
    private String msg;     //描述信息
    private T result;       //json数据
    private boolean ok;     //

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

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
}
