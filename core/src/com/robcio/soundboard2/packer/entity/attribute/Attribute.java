package com.robcio.soundboard2.packer.entity.attribute;

import java.io.Serializable;

public class Attribute implements Serializable {
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

    public Attribute(final int id) {
        super();
        this.id = id;
        this.name = "New attribute " + id;
    }
}
