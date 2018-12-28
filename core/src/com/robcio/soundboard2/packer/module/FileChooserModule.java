package com.robcio.soundboard2.packer.module;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.kotcrab.vis.ui.widget.file.FileChooserListener;
import com.kotcrab.vis.ui.widget.file.FileTypeFilter;
import com.robcio.soundboard2.packer.file.session.SessionLoader;
import com.robcio.soundboard2.packer.file.session.SessionSaver;
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
    public FileChooser provideImageFileChooser() {
        final FileChooser fileChooser = new FileChooser(FileChooser.Mode.OPEN);
        fileChooser.setSelectionMode(FileChooser.SelectionMode.FILES);
        fileChooser.setFileTypeFilter(getImageFilter());
        fileChooser.setKeepWithinParent(true);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setResizable(false);
        fileChooser.setModal(false);
        return fileChooser;
    }

    @Provides
    @Named("openSession")
    public FileChooser provideOpenSessionFileChooser(final SessionLoader sessionLoader) {
        final FileChooser fileChooser = new FileChooser(FileChooser.Mode.OPEN);
        fileChooser.setSelectionMode(FileChooser.SelectionMode.FILES);
        fileChooser.setFileTypeFilter(getSessionFileFilter());
        fileChooser.setKeepWithinParent(false);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setResizable(false);
        fileChooser.setModal(true);
        fileChooser.setListener(new FileChooserListener() {
            @Override
            public void selected(final Array<FileHandle> files) {
                sessionLoader.load(files.first());
            }

            @Override
            public void canceled() {

            }
        });
        return fileChooser;
    }

    @Provides
    @Named("saveSession")
    public FileChooser provideSaveSessionFileChooser(final SessionSaver sessionSaver) {
        final FileChooser fileChooser = new FileChooser(FileChooser.Mode.SAVE);
        fileChooser.setSelectionMode(FileChooser.SelectionMode.FILES);
        fileChooser.setFileTypeFilter(getSessionFileFilter());
        fileChooser.setKeepWithinParent(false);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setResizable(false);
        fileChooser.setModal(true);
        fileChooser.setListener(new FileChooserListener() {
            @Override
            public void selected(final Array<FileHandle> files) {
                final boolean saved = sessionSaver.save(files.first());
                if (!saved) {
                    System.out.println("Could not save session");
                }
            }

            @Override
            public void canceled() {

            }
        });
        return fileChooser;
    }

    private FileTypeFilter getSessionFileFilter() {
        return getFilter("Session file (*.sb2s)", ".sb2s");
    }

    private FileTypeFilter getImageFilter() {
        return getFilter("Filter image (*.png, *.jpg)", ".png", ".jpg");
    }

    private FileTypeFilter getFilter(final String description, final String... extensions) {
        final FileTypeFilter fileTypeFilter = new FileTypeFilter(false);
        fileTypeFilter.addRule(description, extensions);
        return fileTypeFilter;
    }
}
