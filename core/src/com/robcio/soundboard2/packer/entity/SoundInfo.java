package com.robcio.soundboard2.packer.entity;


import com.badlogic.gdx.files.FileHandle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SoundInfo implements Serializable {

    private FileHandle fileHandle;
    private String name;
    private Set<Integer> filtersId;
    //TODO suites
    private ArrayList<String> suites;

    public SoundInfo(final FileHandle fileHandle) {
        this.fileHandle = fileHandle;
        this.name = fileHandle.name();
        this.filtersId = new HashSet<>();
        this.suites = new ArrayList<>();
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

    public Set<Integer> getFiltersId() {
        return filtersId;
    }

    public ArrayList<String> getSuites() {
        return suites;
    }

    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        fileHandle = new FileHandle(aInputStream.readUTF());
        name = aInputStream.readUTF();
        filtersId = (HashSet<Integer>) aInputStream.readObject();
        suites = (ArrayList<String>) aInputStream.readObject();
    }

    private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
        aOutputStream.writeUTF(fileHandle.path());
        aOutputStream.writeUTF(name);
        aOutputStream.writeObject(filtersId);
        aOutputStream.writeObject(suites);
    }
}
