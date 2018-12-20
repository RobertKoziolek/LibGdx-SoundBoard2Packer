package com.robcio.soundboard2.packer.gui.tab.content.packet;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.*;
import com.robcio.soundboard2.packer.entity.FilterInfo;
import com.robcio.soundboard2.packer.entity.SoundInfo;
import com.robcio.soundboard2.packer.gui.component.adapter.SoundFilterAdapter;
import com.robcio.soundboard2.packer.gui.component.menu.FilterMenuBar;
import com.robcio.soundboard2.packer.util.Command;
import com.robcio.soundboard2.packer.util.SoundCreator;
import com.robcio.soundboard2.packer.util.validator.NameValidator;

import javax.inject.Inject;

import static com.robcio.soundboard2.packer.util.Constants.LIST_VIEW_WIDTH;

public class SoundContent extends VisTable {

    private final SoundCreator soundCreator;
    private final FilterMenuBar filterMenuBar;

    @Inject
    public SoundContent(final SoundCreator soundCreator, final FilterMenuBar filterMenuBar) {
        this.soundCreator = soundCreator;
        this.filterMenuBar = filterMenuBar;
        left();
        add("This is sound content");
    }

    public void update(final SoundInfo soundInfo,
                       final Command updateViewCommand,
                       final Command setDirtyCommand) {
        clear();
        final Sound sound = soundCreator.create(soundInfo.getFileHandle());
        final VisTextButton playButton = new VisTextButton("Play", new ChangeListener() {
            @Override
            public void changed(final ChangeEvent event, final Actor actor) {
                sound.stop();
                sound.play();
            }
        });
        add(playButton).colspan(2)
                       .growX()
                       .row();

        final VisValidatableTextField nameField = new VisValidatableTextField(new NameValidator());
        nameField.setText(soundInfo.getName());
        nameField.setTextFieldListener((textField, c) -> {
            if (nameField.isInputValid()) {
                soundInfo.setName(nameField.getText());
                updateViewCommand.perform();
                setDirtyCommand.perform();
            }
        });
        add(nameField).growX()
                      .row();

        final SoundFilterAdapter soundFilterAdapter = new SoundFilterAdapter(soundInfo.getFilters());
        final ListView<FilterInfo> filterView = new ListView<>(soundFilterAdapter);
        final VisTable filterHeader = new VisTable();

        filterMenuBar.update(soundInfo.getFilters(), soundFilterAdapter::add, soundFilterAdapter::remove);
        filterHeader.add(filterMenuBar.getTable());
        filterHeader.add(new VisLabel("Filters:"));
        filterView.setHeader(filterHeader);
        filterView.getScrollPane()
                  .setScrollingDisabled(true, false);
        add(filterView.getMainTable()).growY()
                                      .width(LIST_VIEW_WIDTH);

    }
}
