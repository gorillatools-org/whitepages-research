package com.swmansion.rnscreens;

import android.util.Log;
import android.view.View;
import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerDelegate;
import com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "RNSScreenStackHeaderConfig")
public final class ScreenStackHeaderConfigViewManager extends ViewGroupManager<ScreenStackHeaderConfig> implements RNSScreenStackHeaderConfigManagerInterface<ScreenStackHeaderConfig> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String REACT_CLASS = "RNSScreenStackHeaderConfig";
    private final ViewManagerDelegate<ScreenStackHeaderConfig> delegate = new RNSScreenStackHeaderConfigManagerDelegate(this);

    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public ScreenStackHeaderConfig createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        return new ScreenStackHeaderConfig(themedReactContext);
    }

    public LayoutShadowNode createShadowNodeInstance(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "context");
        return new ScreenStackHeaderConfigShadowNode(reactApplicationContext);
    }

    public void addView(ScreenStackHeaderConfig screenStackHeaderConfig, View view, int i) {
        Intrinsics.checkNotNullParameter(screenStackHeaderConfig, "parent");
        Intrinsics.checkNotNullParameter(view, "child");
        if (view instanceof ScreenStackHeaderSubview) {
            screenStackHeaderConfig.addConfigSubview((ScreenStackHeaderSubview) view, i);
            return;
        }
        throw new JSApplicationCausedNativeException("Config children should be of type RNSScreenStackHeaderSubview");
    }

    public Object updateState(ScreenStackHeaderConfig screenStackHeaderConfig, ReactStylesDiffMap reactStylesDiffMap, StateWrapper stateWrapper) {
        Intrinsics.checkNotNullParameter(screenStackHeaderConfig, "view");
        return super.updateState(screenStackHeaderConfig, reactStylesDiffMap, stateWrapper);
    }

    public void onDropViewInstance(ScreenStackHeaderConfig screenStackHeaderConfig) {
        Intrinsics.checkNotNullParameter(screenStackHeaderConfig, "view");
        screenStackHeaderConfig.destroy();
    }

    public void removeAllViews(ScreenStackHeaderConfig screenStackHeaderConfig) {
        Intrinsics.checkNotNullParameter(screenStackHeaderConfig, "parent");
        screenStackHeaderConfig.removeAllConfigSubviews();
    }

    public void removeViewAt(ScreenStackHeaderConfig screenStackHeaderConfig, int i) {
        Intrinsics.checkNotNullParameter(screenStackHeaderConfig, "parent");
        screenStackHeaderConfig.removeConfigSubview(i);
    }

    public int getChildCount(ScreenStackHeaderConfig screenStackHeaderConfig) {
        Intrinsics.checkNotNullParameter(screenStackHeaderConfig, "parent");
        return screenStackHeaderConfig.getConfigSubviewsCount();
    }

    public View getChildAt(ScreenStackHeaderConfig screenStackHeaderConfig, int i) {
        Intrinsics.checkNotNullParameter(screenStackHeaderConfig, "parent");
        return screenStackHeaderConfig.getConfigSubview(i);
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(ScreenStackHeaderConfig screenStackHeaderConfig) {
        Intrinsics.checkNotNullParameter(screenStackHeaderConfig, "parent");
        super.onAfterUpdateTransaction(screenStackHeaderConfig);
        screenStackHeaderConfig.onUpdate();
    }

    @ReactProp(name = "title")
    public void setTitle(ScreenStackHeaderConfig screenStackHeaderConfig, String str) {
        Intrinsics.checkNotNullParameter(screenStackHeaderConfig, "config");
        screenStackHeaderConfig.setTitle(str);
    }

    @ReactProp(name = "titleFontFamily")
    public void setTitleFontFamily(ScreenStackHeaderConfig screenStackHeaderConfig, String str) {
        Intrinsics.checkNotNullParameter(screenStackHeaderConfig, "config");
        screenStackHeaderConfig.setTitleFontFamily(str);
    }

    @ReactProp(name = "titleFontSize")
    public void setTitleFontSize(ScreenStackHeaderConfig screenStackHeaderConfig, int i) {
        Intrinsics.checkNotNullParameter(screenStackHeaderConfig, "config");
        screenStackHeaderConfig.setTitleFontSize((float) i);
    }

    @ReactProp(name = "titleFontWeight")
    public void setTitleFontWeight(ScreenStackHeaderConfig screenStackHeaderConfig, String str) {
        Intrinsics.checkNotNullParameter(screenStackHeaderConfig, "config");
        screenStackHeaderConfig.setTitleFontWeight(str);
    }

    @ReactProp(customType = "Color", name = "titleColor")
    public void setTitleColor(ScreenStackHeaderConfig screenStackHeaderConfig, Integer num) {
        Intrinsics.checkNotNullParameter(screenStackHeaderConfig, "config");
        if (num != null) {
            screenStackHeaderConfig.setTitleColor(num.intValue());
        }
    }

    @ReactProp(customType = "Color", name = "backgroundColor")
    public void setBackgroundColor(ScreenStackHeaderConfig screenStackHeaderConfig, Integer num) {
        Intrinsics.checkNotNullParameter(screenStackHeaderConfig, "config");
        screenStackHeaderConfig.setBackgroundColor(num);
    }

    @ReactProp(name = "hideShadow")
    public void setHideShadow(ScreenStackHeaderConfig screenStackHeaderConfig, boolean z) {
        Intrinsics.checkNotNullParameter(screenStackHeaderConfig, "config");
        screenStackHeaderConfig.setHideShadow(z);
    }

    @ReactProp(name = "hideBackButton")
    public void setHideBackButton(ScreenStackHeaderConfig screenStackHeaderConfig, boolean z) {
        Intrinsics.checkNotNullParameter(screenStackHeaderConfig, "config");
        screenStackHeaderConfig.setHideBackButton(z);
    }

    @ReactProp(name = "topInsetEnabled")
    public void setTopInsetEnabled(ScreenStackHeaderConfig screenStackHeaderConfig, boolean z) {
        Intrinsics.checkNotNullParameter(screenStackHeaderConfig, "config");
        screenStackHeaderConfig.setTopInsetEnabled(z);
    }

    @ReactProp(customType = "Color", name = "color")
    public void setColor(ScreenStackHeaderConfig screenStackHeaderConfig, Integer num) {
        Intrinsics.checkNotNullParameter(screenStackHeaderConfig, "config");
        screenStackHeaderConfig.setTintColor(num != null ? num.intValue() : 0);
    }

    @ReactProp(name = "hidden")
    public void setHidden(ScreenStackHeaderConfig screenStackHeaderConfig, boolean z) {
        Intrinsics.checkNotNullParameter(screenStackHeaderConfig, "config");
        screenStackHeaderConfig.setHidden(z);
    }

    @ReactProp(name = "translucent")
    public void setTranslucent(ScreenStackHeaderConfig screenStackHeaderConfig, boolean z) {
        Intrinsics.checkNotNullParameter(screenStackHeaderConfig, "config");
        screenStackHeaderConfig.setTranslucent(z);
    }

    @ReactProp(name = "backButtonInCustomView")
    public void setBackButtonInCustomView(ScreenStackHeaderConfig screenStackHeaderConfig, boolean z) {
        Intrinsics.checkNotNullParameter(screenStackHeaderConfig, "config");
        screenStackHeaderConfig.setBackButtonInCustomView(z);
    }

    @ReactProp(name = "direction")
    public void setDirection(ScreenStackHeaderConfig screenStackHeaderConfig, String str) {
        Intrinsics.checkNotNullParameter(screenStackHeaderConfig, "config");
        screenStackHeaderConfig.setDirection(str);
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of("topAttached", MapBuilder.of("registrationName", "onAttached"), "topDetached", MapBuilder.of("registrationName", "onDetached"));
    }

    /* access modifiers changed from: protected */
    public ViewManagerDelegate<ScreenStackHeaderConfig> getDelegate() {
        return this.delegate;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private final void logNotAvailable(String str) {
        Log.w("[RNScreens]", str + " prop is not available on Android");
    }

    public void setBackTitle(ScreenStackHeaderConfig screenStackHeaderConfig, String str) {
        logNotAvailable("backTitle");
    }

    public void setBackTitleFontFamily(ScreenStackHeaderConfig screenStackHeaderConfig, String str) {
        logNotAvailable("backTitleFontFamily");
    }

    public void setBackTitleFontSize(ScreenStackHeaderConfig screenStackHeaderConfig, int i) {
        logNotAvailable("backTitleFontSize");
    }

    public void setBackTitleVisible(ScreenStackHeaderConfig screenStackHeaderConfig, boolean z) {
        logNotAvailable("backTitleVisible");
    }

    public void setLargeTitle(ScreenStackHeaderConfig screenStackHeaderConfig, boolean z) {
        logNotAvailable("largeTitle");
    }

    public void setLargeTitleFontFamily(ScreenStackHeaderConfig screenStackHeaderConfig, String str) {
        logNotAvailable("largeTitleFontFamily");
    }

    public void setLargeTitleFontSize(ScreenStackHeaderConfig screenStackHeaderConfig, int i) {
        logNotAvailable("largeTitleFontSize");
    }

    public void setLargeTitleFontWeight(ScreenStackHeaderConfig screenStackHeaderConfig, String str) {
        logNotAvailable("largeTitleFontWeight");
    }

    public void setLargeTitleBackgroundColor(ScreenStackHeaderConfig screenStackHeaderConfig, Integer num) {
        logNotAvailable("largeTitleBackgroundColor");
    }

    public void setLargeTitleHideShadow(ScreenStackHeaderConfig screenStackHeaderConfig, boolean z) {
        logNotAvailable("largeTitleHideShadow");
    }

    public void setLargeTitleColor(ScreenStackHeaderConfig screenStackHeaderConfig, Integer num) {
        logNotAvailable("largeTitleColor");
    }

    public void setDisableBackButtonMenu(ScreenStackHeaderConfig screenStackHeaderConfig, boolean z) {
        logNotAvailable("disableBackButtonMenu");
    }

    public void setBackButtonDisplayMode(ScreenStackHeaderConfig screenStackHeaderConfig, String str) {
        logNotAvailable("backButtonDisplayMode");
    }

    public void setBlurEffect(ScreenStackHeaderConfig screenStackHeaderConfig, String str) {
        logNotAvailable("blurEffect");
    }
}
