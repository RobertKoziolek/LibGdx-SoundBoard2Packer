package com.robcio.soundboard2.packer.entity;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;

public class SoundInfoHolder implements Serializable {

    private ArrayList<SoundInfo> soundInfos;

    @Inject
    public SoundInfoHolder() {
        this.soundInfos = new ArrayList<>();
    }

    public ArrayList<SoundInfo> getSoundInfos() {
        return soundInfos;
    }
}
