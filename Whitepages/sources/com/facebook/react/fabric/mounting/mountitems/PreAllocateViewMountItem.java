package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.fabric.mounting.SurfaceMountingManager;
import com.facebook.react.uimanager.StateWrapper;
import kotlin.jvm.internal.Intrinsics;

public final class PreAllocateViewMountItem implements MountItem {
    private final String fabricComponentName;
    private final boolean isLayoutable;
    private final ReadableMap props;
    private final int reactTag;
    private final StateWrapper stateWrapper;
    private final int surfaceId;

    public PreAllocateViewMountItem(int i, int i2, String str, ReadableMap readableMap, StateWrapper stateWrapper2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "component");
        this.surfaceId = i;
        this.reactTag = i2;
        this.props = readableMap;
        this.stateWrapper = stateWrapper2;
        this.isLayoutable = z;
        this.fabricComponentName = FabricNameComponentMapping.getFabricComponentName(str);
    }

    public int getSurfaceId() {
        return this.surfaceId;
    }

    public void execute(MountingManager mountingManager) {
        Intrinsics.checkNotNullParameter(mountingManager, "mountingManager");
        SurfaceMountingManager surfaceManager = mountingManager.getSurfaceManager(this.surfaceId);
        if (surfaceManager == null) {
            String str = FabricUIManager.TAG;
            int i = this.surfaceId;
            FLog.e(str, "Skipping View PreAllocation; no SurfaceMountingManager found for [" + i + "]");
            return;
        }
        surfaceManager.preallocateView(this.fabricComponentName, this.reactTag, this.props, this.stateWrapper, this.isLayoutable);
    }

    public String toString() {
        String str;
        String obj;
        StringBuilder sb = new StringBuilder("PreAllocateViewMountItem [");
        sb.append(this.reactTag);
        sb.append("] - component: ");
        sb.append(this.fabricComponentName);
        sb.append(" surfaceId: ");
        sb.append(this.surfaceId);
        sb.append(" isLayoutable: ");
        sb.append(this.isLayoutable);
        if (FabricUIManager.IS_DEVELOPMENT_ENVIRONMENT) {
            sb.append(" props: ");
            ReadableMap readableMap = this.props;
            String str2 = "<null>";
            if (readableMap == null || (str = readableMap.toString()) == null) {
                str = str2;
            }
            sb.append(str);
            sb.append(" state: ");
            StateWrapper stateWrapper2 = this.stateWrapper;
            if (!(stateWrapper2 == null || (obj = stateWrapper2.toString()) == null)) {
                str2 = obj;
            }
            sb.append(str2);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }
}
