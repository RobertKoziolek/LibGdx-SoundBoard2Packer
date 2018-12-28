package com.robcio.soundboard2.packer.gui.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kotcrab.vis.ui.Focusable;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.robcio.soundboard2.packer.entity.attribute.Attribute;
import com.robcio.soundboard2.packer.entity.attribute.ImageFilterInfo;
import com.robcio.soundboard2.packer.util.Command;

import java.util.function.Consumer;

public class AttributeEditor extends VisTable {

    private final FilterNamingField filterNamingField;
    private final VisLabel alignLabel;
    private final Image image;

    public AttributeEditor(final Command itemsDataChangedCommand, final Consumer<Focusable> keyboardFocusConsumer) {
        this.filterNamingField = new FilterNamingField(itemsDataChangedCommand, keyboardFocusConsumer);
        this.alignLabel = new VisLabel();
        this.image = new Image();
    }

    //TODO separate this better
    public void setAttribute(final Attribute attribute) {
        clear();
        filterNamingField.setAttribute(attribute);
        add(filterNamingField).growX()
                              .row();
        if (attribute instanceof ImageFilterInfo) {
            final ImageFilterInfo imageFilterInfo = (ImageFilterInfo) attribute;
            //TODO fix image size
            final Texture texture = new Texture(imageFilterInfo.getImage()
                                                               .path());
            image.setDrawable(new TextureRegionDrawable(new TextureRegion(texture)));
            add(image).growX();
            final ImageFilterInfo.Align align = imageFilterInfo.getAlign();
            alignLabel.setText(align.name());
            add(alignLabel).growX()
                           .row();
        }
    }
}
