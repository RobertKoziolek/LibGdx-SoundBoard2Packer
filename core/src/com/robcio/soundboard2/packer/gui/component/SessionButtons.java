package com.robcio.soundboard2.packer.gui.component;

import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.robcio.soundboard2.packer.util.ButtonHelper;

import javax.inject.Inject;
import javax.inject.Named;

public class SessionButtons extends VisTable {

    @Inject
    public SessionButtons(@Named("openSession") final FileChooser openSessionChooser,
                          @Named("saveSession") final FileChooser saveSessionChooser) {
        final VisTextButton saveSessionButton = ButtonHelper.textButton("Open", () -> {
            add(openSessionChooser).grow();
            //TODO open filechooser in main workspace
        });
        final VisTextButton openSessionButton = ButtonHelper.textButton("Save", () -> {
            add(saveSessionChooser).grow();
        });
        add(new VisLabel("Session")).growX()
                                    .row();
        add(saveSessionButton);
        add(openSessionButton);
    }
}
