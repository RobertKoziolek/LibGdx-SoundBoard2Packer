package com.robcio.soundboard2.packer.gui.tab.content.packet;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.*;
import com.robcio.soundboard2.packer.entity.FilterInfo;
import com.robcio.soundboard2.packer.entity.FilterInfoHolder;
import com.robcio.soundboard2.packer.entity.SoundInfo;
import com.robcio.soundboard2.packer.gui.component.adapter.SoundFilterAdapter;
import com.robcio.soundboard2.packer.gui.component.menu.FilterMenuBar;
import com.robcio.soundboard2.packer.util.Command;
import com.robcio.soundboard2.packer.util.SoundCreator;
import com.robcio.soundboard2.packer.util.validator.NameValidator;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Set;

import static com.robcio.soundboard2.packer.util.Constants.LIST_VIEW_WIDTH;

public class SoundContent extends VisTable {

    private final SoundCreator soundCreator;
    private final FilterMenuBar filterMenuBar;
    private final FilterInfoHolder filterInfoHolder;

    @Inject
    public SoundContent(final SoundCreator soundCreator,
                        final FilterMenuBar filterMenuBar,
                        final FilterInfoHolder filterInfoHolder) {
        this.soundCreator = soundCreator;
        this.filterMenuBar = filterMenuBar;
        this.filterInfoHolder = filterInfoHolder;
        left();
    }

    public void update(final SoundInfo soundInfo,
                       final Command updateViewCommand) {
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
            }
        });
        add(nameField).growX()
                      .row();

        final Set<Integer> filtersId = soundInfo.getFiltersId();
        final ArrayList<FilterInfo> soundFilterInfos = filterInfoHolder.getFilterInfos(filtersId);
        final SoundFilterAdapter soundFilterAdapter = new SoundFilterAdapter(soundFilterInfos, filtersId);
        final ListView<FilterInfo> filterView = new ListView<>(soundFilterAdapter);
        //TODO touchdown listener z UIUtils.right() dla szybszego dodawania, middle na usuwanie moze?
        final VisTable filterHeader = new VisTable();

        filterMenuBar.update(soundFilterInfos, soundFilterAdapter::add, soundFilterAdapter::remove);
        filterHeader.add(filterMenuBar.getTable());
        filterHeader.add(new VisLabel("Filters:"));
        filterView.setHeader(filterHeader);
        filterView.getScrollPane()
                  .setScrollingDisabled(true, false);
        add(filterView.getMainTable()).growY()
                                      .width(LIST_VIEW_WIDTH);

    }
}
