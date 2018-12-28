package com.robcio.soundboard2.packer.gui.tab.content.main;

import com.kotcrab.vis.ui.widget.ListView;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.robcio.soundboard2.packer.entity.attribute.Attribute;
import com.robcio.soundboard2.packer.entity.attribute.SuiteInfoHolder;
import com.robcio.soundboard2.packer.gui.component.AttributeEditor;
import com.robcio.soundboard2.packer.gui.component.adapter.AllSuiteAdapter;
import com.robcio.soundboard2.packer.util.ButtonHelper;
import com.robcio.soundboard2.packer.util.KeyboardFocuser;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.robcio.soundboard2.packer.util.Constants.FILTER_VIEW_WIDTH;

@Singleton
public class SuiteContent extends VisTable {

    private final AllSuiteAdapter allSuiteAdapter;

    @Inject
    public SuiteContent(final AllSuiteAdapter allSuiteAdapter,
                        final KeyboardFocuser keyboardFocuser,
                        final SuiteInfoHolder suiteInfoHolder) {
        top();
        this.allSuiteAdapter = allSuiteAdapter;
        final VisTable suiteControlTable = new VisTable();
        final ListView<Attribute> suiteView = new ListView<>(allSuiteAdapter);
        suiteView.setHeader(new VisLabel("Suites:"));
        final AttributeEditor suiteEditor = new AttributeEditor(allSuiteAdapter::itemsDataChanged,
                                                                keyboardFocuser::focus);
        allSuiteAdapter.setItemClickListener(suiteEditor::setAttribute);
        final VisTextButton addSuiteButton = ButtonHelper.textButton("New attribute", () -> {
            setSuiterToEditor(suiteEditor, new Attribute(suiteInfoHolder.getAttributes()
                                                                        .size()));
        });

        suiteControlTable.add(suiteEditor)
                         .growX()
                         .row();
        suiteControlTable.add(addSuiteButton)
                         .growX()
                         .row();
        fillContent(suiteView, suiteControlTable);
    }

    private void fillContent(final ListView suiteView, final VisTable suiteControlTable) {
        clear();
        add(suiteView.getMainTable()).width(FILTER_VIEW_WIDTH)
                                     .row();
        add(suiteControlTable).growX();
    }

    private void setSuiterToEditor(final AttributeEditor attributeEditor, final Attribute attribute) {
        allSuiteAdapter.add(attribute);
        attributeEditor.setAttribute(attribute);
    }

    public void rebuild() {
        allSuiteAdapter.itemsChanged();
    }
}
