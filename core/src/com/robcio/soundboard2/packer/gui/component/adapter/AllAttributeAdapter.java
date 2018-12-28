package com.robcio.soundboard2.packer.gui.component.adapter;

import com.badlogic.gdx.graphics.Color;
import com.kotcrab.vis.ui.util.adapter.ArrayListAdapter;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.robcio.soundboard2.packer.entity.attribute.Attribute;
import com.robcio.soundboard2.packer.entity.attribute.AttributeHolder;

public abstract class AllAttributeAdapter extends ArrayListAdapter<Attribute, VisLabel> {

    public AllAttributeAdapter(final AttributeHolder attributeHolder) {
        super(attributeHolder.getAttributes());
        setSelectionMode(SelectionMode.SINGLE);
    }

    @Override
    protected VisLabel createView(final Attribute attribute) {
        return new VisLabel(attribute.getName());
    }

    @Override
    protected void updateView(final VisLabel label, final Attribute attribute) {
        label.setText(attribute.getName());
    }

    @Override
    public void itemsDataChanged() {
        final ListSelection<Attribute, VisLabel> selection = getSelectionManager();
        super.itemsDataChanged();
        selection.select(selection.getSelection()
                                  .get(0));
    }

    @Override
    public void add(final Attribute attribute) {
        super.add(attribute);
        getSelectionManager().select(attribute);
    }

    @Override
    protected void selectView(final VisLabel label) {
        label.setColor(Color.GREEN);
    }

    @Override
    protected void deselectView(final VisLabel label) {
        label.setColor(Color.WHITE);
    }
}
