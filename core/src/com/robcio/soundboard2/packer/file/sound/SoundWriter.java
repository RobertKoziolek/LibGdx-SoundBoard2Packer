package com.robcio.soundboard2.packer.file.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.robcio.soundboard2.packer.entity.packet.PacketInfoHolder;
import com.robcio.soundboard2.packer.entity.sound.SoundInfo;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

import static com.robcio.soundboard2.packer.file.Constants.PACKAGE_SOUND;

@Singleton
public class SoundWriter {

    private final PacketInfoHolder packetInfoHolder;

    @Inject
    public SoundWriter(final PacketInfoHolder packetInfoHolder) {
        this.packetInfoHolder = packetInfoHolder;
    }

    public void write() {
        packetInfoHolder.getPacketInfos()
                        .forEach(packetInfo -> {
                            final String folder = packetInfo.getFolder();
                            final String destination = PACKAGE_SOUND + folder + "/";
                            final List<FileHandle> fileHandles = packetInfo.getSoundInfoHolder()
                                                                           .getSoundInfos()
                                                                           .stream()
                                                                           .map(SoundInfo::getFileHandle)
                                                                           .collect(Collectors.toList());
                            fileHandles.forEach(fileHandle -> {
                                final FileHandle external = Gdx.files.external(destination);
                                external.mkdirs();
                                fileHandle.copyTo(external);
                            });
                        });
    }

}
