package com.swmansion.rnscreens.bottomsheet;

import com.facebook.react.uimanager.ReactPointerEventsView;

public final class DimmingViewPointerEventsProxy implements ReactPointerEventsView {
    private DimmingViewPointerEventsImpl pointerEventsImpl;

    public DimmingViewPointerEventsProxy(DimmingViewPointerEventsImpl dimmingViewPointerEventsImpl) {
        this.pointerEventsImpl = dimmingViewPointerEventsImpl;
    }

    public final void setPointerEventsImpl(DimmingViewPointerEventsImpl dimmingViewPointerEventsImpl) {
        this.pointerEventsImpl = dimmingViewPointerEventsImpl;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r0.getPointerEvents();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.react.uimanager.PointerEvents getPointerEvents() {
        /*
            r1 = this;
            com.swmansion.rnscreens.bottomsheet.DimmingViewPointerEventsImpl r0 = r1.pointerEventsImpl
            if (r0 == 0) goto L_0x000a
            com.facebook.react.uimanager.PointerEvents r0 = r0.getPointerEvents()
            if (r0 != 0) goto L_0x000c
        L_0x000a:
            com.facebook.react.uimanager.PointerEvents r0 = com.facebook.react.uimanager.PointerEvents.NONE
        L_0x000c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.bottomsheet.DimmingViewPointerEventsProxy.getPointerEvents():com.facebook.react.uimanager.PointerEvents");
    }
}
