package com.robcio.soundboard2.packer.entity;

import javax.inject.Inject;
import java.util.ArrayList;

public class SoundInfoHolder {

    private ArrayList<SoundInfo> soundInfos;

    @Inject
    public SoundInfoHolder() {
        this.soundInfos = new ArrayList<>();
    }

    public ArrayList<SoundInfo> getSoundInfos() {
        return soundInfos;
    }
}
