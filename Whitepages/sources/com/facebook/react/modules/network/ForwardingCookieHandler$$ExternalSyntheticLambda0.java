package com.facebook.react.modules.network;

import android.webkit.ValueCallback;
import com.facebook.react.bridge.Callback;

public final /* synthetic */ class ForwardingCookieHandler$$ExternalSyntheticLambda0 implements ValueCallback {
    public final /* synthetic */ Callback f$0;

    public /* synthetic */ ForwardingCookieHandler$$ExternalSyntheticLambda0(Callback callback) {
        this.f$0 = callback;
    }

    public final void onReceiveValue(Object obj) {
        ForwardingCookieHandler.clearCookies$lambda$0(this.f$0, (Boolean) obj);
    }
}
