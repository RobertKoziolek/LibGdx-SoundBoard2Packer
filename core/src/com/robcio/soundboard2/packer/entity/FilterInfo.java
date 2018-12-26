package com.robcio.soundboard2.packer.entity;

import java.io.Serializable;

public class FilterInfo implements Serializable {
    protected Integer id;
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public FilterInfo(final int id) {
        super();
        this.id = id;
        this.name = "New filter " + id;
    }
}
