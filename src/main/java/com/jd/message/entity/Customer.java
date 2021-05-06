package com.jd.message.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 13348 on 2021/4/13.
 * 客户表
 */
public class Customer implements Serializable{

    private int id;

    private String name;

    private int projectId;

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

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }




}
