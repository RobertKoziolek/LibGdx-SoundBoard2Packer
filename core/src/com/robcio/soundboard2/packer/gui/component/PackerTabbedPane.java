package com.robcio.soundboard2.packer.gui.component;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.kotcrab.vis.ui.widget.tabbedpane.TabbedPane;
import com.kotcrab.vis.ui.widget.tabbedpane.TabbedPaneAdapter;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.robcio.soundboard2.packer.util.Constants.TAB_PANEL_HEIGHT;

@Singleton
public class PackerTabbedPane extends TabbedPane {

    @Inject
    public PackerTabbedPane() {
        super();
    }

    public void initialize(final Table root, final Table container) {
        root.add(getTable())
            .expandX()
            .height(TAB_PANEL_HEIGHT)
            .fillX();
        root.row();
        root.add(container)
            .expand()
            .fill();
        addListener(new TabbedPaneAdapter() {
            @Override
            public void switchedTab(final Tab tab) {
                final Table content = tab.getContentTable();
                container.clearChildren();
                container.add(content)
                         .expand()
                         .fill();
            }
        });
    }
}
