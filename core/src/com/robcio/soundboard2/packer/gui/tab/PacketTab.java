package com.robcio.soundboard2.packer.gui.tab;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.ListView;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.kotcrab.vis.ui.widget.file.FileChooserAdapter;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.robcio.soundboard2.packer.entity.PacketInfo;
import com.robcio.soundboard2.packer.entity.SoundInfo;
import com.robcio.soundboard2.packer.entity.SoundInfoHolder;
import com.robcio.soundboard2.packer.gui.component.PacketTabPane;
import com.robcio.soundboard2.packer.gui.component.SoundInfoAdapter;

import javax.inject.Inject;

import static com.robcio.soundboard2.packer.util.Constants.LIST_VIEW_WIDTH;

public class PacketTab extends Tab {
    private final VisTable content = new VisTable();
    private final PacketInfo packetInfo;
    private final SoundInfoHolder soundInfoHolder;
    private final PacketTabPane packetTabPane;

    @Inject
    public PacketTab(final PacketInfo packetInfo,
                     final FileChooser fileChooser,
                     final SoundInfoAdapter soundInfoAdapter,
                     final PacketTabPane packetTabPane) {
        super(true, true);
        this.packetInfo = packetInfo;
        this.soundInfoHolder = soundInfoAdapter.getSoundInfoHolder();
        this.packetTabPane = packetTabPane;
        final ListView<SoundInfo> listView = new ListView<>(soundInfoAdapter);
        initializeLayout(packetTabPane, listView);
        updatePacketContent();
        packetTabPane.openFiles(fileChooser);
        {
            listView.setItemClickListener(
                    soundInfo -> packetTabPane.updateSoundContent(soundInfo,
                                                                  soundInfoAdapter::itemsDataChanged,
                                                                  () -> setDirty(true)));
            final VisTextButton fileButton = new VisTextButton("Clear & load mp3 folder", new ChangeListener() {

                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    soundInfoAdapter.deselect();
                    packetTabPane.openFiles(fileChooser);
                }
            });
            listView.setHeader(fileButton);
            listView.getScrollPane()
                    .setScrollingDisabled(true, false);
        }
        {
            fileChooser.setListener(new FileChooserAdapter() {
                @Override
                public void selected(final Array<FileHandle> files) {
                    final String dirName = soundInfoAdapter.readDirectory(files);
                    updateTitle(dirName);
                    setDirty(true);
                    updatePacketContent();
                }
            });
        }
    }

    private void updatePacketContent() {
        packetTabPane.updatePacketContent(packetInfo, soundInfoHolder, this::save, this::updateTitle);
    }

    private void updateTitle(final String title) {
        packetInfo.setName(title);
        getPane().updateTabTitle(this);
    }

    private void initializeLayout(final PacketTabPane packetTabPane, final ListView<SoundInfo> listView) {
        content.left();
        content.add(listView.getMainTable())
               .growY()
               .width(LIST_VIEW_WIDTH);
        content.add(packetTabPane)
               .grow();
    }

    @Override
    public boolean save() {
        super.save();
        setDirty(false);
        //TODO saving
        return true;
    }

    @Override
    public String getTabTitle() {
        return packetInfo.getName();
    }

    @Override
    public Table getContentTable() {
        return content;
    }
}