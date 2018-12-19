package com.robcio.soundboard2.packer.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kotcrab.vis.ui.FocusManager;
import com.kotcrab.vis.ui.Focusable;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Objects;

@Singleton
public class KeyboardFocuser {

    private Stage stage;

    @Inject
    public KeyboardFocuser() {

    }

    public void focus(final Focusable widget) {
        if (Objects.isNull(stage)) throw new IllegalStateException("Keyboard not initialized");
        FocusManager.switchFocus(stage, widget);
        stage.setKeyboardFocus((Actor) widget);
    }

    public void setStage(final Stage stage) {
        this.stage = stage;
    }
}
