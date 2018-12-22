package com.robcio.soundboard2.packer.gui.component;

import com.kotcrab.vis.ui.widget.VisSplitPane;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.robcio.soundboard2.packer.entity.PacketInfo;
import com.robcio.soundboard2.packer.entity.SoundInfo;
import com.robcio.soundboard2.packer.gui.tab.content.packet.PacketContent;
import com.robcio.soundboard2.packer.gui.tab.content.packet.SoundContent;
import com.robcio.soundboard2.packer.util.Command;

import javax.inject.Inject;
import java.util.function.Consumer;

public class PacketTabPane extends VisSplitPane {

    private final PacketContent packetContent;
    private final SoundContent soundContent;

    @Inject
    public PacketTabPane(final PacketContent packetContent, final SoundContent soundContent) {
        super(packetContent, soundContent, true);
        this.packetContent = packetContent;
        this.soundContent = soundContent;
        setSplitAmount(0.3f);
    }

    public void updatePacketContent(final PacketInfo packetInfo,
                                    final Command saveCommand,
                                    final Consumer<String> updateTitleCommand) {
        packetContent.update(packetInfo, saveCommand, updateTitleCommand);
    }

    public void updateSoundContent(final SoundInfo soundInfo,
                                   final Command updateViewCommand) {
        soundContent.update(soundInfo, updateViewCommand);
    }

    public void openFiles(final FileChooser fileChooser) {
        clearSoundContent();
        soundContent.add(fileChooser)
                    .grow();
    }

    public void clearSoundContent() {
        soundContent.clear();
    }
}
