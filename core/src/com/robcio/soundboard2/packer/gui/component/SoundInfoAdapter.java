package com.robcio.soundboard2.packer.gui.component;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.util.adapter.ArrayListAdapter;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.robcio.soundboard2.packer.entity.SoundInfo;
import com.robcio.soundboard2.packer.entity.SoundInfoHolder;

import javax.inject.Inject;
import java.util.Arrays;

public class SoundInfoAdapter extends ArrayListAdapter<SoundInfo, VisTable> {

    @Inject
    public SoundInfoAdapter(final SoundInfoHolder soundInfoHolder) {
        super(soundInfoHolder.getSoundInfos());
        setSelectionMode(SelectionMode.SINGLE);
    }

    @Override
    protected VisTable createView(final SoundInfo item) {
        final VisTable visTable = new VisTable();
        visTable.left();
        visTable.add(new VisLabel(item.getName()));
        return visTable;
    }

    @Override
    protected void updateView(final VisTable view, final SoundInfo item) {
        view.clear();
        view.add(new VisLabel(item.getName()));
    }

    public void setFiles(final Array<FileHandle> files) {
        clear();
        Arrays.stream(files.get(0)
                           .list())
              .map(SoundInfo::new)
              .forEach(this::add);
    }

    @Override
    public void itemsDataChanged() {
        final ListSelection<SoundInfo, VisTable> selection = getSelectionManager();
        super.itemsDataChanged();
        selection.select(selection.getSelection()
                                  .get(0));
    }

    @Override
    protected void selectView(final VisTable view) {
        final VisLabel label = (VisLabel) view.getChildren()
                                              .get(0);
        label.setColor(Color.GREEN);
    }

    @Override
    protected void deselectView(final VisTable view) {
        final VisLabel label = (VisLabel) view.getChildren()
                                              .get(0);
        label.setColor(Color.WHITE);
    }

    public void deselect() {
        getSelectionManager().deselectAll();
    }
}
