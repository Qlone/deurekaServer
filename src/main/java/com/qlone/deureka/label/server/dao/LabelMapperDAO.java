package com.qlone.deureka.label.server.dao;

import com.qlone.deureka.label.server.dto.TypeDTO;

import java.util.List;

public interface LabelMapperDAO {
    /**
     * 插入或更新
     * @param typeDTO
     * @return
     */
    int insertLabel(TypeDTO typeDTO);

    /**
     * 删除
     * @param typeDTO
     * @return
     */
    int deleteLabel(TypeDTO typeDTO);

    /**
     * 查找
     * @param typeDTO
     * @return
     */
    List<TypeDTO> queryLable(TypeDTO typeDTO);

    /**
     * 计数增加
     * @param typeDTO
     * @return
     */
    int addLabelCount(TypeDTO typeDTO);
}
