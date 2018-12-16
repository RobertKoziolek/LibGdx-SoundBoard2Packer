package com.robcio.soundboard2.packer.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.robcio.soundboard2.packer.SoundBoard2Packer;

import static com.robcio.soundboard2.packer.util.Constants.APP_HEIGHT;
import static com.robcio.soundboard2.packer.util.Constants.APP_WIDTH;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = APP_WIDTH;
        config.height = APP_HEIGHT;
        new LwjglApplication(new SoundBoard2Packer(), config);
    }
}
