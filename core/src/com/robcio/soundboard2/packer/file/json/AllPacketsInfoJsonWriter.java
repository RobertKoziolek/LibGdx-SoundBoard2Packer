package com.robcio.soundboard2.packer.file.json;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.robcio.soundboard2.packer.entity.packet.PacketInfo;
import com.robcio.soundboard2.packer.entity.packet.PacketInfoHolder;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

import static com.badlogic.gdx.utils.JsonWriter.OutputType.javascript;
import static com.robcio.soundboard2.packer.file.Constants.PACKAGE_JSON_PACKETS_INFO;

@Singleton
class AllPacketsInfoJsonWriter extends JsonWriter {

    private final PacketInfoHolder packetInfoHolder;

    @Inject
    public AllPacketsInfoJsonWriter(final PacketInfoHolder packetInfoHolder) {
        this.packetInfoHolder = packetInfoHolder;
    }

    @Override
    public void write() {
        System.out.println("Saving all packets info");
        final StringWriter stringWriter = new StringWriter();

        final List<String> dirs = packetInfoHolder.getPacketInfos()
                                                  .stream()
                                                  .map(PacketInfo::getFolder)
                                                  .collect(Collectors.toList());
        final Json json = new Json();
        json.setWriter(stringWriter);
        json.writeObjectStart();
        writeArray(json, "packets", null, dirs);
        json.writeObjectEnd();

        final FileHandle fileHandle = Gdx.files.external(PACKAGE_JSON_PACKETS_INFO);
        json.setOutputType(javascript);
        fileHandle.writeString(json.prettyPrint(stringWriter.toString()), false);
    }
}
