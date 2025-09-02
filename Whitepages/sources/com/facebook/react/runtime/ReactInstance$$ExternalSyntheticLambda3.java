package com.facebook.react.runtime;

import com.facebook.react.bridge.NativeMap;
import com.facebook.react.uimanager.UIConstantsProviderBinding;
import java.util.Map;

public final /* synthetic */ class ReactInstance$$ExternalSyntheticLambda3 implements UIConstantsProviderBinding.ConstantsForViewManagerProvider {
    public final /* synthetic */ ReactInstance f$0;
    public final /* synthetic */ Map f$1;

    public /* synthetic */ ReactInstance$$ExternalSyntheticLambda3(ReactInstance reactInstance, Map map) {
        this.f$0 = reactInstance;
        this.f$1 = map;
    }

    public final NativeMap getConstantsForViewManager(String str) {
        return this.f$0.lambda$new$2(this.f$1, str);
    }
}
