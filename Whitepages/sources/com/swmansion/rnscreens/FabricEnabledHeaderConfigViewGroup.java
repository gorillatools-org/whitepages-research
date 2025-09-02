package com.swmansion.rnscreens;

import android.content.Context;
import android.view.ViewGroup;
import com.facebook.react.uimanager.StateWrapper;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class FabricEnabledHeaderConfigViewGroup extends ViewGroup {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private int lastHeight;
    private int lastPaddingEnd;
    private int lastPaddingStart;

    public final void setStateWrapper(StateWrapper stateWrapper) {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FabricEnabledHeaderConfigViewGroup(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final void updateHeaderConfigState(int i, int i2, int i3, int i4) {
        updateState(i, i2, i3, i4);
    }

    /* JADX WARNING: type inference failed for: r5v8, types: [com.facebook.react.bridge.NativeModule] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void updateState(int r5, int r6, int r7, int r8) {
        /*
            r4 = this;
            int r5 = r4.lastPaddingStart
            int r5 = r5 - r7
            int r5 = java.lang.Math.abs(r5)
            double r0 = (double) r5
            r2 = 4606281698874543309(0x3feccccccccccccd, double:0.9)
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 >= 0) goto L_0x002a
            int r5 = r4.lastPaddingEnd
            int r5 = r5 - r8
            int r5 = java.lang.Math.abs(r5)
            double r0 = (double) r5
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 >= 0) goto L_0x002a
            int r5 = r4.lastHeight
            int r5 = r5 - r6
            int r5 = java.lang.Math.abs(r5)
            double r0 = (double) r5
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 >= 0) goto L_0x002a
            return
        L_0x002a:
            r4.lastPaddingStart = r7
            r4.lastPaddingEnd = r8
            r4.lastHeight = r6
            android.content.Context r5 = r4.getContext()
            boolean r0 = r5 instanceof com.facebook.react.bridge.ReactContext
            r1 = 0
            if (r0 == 0) goto L_0x003c
            com.facebook.react.bridge.ReactContext r5 = (com.facebook.react.bridge.ReactContext) r5
            goto L_0x003d
        L_0x003c:
            r5 = r1
        L_0x003d:
            if (r5 == 0) goto L_0x0048
            java.lang.Class<com.facebook.react.uimanager.UIManagerModule> r0 = com.facebook.react.uimanager.UIManagerModule.class
            com.facebook.react.bridge.NativeModule r5 = r5.getNativeModule(r0)
            r1 = r5
            com.facebook.react.uimanager.UIManagerModule r1 = (com.facebook.react.uimanager.UIManagerModule) r1
        L_0x0048:
            if (r1 == 0) goto L_0x0059
            int r5 = r4.getId()
            com.swmansion.rnscreens.utils.PaddingBundle r0 = new com.swmansion.rnscreens.utils.PaddingBundle
            float r6 = (float) r6
            float r7 = (float) r7
            float r8 = (float) r8
            r0.<init>(r6, r7, r8)
            r1.setViewLocalData(r5, r0)
        L_0x0059:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.FabricEnabledHeaderConfigViewGroup.updateState(int, int, int, int):void");
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
