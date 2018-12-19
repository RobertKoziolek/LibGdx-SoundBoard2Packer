package com.robcio.soundboard2.packer.gui.tab.content.packet;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.*;
import com.robcio.soundboard2.packer.entity.FilterHolder;
import com.robcio.soundboard2.packer.entity.FilterInfo;
import com.robcio.soundboard2.packer.entity.SoundInfo;
import com.robcio.soundboard2.packer.gui.component.adapter.SoundFilterAdapter;
import com.robcio.soundboard2.packer.util.Command;
import com.robcio.soundboard2.packer.util.SoundCreator;
import com.robcio.soundboard2.packer.util.validator.NameValidator;

import javax.inject.Inject;

import static com.robcio.soundboard2.packer.util.Constants.LIST_VIEW_WIDTH;

public class SoundContent extends VisTable {

    private final SoundCreator soundCreator;
    private final FilterHolder filterHolder;

    @Inject
    public SoundContent(final SoundCreator soundCreator, final FilterHolder filterHolder) {
        this.soundCreator = soundCreator;
        this.filterHolder = filterHolder;
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

        //TODO popmenu to add filters from singleton holder
        final SoundFilterAdapter soundFilterAdapter = new SoundFilterAdapter(soundInfo.getFilters());
        final ListView<FilterInfo> filterView = new ListView<>(soundFilterAdapter);
        final VisTable filterHeader = new VisTable();
        filterHeader.add(new VisLabel("Filters:"));
        final Menu addMenu = new Menu("+");
//        final Menu removeMenu = new Menu("-");//TODO remove filter menu
        final MenuBar menuBar = new MenuBar();
        menuBar.addMenu(addMenu);

        filterHolder.getFilterInfos()
                    .forEach(filterInfo -> {
                        final MenuItem menuItem = new MenuItem(filterInfo.getName(), new ChangeListener() {
                            @Override
                            public void changed(ChangeEvent event, Actor actor) {
                                soundFilterAdapter.add(filterInfo);
                            }
                        });
                        addMenu.addItem(menuItem);
                    });

        filterHeader.add(menuBar.getTable());
        filterView.setHeader(filterHeader);
        filterView.getScrollPane()
                  .setScrollingDisabled(true, false);
        add(filterView.getMainTable()).growY()
                                      .width(LIST_VIEW_WIDTH);

    }
}
