package com.robcio.soundboard2.packer.file;

import com.badlogic.gdx.utils.Json;
import com.robcio.soundboard2.packer.entity.PacketInfo;
import com.robcio.soundboard2.packer.entity.SoundInfoHolder;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.StringWriter;

@Singleton
public class JsonWriter {

    @Inject
    public JsonWriter() {

    }

    public void savePacket(final PacketInfo packetInfo, final SoundInfoHolder soundInfoHolder) {
        System.out.println("Saving " + packetInfo.getName());
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
                           //TODO writing filters to json
                           json.writeObjectEnd();
                       });
        json.writeArrayEnd();
        json.writeObjectEnd();

        System.out.println(json.prettyPrint(stringWriter.toString()));
    }
}
