package com.facebook.react.defaults;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.defaults.DefaultTurboModuleManagerDelegate;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class DefaultTurboModuleManagerDelegate$Builder$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ Function1 f$0;

    public /* synthetic */ DefaultTurboModuleManagerDelegate$Builder$$ExternalSyntheticLambda0(Function1 function1) {
        this.f$0 = function1;
    }

    public final Object invoke(Object obj) {
        return DefaultTurboModuleManagerDelegate.Builder.addCxxReactPackage$lambda$3$lambda$2(this.f$0, (ReactApplicationContext) obj);
    }
}
