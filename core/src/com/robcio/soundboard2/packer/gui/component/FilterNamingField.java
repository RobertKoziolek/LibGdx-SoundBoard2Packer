package com.robcio.soundboard2.packer.gui.component;

import com.kotcrab.vis.ui.Focusable;
import com.kotcrab.vis.ui.widget.VisValidatableTextField;
import com.robcio.soundboard2.packer.entity.attribute.Attribute;
import com.robcio.soundboard2.packer.util.Command;
import com.robcio.soundboard2.packer.util.validator.NameValidator;

import java.util.Objects;
import java.util.function.Consumer;

public class FilterNamingField extends VisValidatableTextField {

    private final Consumer<Focusable> keyboardFocusConsumer;
    private Attribute attribute;

    public FilterNamingField(final Command itemsDataChangedCommand, final Consumer<Focusable> keyboardFocusConsumer) {
        super(new NameValidator());
        this.keyboardFocusConsumer = keyboardFocusConsumer;
        setTextFieldListener((textField, c) -> {
            if (!Objects.isNull(attribute) && isInputValid()) {
                attribute.setName(textField.getText());
                itemsDataChangedCommand.perform();
            }
        });
        setDisabled(true);
    }

    public void setAttribute(final Attribute attribute) {
        this.attribute = attribute;
        setDisabled(false);
        setText(attribute.getName());
        keyboardFocusConsumer.accept(this);
        selectAll();
    }
}
