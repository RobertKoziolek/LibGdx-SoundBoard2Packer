package com.robcio.soundboard2.packer.gui.tab;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.robcio.soundboard2.packer.PackerComponent;
import com.robcio.soundboard2.packer.gui.component.PacketTabPanel;
import com.robcio.soundboard2.packer.gui.tab.content.main.FilterContent;

import javax.inject.Inject;

import static com.robcio.soundboard2.packer.util.Constants.LIST_VIEW_WIDTH;

public class MainTab extends Tab {
    private final VisTable content = new VisTable();

    @Inject
    public MainTab(final PackerComponent packerComponent,
                   final PacketTabPanel tabbedPane,
                   final FilterContent filterContent) {
        super(false, false);
        content.add(filterContent)
               .growY()
               .width(LIST_VIEW_WIDTH);


        final VisTextButton packageButton = new VisTextButton("Add package", new ChangeListener() {
            @Override
            public void changed(final ChangeEvent event, final Actor actor) {
                tabbedPane.add(packerComponent.packetTab());
            }
        });
        content.add(packageButton);
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