package com.qlone.deureka.bill.server.service.impl;

import com.qlone.deureka.api.aop.ParseToken;
import com.qlone.deureka.api.basic.enumapi.ApiResult;
import com.qlone.deureka.api.basic.enumapi.EnumFactory;
import com.qlone.deureka.bill.code.BillEnum;
import com.qlone.deureka.bill.server.dao.BillMapper;
import com.qlone.deureka.bill.server.dto.BillDTO;
import com.qlone.deureka.bill.server.service.BillService;
import com.qlone.deureka.login.server.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillMapper billMapper;
    @Override
    @ParseToken
    public ApiResult<BillEnum, String> insertBill(String token, BillDTO billDTO) {
        String userId = TokenUtil.getValue(token);
        billDTO.setIdUser(userId);

        int res = billMapper.insertOrUpdateBill(billDTO);

        return res > 0 ? EnumFactory.resultBill(BillEnum.SUCCESS,billDTO.getIdBill())
                : EnumFactory.resultBill(BillEnum.FAIL,"");
    }

    @Override
    @ParseToken
    public ApiResult<BillEnum, List<BillDTO>> queryBill(String token, BillDTO billDTO) {
        String userId = TokenUtil.getValue(token);
        billDTO.setIdUser(userId);

        List<BillDTO> res = billMapper.queryBill(billDTO);

        return EnumFactory.resultBill(BillEnum.SUCCESS,res);
    }
}
