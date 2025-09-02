package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.fabric.mounting.MountingManager;
import kotlin.jvm.internal.Intrinsics;

public final class DispatchStringCommandMountItem extends DispatchCommandMountItem {
    private final ReadableArray commandArgs;
    private final String commandId;
    private final int reactTag;
    private final int surfaceId;

    public DispatchStringCommandMountItem(int i, int i2, String str, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(str, "commandId");
        this.surfaceId = i;
        this.reactTag = i2;
        this.commandId = str;
        this.commandArgs = readableArray;
    }

    public int getSurfaceId() {
        return this.surfaceId;
    }

    public void execute(MountingManager mountingManager) {
        Intrinsics.checkNotNullParameter(mountingManager, "mountingManager");
        mountingManager.receiveCommand(this.surfaceId, this.reactTag, this.commandId, this.commandArgs);
    }

    public String toString() {
        int i = this.reactTag;
        String str = this.commandId;
        return "DispatchStringCommandMountItem [" + i + "] " + str;
    }
}
