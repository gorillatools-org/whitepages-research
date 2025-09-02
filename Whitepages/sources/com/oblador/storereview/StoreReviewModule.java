package com.oblador.storereview;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class StoreReviewModule extends ReactContextBaseJavaModule {
    StoreReviewModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public String getName() {
        return "RNStoreReview";
    }

    @ReactMethod
    public void requestReview() {
        StoreReviewModuleImpl.requestReview(getReactApplicationContext());
    }
}
