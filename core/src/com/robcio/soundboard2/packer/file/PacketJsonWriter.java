package com.robcio.soundboard2.packer.file;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.robcio.soundboard2.packer.entity.FilterInfo;
import com.robcio.soundboard2.packer.entity.FilterInfoHolder;
import com.robcio.soundboard2.packer.entity.PacketInfo;
import com.robcio.soundboard2.packer.entity.SoundInfoHolder;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


//TODO All packets info and indicator info, all zipped with sounds and images
@Singleton
public class PacketJsonWriter {

    private final FilterInfoHolder filterInfoHolder;

    @Inject
    public PacketJsonWriter(final FilterInfoHolder filterInfoHolder) {

        this.filterInfoHolder = filterInfoHolder;
    }

    public void savePacket(final PacketInfo packetInfo) {
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
                           writeArray(json, "filters", packetInfo.getName(), filters);
                           writeArray(json, "suites", null, soundInfo.getSuites());
                           json.writeObjectEnd();
                       });
        json.writeArrayEnd();
        json.writeObjectEnd();

        System.out.println(json.prettyPrint(stringWriter.toString()));
    }

    private void writeArray(final Json json, final String name, final String first, final List<String> values) {
        json.writeArrayStart(name);
        final JsonWriter writer = json.getWriter();
        if (!Objects.isNull(first)) {
            writeValue(writer, first);
        }
        values.forEach(value -> writeValue(writer, value));
        json.writeArrayEnd();
    }

    private void writeValue(final JsonWriter writer, final String value) {
        try {
            writer.value(value);
        } catch (final IOException e) {
            e.printStackTrace();
        }

    }
}
