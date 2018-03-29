package com.qlone.deureka.api.basic.dto;

import java.io.Serializable;
import java.util.Date;

public class BasicDTO implements Serializable {
    /**
     * 创建时期，默认 now
     */
    private Date create_date;
    /**
     * 创建者，默认 sys
     */
    private String create_by;
    /**
     * 更新时间，默认 now
     */
    private Date update_date;
    /**
     * 更新者，默认 sys
     */
    private String update_by;

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }
}
