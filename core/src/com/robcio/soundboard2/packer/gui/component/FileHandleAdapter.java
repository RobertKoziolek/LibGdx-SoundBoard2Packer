package com.robcio.soundboard2.packer.gui.component;

import com.badlogic.gdx.files.FileHandle;
import com.kotcrab.vis.ui.util.adapter.ArrayListAdapter;
import com.kotcrab.vis.ui.widget.VisTable;
import com.robcio.soundboard2.packer.entity.SoundHolder;

import javax.inject.Inject;

public class FileHandleAdapter extends ArrayListAdapter<FileHandle, VisTable> {

    @Inject
    public FileHandleAdapter(final SoundHolder soundHolder) {
        super(soundHolder.getFileHandles());
    }

    @Override
    protected VisTable createView(final FileHandle item) {
        final VisTable visTable = new VisTable();
        visTable.left();
        visTable.add(item.name());
        return visTable;
    }

    @Override
    protected void updateView(final VisTable view, final FileHandle item) {
        //TODO invoked by adapter.itemDataChanged()
    }

}
