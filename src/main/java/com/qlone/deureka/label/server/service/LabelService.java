package com.qlone.deureka.label.server.service;

import com.qlone.deureka.api.basic.enumapi.ApiResult;
import com.qlone.deureka.label.code.LabelEnum;
import com.qlone.deureka.label.server.dto.TypeDTO;

import java.util.List;

public interface LabelService {
    /**
     * 插入标签
     */

    ApiResult<LabelEnum,String> addLabel(String token,String labelName);

    /**
     * 搜索api
     */
    ApiResult<LabelEnum,List<TypeDTO>> queryLabel(String token,TypeDTO typeDTO);

    /**
     * 删除标签
     * @param token
     * @param labelName
     * @return
     */
    ApiResult<LabelEnum,String> deleteLabel(String token,String labelName);
}
