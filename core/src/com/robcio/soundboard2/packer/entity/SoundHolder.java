package com.robcio.soundboard2.packer.entity;

import com.badlogic.gdx.files.FileHandle;

import javax.inject.Inject;
import java.util.ArrayList;

public class SoundHolder {

    private ArrayList<FileHandle> fileHandles;

    @Inject
    public SoundHolder() {
        this.fileHandles = new ArrayList<>();
    }

    public ArrayList<FileHandle> getFileHandles() {
        return fileHandles;
    }
}
