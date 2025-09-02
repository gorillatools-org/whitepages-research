package com.swmansion.rnscreens;

import com.facebook.react.BaseReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class RNScreensPackage extends BaseReactPackage {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public List createViewManagers(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        InsetsObserverProxy.INSTANCE.registerWithContext(reactApplicationContext);
        return CollectionsKt.listOf(new ScreenContainerViewManager(), new ScreenViewManager(), new ModalScreenViewManager(), new ScreenStackViewManager(), new ScreenStackHeaderConfigViewManager(), new ScreenStackHeaderSubviewManager(), new SearchBarManager(), new ScreenFooterManager(), new ScreenContentWrapperManager());
    }

    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(str, "s");
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactApplicationContext");
        if (Intrinsics.areEqual((Object) str, (Object) "RNSModule")) {
            return new ScreensModule(reactApplicationContext);
        }
        return null;
    }

    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        return new RNScreensPackage$$ExternalSyntheticLambda0();
    }

    /* access modifiers changed from: private */
    public static final Map getReactModuleInfoProvider$lambda$0() {
        HashMap hashMap = new HashMap();
        hashMap.put("RNSModule", new ReactModuleInfo("RNSModule", "RNSModule", false, false, true, false, false));
        return hashMap;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
