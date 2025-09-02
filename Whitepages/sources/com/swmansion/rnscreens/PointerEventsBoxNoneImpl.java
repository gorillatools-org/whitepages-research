package com.swmansion.rnscreens;

import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactPointerEventsView;

public final class PointerEventsBoxNoneImpl implements ReactPointerEventsView {
    private final PointerEvents pointerEvents = PointerEvents.BOX_NONE;

    public PointerEvents getPointerEvents() {
        return this.pointerEvents;
    }
}
