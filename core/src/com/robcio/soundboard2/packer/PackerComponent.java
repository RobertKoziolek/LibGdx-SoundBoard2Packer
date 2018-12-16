package com.robcio.soundboard2.packer;

import com.robcio.soundboard2.packer.file.ChooserModule;
import com.robcio.soundboard2.packer.gui.MainScreen;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = ChooserModule.class)
interface PackerComponent {
    MainScreen mainScreen();
}
