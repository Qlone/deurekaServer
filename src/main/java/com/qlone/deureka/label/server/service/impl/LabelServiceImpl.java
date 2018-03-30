package com.qlone.deureka.label.server.service.impl;

import com.qlone.deureka.api.basic.enumapi.ApiResult;
import com.qlone.deureka.label.code.LabelEnum;
import com.qlone.deureka.label.server.dao.LabelMapperDAO;
import com.qlone.deureka.label.server.dto.TypeDTO;
import com.qlone.deureka.label.server.service.LabelService;
import com.qlone.deureka.login.server.util.TokenUtil;
import jdk.nashorn.internal.parser.Token;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabelServiceImpl implements LabelService {
    Logger logger = LoggerFactory.getLogger(LabelServiceImpl.class);
    @Autowired
    LabelMapperDAO labelMapperDAO;

    @Override
    public ApiResult<LabelEnum, String> addLabel(String token, String labelName) {
        if(StringUtils.isEmpty(token) || StringUtils.isEmpty(labelName)){
            return LabelEnum.result(LabelEnum.LABEL_PARAMERROR,"");
        }
        String userId = TokenUtil.getValue(token);
        TypeDTO typeDTO = new TypeDTO();
        typeDTO.setIdUser(userId);
        typeDTO.setType(labelName);
        try {
            int res = labelMapperDAO.insertLabel(typeDTO);
            return res > 0 ? LabelEnum.result(LabelEnum.SUCCESS,typeDTO.getIdType())
                    : LabelEnum.result(LabelEnum.FAIL,"");
        }catch (Exception e){
            logger.error("error",e);
            return LabelEnum.result(LabelEnum.LABEL_EXECEPTION,"");
        }


    }
}
