package com.facebook.react.runtime;

import com.facebook.react.bridge.NativeMap;
import com.facebook.react.uimanager.UIConstantsProviderBinding;
import java.util.Map;

public final /* synthetic */ class ReactInstance$$ExternalSyntheticLambda4 implements UIConstantsProviderBinding.ConstantsProvider {
    public final /* synthetic */ ReactInstance f$0;
    public final /* synthetic */ Map f$1;

    public /* synthetic */ ReactInstance$$ExternalSyntheticLambda4(ReactInstance reactInstance, Map map) {
        this.f$0 = reactInstance;
        this.f$1 = map;
    }

    public final NativeMap getConstants() {
        return this.f$0.lambda$new$3(this.f$1);
    }
}
