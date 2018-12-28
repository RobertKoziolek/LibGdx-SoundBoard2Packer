package com.robcio.soundboard2.packer.file.session;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.robcio.soundboard2.packer.entity.attribute.FilterInfoHolder;
import com.robcio.soundboard2.packer.entity.attribute.SuiteInfoHolder;
import com.robcio.soundboard2.packer.entity.packet.PacketInfoHolder;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static com.robcio.soundboard2.packer.file.Constants.LAST_SESSION_FILE;

@Singleton
public class SessionSaver {

    private final FilterInfoHolder filterInfoHolder;
    private final SuiteInfoHolder suiteInfoHolder;
    private final PacketInfoHolder packetInfoHolder;

    @Inject
    public SessionSaver(final FilterInfoHolder filterInfoHolder,
                        final SuiteInfoHolder suiteInfoHolder,
                        final PacketInfoHolder packetInfoHolder) {
        this.filterInfoHolder = filterInfoHolder;
        this.suiteInfoHolder = suiteInfoHolder;
        this.packetInfoHolder = packetInfoHolder;
    }

    public boolean saveLastSession() {
        return save(Gdx.files.external(LAST_SESSION_FILE));
    }

    public boolean save(final FileHandle file) {
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
}
