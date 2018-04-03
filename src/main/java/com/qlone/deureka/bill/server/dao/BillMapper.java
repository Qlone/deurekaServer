package com.qlone.deureka.bill.server.dao;

import com.qlone.deureka.bill.server.dto.BillDTO;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface BillMapper {
    /**
     * 插入账单
     * @param billDTO
     * @return
     */
    int insertOrUpdateBill(BillDTO billDTO);

    /**
     * 查询账单
     */

    List<BillDTO> queryBill(BillDTO billDTO);
    /**
     * 统计功能
     */
    HashMap<String,Object> countBillByType(String userid, String[] type, boolean income, Date minCreateTime,Date maxCreateTime);
}
