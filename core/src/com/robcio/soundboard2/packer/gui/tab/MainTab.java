package com.robcio.soundboard2.packer.gui.tab;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.robcio.soundboard2.packer.PackerComponent;
import com.robcio.soundboard2.packer.file.PackageWriter;
import com.robcio.soundboard2.packer.file.SessionSaver;
import com.robcio.soundboard2.packer.gui.component.PacketTabPanel;
import com.robcio.soundboard2.packer.gui.tab.content.main.FilterContent;

import javax.inject.Inject;

import static com.robcio.soundboard2.packer.util.Constants.FILTER_VIEW_WIDTH;

public class MainTab extends Tab {

    private final VisTable content = new VisTable();

    @Inject
    public MainTab(final PackerComponent packerComponent,
                   final PackageWriter packageWriter,
                   final PacketTabPanel tabbedPanel,
                   final FilterContent filterContent,
                   final SessionSaver sessionSaver) {
        super(false, false);
        content.add(filterContent)
               .growY()
               .width(FILTER_VIEW_WIDTH);


        final VisTextButton packageButton = new VisTextButton("Add package", new ChangeListener() {
            @Override
            public void changed(final ChangeEvent event, final Actor actor) {
                tabbedPanel.add(packerComponent.packetTab());
            }
        });
        content.add(packageButton);

        final VisTextButton loadPackageButton = new VisTextButton("Load package", new ChangeListener() {
            @Override
            public void changed(final ChangeEvent event, final Actor actor) {
                tabbedPanel.closeCloseableTabs();
                sessionSaver.loadTabs();
                filterContent.rebuild();
            }
        });
        content.add(loadPackageButton);

        final VisTextButton writeToFileButton = new VisTextButton("Export all", new ChangeListener() {
            @Override
            public void changed(final ChangeEvent event, final Actor actor) {
                packageWriter.write();
            }
        });
        content.add(writeToFileButton);

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