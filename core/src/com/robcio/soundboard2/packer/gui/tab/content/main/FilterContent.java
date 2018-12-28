package com.robcio.soundboard2.packer.gui.tab.content.main;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.ListView;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.kotcrab.vis.ui.widget.file.FileChooserListener;
import com.robcio.soundboard2.packer.entity.attribute.Attribute;
import com.robcio.soundboard2.packer.entity.attribute.FilterInfoHolder;
import com.robcio.soundboard2.packer.entity.attribute.ImageFilterInfo;
import com.robcio.soundboard2.packer.gui.component.AttributeEditor;
import com.robcio.soundboard2.packer.gui.component.adapter.AllFilterAdapter;
import com.robcio.soundboard2.packer.util.ButtonHelper;
import com.robcio.soundboard2.packer.util.KeyboardFocuser;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

import static com.robcio.soundboard2.packer.util.Constants.FILTER_VIEW_WIDTH;

//TODO abstract filter/suite content up
@Singleton
public class FilterContent extends VisTable {

    private final AllFilterAdapter allFilterAdapter;

    @Inject
    public FilterContent(final AllFilterAdapter allFilterAdapter,
                         final KeyboardFocuser keyboardFocuser,
                         final FilterInfoHolder filterInfoHolder,
                         @Named("image") final Provider<FileChooser> fileChooserProvider) {
        top();
        this.allFilterAdapter = allFilterAdapter;
        final VisTable filterControlTable = new VisTable();
        final ListView<Attribute> filterView = new ListView<>(allFilterAdapter);
        filterView.setHeader(new VisLabel("Filters:"));
        final AttributeEditor attributeEditor = new AttributeEditor(allFilterAdapter::itemsDataChanged,
                                                                    keyboardFocuser::focus);
        allFilterAdapter.setItemClickListener(attributeEditor::setAttribute);
        final VisTextButton addFilterButton = ButtonHelper.textButton("New attribute", () -> {
            setEditorFilter(attributeEditor, new Attribute(filterInfoHolder.getAttributes()
                                                                           .size()));
        });
        final VisTextButton addImageFilterButton = ButtonHelper.textButton("Image attribute", () -> {
            final FileChooser fileChooser = fileChooserProvider.get();
            fileChooser.setListener(new FileChooserListener() {
                @Override
                public void selected(final Array<FileHandle> files) {
                    final FileHandle fileHandle = files.get(0);
                    setEditorFilter(attributeEditor, new ImageFilterInfo(filterInfoHolder.getAttributes()
                                                                                         .size(), fileHandle));
                    fillContent(filterView, filterControlTable);
                }

                @Override
                public void canceled() {
                    fillContent(filterView, filterControlTable);
                }
            });
            openFileChooser(fileChooser);
        });
        filterControlTable.add(attributeEditor)
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

    private void setEditorFilter(final AttributeEditor attributeEditor, final Attribute attribute) {
        allFilterAdapter.add(attribute);
        attributeEditor.setAttribute(attribute);
    }

    public void rebuild() {
        allFilterAdapter.itemsChanged();
    }
}
