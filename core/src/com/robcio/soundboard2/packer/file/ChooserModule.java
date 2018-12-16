package com.robcio.soundboard2.packer.file;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.kotcrab.vis.ui.widget.file.FileChooserAdapter;
import com.kotcrab.vis.ui.widget.file.FileChooserListener;
import com.robcio.soundboard2.packer.entity.SoundHolder;
import com.robcio.soundboard2.packer.util.Filter;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ChooserModule {

    static {
        FileChooser.setDefaultPrefsName("com.robcio.soundboard2.packer.chooser");
    }

    @Provides
    @Singleton
    public FileChooser provideFileChooser(final FileChooserListener listener) {
        final FileChooser fileChooser = new FileChooser(FileChooser.Mode.OPEN);
        fileChooser.setListener(listener);
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileTypeFilter(Filter.MP3_FILE_FILTER);
        fileChooser.setSelectionMode(FileChooser.SelectionMode.FILES);
        return fileChooser;
    }


    @Provides
    @Singleton
    public FileChooserListener provideListener(final SoundHolder soundHolder) {
        return new FileChooserAdapter() {
            @Override
            public void selected(final Array<FileHandle> files) {
                files.forEach(soundHolder::add);
            }
        };
    }
}
