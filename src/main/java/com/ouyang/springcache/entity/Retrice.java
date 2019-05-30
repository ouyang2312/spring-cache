package com.ouyang.springcache.entity;

import java.io.Serializable;

/**
 * @author oy
 * @description
 * @date 2019/5/5
 */
public class Retrice implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private int deleteId;

    public Retrice() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeleteId() {
        return deleteId;
    }

    public void setDeleteId(int deleteId) {
        this.deleteId = deleteId;
    }
}
