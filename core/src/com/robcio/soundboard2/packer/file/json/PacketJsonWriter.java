package com.robcio.soundboard2.packer.file.json;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.robcio.soundboard2.packer.entity.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

import static com.badlogic.gdx.utils.JsonWriter.OutputType.javascript;
import static com.robcio.soundboard2.packer.file.Constants.PACKAGE_JSON;

//TODO All packets info and indicator info, all zipped with sounds and images
@Singleton
public class PacketJsonWriter extends JsonWriter {

    private final PacketInfoHolder packetInfoHolder;
    private final FilterInfoHolder filterInfoHolder;

    @Inject
    public PacketJsonWriter(final PacketInfoHolder packetInfoHolder, final FilterInfoHolder filterInfoHolder) {
        this.packetInfoHolder = packetInfoHolder;
        this.filterInfoHolder = filterInfoHolder;
    }

    @Override
    public void write() {
        packetInfoHolder.getPacketInfos()
                        .forEach(this::savePacket);
    }

    private void savePacket(final PacketInfo packetInfo) {
        System.out.println("Saving " + packetInfo.getName());
        final SoundInfoHolder soundInfoHolder = packetInfo.getSoundInfoHolder();
        final StringWriter stringWriter = new StringWriter();

        final Json json = new Json();
        json.setWriter(stringWriter);
        json.writeObjectStart();
        json.writeValue("folder", packetInfo.getFolder());
        json.writeValue("filter", packetInfo.getName());
        json.writeValue("format", ".mp3");
        json.writeArrayStart("voiceModels");
        soundInfoHolder.getSoundInfos()
                       .forEach(soundInfo -> {
                           json.writeObjectStart();
                           json.writeValue("name", soundInfo.getName());
                           json.writeValue("file", soundInfo.getFileHandle()
                                                            .name()
                                                            .replace(".mp3", ""));
                           final List<String> filters = filterInfoHolder.getFilterInfos(soundInfo.getFiltersId())
                                                                        .stream()
                                                                        .map(FilterInfo::getName)
                                                                        .collect(Collectors.toList());
                           writeArray(json, "filter", packetInfo.getName(), filters);
                           writeArray(json, "suite", null, soundInfo.getSuites());
                           json.writeObjectEnd();
                       });
        json.writeArrayEnd();
        json.writeObjectEnd();

        final FileHandle fileHandle = Gdx.files.external(PACKAGE_JSON + "/" + packetInfo.getFolder() + ".json");
        json.setOutputType(javascript);
        fileHandle.writeString(json.prettyPrint(stringWriter.toString()), false);
    }
}
