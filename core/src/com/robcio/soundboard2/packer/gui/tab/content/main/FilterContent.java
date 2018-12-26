package com.robcio.soundboard2.packer.gui.tab.content.main;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.ListView;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.kotcrab.vis.ui.widget.file.FileChooserListener;
import com.robcio.soundboard2.packer.entity.FilterInfo;
import com.robcio.soundboard2.packer.entity.FilterInfoHolder;
import com.robcio.soundboard2.packer.entity.ImageFilterInfo;
import com.robcio.soundboard2.packer.gui.component.FilterEditor;
import com.robcio.soundboard2.packer.gui.component.adapter.FilterAdapter;
import com.robcio.soundboard2.packer.util.KeyboardFocuser;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

import static com.robcio.soundboard2.packer.util.Constants.FILTER_VIEW_WIDTH;

@Singleton
public class FilterContent extends VisTable {

    private final FilterAdapter filterAdapter;

    @Inject
    public FilterContent(final FilterAdapter filterAdapter,
                         final KeyboardFocuser keyboardFocuser,
                         final FilterInfoHolder filterInfoHolder,
                         @Named("image") final Provider<FileChooser> fileChooserProvider) {
        this.filterAdapter = filterAdapter;
        final VisTable filterControlTable = new VisTable();
        final ListView<FilterInfo> filterView = new ListView<>(filterAdapter);
        filterView.setHeader(new VisLabel("Filters:"));
        final FilterEditor filterEditor = new FilterEditor(filterAdapter::itemsDataChanged,
                                                           keyboardFocuser::focus);
        filterAdapter.setItemClickListener(filterEditor::setFilterInfo);
        final VisTextButton addFilterButton = new VisTextButton("New filter", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setEditorFilter(filterEditor, new FilterInfo(filterInfoHolder.getFilterInfos()
                                                                             .size()));
            }
        });
        final VisTextButton addImageFilterButton = new VisTextButton("Image filter", new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                final FileChooser fileChooser = fileChooserProvider.get();
                fileChooser.setListener(new FileChooserListener() {
                    @Override
                    public void selected(final Array<FileHandle> files) {
                        final FileHandle fileHandle = files.get(0);
                        setEditorFilter(filterEditor, new ImageFilterInfo(filterInfoHolder.getFilterInfos()
                                                                                          .size(), fileHandle));
                        fillContent(filterView, filterControlTable);
                    }

                    @Override
                    public void canceled() {
                        fillContent(filterView, filterControlTable);
                    }
                });
                openFileChooser(fileChooser);
            }
        });
        filterControlTable.add(filterEditor)
                          .growX()
                          .row();
        filterControlTable.add(addFilterButton)
                          .growX()
                          .row();
        filterControlTable.add(addImageFilterButton)
                          .growX()
                          .row();
        fillContent(filterView, filterControlTable);
    }

    //TODO separate filter editor, image viewing, choosing and list depending on space in main tab
    private void openFileChooser(final FileChooser fileChooser) {
        clear();
        add(fileChooser);
    }

    private void fillContent(final ListView filterView, final VisTable filterControlTable) {
        clear();
        add(filterView.getMainTable()).width(FILTER_VIEW_WIDTH)
                                      .row();
        add(filterControlTable).growX();
    }

    private void setEditorFilter(final FilterEditor filterEditor, final FilterInfo filterInfo) {
        filterAdapter.add(filterInfo);
        filterEditor.setFilterInfo(filterInfo);
    }

    public void rebuild() {
        filterAdapter.itemsChanged();
    }
}
