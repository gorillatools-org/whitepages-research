package com.facebook.react.fabric.events;

import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.uimanager.events.RCTModernEventEmitter;
import com.facebook.react.uimanager.events.TouchEvent;
import com.facebook.systrace.Systrace;
import com.salesforce.marketingcloud.config.a;
import kotlin.jvm.internal.Intrinsics;

public final class FabricEventEmitter implements RCTModernEventEmitter {
    private final FabricUIManager uiManager;

    public FabricEventEmitter(FabricUIManager fabricUIManager) {
        Intrinsics.checkNotNullParameter(fabricUIManager, "uiManager");
        this.uiManager = fabricUIManager;
    }

    public void receiveEvent(int i, String str, WritableMap writableMap) {
        Intrinsics.checkNotNullParameter(str, a.h);
        receiveEvent(-1, i, str, writableMap);
    }

    public void receiveEvent(int i, int i2, String str, WritableMap writableMap) {
        Intrinsics.checkNotNullParameter(str, a.h);
        receiveEvent(i, i2, str, false, 0, writableMap, 2);
    }

    public void receiveEvent(int i, int i2, String str, boolean z, int i3, WritableMap writableMap, int i4) {
        String str2 = str;
        Intrinsics.checkNotNullParameter(str, a.h);
        Systrace.beginSection(0, "FabricEventEmitter.receiveEvent('" + str + "')");
        try {
            this.uiManager.receiveEvent(i, i2, str, z, writableMap, i4);
        } finally {
            Systrace.endSection(0);
        }
    }

    public void receiveTouches(String str, WritableArray writableArray, WritableArray writableArray2) {
        Intrinsics.checkNotNullParameter(str, a.h);
        Intrinsics.checkNotNullParameter(writableArray, "touches");
        Intrinsics.checkNotNullParameter(writableArray2, "changedIndices");
        throw new UnsupportedOperationException("EventEmitter#receiveTouches is not supported by Fabric");
    }

    public void receiveTouches(TouchEvent touchEvent) {
        Intrinsics.checkNotNullParameter(touchEvent, "event");
        throw new UnsupportedOperationException("EventEmitter#receiveTouches is not supported by Fabric");
    }
}
