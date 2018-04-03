package com.qlone.deureka.bill.server.service;

import com.qlone.deureka.api.basic.enumapi.ApiResult;
import com.qlone.deureka.bill.code.BillEnum;
import com.qlone.deureka.bill.server.dto.BillDTO;

import java.util.Date;
import java.util.List;

public interface BillService {
    /**
     * 插入新的账单
     * @param token
     * @param billDTO
     * @return string 为新增的 id
     */
    ApiResult<BillEnum,String> insertBill(String token, BillDTO billDTO);

    /**
     * 获取账单
     */
    ApiResult<BillEnum,List<BillDTO>> queryBill(String token,BillDTO billDTO);

    /**
     * 获取总统计
     */
    ApiResult<BillEnum,List<BillDTO>> selectTotalBill(String token);
    /**
     * 灵活获取
     */
    ApiResult<BillEnum,Object> multiSearchBill(String token, String[] type, String income, Date minDate, Date maxDate);

    ApiResult<BillEnum,String> deleteBill(String token,String idBill);
}
