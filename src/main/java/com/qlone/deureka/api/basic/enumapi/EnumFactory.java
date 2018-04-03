package com.qlone.deureka.api.basic.enumapi;

import com.qlone.deureka.bill.code.BillEnum;

public class EnumFactory {
    public static <T>  ApiResult<BillEnum,T> resultBill(BillEnum code,Object msg){
        return new ApiResult<>(code,(T)msg);
    }
}
