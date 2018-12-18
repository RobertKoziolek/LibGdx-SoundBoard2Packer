package com.robcio.soundboard2.packer;

import com.robcio.soundboard2.packer.gui.MainScreen;
import com.robcio.soundboard2.packer.gui.tab.PacketTab;
import com.robcio.soundboard2.packer.module.FileChooserModule;
import com.robcio.soundboard2.packer.util.SoundCreator;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = FileChooserModule.class)
public interface PackerComponent {
    MainScreen mainScreen();

    PacketTab packetTab();

    SoundCreator soundCreator();
}
