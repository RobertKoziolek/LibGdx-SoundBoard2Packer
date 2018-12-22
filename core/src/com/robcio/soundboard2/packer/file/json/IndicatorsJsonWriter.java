package com.robcio.soundboard2.packer.file.json;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.robcio.soundboard2.packer.entity.FilterInfo;
import com.robcio.soundboard2.packer.entity.FilterInfoHolder;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.StringWriter;
import java.util.ArrayList;

import static com.badlogic.gdx.utils.JsonWriter.OutputType.javascript;
import static com.robcio.soundboard2.packer.file.Constants.PACKAGE_JSON_INDICATORS;

@Singleton
class IndicatorsJsonWriter extends JsonWriter {

    private final FilterInfoHolder filterInfoHolder;

    @Inject
    public IndicatorsJsonWriter(final FilterInfoHolder filterInfoHolder) {
        this.filterInfoHolder = filterInfoHolder;
    }

    @Override
    public void write() {
        System.out.println("Saving indicator info");
        final StringWriter stringWriter = new StringWriter();

        final ArrayList<FilterInfo> filterInfos = filterInfoHolder.getFilterInfos();

        final Json json = new Json();
        json.setWriter(stringWriter);
        json.writeObjectStart();
        json.writeArrayStart("indicators");
        json.writeObjectStart();

        //TODO foreach and align property
        json.writeValue("name", "name");
        json.writeValue("file", "filename");
        json.writeValue("align", "ALIGN");

        json.writeObjectEnd();
        json.writeArrayEnd();
        json.writeObjectEnd();

        final FileHandle fileHandle = Gdx.files.external(PACKAGE_JSON_INDICATORS);
        json.setOutputType(javascript);
        fileHandle.writeString(json.prettyPrint(stringWriter.toString()), false);
    }
}
