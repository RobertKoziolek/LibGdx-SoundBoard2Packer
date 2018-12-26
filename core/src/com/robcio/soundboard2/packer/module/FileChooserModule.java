package com.robcio.soundboard2.packer.module;

import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.kotcrab.vis.ui.widget.file.FileTypeFilter;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;

@Module
public class FileChooserModule {

    static {
        FileChooser.setDefaultPrefsName("com.robcio.soundboard2.packer.chooser");
        FileChooser.setSaveLastDirectory(true);
    }

    @Provides
    @Named("packetSounds")
    //TODO file chooser for image and packet for reading later, will need @qualifier
    public FileChooser providePacketSoundsFileChooser() {
        final FileChooser fileChooser = new FileChooser(FileChooser.Mode.OPEN);
        fileChooser.setSelectionMode(FileChooser.SelectionMode.DIRECTORIES);
        fileChooser.setKeepWithinParent(true);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setResizable(false);
        fileChooser.setModal(false);
        return fileChooser;
    }

    @Provides
    @Named("image")
    //TODO file chooser for image and packet for reading later, will need @qualifier
    public FileChooser provideFileChooser() {
        final FileChooser fileChooser = new FileChooser(FileChooser.Mode.OPEN);
        fileChooser.setSelectionMode(FileChooser.SelectionMode.FILES);
        fileChooser.setFileTypeFilter(getImageFilter());
        fileChooser.setKeepWithinParent(true);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setResizable(false);
        fileChooser.setModal(false);
        return fileChooser;
    }

    private FileTypeFilter getImageFilter() {
        final FileTypeFilter fileTypeFilter = new FileTypeFilter(false);
        fileTypeFilter.addRule("Filter image (*.png, *.jpg)", ".png", ".jpg");
        return fileTypeFilter;
    }
}
