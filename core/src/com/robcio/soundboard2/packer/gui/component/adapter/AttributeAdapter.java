package com.robcio.soundboard2.packer.gui.component.adapter;

import com.kotcrab.vis.ui.util.adapter.ArrayListAdapter;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.robcio.soundboard2.packer.entity.attribute.Attribute;

import java.util.ArrayList;
import java.util.Set;

public class AttributeAdapter extends ArrayListAdapter<Attribute, VisLabel> {


    private final Set<Integer> filtersId;

    public AttributeAdapter(final ArrayList<Attribute> soundFilters, final Set<Integer> filtersId) {
        super(soundFilters);
        this.filtersId = filtersId;
    }

    @Override
    protected VisLabel createView(final Attribute item) {
        return new VisLabel(item.getName());
    }

    @Override
    public void add(final Attribute attribute) {
        super.add(attribute);
        filtersId.add(attribute.getId());
    }

    @Override
    public boolean remove(final Attribute attribute) {
        final boolean removed = super.remove(attribute);
        if (removed) {
            filtersId.remove(attribute.getId());
        }
        return removed;
    }
}
