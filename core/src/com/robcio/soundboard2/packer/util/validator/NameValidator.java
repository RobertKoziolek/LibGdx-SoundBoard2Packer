package com.robcio.soundboard2.packer.util.validator;

import com.kotcrab.vis.ui.util.InputValidator;

public class NameValidator implements InputValidator {

    public NameValidator() {
    }

    @Override
    public boolean validateInput(final String input) {
        return !input.isEmpty();
    }
}
