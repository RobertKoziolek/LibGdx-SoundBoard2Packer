package com.robcio.soundboard2.packer.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class ButtonHelper {

    public static VisTextButton textButton(final String text, final Command command) {
        return new VisTextButton(text, new ChangeListener() {
            @Override
            public void changed(final ChangeEvent event, final Actor actor) {
                command.perform();
            }
        });
    }
}
