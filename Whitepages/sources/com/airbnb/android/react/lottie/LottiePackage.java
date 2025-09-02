package com.airbnb.android.react.lottie;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class LottiePackage implements ReactPackage {
    public List createNativeModules(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        return CollectionsKt.emptyList();
    }

    public List createViewManagers(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        return CollectionsKt.listOf(new LottieAnimationViewManager());
    }
}
