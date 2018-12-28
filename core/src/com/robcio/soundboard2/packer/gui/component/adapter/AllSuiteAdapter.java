package com.robcio.soundboard2.packer.gui.component.adapter;

import com.robcio.soundboard2.packer.entity.attribute.SuiteInfoHolder;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AllSuiteAdapter extends AllAttributeAdapter {

    @Inject
    public AllSuiteAdapter(final SuiteInfoHolder suiteInfoHolder) {
        super(suiteInfoHolder);
    }

}
