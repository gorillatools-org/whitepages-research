package com.facebook.react.defaults;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UIManagerProvider;

public final /* synthetic */ class DefaultReactNativeHost$$ExternalSyntheticLambda0 implements UIManagerProvider {
    public final /* synthetic */ DefaultReactNativeHost f$0;

    public /* synthetic */ DefaultReactNativeHost$$ExternalSyntheticLambda0(DefaultReactNativeHost defaultReactNativeHost) {
        this.f$0 = defaultReactNativeHost;
    }

    public final UIManager createUIManager(ReactApplicationContext reactApplicationContext) {
        return DefaultReactNativeHost.getUIManagerProvider$lambda$0(this.f$0, reactApplicationContext);
    }
}
