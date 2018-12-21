package com.robcio.soundboard2.packer.file;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.robcio.soundboard2.packer.PackerComponent;
import com.robcio.soundboard2.packer.entity.FilterInfo;
import com.robcio.soundboard2.packer.entity.FilterInfoHolder;
import com.robcio.soundboard2.packer.entity.PacketInfoHolder;
import com.robcio.soundboard2.packer.gui.tab.PacketTab;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

@Singleton
public class StateSaver {

    private final PackerComponent packerComponent;
    private final FilterInfoHolder filterInfoHolder;
    private final PacketInfoHolder packetInfoHolder;

    @Inject
    public StateSaver(final PackerComponent packerComponent,
                      final FilterInfoHolder filterInfoHolder,
                      final PacketInfoHolder packetInfoHolder) {
        this.packerComponent = packerComponent;
        this.filterInfoHolder = filterInfoHolder;
        this.packetInfoHolder = packetInfoHolder;
    }

    public boolean save() {
        final FileHandle file = Gdx.files.external("packettabstate.tab");
        try (final ObjectOutputStream objectOutputStream = new ObjectOutputStream(file.write(false))) {
            objectOutputStream.writeObject(filterInfoHolder.getFilterInfos());

            objectOutputStream.writeObject(packetInfoHolder);
            objectOutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    //TODO multiple tabs loading/saving

    //TODO should close all opened tabs upon loading to prevent error with filters and double tabs
    public void loadTabs() {
        final FileHandle file = Gdx.files.external("packettabstate.tab");
        try (final ObjectInputStream objectInputStream = new ObjectInputStream(file.read())) {
            {
                final ArrayList<FilterInfo> filterInfos = (ArrayList<FilterInfo>) objectInputStream.readObject();
                filterInfoHolder.loadFilterInfos(filterInfos);
            }
            {
                final PacketInfoHolder packetInfoHolder = (PacketInfoHolder) objectInputStream.readObject();
                packetInfoHolder.getPacketInfos()
                                .forEach(packetInfo -> {
                                    final Tab packetTab = new PacketTab(this,
                                                                        packetInfo,
                                                                        packerComponent.packetTabPane(),
                                                                        packerComponent.fileChooser());
                                    packerComponent.tabbedPanel()
                                                   .add(packetTab);
                                });
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}