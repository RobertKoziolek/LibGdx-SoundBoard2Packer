package com.robcio.soundboard2.packer;

import com.badlogic.gdx.Game;
import com.kotcrab.vis.ui.VisUI;


public class SoundBoard2Packer extends Game {

    @Override
    public void create() {
        VisUI.load();
        final PackerComponent packerComponent = DaggerPackerComponent.create();

        setScreen(packerComponent.mainScreen());
    }

    @Override
    public void dispose() {
        super.dispose();
        getScreen().dispose();
        VisUI.dispose();
    }
}
