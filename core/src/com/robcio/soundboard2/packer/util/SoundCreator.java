package com.robcio.soundboard2.packer.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class SoundCreator {

    private List<Sound> sounds;

    @Inject
    public SoundCreator() {
        sounds = new ArrayList<>();
    }

    public Sound create(final FileHandle fileHandle) {
        final Sound sound = Gdx.audio.newSound(fileHandle);
        sounds.add(sound);
        return sound;
    }

    public void dispose() {
        sounds.forEach(Sound::dispose);
    }
}
