package com.facebook.react.modules.network;

import com.facebook.react.bridge.ReactApplicationContext;
import okhttp3.Interceptor;
import okhttp3.Response;

public final /* synthetic */ class NetworkingModule$$ExternalSyntheticLambda0 implements Interceptor {
    public final /* synthetic */ NetworkingModule f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ ReactApplicationContext f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ NetworkingModule$$ExternalSyntheticLambda0(NetworkingModule networkingModule, String str, ReactApplicationContext reactApplicationContext, int i) {
        this.f$0 = networkingModule;
        this.f$1 = str;
        this.f$2 = reactApplicationContext;
        this.f$3 = i;
    }

    public final Response intercept(Interceptor.Chain chain) {
        return this.f$0.lambda$sendRequestInternal$0(this.f$1, this.f$2, this.f$3, chain);
    }
}
