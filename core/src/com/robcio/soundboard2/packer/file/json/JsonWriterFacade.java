package com.robcio.soundboard2.packer.file.json;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
//TODO zipped
public class JsonWriterFacade {


    private final AllPacketsInfoJsonWriter allPacketsInfoJsonWriter;
    private final IndicatorsJsonWriter indicatorsJsonWriter;
    private final PacketJsonWriter packetJsonWriter;

    @Inject
    public JsonWriterFacade(final AllPacketsInfoJsonWriter allPacketsInfoJsonWriter,
                            final IndicatorsJsonWriter indicatorsJsonWriter,
                            final PacketJsonWriter packetJsonWriter) {
        this.allPacketsInfoJsonWriter = allPacketsInfoJsonWriter;
        this.indicatorsJsonWriter = indicatorsJsonWriter;
        this.packetJsonWriter = packetJsonWriter;
    }

    public void writeAll() {
        allPacketsInfoJsonWriter.write();
        indicatorsJsonWriter.write();
        packetJsonWriter.write();
    }
}
