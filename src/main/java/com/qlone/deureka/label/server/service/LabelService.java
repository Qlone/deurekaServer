package com.qlone.deureka.label.server.service;

import com.qlone.deureka.api.basic.enumapi.ApiResult;
import com.qlone.deureka.label.code.LabelEnum;

public interface LabelService {
    /**
     * 插入标签
     */

    ApiResult<LabelEnum,String> addLabel(String token,String labelName);
}
