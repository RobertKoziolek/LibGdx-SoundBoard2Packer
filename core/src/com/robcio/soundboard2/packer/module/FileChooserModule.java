package com.robcio.soundboard2.packer.module;

import com.kotcrab.vis.ui.widget.file.FileChooser;
import dagger.Module;
import dagger.Provides;

@Module
public class FileChooserModule {

    static {
        FileChooser.setDefaultPrefsName("com.robcio.soundboard2.packer.chooser");
        FileChooser.setSaveLastDirectory(true);
    }

    @Provides
    public FileChooser provideFileChooser() {
        final FileChooser fileChooser = new FileChooser(FileChooser.Mode.OPEN);
        fileChooser.setSelectionMode(FileChooser.SelectionMode.DIRECTORIES);
        fileChooser.setKeepWithinParent(true);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setResizable(false);
        fileChooser.setModal(false);
        return fileChooser;
    }
}
