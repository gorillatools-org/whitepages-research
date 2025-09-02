package com.zoontek.rnpermissions;

import com.facebook.react.TurboReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

public final class RNPermissionsPackage extends TurboReactPackage {
    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        if (Intrinsics.areEqual((Object) str, (Object) "RNPermissions")) {
            return new RNPermissionsModule(reactApplicationContext);
        }
        return null;
    }

    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        return new RNPermissionsPackage$$ExternalSyntheticLambda0();
    }

    /* access modifiers changed from: private */
    public static final Map getReactModuleInfoProvider$lambda$0() {
        HashMap hashMap = new HashMap();
        hashMap.put("RNPermissions", new ReactModuleInfo("RNPermissions", "RNPermissions", false, false, true, false, false));
        return hashMap;
    }
}
