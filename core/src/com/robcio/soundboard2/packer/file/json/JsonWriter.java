package com.robcio.soundboard2.packer.file.json;

import com.badlogic.gdx.utils.Json;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

abstract class JsonWriter {

    public abstract void write();

    void writeArray(final Json json, final String name, final String first, final List<String> values) {
        json.writeArrayStart(name);
        final com.badlogic.gdx.utils.JsonWriter writer = json.getWriter();
        if (!Objects.isNull(first)) {
            writeValue(writer, first);
        }
        values.forEach(value -> writeValue(writer, value));
        json.writeArrayEnd();
    }

    private void writeValue(final com.badlogic.gdx.utils.JsonWriter writer, final String value) {
        try {
            writer.value(value);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
