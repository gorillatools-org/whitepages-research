package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.fabric.mounting.MountingManager;
import kotlin.jvm.internal.Intrinsics;

public final class DispatchIntCommandMountItem extends DispatchCommandMountItem {
    private final ReadableArray commandArgs;
    private final int commandId;
    private final int reactTag;
    private final int surfaceId;

    public DispatchIntCommandMountItem(int i, int i2, int i3, ReadableArray readableArray) {
        this.surfaceId = i;
        this.reactTag = i2;
        this.commandId = i3;
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
        int i2 = this.commandId;
        return "DispatchIntCommandMountItem [" + i + "] " + i2;
    }
}
