package com.robcio.soundboard2.packer.gui.tab.content.packet.sound;

import com.kotcrab.vis.ui.widget.ListView;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.robcio.soundboard2.packer.entity.FilterInfo;
import com.robcio.soundboard2.packer.entity.FilterInfoHolder;
import com.robcio.soundboard2.packer.entity.SoundInfo;
import com.robcio.soundboard2.packer.gui.component.adapter.SoundFilterAdapter;
import com.robcio.soundboard2.packer.gui.component.menu.FilterMenuBar;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Set;

public class FilterContent extends VisTable {

    private final FilterMenuBar filterMenuBar;
    private final FilterInfoHolder filterInfoHolder;

    @Inject
    public FilterContent(final FilterMenuBar filterMenuBar, final FilterInfoHolder filterInfoHolder) {
        this.filterMenuBar = filterMenuBar;
        this.filterInfoHolder = filterInfoHolder;
        top();
    }


    public void update(final SoundInfo soundInfo) {
        final Set<Integer> filtersId = soundInfo.getFiltersId();
        final ArrayList<FilterInfo> soundFilterInfos = filterInfoHolder.getFilterInfos(filtersId);
        final SoundFilterAdapter soundFilterAdapter = new SoundFilterAdapter(soundFilterInfos, filtersId);
        final ListView<FilterInfo> filterView = new ListView<>(soundFilterAdapter);
        filterView.getScrollPane()
                  .setScrollingDisabled(true, false);
        //TODO touchdown listener z UIUtils.right() dla szybszego dodawania, middle na usuwanie moze?
        filterMenuBar.update(soundFilterInfos, soundFilterAdapter::add, soundFilterAdapter::remove);

        clear();
        add(new VisLabel("Filters:"));
        add(filterMenuBar.getTable()).growX()
                                     .row();
        addSeparator();
        add(filterView.getMainTable()).colspan(2)
                                      .grow();
    }
}
