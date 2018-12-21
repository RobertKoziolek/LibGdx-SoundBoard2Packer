package com.robcio.soundboard2.packer;

import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.robcio.soundboard2.packer.gui.MainScreen;
import com.robcio.soundboard2.packer.gui.component.PacketTabPane;
import com.robcio.soundboard2.packer.gui.component.PacketTabPanel;
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

    FileChooser fileChooser();

    PacketTabPane packetTabPane();

    PacketTabPanel tabbedPanel();
}
