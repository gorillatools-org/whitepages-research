package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.StateWrapper;
import kotlin.jvm.internal.Intrinsics;

public final class MountItemFactory {
    public static final MountItemFactory INSTANCE = new MountItemFactory();

    private MountItemFactory() {
    }

    public static final DispatchCommandMountItem createDispatchCommandMountItem(int i, int i2, int i3, ReadableArray readableArray) {
        return new DispatchIntCommandMountItem(i, i2, i3, readableArray);
    }

    public static final DispatchCommandMountItem createDispatchCommandMountItem(int i, int i2, String str, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(str, "commandId");
        return new DispatchStringCommandMountItem(i, i2, str, readableArray);
    }

    public static final MountItem createSendAccessibilityEventMountItem(int i, int i2, int i3) {
        return new SendAccessibilityEventMountItem(i, i2, i3);
    }

    public static final MountItem createPreAllocateViewMountItem(int i, int i2, String str, ReadableMap readableMap, StateWrapper stateWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(str, "component");
        return new PreAllocateViewMountItem(i, i2, str, readableMap, stateWrapper, z);
    }

    public static final MountItem createDestroyViewMountItem(int i, int i2) {
        return new DestroyUnmountedViewMountItem(i, i2);
    }

    public static final MountItem createIntBufferBatchMountItem(int i, int[] iArr, Object[] objArr, int i2) {
        Intrinsics.checkNotNullParameter(iArr, "intBuf");
        Intrinsics.checkNotNullParameter(objArr, "objBuf");
        return new IntBufferBatchMountItem(i, iArr, objArr, i2);
    }
}
