package com.robcio.soundboard2.packer.entity;


import javax.inject.Inject;
import java.io.Serializable;

public class PacketInfo implements Serializable {

    private static final String DEFAULT_NAME = "Packet";

    private String name;
    private String folder;
    private SoundInfoHolder soundInfoHolder;

    @Inject
    public PacketInfo(final SoundInfoHolder soundInfoHolder) {
        this.soundInfoHolder = soundInfoHolder;
        this.name = DEFAULT_NAME;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        if (this.name == DEFAULT_NAME) {
            folder = name;
        }
        this.name = name;
    }

    public String getFolder() {
        return folder;
    }

    public SoundInfoHolder getSoundInfoHolder() {
        return soundInfoHolder;
    }
}
