package com.robcio.soundboard2.packer.gui.tab.content.main;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.ListView;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.robcio.soundboard2.packer.entity.FilterInfo;
import com.robcio.soundboard2.packer.gui.component.FilterNamingField;
import com.robcio.soundboard2.packer.gui.component.adapter.FilterAdapter;
import com.robcio.soundboard2.packer.util.KeyboardFocuser;

import javax.inject.Inject;

import static com.robcio.soundboard2.packer.util.Constants.LIST_VIEW_WIDTH;

public class FilterContent extends VisTable {

    @Inject
    public FilterContent(final FilterAdapter filterAdapter, final KeyboardFocuser keyboardFocuser) {
        final ListView<FilterInfo> filterView = new ListView<>(filterAdapter);
        filterView.setHeader(new VisLabel("Filters:"));
        final FilterNamingField filterEditor = new FilterNamingField(filterAdapter::itemsDataChanged,
                                                                     keyboardFocuser::focus);
        filterAdapter.setItemClickListener(filterEditor::setFilterInfo);
        final VisTextButton addFilterButton = new VisTextButton("New filter", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                final FilterInfo filterInfo = new FilterInfo();
                filterAdapter.add(filterInfo);
                filterEditor.setFilterInfo(filterInfo);
            }
        });

        //TODO image addition
        final VisTable filterControlTable = new VisTable();
        filterControlTable.add(filterEditor)
                          .growX()
                          .row();
        filterControlTable.add(addFilterButton)
                          .growX()
                          .row();

        add(filterView.getMainTable()).width(LIST_VIEW_WIDTH)
                                      .row();
        add(filterControlTable).growX();
    }
}
