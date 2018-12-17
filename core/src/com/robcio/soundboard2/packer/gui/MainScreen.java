package com.robcio.soundboard2.packer.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MainScreen extends ScreenAdapter {

    private final Stage stage;

    @Inject
    public MainScreen(final MainStage stage) {
        this.stage = stage;
        Gdx.input.setInputProcessor(stage);
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
        stage.dispose();
    }
}
