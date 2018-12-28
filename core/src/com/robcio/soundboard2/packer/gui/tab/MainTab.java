package com.robcio.soundboard2.packer.gui.tab;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisSplitPane;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.robcio.soundboard2.packer.file.PackageWriter;
import com.robcio.soundboard2.packer.file.SessionSaver;
import com.robcio.soundboard2.packer.gui.component.PacketTabPanel;
import com.robcio.soundboard2.packer.gui.tab.content.main.FilterContent;
import com.robcio.soundboard2.packer.gui.tab.content.main.SuiteContent;
import com.robcio.soundboard2.packer.util.ButtonHelper;

import javax.inject.Inject;

public class MainTab extends Tab {

    private final VisTable content = new VisTable();

    //TODO better editing and adding of filters
    @Inject
    public MainTab(final PackageWriter packageWriter,
                   final PacketTabPanel tabbedPanel,
                   final FilterContent filterContent,
                   final SuiteContent suiteContent,
                   final SessionSaver sessionSaver) {
        super(false, false);

        final VisTable controlTable = new VisTable();
        controlTable.top();

        final VisTextButton addPacketButton = ButtonHelper.textButton("Add packet", tabbedPanel::addPacketTab);
        final VisTextButton writeToFileButton = ButtonHelper.textButton("Export all", packageWriter::write);
        final VisTextButton loadLastSessionButton = ButtonHelper.textButton("Load last session", () -> {
            tabbedPanel.closeCloseableTabs();
            sessionSaver.loadTabs();
            filterContent.rebuild();
            suiteContent.rebuild();
        });

        final VisTable bottomTable = new VisTable();
        final VisSplitPane bottomPanel = new VisSplitPane(filterContent, suiteContent, false);
        bottomTable.add(bottomPanel)
                   .grow();

        controlTable.add(addPacketButton);
        controlTable.add(loadLastSessionButton);
        controlTable.add(writeToFileButton);

        final VisSplitPane splitPane = new VisSplitPane(controlTable, bottomTable, true);
        splitPane.setSplitAmount(0.2f);
        content.add(splitPane)
               .grow();
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