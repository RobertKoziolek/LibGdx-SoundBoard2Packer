package com.robcio.soundboard2.packer.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.file.FileChooser;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;

import static com.robcio.soundboard2.packer.util.Constants.APP_HEIGHT;
import static com.robcio.soundboard2.packer.util.Constants.APP_WIDTH;

@Singleton
public class MainScreen extends ScreenAdapter {

    private final Stage stage;

    private final Set<Sound> sounds = new HashSet<>();

    @Inject
    public MainScreen(final FileChooser fileChooser) {
        this.stage = new Stage(new StretchViewport(APP_WIDTH, APP_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        final VisTable table = new VisTable();
        table.setFillParent(true);
        table.add("Test label");

        final VisTextButton visTextButton = new VisTextButton("Open mp3 files", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.addActor(fileChooser);
            }
        });
        stage.addActor(visTextButton);
        stage.addActor(table);
    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(final int width, final int height) {
        stage.getViewport()
             .update(width, height, false);
    }

    @Override
    public void show() {
    }

    @Override
    public void dispose() {
        sounds.forEach(Sound::dispose);
        stage.dispose();
    }
}
