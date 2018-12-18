package com.robcio.soundboard2.packer.entity;


import com.badlogic.gdx.files.FileHandle;

public class SoundInfo {

    private final FileHandle fileHandle;
    private String name;

    public SoundInfo(final FileHandle fileHandle) {
        this.fileHandle = fileHandle;
        this.name = fileHandle.name();
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
}
