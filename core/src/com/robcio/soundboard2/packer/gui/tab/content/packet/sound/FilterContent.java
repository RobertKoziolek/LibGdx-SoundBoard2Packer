package com.robcio.soundboard2.packer.gui.tab.content.packet.sound;

import com.robcio.soundboard2.packer.entity.attribute.FilterInfoHolder;
import com.robcio.soundboard2.packer.gui.component.menu.FilterMenuBar;

import javax.inject.Inject;

public class FilterContent extends AttributeContent {

    @Inject
    public FilterContent(final FilterMenuBar filterMenuBar, final FilterInfoHolder filterInfoHolder) {
        super(filterMenuBar, filterInfoHolder);
    }

    @Override
    String getHeadline() {
        return "Filters:";
    }
}
