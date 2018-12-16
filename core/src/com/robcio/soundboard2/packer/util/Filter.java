package com.robcio.soundboard2.packer.util;

import com.kotcrab.vis.ui.widget.file.FileTypeFilter;

public class Filter {
    public static final FileTypeFilter MP3_FILE_FILTER = new FileTypeFilter(false);

    static {
        MP3_FILE_FILTER.addRule("Mp3 files (*.mp3)", "mp3");
    }
}
