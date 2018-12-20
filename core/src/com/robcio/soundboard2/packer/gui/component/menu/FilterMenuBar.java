package com.robcio.soundboard2.packer.gui.component.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuBar;
import com.kotcrab.vis.ui.widget.MenuItem;
import com.robcio.soundboard2.packer.entity.FilterHolder;
import com.robcio.soundboard2.packer.entity.FilterInfo;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class FilterMenuBar extends MenuBar {
    private final FilterHolder filterHolder;
    private final Menu addMenu;
    private final Menu removeMenu;

    @Inject
    public FilterMenuBar(final FilterHolder filterHolder) {
        this.filterHolder = filterHolder;
        this.addMenu = new Menu("+");
        this.removeMenu = new Menu("-");
        addMenu(addMenu);
        addMenu(removeMenu);
    }

    public void update(final ArrayList<FilterInfo> soundFilters,
                       final Consumer<FilterInfo> addFilter,
                       final Consumer<FilterInfo> removeFilter) {
        setMenuListener(new MenuBar.MenuBarListener() {
            @Override
            public void menuOpened(Menu menu) {
            }

            @Override
            public void menuClosed(Menu menu) {
                updateInternal(soundFilters, addFilter, removeFilter);
            }
        });
        updateInternal(soundFilters, addFilter, removeFilter);
    }

    private void updateInternal(final ArrayList<FilterInfo> soundFilters,
                                final Consumer<FilterInfo> addFilter,
                                final Consumer<FilterInfo> removeFilter) {
        final List<FilterInfo> availableFilters = filterHolder.getFilterInfos()
                                                              .stream()
                                                              .filter(f -> !soundFilters.contains(f))
                                                              .collect(Collectors.toList());
        setMenuItems(addMenu, availableFilters, addFilter);
        setMenuItems(removeMenu, soundFilters, removeFilter);
    }

    private void setMenuItems(final Menu menu,
                              final List<FilterInfo> filters,
                              final Consumer<FilterInfo> filterAction) {
        menu.clear();
        filters.forEach(filterInfo -> {
            final MenuItem menuItem = new MenuItem(filterInfo.getName(), new ChangeListener() {
                @Override
                public void changed(final ChangeEvent event, final Actor actor) {
                    filterAction.accept(filterInfo);
                }
            });
            menu.addItem(menuItem);
        });
    }
}
