package com.robcio.soundboard2.packer.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class SoundCreator {

    private Map<FileHandle, Sound> sounds;

    @Inject
    public SoundCreator() {
        sounds = new HashMap<>();
    }

    public Sound create(final FileHandle fileHandle) {
        if (sounds.containsKey(fileHandle)) {
            return sounds.get(fileHandle);
        }
        final Sound sound = Gdx.audio.newSound(fileHandle);
        sounds.put(fileHandle, sound);
        return sound;
    }

    public void dispose() {
        System.out.println(sounds.size());
        sounds.values()
              .forEach(Sound::dispose);
    }
}
