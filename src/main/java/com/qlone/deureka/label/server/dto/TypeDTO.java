package com.qlone.deureka.label.server.dto;

import java.util.Date;

public class TypeDTO {
    
    private String idType;
    
    private String idUser;
    /**
     * 标签名字
     */
    private String type;
    
    private Date createdDate;

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
