package com.swmansion.rnscreens.bottomsheet;

import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactPointerEventsView;
import kotlin.jvm.internal.Intrinsics;

public final class DimmingViewPointerEventsImpl implements ReactPointerEventsView {
    private final DimmingView dimmingView;

    public DimmingViewPointerEventsImpl(DimmingView dimmingView2) {
        Intrinsics.checkNotNullParameter(dimmingView2, "dimmingView");
        this.dimmingView = dimmingView2;
    }

    public PointerEvents getPointerEvents() {
        return this.dimmingView.getBlockGestures$react_native_screens_release() ? PointerEvents.AUTO : PointerEvents.NONE;
    }
}
