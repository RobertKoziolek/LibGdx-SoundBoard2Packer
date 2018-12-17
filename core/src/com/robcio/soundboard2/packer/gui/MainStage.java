package com.robcio.soundboard2.packer.gui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.kotcrab.vis.ui.widget.tabbedpane.TabbedPane;
import com.kotcrab.vis.ui.widget.tabbedpane.TabbedPaneAdapter;
import com.robcio.soundboard2.packer.gui.tab.MainTab;
import com.robcio.soundboard2.packer.gui.tab.TestTab;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.robcio.soundboard2.packer.util.Constants.APP_HEIGHT;
import static com.robcio.soundboard2.packer.util.Constants.APP_WIDTH;

@Singleton
public class MainStage extends Stage {

    @Inject
    public MainStage(final MainTab mainTab, final TestTab testTab) {
        super(new StretchViewport(APP_WIDTH, APP_HEIGHT));
        final Table root = initializeRoot();
        final Table container = new VisTable();
        final TabbedPane tabbedPane = initializeTabbedPane(root, container);
        tabbedPane.add(mainTab);
        tabbedPane.add(testTab);
    }

    private Table initializeRoot() {
        final Table table = new VisTable();
        table.setFillParent(true);
        addActor(table);
        return table;
    }

    private TabbedPane initializeTabbedPane(final Table root, final Table container) {
        final TabbedPane tabbedPane = new TabbedPane();
        root.add(tabbedPane.getTable())
            .expandX()
            .fillX();
        root.row();
        root.add(container)
            .expand()
            .fill();
        tabbedPane.addListener(new TabbedPaneAdapter() {
            @Override
            public void switchedTab(final Tab tab) {
                //TODO can modify here to add a new tab by clicking a + tab
                final Table content = tab.getContentTable();
                container.clearChildren();
                container.add(content)
                         .expand()
                         .fill();
            }
        });
        return tabbedPane;
    }
}
