package com.robcio.soundboard2.packer.file;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.robcio.soundboard2.packer.entity.FilterInfo;
import com.robcio.soundboard2.packer.entity.FilterInfoHolder;
import com.robcio.soundboard2.packer.entity.PacketInfo;
import com.robcio.soundboard2.packer.entity.PacketInfoHolder;
import com.robcio.soundboard2.packer.gui.component.PacketTabPane;
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
    //TODO sesion saver, keep some of the last sessions (with id) + file chooser?

    private final Provider<PacketTabPanel> packetTabPanelProvider;
    private final Provider<FileChooser> fileChooserProvider;
    private final Provider<PacketTabPane> packetTabPaneProvider;
    private final FilterInfoHolder filterInfoHolder;
    private final PacketInfoHolder packetInfoHolder;

    @Inject
    public SessionSaver(final Provider<PacketTabPanel> packetTabPanelProvider,
                        @Named("packetSounds") final Provider<FileChooser> fileChooserProvider,
                        final Provider<PacketTabPane> packetTabPaneProvider,
                        final FilterInfoHolder filterInfoHolder,
                        final PacketInfoHolder packetInfoHolder) {
        this.packetTabPanelProvider = packetTabPanelProvider;
        this.fileChooserProvider = fileChooserProvider;
        this.packetTabPaneProvider = packetTabPaneProvider;
        this.filterInfoHolder = filterInfoHolder;
        this.packetInfoHolder = packetInfoHolder;
    }

    public boolean save() {
        final FileHandle file = Gdx.files.external(SESSION_FILE);
        try (final ObjectOutputStream objectOutputStream = new ObjectOutputStream(file.write(false))) {
            objectOutputStream.writeObject(filterInfoHolder.getFilterInfos());
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
                final ArrayList<FilterInfo> filterInfos = (ArrayList<FilterInfo>) objectInputStream.readObject();
                filterInfoHolder.loadFilterInfos(filterInfos);
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
