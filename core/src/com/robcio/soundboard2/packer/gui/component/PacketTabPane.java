package com.robcio.soundboard2.packer.gui.component;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisSplitPane;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisValidatableTextField;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.robcio.soundboard2.packer.entity.SoundInfo;
import com.robcio.soundboard2.packer.util.Command;
import com.robcio.soundboard2.packer.util.SoundCreator;
import com.robcio.soundboard2.packer.util.validator.NameValidator;

import javax.inject.Inject;

public class PacketTabPane extends VisSplitPane {

    private final VisTable packetContent, soundContent;
    private final SoundCreator soundCreator;

    @Inject
    public PacketTabPane(final SoundCreator soundCreator) {
        super(null, null, true);
        this.soundCreator = soundCreator;
        this.packetContent = new VisTable();
        this.soundContent = new VisTable();
        setWidgets(packetContent, soundContent);
        setSplitAmount(0.3f);

        packetContent.top();
        packetContent.add("This is packet info");


        soundContent.top();
        soundContent.add("This is sound content");
    }

    public void updateSoundContent(final SoundInfo soundInfo, final Command updateViewCommand) {
        soundContent.clear();
        final Sound sound = soundCreator.create(soundInfo.getFileHandle());
        final VisTextButton playButton = new VisTextButton("Play", new ChangeListener() {
            @Override
            public void changed(final ChangeEvent event, final Actor actor) {
                sound.stop();
                sound.play();
            }
        });
        soundContent.add(playButton)
                    .growX()
                    .row();

        soundContent.add("The sound in question is " + soundInfo.getName())
                    .row();

        final VisValidatableTextField nameField = new VisValidatableTextField(new NameValidator());
        soundContent.add(nameField)
                    .row();
        final VisTextButton nameChangeButton = new VisTextButton("Change", new ChangeListener() {
            @Override
            public void changed(final ChangeEvent event, final Actor actor) {
                if (nameField.isInputValid()) {
                    soundInfo.setName(nameField.getText());
                }
                updateViewCommand.perform();
            }
        });
        soundContent.add(nameChangeButton)
                    .row();


    }

    public void openFiles(final FileChooser fileChooser) {
        soundContent.clear();
        soundContent.add(fileChooser)
                    .grow();
    }
}
