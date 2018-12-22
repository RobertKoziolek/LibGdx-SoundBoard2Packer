package com.robcio.soundboard2.packer.file;

import com.robcio.soundboard2.packer.file.json.JsonWriterFacade;
import com.robcio.soundboard2.packer.file.sound.SoundWriter;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PackageWriter {

    private final JsonWriterFacade jsonWriterFacade;
    private final SoundWriter soundWriter;

    @Inject
    public PackageWriter(final JsonWriterFacade jsonWriterFacade, final SoundWriter soundWriter) {
        this.jsonWriterFacade = jsonWriterFacade;
        this.soundWriter = soundWriter;
    }

    public void write() {
        jsonWriterFacade.writeAll();
        soundWriter.write();
    }
}
