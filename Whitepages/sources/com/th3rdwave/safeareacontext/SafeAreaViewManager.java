package com.th3rdwave.safeareacontext;

import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.view.ReactViewGroup;
import com.facebook.react.views.view.ReactViewManager;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "RNCSafeAreaView")
public final class SafeAreaViewManager extends ReactViewManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String REACT_CLASS = "RNCSafeAreaView";

    public String getName() {
        return REACT_CLASS;
    }

    public SafeAreaView createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        return new SafeAreaView(themedReactContext);
    }

    public SafeAreaViewShadowNode createShadowNodeInstance() {
        return new SafeAreaViewShadowNode();
    }

    public Class<SafeAreaViewShadowNode> getShadowNodeClass() {
        return SafeAreaViewShadowNode.class;
    }

    @ReactProp(name = "mode")
    public final void setMode(SafeAreaView safeAreaView, String str) {
        Intrinsics.checkNotNullParameter(safeAreaView, "view");
        if (Intrinsics.areEqual((Object) str, (Object) ViewProps.PADDING)) {
            safeAreaView.setMode(SafeAreaViewMode.PADDING);
        } else if (Intrinsics.areEqual((Object) str, (Object) ViewProps.MARGIN)) {
            safeAreaView.setMode(SafeAreaViewMode.MARGIN);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0037, code lost:
        if (r2 == null) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0050, code lost:
        if (r3 == null) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0069, code lost:
        if (r7 == null) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001e, code lost:
        if (r0 == null) goto L_0x0020;
     */
    @com.facebook.react.uimanager.annotations.ReactProp(name = "edges")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setEdges(com.th3rdwave.safeareacontext.SafeAreaView r6, com.facebook.react.bridge.ReadableMap r7) {
        /*
            r5 = this;
            java.lang.String r0 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            if (r7 == 0) goto L_0x0075
            java.lang.String r0 = "top"
            java.lang.String r0 = r7.getString(r0)
            java.lang.String r1 = "toUpperCase(...)"
            if (r0 == 0) goto L_0x0020
            java.util.Locale r2 = java.util.Locale.ROOT
            java.lang.String r0 = r0.toUpperCase(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            com.th3rdwave.safeareacontext.SafeAreaViewEdgeModes r0 = com.th3rdwave.safeareacontext.SafeAreaViewEdgeModes.valueOf(r0)
            if (r0 != 0) goto L_0x0022
        L_0x0020:
            com.th3rdwave.safeareacontext.SafeAreaViewEdgeModes r0 = com.th3rdwave.safeareacontext.SafeAreaViewEdgeModes.OFF
        L_0x0022:
            java.lang.String r2 = "right"
            java.lang.String r2 = r7.getString(r2)
            if (r2 == 0) goto L_0x0039
            java.util.Locale r3 = java.util.Locale.ROOT
            java.lang.String r2 = r2.toUpperCase(r3)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r1)
            com.th3rdwave.safeareacontext.SafeAreaViewEdgeModes r2 = com.th3rdwave.safeareacontext.SafeAreaViewEdgeModes.valueOf(r2)
            if (r2 != 0) goto L_0x003b
        L_0x0039:
            com.th3rdwave.safeareacontext.SafeAreaViewEdgeModes r2 = com.th3rdwave.safeareacontext.SafeAreaViewEdgeModes.OFF
        L_0x003b:
            java.lang.String r3 = "bottom"
            java.lang.String r3 = r7.getString(r3)
            if (r3 == 0) goto L_0x0052
            java.util.Locale r4 = java.util.Locale.ROOT
            java.lang.String r3 = r3.toUpperCase(r4)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r1)
            com.th3rdwave.safeareacontext.SafeAreaViewEdgeModes r3 = com.th3rdwave.safeareacontext.SafeAreaViewEdgeModes.valueOf(r3)
            if (r3 != 0) goto L_0x0054
        L_0x0052:
            com.th3rdwave.safeareacontext.SafeAreaViewEdgeModes r3 = com.th3rdwave.safeareacontext.SafeAreaViewEdgeModes.OFF
        L_0x0054:
            java.lang.String r4 = "left"
            java.lang.String r7 = r7.getString(r4)
            if (r7 == 0) goto L_0x006b
            java.util.Locale r4 = java.util.Locale.ROOT
            java.lang.String r7 = r7.toUpperCase(r4)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r1)
            com.th3rdwave.safeareacontext.SafeAreaViewEdgeModes r7 = com.th3rdwave.safeareacontext.SafeAreaViewEdgeModes.valueOf(r7)
            if (r7 != 0) goto L_0x006d
        L_0x006b:
            com.th3rdwave.safeareacontext.SafeAreaViewEdgeModes r7 = com.th3rdwave.safeareacontext.SafeAreaViewEdgeModes.OFF
        L_0x006d:
            com.th3rdwave.safeareacontext.SafeAreaViewEdges r1 = new com.th3rdwave.safeareacontext.SafeAreaViewEdges
            r1.<init>(r0, r2, r3, r7)
            r6.setEdges(r1)
        L_0x0075:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.th3rdwave.safeareacontext.SafeAreaViewManager.setEdges(com.th3rdwave.safeareacontext.SafeAreaView, com.facebook.react.bridge.ReadableMap):void");
    }

    public Object updateState(ReactViewGroup reactViewGroup, ReactStylesDiffMap reactStylesDiffMap, StateWrapper stateWrapper) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        ((SafeAreaView) reactViewGroup).setStateWrapper(stateWrapper);
        return null;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
