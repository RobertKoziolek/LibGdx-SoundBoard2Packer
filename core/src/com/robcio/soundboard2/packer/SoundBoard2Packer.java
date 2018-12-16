package com.robcio.soundboard2.packer;

import com.badlogic.gdx.Game;
import com.robcio.soundboard2.packer.gui.MainScreen;


public class SoundBoard2Packer extends Game {

    @Override
    public void create() {
        setScreen(new MainScreen());
    }

    @Override
    public void dispose() {
        super.dispose();
        getScreen().dispose();
    }
}
