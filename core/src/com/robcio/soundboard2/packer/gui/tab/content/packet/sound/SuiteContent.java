package com.robcio.soundboard2.packer.gui.tab.content.packet.sound;

import com.robcio.soundboard2.packer.entity.attribute.SuiteInfoHolder;
import com.robcio.soundboard2.packer.gui.component.menu.SuiteMenuBar;

import javax.inject.Inject;

public class SuiteContent extends AttributeContent {

    @Inject
    public SuiteContent(final SuiteMenuBar suiteMenuBar, final SuiteInfoHolder suiteInfoHolder) {
        super(suiteMenuBar, suiteInfoHolder);
    }

    @Override
    String getHeadline() {
        return "Suites:";
    }
}
