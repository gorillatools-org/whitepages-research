package com.facebook.react.internal.interop;

import android.content.Context;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.salesforce.marketingcloud.config.a;
import kotlin.jvm.internal.Intrinsics;

public final class InteropEventEmitter implements RCTEventEmitter {
    private EventDispatcher eventDispatcherOverride;
    private final ReactContext reactContext;

    public InteropEventEmitter(ReactContext reactContext2) {
        Intrinsics.checkNotNullParameter(reactContext2, "reactContext");
        this.reactContext = reactContext2;
    }

    public void receiveEvent(int i, String str, WritableMap writableMap) {
        Intrinsics.checkNotNullParameter(str, a.h);
        EventDispatcher eventDispatcher = this.eventDispatcherOverride;
        if (eventDispatcher == null) {
            eventDispatcher = UIManagerHelper.getEventDispatcherForReactTag(this.reactContext, i);
        }
        int surfaceId = UIManagerHelper.getSurfaceId((Context) this.reactContext);
        if (eventDispatcher != null) {
            eventDispatcher.dispatchEvent(new InteropEvent(str, writableMap, surfaceId, i));
        }
    }

    public void receiveTouches(String str, WritableArray writableArray, WritableArray writableArray2) {
        Intrinsics.checkNotNullParameter(str, a.h);
        Intrinsics.checkNotNullParameter(writableArray, "touches");
        Intrinsics.checkNotNullParameter(writableArray2, "changedIndices");
        throw new UnsupportedOperationException("EventEmitter#receiveTouches is not supported by the Fabric Interop Layer");
    }

    @VisibleForTesting
    public final void overrideEventDispatcher(EventDispatcher eventDispatcher) {
        this.eventDispatcherOverride = eventDispatcher;
    }
}
