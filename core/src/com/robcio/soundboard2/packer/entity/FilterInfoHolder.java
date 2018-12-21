package com.robcio.soundboard2.packer.entity;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class FilterInfoHolder implements Serializable {

    private ArrayList<FilterInfo> filterInfos;

    @Inject
    public FilterInfoHolder() {
        filterInfos = new ArrayList<>();
    }

    public ArrayList<FilterInfo> getFilterInfos() {
        return filterInfos;
    }

    public ArrayList<FilterInfo> getFilterInfos(final Set<Integer> filterIds) {
        return filterInfos.stream()
                          .filter(filterInfo -> filterIds.contains(filterInfo.getId()))
                          .collect(Collectors.toCollection(ArrayList::new));
    }

    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        filterInfos = (ArrayList<FilterInfo>) aInputStream.readObject();
    }

    private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
        aOutputStream.writeObject(filterInfos);
    }

    public void loadFilterInfos(final ArrayList<FilterInfo> filterInfos) {
        this.filterInfos.clear();
        this.filterInfos.addAll(filterInfos);
    }
}
