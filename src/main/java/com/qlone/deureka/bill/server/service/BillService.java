package com.qlone.deureka.bill.server.service;

import com.qlone.deureka.api.basic.enumapi.ApiResult;
import com.qlone.deureka.bill.code.BillEnum;
import com.qlone.deureka.bill.server.dto.BillDTO;

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
}
