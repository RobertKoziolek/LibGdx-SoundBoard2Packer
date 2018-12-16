package com.robcio.soundboard2.packer.file;

import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.kotcrab.vis.ui.widget.file.FileChooserListener;
import com.robcio.soundboard2.packer.util.Filter;
import lombok.NonNull;

public class Chooser {

    private static final FileChooser MP3_FILE_CHOOSER = new FileChooser(FileChooser.Mode.OPEN);

    static {
        FileChooser.setDefaultPrefsName("com.robcio.soundboard2.packer.chooser");
        MP3_FILE_CHOOSER.setMultiSelectionEnabled(true);
        MP3_FILE_CHOOSER.setFileTypeFilter(Filter.MP3_FILE_FILTER);
        MP3_FILE_CHOOSER.setSelectionMode(FileChooser.SelectionMode.FILES);
    }

    //TODO if used for more than one thing needs remodelling
    public static FileChooser getMp3FileChooser(@NonNull final FileChooserListener listener) {
        MP3_FILE_CHOOSER.setListener(listener);
        return MP3_FILE_CHOOSER;
    }
}
