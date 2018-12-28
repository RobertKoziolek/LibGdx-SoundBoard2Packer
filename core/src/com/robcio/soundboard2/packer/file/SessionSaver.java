package com.robcio.soundboard2.packer.file;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.robcio.soundboard2.packer.entity.attribute.Attribute;
import com.robcio.soundboard2.packer.entity.attribute.FilterInfoHolder;
import com.robcio.soundboard2.packer.entity.attribute.SuiteInfoHolder;
import com.robcio.soundboard2.packer.entity.packet.PacketInfo;
import com.robcio.soundboard2.packer.entity.packet.PacketInfoHolder;
import com.robcio.soundboard2.packer.gui.component.PacketAndSoundDetailPane;
import com.robcio.soundboard2.packer.gui.component.PacketTabPanel;
import com.robcio.soundboard2.packer.gui.tab.PacketTab;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static com.robcio.soundboard2.packer.file.Constants.SESSION_FILE;

@Singleton
public class SessionSaver {
    //TODO keep some of the last sessions (with id) + file chooser? basically project files

    private final Provider<PacketTabPanel> packetTabPanelProvider;
    private final Provider<FileChooser> fileChooserProvider;
    private final Provider<PacketAndSoundDetailPane> packetTabPaneProvider;
    private final FilterInfoHolder filterInfoHolder;
    private final SuiteInfoHolder suiteInfoHolder;
    private final PacketInfoHolder packetInfoHolder;

    @Inject
    public SessionSaver(final Provider<PacketTabPanel> packetTabPanelProvider,
                        @Named("packetSounds") final Provider<FileChooser> fileChooserProvider,
                        final Provider<PacketAndSoundDetailPane> packetTabPaneProvider,
                        final FilterInfoHolder filterInfoHolder,
                        final SuiteInfoHolder suiteInfoHolder,
                        final PacketInfoHolder packetInfoHolder) {
        this.packetTabPanelProvider = packetTabPanelProvider;
        this.fileChooserProvider = fileChooserProvider;
        this.packetTabPaneProvider = packetTabPaneProvider;
        this.filterInfoHolder = filterInfoHolder;
        this.suiteInfoHolder = suiteInfoHolder;
        this.packetInfoHolder = packetInfoHolder;
    }

    public boolean save() {
        final FileHandle file = Gdx.files.external(SESSION_FILE);
        try (final ObjectOutputStream objectOutputStream = new ObjectOutputStream(file.write(false))) {
            objectOutputStream.writeObject(filterInfoHolder.getAttributes());
            objectOutputStream.writeObject(suiteInfoHolder.getAttributes());
            objectOutputStream.writeObject(packetInfoHolder.getPacketInfos());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void loadTabs() {
        final FileHandle file = Gdx.files.external(SESSION_FILE);
        try (final ObjectInputStream objectInputStream = new ObjectInputStream(file.read())) {
            {
                final ArrayList<Attribute> attributes = (ArrayList<Attribute>) objectInputStream.readObject();
                filterInfoHolder.loadAttributes(attributes);
            }
            {
                final ArrayList<Attribute> attributes = (ArrayList<Attribute>) objectInputStream.readObject();
                suiteInfoHolder.loadAttributes(attributes);
            }
            {
                final ArrayList<PacketInfo> packetInfos = (ArrayList<PacketInfo>) objectInputStream.readObject();
                packetInfoHolder.loadPacketInfos(packetInfos);
                packetInfoHolder.getPacketInfos()
                                .forEach(packetInfo -> {
                                    final Tab packetTab = new PacketTab(packetInfo,
                                                                        packetTabPaneProvider.get(),
                                                                        fileChooserProvider.get());
                                    packetTabPanelProvider.get()
                                                          .add(packetTab);
                                });
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
