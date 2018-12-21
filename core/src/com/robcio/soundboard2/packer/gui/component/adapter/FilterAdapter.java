package com.robcio.soundboard2.packer.gui.component.adapter;

import com.badlogic.gdx.graphics.Color;
import com.kotcrab.vis.ui.util.adapter.ArrayListAdapter;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.robcio.soundboard2.packer.entity.FilterInfo;
import com.robcio.soundboard2.packer.entity.FilterInfoHolder;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FilterAdapter extends ArrayListAdapter<FilterInfo, VisLabel> {

    private final FilterInfoHolder filterInfoHolder;

    @Inject
    public FilterAdapter(final FilterInfoHolder filterInfoHolder) {
        super(filterInfoHolder.getFilterInfos());
        this.filterInfoHolder = filterInfoHolder;
        setSelectionMode(SelectionMode.SINGLE);
    }

    @Override
    protected VisLabel createView(final FilterInfo filterInfo) {
        return new VisLabel(filterInfo.getName());
    }

    @Override
    protected void updateView(final VisLabel label, final FilterInfo filterInfo) {
        label.setText(filterInfo.getName());
    }

    @Override
    public void itemsDataChanged() {
        final ListSelection<FilterInfo, VisLabel> selection = getSelectionManager();
        super.itemsDataChanged();
        selection.select(selection.getSelection()
                                  .get(0));
    }

    @Override
    public void add(final FilterInfo filterInfo) {
        super.add(filterInfo);
        getSelectionManager().select(filterInfo);
    }

    @Override
    protected void selectView(final VisLabel label) {
        label.setColor(Color.GREEN);
    }

    @Override
    protected void deselectView(final VisLabel label) {
        label.setColor(Color.WHITE);
    }
}
