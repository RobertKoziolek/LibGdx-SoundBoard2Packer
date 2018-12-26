package com.robcio.soundboard2.packer.entity;

import com.badlogic.gdx.files.FileHandle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ImageFilterInfo extends FilterInfo {

    public enum Align {LEFT, CENTER, RIGHT}

    private FileHandle image;
    private Align align;

    public FileHandle getImage() {
        return image;
    }

    public void setImage(final FileHandle image) {
        this.image = image;
    }

    //TODO setting
    public void setAlign(Align align) {
        this.align = align;
    }

    public Align getAlign() {
        return align;
    }

    public ImageFilterInfo(final int id, final FileHandle image) {
        super(id);
        this.id = id;
        this.name = "New image filter " + id;
        this.image = image;
        this.align = Align.CENTER;
    }

    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        id = aInputStream.readInt();
        name = aInputStream.readUTF();
        image = new FileHandle(aInputStream.readUTF());
        align = (Align) aInputStream.readObject();
    }

    private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
        aOutputStream.writeInt(id);
        aOutputStream.writeUTF(name);
        aOutputStream.writeUTF(image.path());
        aOutputStream.writeObject(align);
    }
}
