package com.qlone.deureka.bill.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.qlone.deureka.bill.server.dto.BillDTO;
import com.qlone.deureka.bill.server.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    BillService billService;
    @RequestMapping(value = "/add",produces =  { "application/json;charset=UTF-8" })
    public Object insertBill(@RequestParam(value = "token") String token,
                             @RequestParam(value = "money") BigDecimal money,
                             @RequestParam(value = "type") String[] type){
        BillDTO billDTO = new BillDTO();
        billDTO.setMoney(money);
        StringBuffer buff = new StringBuffer();
        for(String s: type){
            buff.append("{");
            buff.append(s);
            buff.append("}");
            buff.append(",");
        }
        buff.deleteCharAt(buff.length()-1);
        billDTO.setType(buff.toString());
        billDTO.setDatetime(new Date());
        return billService.insertBill(token,billDTO);
    }

    @RequestMapping(value = "/query",produces =  { "application/json;charset=UTF-8" })
    public Object queryBill(@RequestParam(value = "token") String token,
                            @RequestParam(value = "pageNum" , defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize" ,defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return billService.queryBill(token,new BillDTO());
    }
}
