package com.facebook.react;

import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.bridge.NativeModule;
import java.util.Map;

public final /* synthetic */ class ReactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda2 implements ReactPackageTurboModuleManagerDelegate.ModuleProvider {
    public final /* synthetic */ Map f$0;

    public /* synthetic */ ReactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda2(Map map) {
        this.f$0 = map;
    }

    public final NativeModule getModule(String str) {
        return (NativeModule) this.f$0.get(str);
    }
}
