package com.robcio.soundboard2.packer.module;

import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.robcio.soundboard2.packer.util.Filter;
import dagger.Module;
import dagger.Provides;

@Module
public class ChooserModule {

    static {
        FileChooser.setDefaultPrefsName("com.robcio.soundboard2.packer.chooser");
    }

    @Provides
    public FileChooser provideFileChooser() {
        final FileChooser fileChooser = new FileChooser(FileChooser.Mode.OPEN);
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileTypeFilter(Filter.MP3_FILE_FILTER);
        fileChooser.setSelectionMode(FileChooser.SelectionMode.FILES);
        return fileChooser;
    }
}
