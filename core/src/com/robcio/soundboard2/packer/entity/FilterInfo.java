package com.robcio.soundboard2.packer.entity;

import com.badlogic.gdx.files.FileHandle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

public class FilterInfo implements Serializable {
    private Integer id;
    private String name;
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

    public Integer getId() {
        return id;
    }

    public FilterInfo(final int id) {
        super();
        this.id = id;
        this.name = "New filter " + id;
    }

    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        id = aInputStream.readInt();
        name = aInputStream.readUTF();
        final String imagePath = aInputStream.readUTF();
        if (!imagePath.isEmpty()) {
            image = new FileHandle(aInputStream.readUTF());
        }
    }

    private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
        aOutputStream.writeInt(id);
        aOutputStream.writeUTF(name);
        if (Objects.isNull(image)) {
            aOutputStream.writeUTF("");
        } else {
            aOutputStream.writeUTF(image.path());
        }
    }
}
