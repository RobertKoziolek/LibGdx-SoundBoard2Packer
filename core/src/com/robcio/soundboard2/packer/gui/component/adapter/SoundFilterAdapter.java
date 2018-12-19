package com.robcio.soundboard2.packer.gui.component.adapter;

import com.kotcrab.vis.ui.util.adapter.ArrayListAdapter;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.robcio.soundboard2.packer.entity.FilterInfo;

import java.util.ArrayList;

public class SoundFilterAdapter extends ArrayListAdapter<FilterInfo, VisLabel> {


    public SoundFilterAdapter(final ArrayList<FilterInfo> soundFilters) {
        super(soundFilters);
    }

    @Override
    protected VisLabel createView(final FilterInfo item) {
        return new VisLabel(item.getName());
    }
}
