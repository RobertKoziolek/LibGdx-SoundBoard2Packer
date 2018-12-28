package com.robcio.soundboard2.packer.entity.attribute;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AttributeHolder implements Serializable {

    private ArrayList<Attribute> attributes;

    public AttributeHolder() {
        attributes = new ArrayList<>();
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public ArrayList<Attribute> getAttributes(final Set<Integer> attributeIds) {
        return attributes.stream()
                         .filter(attribute -> attributeIds.contains(attribute.getId()))
                         .collect(Collectors.toCollection(ArrayList::new));
    }

    public void loadAttributes(final ArrayList<Attribute> attributes) {
        this.attributes.clear();
        this.attributes.addAll(attributes);
    }
}
