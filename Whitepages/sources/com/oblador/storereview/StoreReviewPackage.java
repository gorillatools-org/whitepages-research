package com.oblador.storereview;

import com.facebook.react.TurboReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import java.util.HashMap;
import java.util.Map;

public class StoreReviewPackage extends TurboReactPackage {
    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        if (str.equals("RNStoreReview")) {
            return new StoreReviewModule(reactApplicationContext);
        }
        return null;
    }

    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        return new StoreReviewPackage$$ExternalSyntheticLambda0();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Map lambda$getReactModuleInfoProvider$0() {
        HashMap hashMap = new HashMap();
        hashMap.put("RNStoreReview", new ReactModuleInfo("RNStoreReview", "RNStoreReview", false, false, true, false, false));
        return hashMap;
    }
}
