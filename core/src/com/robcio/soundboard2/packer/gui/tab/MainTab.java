package com.robcio.soundboard2.packer.gui.tab;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;

import javax.inject.Inject;

public class MainTab extends Tab {
    private final VisTable content = new VisTable();

    @Inject
    public MainTab(final FileChooser fileChooser) {
        super(false, false);
        final VisTextButton visTextButton = new VisTextButton("Open mp3 files", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                content.add(fileChooser);
                //TODO /\ should add it higher, distorts what already is inside the content table
            }
        });
        content.add(new VisLabel("This is the main tab"));
        content.add(visTextButton);
    }

    @Override
    public String getTabTitle() {
        return "Main";
    }

    @Override
    public Table getContentTable() {
        return content;

    }
}