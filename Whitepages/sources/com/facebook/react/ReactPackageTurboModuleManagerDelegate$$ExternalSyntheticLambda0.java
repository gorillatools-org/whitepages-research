package com.facebook.react;

import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;

public final /* synthetic */ class ReactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda0 implements ReactPackageTurboModuleManagerDelegate.ModuleProvider {
    public final /* synthetic */ BaseReactPackage f$0;
    public final /* synthetic */ ReactApplicationContext f$1;

    public /* synthetic */ ReactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda0(BaseReactPackage baseReactPackage, ReactApplicationContext reactApplicationContext) {
        this.f$0 = baseReactPackage;
        this.f$1 = reactApplicationContext;
    }

    public final NativeModule getModule(String str) {
        return this.f$0.getModule(str, this.f$1);
    }
}
