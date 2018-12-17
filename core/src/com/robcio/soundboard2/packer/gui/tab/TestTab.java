package com.robcio.soundboard2.packer.gui.tab;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;

import javax.inject.Inject;

public class TestTab extends Tab {
    private final VisTable content = new VisTable();

    @Inject
    public TestTab() {
        super(false, true);
        content.add(new VisLabel("This is a test tab"));
    }

    @Override
    public String getTabTitle() {
        return "Test Tab";
    }

    @Override
    public Table getContentTable() {
        return content;
    }
}