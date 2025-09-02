package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.RetryableMountingLayerException;
import com.facebook.react.fabric.mounting.MountingManager;
import kotlin.jvm.internal.Intrinsics;

public final class SendAccessibilityEvent implements MountItem {
    private final String TAG = "Fabric.SendAccessibilityEvent";
    private final int _surfaceId;
    private final int eventType;
    private final int reactTag;

    public SendAccessibilityEvent(int i, int i2, int i3) {
        this._surfaceId = i;
        this.reactTag = i2;
        this.eventType = i3;
    }

    public void execute(MountingManager mountingManager) {
        Intrinsics.checkNotNullParameter(mountingManager, "mountingManager");
        try {
            mountingManager.sendAccessibilityEvent(this._surfaceId, this.reactTag, this.eventType);
        } catch (RetryableMountingLayerException e) {
            ReactSoftExceptionLogger.logSoftException(this.TAG, e);
        }
    }

    public int getSurfaceId() {
        return this._surfaceId;
    }

    public String toString() {
        int i = this.reactTag;
        int i2 = this.eventType;
        return "SendAccessibilityEvent [" + i + "] " + i2;
    }
}
