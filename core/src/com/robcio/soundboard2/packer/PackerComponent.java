package com.robcio.soundboard2.packer;

import com.robcio.soundboard2.packer.gui.MainScreen;
import com.robcio.soundboard2.packer.gui.tab.PackageTab;
import com.robcio.soundboard2.packer.module.ChooserModule;
import com.robcio.soundboard2.packer.util.SoundCreator;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = ChooserModule.class)
public interface PackerComponent {
    MainScreen mainScreen();

    PackageTab packageTab();

    SoundCreator soundCreator();
}
