package com.qlone.deureka.label.server.dao;

import com.qlone.deureka.label.server.dto.TypeDTO;

public interface LabelMapperDAO {
    /**
     * 插入或更新
     * @param typeDTO
     * @return
     */
    int insertLabel(TypeDTO typeDTO);
}
