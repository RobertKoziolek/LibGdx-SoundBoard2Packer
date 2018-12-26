package com.robcio.soundboard2.packer.gui.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kotcrab.vis.ui.Focusable;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.robcio.soundboard2.packer.entity.FilterInfo;
import com.robcio.soundboard2.packer.entity.ImageFilterInfo;
import com.robcio.soundboard2.packer.util.Command;

import java.util.function.Consumer;

public class FilterEditor extends VisTable {

    private final FilterNamingField filterNamingField;
    private final VisLabel alignLabel;
    private final Image image;

    public FilterEditor(final Command itemsDataChangedCommand, final Consumer<Focusable> keyboardFocusConsumer) {
        this.filterNamingField = new FilterNamingField(itemsDataChangedCommand, keyboardFocusConsumer);
        this.alignLabel = new VisLabel();
        this.image = new Image();
    }

    public void setFilterInfo(final FilterInfo filterInfo) {
        clear();
        filterNamingField.setFilterInfo(filterInfo);
        add(filterNamingField).growX()
                              .row();
        if (filterInfo instanceof ImageFilterInfo) {
            final ImageFilterInfo imageFilterInfo = (ImageFilterInfo) filterInfo;
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
