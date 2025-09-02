package com.facebook.imagepipeline.backends.okhttp3;

import okhttp3.Call;

public final /* synthetic */ class OkHttpNetworkFetcher$fetchWithRequest$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Call f$0;

    public /* synthetic */ OkHttpNetworkFetcher$fetchWithRequest$1$$ExternalSyntheticLambda0(Call call) {
        this.f$0 = call;
    }

    public final void run() {
        OkHttpNetworkFetcher$fetchWithRequest$1.onCancellationRequested$lambda$0(this.f$0);
    }
}
