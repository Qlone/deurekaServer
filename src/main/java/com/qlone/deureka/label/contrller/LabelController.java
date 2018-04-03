package com.qlone.deureka.label.contrller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.qlone.deureka.api.aop.ParseToken;
import com.qlone.deureka.label.server.dto.TypeDTO;
import com.qlone.deureka.label.server.service.LabelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/label")
public class LabelController {
    @Autowired
    LabelService labelService;
    Logger logger = LoggerFactory.getLogger(LabelController.class);

    @RequestMapping(value = "/add",produces = { "application/json;charset=UTF-8" })
    public Object addLable(@RequestParam(value = "token") String token,
                           @RequestParam(value = "type") String lableName){
        return labelService.addLabel(token,lableName);
    }

    @RequestMapping(value = "/query",produces = { "application/json;charset=UTF-8" })
    public Object queryLable(@RequestParam(value = "token") String token,
                             @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return labelService.queryLabel(token,new TypeDTO());
    }

    @RequestMapping(value = "/del",produces = { "application/json;charset=UTF-8" })
    public Object deleteLable(@RequestParam(value = "token") String token,
                              @RequestParam(value = "type",defaultValue = "") String type){
        return labelService.deleteLabel(token,type);
    }
}
