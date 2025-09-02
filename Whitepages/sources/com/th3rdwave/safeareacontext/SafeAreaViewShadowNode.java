package com.th3rdwave.safeareacontext;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.NativeViewHierarchyOptimizer;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.google.firebase.messaging.Constants;
import kotlin.jvm.internal.Intrinsics;

public final class SafeAreaViewShadowNode extends LayoutShadowNode {
    private SafeAreaViewLocalData mLocalData;
    private final float[] mMargins;
    private boolean mNeedsUpdate;
    private final float[] mPaddings;

    public SafeAreaViewShadowNode() {
        int[] iArr = ViewProps.PADDING_MARGIN_SPACING_TYPES;
        this.mPaddings = new float[iArr.length];
        this.mMargins = new float[iArr.length];
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            this.mPaddings[i] = Float.NaN;
            this.mMargins[i] = Float.NaN;
        }
    }

    private final void updateInsets() {
        SafeAreaViewLocalData safeAreaViewLocalData = this.mLocalData;
        if (safeAreaViewLocalData != null) {
            SafeAreaViewMode mode = safeAreaViewLocalData.getMode();
            SafeAreaViewMode safeAreaViewMode = SafeAreaViewMode.PADDING;
            float[] fArr = mode == safeAreaViewMode ? this.mPaddings : this.mMargins;
            float f = fArr[8];
            if (Float.isNaN(f)) {
                f = 0.0f;
            }
            float f2 = f;
            float f3 = f2;
            float f4 = f3;
            float f5 = fArr[7];
            if (!Float.isNaN(f5)) {
                f = f5;
                f3 = f;
            }
            float f6 = fArr[6];
            if (!Float.isNaN(f6)) {
                f2 = f6;
                f4 = f2;
            }
            float f7 = fArr[1];
            if (!Float.isNaN(f7)) {
                f = f7;
            }
            float f8 = fArr[2];
            if (!Float.isNaN(f8)) {
                f2 = f8;
            }
            float f9 = fArr[3];
            if (!Float.isNaN(f9)) {
                f3 = f9;
            }
            float f10 = fArr[0];
            if (!Float.isNaN(f10)) {
                f4 = f10;
            }
            float pixelFromDIP = PixelUtil.toPixelFromDIP(f);
            float pixelFromDIP2 = PixelUtil.toPixelFromDIP(f2);
            float pixelFromDIP3 = PixelUtil.toPixelFromDIP(f3);
            float pixelFromDIP4 = PixelUtil.toPixelFromDIP(f4);
            SafeAreaViewEdges edges = safeAreaViewLocalData.getEdges();
            EdgeInsets insets = safeAreaViewLocalData.getInsets();
            if (safeAreaViewLocalData.getMode() == safeAreaViewMode) {
                super.setPadding(1, getEdgeValue(edges.getTop(), insets.getTop(), pixelFromDIP));
                super.setPadding(2, getEdgeValue(edges.getRight(), insets.getRight(), pixelFromDIP2));
                super.setPadding(3, getEdgeValue(edges.getBottom(), insets.getBottom(), pixelFromDIP3));
                super.setPadding(0, getEdgeValue(edges.getLeft(), insets.getLeft(), pixelFromDIP4));
                return;
            }
            super.setMargin(1, getEdgeValue(edges.getTop(), insets.getTop(), pixelFromDIP));
            super.setMargin(2, getEdgeValue(edges.getRight(), insets.getRight(), pixelFromDIP2));
            super.setMargin(3, getEdgeValue(edges.getBottom(), insets.getBottom(), pixelFromDIP3));
            super.setMargin(0, getEdgeValue(edges.getLeft(), insets.getLeft(), pixelFromDIP4));
        }
    }

    private final float getEdgeValue(SafeAreaViewEdgeModes safeAreaViewEdgeModes, float f, float f2) {
        if (safeAreaViewEdgeModes == SafeAreaViewEdgeModes.OFF) {
            return f2;
        }
        return safeAreaViewEdgeModes == SafeAreaViewEdgeModes.MAXIMUM ? Math.max(f, f2) : f + f2;
    }

    private final void resetInsets(SafeAreaViewMode safeAreaViewMode) {
        if (safeAreaViewMode == SafeAreaViewMode.PADDING) {
            super.setPadding(1, this.mPaddings[1]);
            super.setPadding(2, this.mPaddings[2]);
            super.setPadding(3, this.mPaddings[3]);
            super.setPadding(0, this.mPaddings[0]);
        } else {
            super.setMargin(1, this.mMargins[1]);
            super.setMargin(2, this.mMargins[2]);
            super.setMargin(3, this.mMargins[3]);
            super.setMargin(0, this.mMargins[0]);
        }
        markUpdated();
    }

    public void onBeforeLayout(NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer) {
        Intrinsics.checkNotNullParameter(nativeViewHierarchyOptimizer, "nativeViewHierarchyOptimizer");
        if (this.mNeedsUpdate) {
            this.mNeedsUpdate = false;
            updateInsets();
        }
    }

    public void setLocalData(Object obj) {
        Intrinsics.checkNotNullParameter(obj, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        if (obj instanceof SafeAreaViewLocalData) {
            SafeAreaViewLocalData safeAreaViewLocalData = this.mLocalData;
            if (!(safeAreaViewLocalData == null || safeAreaViewLocalData.getMode() == ((SafeAreaViewLocalData) obj).getMode())) {
                resetInsets(safeAreaViewLocalData.getMode());
            }
            this.mLocalData = (SafeAreaViewLocalData) obj;
            this.mNeedsUpdate = false;
            updateInsets();
        }
    }

    @ReactPropGroup(names = {"padding", "paddingVertical", "paddingHorizontal", "paddingStart", "paddingEnd", "paddingTop", "paddingBottom", "paddingLeft", "paddingRight"})
    public void setPaddings(int i, Dynamic dynamic) {
        Intrinsics.checkNotNullParameter(dynamic, ViewProps.PADDING);
        this.mPaddings[ViewProps.PADDING_MARGIN_SPACING_TYPES[i]] = dynamic.getType() == ReadableType.Number ? (float) dynamic.asDouble() : Float.NaN;
        super.setPaddings(i, dynamic);
        this.mNeedsUpdate = true;
    }

    @ReactPropGroup(names = {"margin", "marginVertical", "marginHorizontal", "marginStart", "marginEnd", "marginTop", "marginBottom", "marginLeft", "marginRight"})
    public void setMargins(int i, Dynamic dynamic) {
        Intrinsics.checkNotNullParameter(dynamic, ViewProps.MARGIN);
        this.mMargins[ViewProps.PADDING_MARGIN_SPACING_TYPES[i]] = dynamic.getType() == ReadableType.Number ? (float) dynamic.asDouble() : Float.NaN;
        super.setMargins(i, dynamic);
        this.mNeedsUpdate = true;
    }
}
