package com.robcio.soundboard2.packer.entity;


import javax.inject.Inject;

public class PacketInfo {

    private String name;

    @Inject
    public PacketInfo() {
        this.name = "Packet";
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
