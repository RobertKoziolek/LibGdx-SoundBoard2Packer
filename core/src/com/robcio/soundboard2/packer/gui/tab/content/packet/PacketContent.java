package com.robcio.soundboard2.packer.gui.tab.content.packet;

import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisValidatableTextField;
import com.robcio.soundboard2.packer.entity.PacketInfo;
import com.robcio.soundboard2.packer.util.ButtonHelper;
import com.robcio.soundboard2.packer.util.Command;
import com.robcio.soundboard2.packer.util.validator.NameValidator;

import javax.inject.Inject;
import java.util.function.Consumer;

public class PacketContent extends VisTable {

    @Inject
    public PacketContent() {
        top();
    }

    public void update(final PacketInfo packetInfo,
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
        final VisTextButton saveButton = ButtonHelper.textButton("Save packet", saveCommand);
        add(nameField).growX()
                      .row();
        add(saveButton).colspan(2)
                       .row();
    }
}
