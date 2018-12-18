package com.robcio.soundboard2.packer.gui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.kotcrab.vis.ui.widget.VisTable;
import com.robcio.soundboard2.packer.gui.component.PackerTabbedPane;
import com.robcio.soundboard2.packer.gui.tab.MainTab;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.robcio.soundboard2.packer.util.Constants.APP_HEIGHT;
import static com.robcio.soundboard2.packer.util.Constants.APP_WIDTH;

@Singleton
public class MainStage extends Stage {

    @Inject
    public MainStage(final PackerTabbedPane tabbedPane, final MainTab mainTab) {
        super(new StretchViewport(APP_WIDTH, APP_HEIGHT));
        final Table root = initializeRoot();
        tabbedPane.initialize(root);
        tabbedPane.add(mainTab);
    }

    private Table initializeRoot() {
        final Table table = new VisTable();
        table.setFillParent(true);
        addActor(table);
        return table;
    }
}
