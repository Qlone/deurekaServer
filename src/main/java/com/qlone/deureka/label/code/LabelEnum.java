package com.qlone.deureka.label.code;

import com.qlone.deureka.api.basic.enumapi.ApiResult;

public enum  LabelEnum {

    SUCCESS("200","成功"),
    FAIL("201","失败"),
    LABEL_PARAMERROR("202","参数错误"),
    LABEL_EXECEPTION("500","服务错误")
    ;


    public static <T> ApiResult<LabelEnum,T> result(LabelEnum code, Object msg){
        return new ApiResult<>(code,(T)msg);
    }

    LabelEnum(String code,String msg){
        this.code = code;
        this.msg = msg;
    }
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
