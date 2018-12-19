package com.robcio.soundboard2.packer.entity;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;

@Singleton
public class FilterHolder {

    private final ArrayList<FilterInfo> filterInfos;

    @Inject
    public FilterHolder() {
        filterInfos = new ArrayList<>();
    }

    public ArrayList<FilterInfo> getFilterInfos() {
        return filterInfos;
    }

}
