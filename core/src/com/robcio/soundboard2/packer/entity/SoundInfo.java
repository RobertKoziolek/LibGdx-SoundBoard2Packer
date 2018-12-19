package com.robcio.soundboard2.packer.entity;


import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;

public class SoundInfo {

    private final FileHandle fileHandle;
    private String name;
    private final ArrayList<FilterInfo> filters;

    public SoundInfo(final FileHandle fileHandle) {
        this.fileHandle = fileHandle;
        this.name = fileHandle.name();
        this.filters = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public FileHandle getFileHandle() {
        return fileHandle;
    }

    public ArrayList<FilterInfo> getFilters() {
        return filters;
    }
}
