package com.facebook.react.internal.interop;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.salesforce.marketingcloud.config.a;
import kotlin.jvm.internal.Intrinsics;

public final class InteropEvent extends Event<InteropEvent> {
    private final WritableMap eventData;
    private final String eventName;

    public final String eventName() {
        return this.eventName;
    }

    public final WritableMap eventData() {
        return this.eventData;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InteropEvent(String str, WritableMap writableMap, int i, int i2) {
        super(i, i2);
        Intrinsics.checkNotNullParameter(str, a.h);
        this.eventName = str;
        this.eventData = writableMap;
    }

    public String getEventName() {
        return this.eventName;
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        return this.eventData;
    }
}
