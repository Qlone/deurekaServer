package com.qlone.deureka.label.contrller;

import com.qlone.deureka.api.aop.ParseToken;
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

    @ParseToken
    @RequestMapping(value = "/add",produces = { "application/json;charset=UTF-8" })
    public Object addLable(@RequestParam(value = "token") String token,
                           @RequestParam(value = "type") String lableName){
        logger.info("token:"+token);
        return labelService.addLabel(token,lableName);
    }
}
