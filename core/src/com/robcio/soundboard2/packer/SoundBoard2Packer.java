package com.robcio.soundboard2.packer;

import com.badlogic.gdx.Game;
import com.kotcrab.vis.ui.VisUI;


public class SoundBoard2Packer extends Game {
    private PackerComponent packerComponent;

    @Override
    public void create() {
        VisUI.load();
        packerComponent = DaggerPackerComponent.create();

        setScreen(packerComponent.mainScreen());
    }

    @Override
    public void dispose() {
        super.dispose();
        packerComponent.soundCreator()
                       .dispose();
        getScreen().dispose();
        VisUI.dispose();
    }
}
