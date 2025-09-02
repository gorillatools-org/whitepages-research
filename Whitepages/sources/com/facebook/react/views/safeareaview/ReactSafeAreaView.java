package com.facebook.react.views.safeareaview;

import android.view.View;
import android.view.ViewGroup;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import kotlin.jvm.internal.Intrinsics;

public final class ReactSafeAreaView extends ViewGroup {
    private final ThemedReactContext reactContext;
    private StateWrapper stateWrapper;

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    public final ThemedReactContext getReactContext() {
        return this.reactContext;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactSafeAreaView(ThemedReactContext themedReactContext) {
        super(themedReactContext);
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        this.reactContext = themedReactContext;
    }

    public final StateWrapper getStateWrapper$ReactAndroid_release() {
        return this.stateWrapper;
    }

    public final void setStateWrapper$ReactAndroid_release(StateWrapper stateWrapper2) {
        this.stateWrapper = stateWrapper2;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewCompat.setOnApplyWindowInsetsListener(this, new ReactSafeAreaView$$ExternalSyntheticLambda0(this));
        requestApplyInsets();
    }

    /* access modifiers changed from: private */
    public static final WindowInsetsCompat onAttachedToWindow$lambda$0(ReactSafeAreaView reactSafeAreaView, View view, WindowInsetsCompat windowInsetsCompat) {
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        Intrinsics.checkNotNullParameter(windowInsetsCompat, "windowInsets");
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout());
        Intrinsics.checkNotNullExpressionValue(insets, "getInsets(...)");
        reactSafeAreaView.updateState(insets);
        return WindowInsetsCompat.CONSUMED;
    }

    private final void updateState(Insets insets) {
        StateWrapper stateWrapper2 = this.stateWrapper;
        if (stateWrapper2 != null) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            PixelUtil pixelUtil = PixelUtil.INSTANCE;
            writableNativeMap.putDouble(ViewProps.LEFT, (double) pixelUtil.pxToDp((float) insets.left));
            writableNativeMap.putDouble(ViewProps.TOP, (double) pixelUtil.pxToDp((float) insets.top));
            writableNativeMap.putDouble(ViewProps.BOTTOM, (double) pixelUtil.pxToDp((float) insets.bottom));
            writableNativeMap.putDouble(ViewProps.RIGHT, (double) pixelUtil.pxToDp((float) insets.right));
            stateWrapper2.updateState(writableNativeMap);
            return;
        }
        ThemedReactContext themedReactContext = this.reactContext;
        themedReactContext.runOnNativeModulesQueueThread(new ReactSafeAreaView$updateState$2(this, insets, themedReactContext));
    }
}
