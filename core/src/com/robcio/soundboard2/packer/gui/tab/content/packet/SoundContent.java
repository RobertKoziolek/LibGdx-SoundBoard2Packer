package com.robcio.soundboard2.packer.gui.tab.content.packet;

import com.badlogic.gdx.audio.Sound;
import com.kotcrab.vis.ui.widget.VisSplitPane;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisValidatableTextField;
import com.robcio.soundboard2.packer.entity.SoundInfo;
import com.robcio.soundboard2.packer.gui.tab.content.packet.sound.FilterContent;
import com.robcio.soundboard2.packer.util.ButtonHelper;
import com.robcio.soundboard2.packer.util.Command;
import com.robcio.soundboard2.packer.util.SoundCreator;
import com.robcio.soundboard2.packer.util.validator.NameValidator;

import javax.inject.Inject;

public class SoundContent extends VisTable {

    private final SoundCreator soundCreator;
    private final FilterContent filterContent;

    @Inject
    public SoundContent(final SoundCreator soundCreator,
                        final FilterContent filterContent) {
        this.soundCreator = soundCreator;
        this.filterContent = filterContent;
        left();
    }

    public void update(final SoundInfo soundInfo, final Command updateViewCommand) {
        final Sound sound = soundCreator.create(soundInfo.getFileHandle());
        final VisTextButton playButton = ButtonHelper.textButton("Play", () -> {
            sound.stop();
            sound.play();
        });
        final VisValidatableTextField nameField = new VisValidatableTextField(new NameValidator());
        nameField.setText(soundInfo.getName());
        nameField.setTextFieldListener((textField, c) -> {
            if (nameField.isInputValid()) {
                soundInfo.setName(nameField.getText());
                updateViewCommand.perform();
            }
        });

        filterContent.update(soundInfo);
        final VisSplitPane splitPane = new VisSplitPane(filterContent, null, false);

        clear();
        add(playButton).colspan(2)
                       .growX()
                       .row();
        addSeparator();
        add(nameField).growX()
                      .row();
        addSeparator();
        add(splitPane).grow();
    }
}
