package com.th3rdwave.safeareacontext;

import com.facebook.react.BaseReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class SafeAreaContextPackage extends BaseReactPackage {
    /* access modifiers changed from: private */
    public static final Map getReactModuleInfoProvider$lambda$0(Map map) {
        return map;
    }

    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        if (Intrinsics.areEqual((Object) str, (Object) SafeAreaContextModule.NAME)) {
            return new SafeAreaContextModule(reactApplicationContext);
        }
        return null;
    }

    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        HashMap hashMap = new HashMap();
        Class cls = new Class[]{SafeAreaContextModule.class}[0];
        ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
        if (reactModule != null) {
            String name = reactModule.name();
            String name2 = reactModule.name();
            String name3 = cls.getName();
            Intrinsics.checkNotNullExpressionValue(name3, "getName(...)");
            hashMap.put(name, new ReactModuleInfo(name2, name3, true, reactModule.needsEagerInit(), reactModule.isCxxModule(), false));
        }
        return new SafeAreaContextPackage$$ExternalSyntheticLambda0(hashMap);
    }

    public List createViewManagers(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        return CollectionsKt.listOf(new SafeAreaProviderManager(), new SafeAreaViewManager());
    }
}
