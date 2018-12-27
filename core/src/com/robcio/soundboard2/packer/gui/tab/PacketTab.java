package com.robcio.soundboard2.packer.gui.tab;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.ListView;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.kotcrab.vis.ui.widget.file.FileChooserAdapter;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.robcio.soundboard2.packer.entity.PacketInfo;
import com.robcio.soundboard2.packer.entity.SoundInfo;
import com.robcio.soundboard2.packer.gui.component.PacketAndSoundDetailPane;
import com.robcio.soundboard2.packer.gui.component.adapter.SoundInfoAdapter;
import com.robcio.soundboard2.packer.util.ButtonHelper;
import com.robcio.soundboard2.packer.util.Command;

import javax.inject.Inject;
import javax.inject.Named;

import static com.robcio.soundboard2.packer.util.Constants.LIST_VIEW_WIDTH;

public class PacketTab extends Tab {
    private final PacketInfo packetInfo;
    private final PacketAndSoundDetailPane packetAndSoundDetailPane;

    private final VisTable content = new VisTable();
    private final Command onShowCommand;

    @Inject
    public PacketTab(final PacketInfo packetInfo,
                     final PacketAndSoundDetailPane packetAndSoundDetailPane,
                     @Named("packetSounds") final FileChooser fileChooser) {
        super(true, true);
        this.packetInfo = packetInfo;
        this.packetAndSoundDetailPane = packetAndSoundDetailPane;

        final SoundInfoAdapter soundInfoAdapter = new SoundInfoAdapter(packetInfo.getSoundInfoHolder());
        final ListView<SoundInfo> listView = new ListView<>(soundInfoAdapter);
        initializeLayout(packetAndSoundDetailPane, listView);
        updatePacketContent();
        packetAndSoundDetailPane.openFiles(fileChooser);
        {
            listView.setItemClickListener(
                    soundInfo -> packetAndSoundDetailPane.updateSoundContent(soundInfo,
                                                                             soundInfoAdapter::itemsDataChanged));
            final VisTextButton loadFilesButton = ButtonHelper.textButton("Clear & load sound folder", () -> {
                soundInfoAdapter.deselect();
                packetAndSoundDetailPane.openFiles(fileChooser);
            });
            listView.setHeader(loadFilesButton);
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
                    soundInfoAdapter.selectFirst(listView.getClickListener());
                }
            });
        }
        {
            onShowCommand = () -> {
                soundInfoAdapter.reselect(listView.getClickListener());
            };
        }
    }

    private void updatePacketContent() {
        packetAndSoundDetailPane.updatePacketContent(packetInfo, this::save, this::updateTitle);
    }

    private void updateTitle(final String title) {
        packetInfo.setName(title);
        getPane().updateTabTitle(this);
    }

    private void initializeLayout(final PacketAndSoundDetailPane packetAndSoundDetailPane,
                                  final ListView<SoundInfo> listView) {
        content.left();
        content.add(listView.getMainTable())
               .growY()
               .width(LIST_VIEW_WIDTH);
        content.add(packetAndSoundDetailPane)
               .grow();
    }

    @Override
    public void onShow() {
        super.onShow();
        onShowCommand.perform();
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