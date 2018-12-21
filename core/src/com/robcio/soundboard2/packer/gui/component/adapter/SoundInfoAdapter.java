package com.robcio.soundboard2.packer.gui.component.adapter;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.util.adapter.ArrayListAdapter;
import com.kotcrab.vis.ui.widget.ListView;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.robcio.soundboard2.packer.entity.SoundInfo;
import com.robcio.soundboard2.packer.entity.SoundInfoHolder;

import java.util.Arrays;

public class SoundInfoAdapter extends ArrayListAdapter<SoundInfo, VisTable> {

    private final SoundInfoHolder soundInfoHolder;

    public SoundInfoAdapter(final SoundInfoHolder soundInfoHolder) {
        super(soundInfoHolder.getSoundInfos());
        this.soundInfoHolder = soundInfoHolder;
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
    protected void updateView(final VisTable table, final SoundInfo soundInfo) {
        table.clear();
        table.add(new VisLabel(soundInfo.getName()));
    }

    public String readDirectory(final Array<FileHandle> files) {
        //TODO loading needs to check for mp3 only / maybe .ogg too if decided on using more than mp3
        clear();
        final FileHandle fileHandle = files.get(0);
        Arrays.stream(fileHandle
                              .list())
              .map(SoundInfo::new)
              .forEach(this::add);
        return fileHandle.name();
    }

    @Override
    public void itemsDataChanged() {
        final ListSelection<SoundInfo, VisTable> selection = getSelectionManager();
        super.itemsDataChanged();
        if (size() > 0) {
            selection.select(selection.getSelection()
                                      .get(0));
        }
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

    public SoundInfoHolder getSoundInfoHolder() {
        return soundInfoHolder;
    }

    public void selectFirst(final ListView.ItemClickListener<SoundInfo> clickListener) {
        if (size() > 0) {
            final SoundInfo soundInfo = get(0);
            getSelectionManager().select(soundInfo);
            clickListener.clicked(soundInfo);
        }
    }

    public void reselect(final ListView.ItemClickListener<SoundInfo> clickListener) {
        if (size() > 0) {
            final SoundInfo soundInfo = get(0);
            getSelectionManager().select(soundInfo);
            clickListener.clicked(soundInfo);
        }
    }
}
