package com.robcio.soundboard2.packer.gui.component.menu;

import com.robcio.soundboard2.packer.entity.attribute.SuiteInfoHolder;

import javax.inject.Inject;

public class SuiteMenuBar extends AttributeMenuBar {

    @Inject
    public SuiteMenuBar(final SuiteInfoHolder suiteInfoHolder) {
        super(suiteInfoHolder);
    }
}
