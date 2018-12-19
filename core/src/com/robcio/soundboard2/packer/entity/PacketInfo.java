package com.robcio.soundboard2.packer.entity;


import javax.inject.Inject;

public class PacketInfo {

    private static final String DEFAULT_NAME = "Packet";

    private String name;
    private String folder;

    @Inject
    public PacketInfo() {
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
}
