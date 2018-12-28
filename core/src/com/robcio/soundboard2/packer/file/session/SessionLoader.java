package com.robcio.soundboard2.packer.file.session;

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
import com.robcio.soundboard2.packer.gui.tab.content.main.FilterContent;
import com.robcio.soundboard2.packer.gui.tab.content.main.SuiteContent;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static com.robcio.soundboard2.packer.file.Constants.LAST_SESSION_FILE;

@Singleton
public class SessionLoader {

    private final Provider<PacketTabPanel> packetTabPanelProvider;
    private final Provider<FileChooser> fileChooserProvider;
    private final Provider<PacketAndSoundDetailPane> packetTabPaneProvider;
    private final FilterInfoHolder filterInfoHolder;
    private final SuiteInfoHolder suiteInfoHolder;
    private final PacketInfoHolder packetInfoHolder;
    private final PacketTabPanel tabbedPanel;
    private final FilterContent filterContent;
    private final SuiteContent suiteContent;

    @Inject
    public SessionLoader(final Provider<PacketTabPanel> packetTabPanelProvider,
                         @Named("packetSounds") final Provider<FileChooser> fileChooserProvider,
                         final Provider<PacketAndSoundDetailPane> packetTabPaneProvider,
                         final FilterInfoHolder filterInfoHolder,
                         final SuiteInfoHolder suiteInfoHolder,
                         final PacketInfoHolder packetInfoHolder,
                         final PacketTabPanel tabbedPanel,
                         final FilterContent filterContent,
                         final SuiteContent suiteContent) {
        this.packetTabPanelProvider = packetTabPanelProvider;
        this.fileChooserProvider = fileChooserProvider;
        this.packetTabPaneProvider = packetTabPaneProvider;
        this.filterInfoHolder = filterInfoHolder;
        this.suiteInfoHolder = suiteInfoHolder;
        this.packetInfoHolder = packetInfoHolder;
        this.tabbedPanel = tabbedPanel;
        this.filterContent = filterContent;
        this.suiteContent = suiteContent;
    }

    public void loadLastSession() {
        load(Gdx.files.external(LAST_SESSION_FILE));
    }

    public void load(final FileHandle file) {
        tabbedPanel.closeCloseableTabs();
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
            filterContent.rebuild();
            suiteContent.rebuild();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
