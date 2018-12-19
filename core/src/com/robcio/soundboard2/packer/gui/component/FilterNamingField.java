package com.robcio.soundboard2.packer.gui.component;

import com.kotcrab.vis.ui.Focusable;
import com.kotcrab.vis.ui.widget.VisValidatableTextField;
import com.robcio.soundboard2.packer.entity.FilterInfo;
import com.robcio.soundboard2.packer.util.Command;
import com.robcio.soundboard2.packer.util.validator.NameValidator;

import java.util.Objects;
import java.util.function.Consumer;

public class FilterNamingField extends VisValidatableTextField {

    private final Consumer<Focusable> keyboardFocusConsumer;
    private FilterInfo filterInfo;

    public FilterNamingField(final Command itemsDataChangedCommand, final Consumer<Focusable> keyboardFocusConsumer) {
        super(new NameValidator());
        this.keyboardFocusConsumer = keyboardFocusConsumer;
        setTextFieldListener((textField, c) -> {
            if (!Objects.isNull(filterInfo) && isInputValid()) {
                filterInfo.setName(textField.getText());
                itemsDataChangedCommand.perform();
            }
        });
        setDisabled(true);
    }

    public void setFilterInfo(final FilterInfo filterInfo) {
        this.filterInfo = filterInfo;
        setDisabled(false);
        setText(filterInfo.getName());
        keyboardFocusConsumer.accept(this);
        selectAll();
    }
}
