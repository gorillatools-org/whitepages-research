package com.facebook.react.shell;

import com.facebook.react.module.model.ReactModuleInfoProvider;
import java.util.Map;

public final /* synthetic */ class MainReactPackage$$ExternalSyntheticLambda0 implements ReactModuleInfoProvider {
    public final /* synthetic */ Map f$0;

    public /* synthetic */ MainReactPackage$$ExternalSyntheticLambda0(Map map) {
        this.f$0 = map;
    }

    public final Map getReactModuleInfos() {
        return MainReactPackage.fallbackForMissingClass$lambda$19(this.f$0);
    }
}
