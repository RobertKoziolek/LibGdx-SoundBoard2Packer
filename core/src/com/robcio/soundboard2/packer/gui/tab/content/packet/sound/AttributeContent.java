package com.robcio.soundboard2.packer.gui.tab.content.packet.sound;

import com.kotcrab.vis.ui.widget.ListView;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.robcio.soundboard2.packer.entity.attribute.Attribute;
import com.robcio.soundboard2.packer.entity.attribute.AttributeHolder;
import com.robcio.soundboard2.packer.gui.component.adapter.AttributeAdapter;
import com.robcio.soundboard2.packer.gui.component.menu.AttributeMenuBar;

import java.util.ArrayList;
import java.util.Set;

public abstract class AttributeContent extends VisTable {

    private final AttributeMenuBar attributeMenuBar;
    private final AttributeHolder attributeHolder;

    public AttributeContent(final AttributeMenuBar attributeMenuBar, final AttributeHolder attributeHolder) {
        this.attributeMenuBar = attributeMenuBar;
        this.attributeHolder = attributeHolder;
        top();
    }


    public void update(final Set<Integer> attributeIds) {
        final ArrayList<Attribute> attributes = attributeHolder.getAttributes(attributeIds);
        final AttributeAdapter attributeAdapter = new AttributeAdapter(attributes, attributeIds);
        final ListView<Attribute> attributeView = new ListView<>(attributeAdapter);
        attributeView.getScrollPane()
                     .setScrollingDisabled(true, false);
        //TODO touchdown listener z UIUtils.right() dla szybszego dodawania, middle na usuwanie moze?
        attributeMenuBar.update(attributes, attributeAdapter::add, attributeAdapter::remove);

        clear();
        add(new VisLabel(getHeadline()));
        add(attributeMenuBar.getTable()).growX()
                                        .row();
        addSeparator();
        add(attributeView.getMainTable()).colspan(2)
                                         .grow();
    }

    abstract String getHeadline();
}
