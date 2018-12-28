package com.robcio.soundboard2.packer.gui.component.adapter;

import com.robcio.soundboard2.packer.entity.attribute.FilterInfoHolder;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AllFilterAdapter extends AllAttributeAdapter {

    @Inject
    public AllFilterAdapter(final FilterInfoHolder filterInfoHolder) {
        super(filterInfoHolder);
    }

}
