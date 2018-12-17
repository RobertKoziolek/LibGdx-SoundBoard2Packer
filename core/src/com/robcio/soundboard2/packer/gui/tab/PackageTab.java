package com.robcio.soundboard2.packer.gui.tab;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.ListView;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.kotcrab.vis.ui.widget.file.FileChooserAdapter;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.robcio.soundboard2.packer.gui.component.FileHandleAdapter;
import com.robcio.soundboard2.packer.util.SoundCreator;

import javax.inject.Inject;

import static com.robcio.soundboard2.packer.util.Constants.LIST_VIEW_WIDTH;

public class PackageTab extends Tab {
    private final VisTable content = new VisTable();

    @Inject
    public PackageTab(final FileChooser fileChooser,
                      final FileHandleAdapter fileHandleAdapter,
                      final SoundCreator soundCreator) {
        super(true, true);
        final ListView<FileHandle> listView = new ListView<>(fileHandleAdapter);
        content.left();
        content.add(listView.getMainTable())
               .growY()
               .width(LIST_VIEW_WIDTH);
        content.add(new VisLabel("This is a package tab"));

        {
            listView.setItemClickListener(fileHandle -> {
                final Sound sound = soundCreator.create(fileHandle);
                sound.play();
            });
        }

        {
            final VisTextButton fileButton = new VisTextButton("Clear & open mp3 files", new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    content.add(fileChooser);
                }
            });
            fileChooser.setListener(new FileChooserAdapter() {
                @Override
                public void selected(final Array<FileHandle> files) {
                    fileHandleAdapter.clear();
                    files.forEach(fileHandleAdapter::add);
                    setDirty(true);
                }
            });
            listView.setHeader(fileButton);
            listView.getScrollPane()
                    .setScrollingDisabled(true, false);
            //TODO /\ adding new things scrolls it to the right
        }
    }

    @Override
    public String getTabTitle() {
        return "Package";
    }

    @Override
    public Table getContentTable() {
        return content;

    }
}