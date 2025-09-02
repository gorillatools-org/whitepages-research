package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.fabric.mounting.SurfaceMountingManager;
import kotlin.jvm.internal.Intrinsics;

public final class DestroyUnmountedViewMountItem implements MountItem {
    private final int _surfaceId;
    private final int reactTag;

    public DestroyUnmountedViewMountItem(int i, int i2) {
        this._surfaceId = i;
        this.reactTag = i2;
    }

    public void execute(MountingManager mountingManager) {
        Intrinsics.checkNotNullParameter(mountingManager, "mountingManager");
        SurfaceMountingManager surfaceManager = mountingManager.getSurfaceManager(this._surfaceId);
        if (surfaceManager != null) {
            surfaceManager.deleteView(this.reactTag);
        }
    }

    public int getSurfaceId() {
        return this._surfaceId;
    }
}
