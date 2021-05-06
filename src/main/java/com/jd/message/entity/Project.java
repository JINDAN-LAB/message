package com.jd.message.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 13348 on 2021/4/13.
 * 项目表
 */
public class Project implements Serializable {

    private int id;

    private String name;

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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
