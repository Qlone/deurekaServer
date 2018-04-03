package com.qlone.deureka.bill.code;

public enum BillEnum {

    SUCCESS("200","成功"),
    FAIL("201","失败")
    ;

    BillEnum(String code,String msg){
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
