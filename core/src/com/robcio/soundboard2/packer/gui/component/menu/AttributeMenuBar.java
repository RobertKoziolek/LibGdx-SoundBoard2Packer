package com.robcio.soundboard2.packer.gui.component.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuBar;
import com.kotcrab.vis.ui.widget.MenuItem;
import com.robcio.soundboard2.packer.entity.attribute.Attribute;
import com.robcio.soundboard2.packer.entity.attribute.AttributeHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class AttributeMenuBar extends MenuBar {
    private final AttributeHolder attributeHolder;
    private final Menu addMenu;
    private final Menu removeMenu;

    public AttributeMenuBar(final AttributeHolder attributeHolder) {
        this.attributeHolder = attributeHolder;
        this.addMenu = new Menu("add");
        this.removeMenu = new Menu("remove");
    }

    public void update(final ArrayList<Attribute> attributes,
                       final Consumer<Attribute> addAttribute,
                       final Consumer<Attribute> removeAttribute) {
        setMenuListener(new MenuBarListener() {
            @Override
            public void menuOpened(Menu menu) {
            }

            @Override
            public void menuClosed(Menu menu) {
                updateInternal(attributes, addAttribute, removeAttribute);
            }
        });
        updateInternal(attributes, addAttribute, removeAttribute);
    }

    private void updateInternal(final ArrayList<Attribute> attributes,
                                final Consumer<Attribute> addAttribute,
                                final Consumer<Attribute> removeAttribute) {
        final List<Attribute> availableAttributes = attributeHolder.getAttributes()
                                                                   .stream()
                                                                   .filter(f -> !attributes.contains(f))
                                                                   .collect(Collectors.toList());
        removeMenu(addMenu);
        removeMenu(removeMenu);
        addMenu(addMenu, availableAttributes, addAttribute);
        addMenu(removeMenu, attributes, removeAttribute);
    }

    private void addMenu(final Menu menu,
                         final List<Attribute> attributes,
                         final Consumer<Attribute> attributeAction) {
        if (!attributes.isEmpty()) {
            addMenu(menu);
        }
        menu.clear();
        attributes.forEach(attribute -> {
            final MenuItem menuItem = new MenuItem(attribute.getName(), new ChangeListener() {
                @Override
                public void changed(final ChangeEvent event, final Actor actor) {
                    attributeAction.accept(attribute);
                }
            });
            menu.addItem(menuItem);
        });
    }
}
