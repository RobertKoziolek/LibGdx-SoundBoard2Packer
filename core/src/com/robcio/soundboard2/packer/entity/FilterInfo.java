package com.robcio.soundboard2.packer.entity;

import com.badlogic.gdx.files.FileHandle;

public class FilterInfo {
    private String name = "New filter";
    private FileHandle image;

    public FileHandle getImage() {
        return image;
    }

    public void setImage(final FileHandle image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
