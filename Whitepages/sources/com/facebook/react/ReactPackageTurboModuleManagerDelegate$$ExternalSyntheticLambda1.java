package com.facebook.react;

import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.bridge.NativeModule;
import java.util.Map;

public final /* synthetic */ class ReactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda1 implements ReactPackageTurboModuleManagerDelegate.ModuleProvider {
    public final /* synthetic */ Map f$0;

    public /* synthetic */ ReactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda1(Map map) {
        this.f$0 = map;
    }

    public final NativeModule getModule(String str) {
        return ReactPackageTurboModuleManagerDelegate.lambda$initialize$1(this.f$0, str);
    }
}
