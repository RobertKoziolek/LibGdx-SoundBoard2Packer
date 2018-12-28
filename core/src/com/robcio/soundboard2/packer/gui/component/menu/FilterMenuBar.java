package com.robcio.soundboard2.packer.gui.component.menu;

import com.robcio.soundboard2.packer.entity.attribute.FilterInfoHolder;

import javax.inject.Inject;

public class FilterMenuBar extends AttributeMenuBar {

    @Inject
    public FilterMenuBar(final FilterInfoHolder filterInfoHolder) {
        super(filterInfoHolder);
    }
}
