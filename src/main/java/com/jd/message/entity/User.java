package com.jd.message.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 13348 on 2021/4/13.
 * 用户表
 */
public class User implements Serializable {

    private int id;

    private String name;

    private int customerId;

    private Date createtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }


}
