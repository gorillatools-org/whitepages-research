package com.airbnb.android.react.lottie;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.dooboolab.rniap.BuildConfig;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;
import java.util.WeakHashMap;
import kotlin.jvm.internal.Intrinsics;

public final class LottieAnimationViewManager extends SimpleViewManager<LottieAnimationView> {
    private final WeakHashMap<LottieAnimationView, LottieAnimationViewPropertyManager> propManagersMap = new WeakHashMap<>();

    private final LottieAnimationViewPropertyManager getOrCreatePropertyManager(LottieAnimationView lottieAnimationView) {
        LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager = this.propManagersMap.get(lottieAnimationView);
        if (lottieAnimationViewPropertyManager != null) {
            return lottieAnimationViewPropertyManager;
        }
        LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager2 = new LottieAnimationViewPropertyManager(lottieAnimationView);
        this.propManagersMap.put(lottieAnimationView, lottieAnimationViewPropertyManager2);
        return lottieAnimationViewPropertyManager2;
    }

    public Map<String, Object> getExportedViewConstants() {
        return LottieAnimationViewManagerImpl.getExportedViewConstants();
    }

    public String getName() {
        return "LottieAnimationView";
    }

    public LottieAnimationView createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        LottieAnimationView createViewInstance = LottieAnimationViewManagerImpl.createViewInstance(themedReactContext);
        createViewInstance.setFailureListener(new LottieAnimationViewManager$$ExternalSyntheticLambda0(createViewInstance));
        createViewInstance.addLottieOnCompositionLoadedListener(new LottieAnimationViewManager$$ExternalSyntheticLambda1(createViewInstance));
        createViewInstance.addAnimatorListener(new LottieAnimationViewManager$createViewInstance$3(createViewInstance));
        return createViewInstance;
    }

    /* access modifiers changed from: private */
    public static final void createViewInstance$lambda$0(LottieAnimationView lottieAnimationView, Throwable th) {
        Intrinsics.checkNotNull(th);
        LottieAnimationViewManagerImpl.sendAnimationFailureEvent(lottieAnimationView, th);
    }

    /* access modifiers changed from: private */
    public static final void createViewInstance$lambda$1(LottieAnimationView lottieAnimationView, LottieComposition lottieComposition) {
        LottieAnimationViewManagerImpl.sendAnimationLoadedEvent(lottieAnimationView);
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return LottieAnimationViewManagerImpl.getExportedCustomDirectEventTypeConstants();
    }

    public void receiveCommand(LottieAnimationView lottieAnimationView, String str, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        Intrinsics.checkNotNullParameter(str, "commandName");
        switch (str.hashCode()) {
            case -934426579:
                if (str.equals("resume")) {
                    LottieAnimationViewManagerImpl.resume(lottieAnimationView);
                    return;
                }
                return;
            case 3443508:
                if (str.equals(BuildConfig.FLAVOR)) {
                    int i = -1;
                    int i2 = readableArray != null ? readableArray.getInt(0) : -1;
                    if (readableArray != null) {
                        i = readableArray.getInt(1);
                    }
                    LottieAnimationViewManagerImpl.play(lottieAnimationView, i2, i);
                    return;
                }
                return;
            case 106440182:
                if (str.equals("pause")) {
                    LottieAnimationViewManagerImpl.pause(lottieAnimationView);
                    return;
                }
                return;
            case 108404047:
                if (str.equals("reset")) {
                    LottieAnimationViewManagerImpl.reset(lottieAnimationView);
                    return;
                }
                return;
            default:
                return;
        }
    }

    @ReactProp(name = "sourceName")
    public final void setSourceName(LottieAnimationView lottieAnimationView, String str) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        LottieAnimationViewManagerImpl.setSourceName(str, getOrCreatePropertyManager(lottieAnimationView));
    }

    @ReactProp(name = "sourceJson")
    public final void setSourceJson(LottieAnimationView lottieAnimationView, String str) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        LottieAnimationViewManagerImpl.setSourceJson(str, getOrCreatePropertyManager(lottieAnimationView));
    }

    @ReactProp(name = "sourceURL")
    public final void setSourceURL(LottieAnimationView lottieAnimationView, String str) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        LottieAnimationViewManagerImpl.setSourceURL(str, getOrCreatePropertyManager(lottieAnimationView));
    }

    @ReactProp(name = "cacheComposition")
    public final void setCacheComposition(LottieAnimationView lottieAnimationView, boolean z) {
        Intrinsics.checkNotNull(lottieAnimationView);
        LottieAnimationViewManagerImpl.setCacheComposition(lottieAnimationView, z);
    }

    @ReactProp(name = "resizeMode")
    public final void setResizeMode(LottieAnimationView lottieAnimationView, String str) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        LottieAnimationViewManagerImpl.setResizeMode(str, getOrCreatePropertyManager(lottieAnimationView));
    }

    @ReactProp(name = "renderMode")
    public final void setRenderMode(LottieAnimationView lottieAnimationView, String str) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        LottieAnimationViewManagerImpl.setRenderMode(str, getOrCreatePropertyManager(lottieAnimationView));
    }

    @ReactProp(name = "hardwareAccelerationAndroid")
    public final void setHardwareAccelerationAndroid(LottieAnimationView lottieAnimationView, Boolean bool) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        Intrinsics.checkNotNull(bool);
        LottieAnimationViewManagerImpl.setHardwareAcceleration(bool.booleanValue(), getOrCreatePropertyManager(lottieAnimationView));
    }

    @ReactProp(name = "progress")
    public final void setProgress(LottieAnimationView lottieAnimationView, float f) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        LottieAnimationViewManagerImpl.setProgress(f, getOrCreatePropertyManager(lottieAnimationView));
    }

    @ReactProp(name = "speed")
    public final void setSpeed(LottieAnimationView lottieAnimationView, double d) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        LottieAnimationViewManagerImpl.setSpeed(d, getOrCreatePropertyManager(lottieAnimationView));
    }

    @ReactProp(name = "loop")
    public final void setLoop(LottieAnimationView lottieAnimationView, boolean z) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        LottieAnimationViewManagerImpl.setLoop(z, getOrCreatePropertyManager(lottieAnimationView));
    }

    @ReactProp(name = "autoPlay")
    public final void setAutoPlay(LottieAnimationView lottieAnimationView, boolean z) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        LottieAnimationViewManagerImpl.setAutoPlay(z, getOrCreatePropertyManager(lottieAnimationView));
    }

    @ReactProp(name = "imageAssetsFolder")
    public final void setImageAssetsFolder(LottieAnimationView lottieAnimationView, String str) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        LottieAnimationViewManagerImpl.setImageAssetsFolder(str, getOrCreatePropertyManager(lottieAnimationView));
    }

    @ReactProp(name = "enableMergePathsAndroidForKitKatAndAbove")
    public final void setEnableMergePaths(LottieAnimationView lottieAnimationView, boolean z) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        LottieAnimationViewManagerImpl.setEnableMergePaths(z, getOrCreatePropertyManager(lottieAnimationView));
    }

    @ReactProp(name = "enableSafeModeAndroid")
    public final void setEnableSafeMode(LottieAnimationView lottieAnimationView, boolean z) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        LottieAnimationViewManagerImpl.setEnableSafeMode(z, getOrCreatePropertyManager(lottieAnimationView));
    }

    @ReactProp(name = "colorFilters")
    public final void setColorFilters(LottieAnimationView lottieAnimationView, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        LottieAnimationViewManagerImpl.setColorFilters(readableArray, getOrCreatePropertyManager(lottieAnimationView));
    }

    @ReactProp(name = "textFiltersAndroid")
    public final void setTextFilters(LottieAnimationView lottieAnimationView, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        LottieAnimationViewManagerImpl.setTextFilters(readableArray, getOrCreatePropertyManager(lottieAnimationView));
    }

    @ReactProp(name = "sourceDotLottieURI")
    public final void setSourceDotLottie(LottieAnimationView lottieAnimationView, String str) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        LottieAnimationViewManagerImpl.setSourceDotLottieURI(str, getOrCreatePropertyManager(lottieAnimationView));
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(LottieAnimationView lottieAnimationView) {
        Intrinsics.checkNotNullParameter(lottieAnimationView, "view");
        super.onAfterUpdateTransaction(lottieAnimationView);
        getOrCreatePropertyManager(lottieAnimationView).commitChanges();
    }
}
