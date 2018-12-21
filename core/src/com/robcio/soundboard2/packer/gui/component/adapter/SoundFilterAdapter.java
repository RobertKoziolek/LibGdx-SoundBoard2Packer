package com.robcio.soundboard2.packer.gui.component.adapter;

import com.kotcrab.vis.ui.util.adapter.ArrayListAdapter;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.robcio.soundboard2.packer.entity.FilterInfo;

import java.util.ArrayList;
import java.util.Set;

public class SoundFilterAdapter extends ArrayListAdapter<FilterInfo, VisLabel> {


    private final Set<Integer> filtersId;

    public SoundFilterAdapter(final ArrayList<FilterInfo> soundFilters, final Set<Integer> filtersId) {
        super(soundFilters);
        this.filtersId = filtersId;
    }

    @Override
    protected VisLabel createView(final FilterInfo item) {
        return new VisLabel(item.getName());
    }

    @Override
    public void add(final FilterInfo filterInfo) {
        super.add(filterInfo);
        filtersId.add(filterInfo.getId());
    }

    @Override
    public boolean remove(final FilterInfo filterInfo) {
        final boolean removed = super.remove(filterInfo);
        if (removed) {
            filtersId.remove(filterInfo.getId());
        }
        return removed;
    }
}
