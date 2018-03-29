package com.qlone.deureka.api.basic.enumapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ApiResult <T,V> implements Serializable{
    /**
     * code 为标示返回类型 如传入enum
     * msg 为函数具体返回信息如 string 或者dto 或者mapper
     */
    private T code;
    @JsonProperty(value = "data")
    private V msg;

    public ApiResult(T code,V msg){
        this.code = code;
        this.msg = msg;
    }



    public T getCode() {
        return code;
    }

    public void setCode(T code) {
        this.code = code;
    }

    public V getMsg() {
        return msg;
    }

    public void setMsg(V msg) {
        this.msg = msg;
    }
}
