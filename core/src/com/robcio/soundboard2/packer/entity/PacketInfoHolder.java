package com.robcio.soundboard2.packer.entity;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.Serializable;
import java.util.ArrayList;

@Singleton
public class PacketInfoHolder implements Serializable {

    private ArrayList<PacketInfo> packetInfos;

    @Inject
    public PacketInfoHolder() {
        this.packetInfos = new ArrayList<>();
    }

    public ArrayList<PacketInfo> getPacketInfos() {
        return packetInfos;
    }

    public void add(final PacketInfo packetInfo) {
        packetInfos.add(packetInfo);
    }

    public void loadPacketInfos(final ArrayList<PacketInfo> packetInfos) {
        this.packetInfos.clear();
        this.packetInfos.addAll(packetInfos);

    }
}
