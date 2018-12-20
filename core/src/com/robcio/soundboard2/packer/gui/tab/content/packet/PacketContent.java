package com.robcio.soundboard2.packer.gui.tab.content.packet;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisValidatableTextField;
import com.robcio.soundboard2.packer.entity.PacketInfo;
import com.robcio.soundboard2.packer.entity.SoundInfoHolder;
import com.robcio.soundboard2.packer.file.PacketJsonWriter;
import com.robcio.soundboard2.packer.util.Command;
import com.robcio.soundboard2.packer.util.validator.NameValidator;

import javax.inject.Inject;
import java.util.function.Consumer;

public class PacketContent extends VisTable {

    private final PacketJsonWriter packetJsonWriter;

    @Inject
    public PacketContent(final PacketJsonWriter packetJsonWriter) {
        this.packetJsonWriter = packetJsonWriter;
        top();
        add("This is packet info");
    }

    public void update(final PacketInfo packetInfo,
                       final SoundInfoHolder soundInfoHolder,
                       final Command saveCommand,
                       final Consumer<String> updateTitleCommand) {
        clear();
        final VisValidatableTextField nameField = new VisValidatableTextField(new NameValidator());
        nameField.setText(packetInfo.getName());
        nameField.setTextFieldListener((textField, c) -> {
            if (nameField.isInputValid()) {
                updateTitleCommand.accept(nameField.getText());
            }
        });
        final VisTextButton saveButton = new VisTextButton("Save packet", new ChangeListener() {
            @Override
            public void changed(final ChangeEvent event, final Actor actor) {
                saveCommand.perform();
            }
        });
        final VisTextButton writerButton = new VisTextButton("Save to file", new ChangeListener() {
            @Override
            public void changed(final ChangeEvent event, final Actor actor) {
                packetJsonWriter.savePacket(packetInfo, soundInfoHolder);
            }
        });
        add(nameField).growX()
                      .row();
        add(saveButton).colspan(2)
                       .row();
        add(writerButton).colspan(2)
                         .row();
    }
}
