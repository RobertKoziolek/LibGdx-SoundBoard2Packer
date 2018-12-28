package com.robcio.soundboard2.packer.file.json;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.robcio.soundboard2.packer.entity.attribute.FilterInfoHolder;
import com.robcio.soundboard2.packer.entity.attribute.ImageFilterInfo;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

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

        final List<ImageFilterInfo> indicators = filterInfoHolder.getAttributes()
                                                                 .stream()
                                                                 .filter(f -> f instanceof ImageFilterInfo)
                                                                 .map(f -> (ImageFilterInfo) f)
                                                                 .collect(Collectors.toList());

        final Json json = new Json();
        json.setWriter(stringWriter);
        json.writeObjectStart();
        json.writeArrayStart("indicators");
        json.writeObjectStart();
        indicators.forEach(imageFilterInfo -> {
            json.writeValue("name", imageFilterInfo.getName());
            json.writeValue("file", imageFilterInfo.getImage()
                                                   .name());
            json.writeValue("align", imageFilterInfo.getAlign());
        });
        json.writeObjectEnd();
        json.writeArrayEnd();
        json.writeObjectEnd();

        final FileHandle fileHandle = Gdx.files.external(PACKAGE_JSON_INDICATORS);
        json.setOutputType(javascript);
        fileHandle.writeString(json.prettyPrint(stringWriter.toString()), false);
    }
}
