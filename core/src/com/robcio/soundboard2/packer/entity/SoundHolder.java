package com.robcio.soundboard2.packer.entity;

import com.badlogic.gdx.files.FileHandle;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.LinkedHashSet;
import java.util.Set;

@Singleton
public class SoundHolder {

    private Set<FileHandle> set;

    @Inject
    public SoundHolder() {
        this.set = new LinkedHashSet<>();
    }

    public void add(final FileHandle fileHandle) {
        System.out.println("adding " + fileHandle.file()
                                                 .getName());
        this.set.add(fileHandle);
    }
}
