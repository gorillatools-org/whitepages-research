package com.facebook.imagepipeline.backends.okhttp3;

import android.os.Looper;
import com.facebook.imagepipeline.producers.BaseProducerContextCallbacks;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;

public final class OkHttpNetworkFetcher$fetchWithRequest$1 extends BaseProducerContextCallbacks {
    final /* synthetic */ Call $call;
    final /* synthetic */ OkHttpNetworkFetcher this$0;

    OkHttpNetworkFetcher$fetchWithRequest$1(Call call, OkHttpNetworkFetcher okHttpNetworkFetcher) {
        this.$call = call;
        this.this$0 = okHttpNetworkFetcher;
    }

    public void onCancellationRequested() {
        if (!Intrinsics.areEqual((Object) Looper.myLooper(), (Object) Looper.getMainLooper())) {
            this.$call.cancel();
        } else {
            this.this$0.cancellationExecutor.execute(new OkHttpNetworkFetcher$fetchWithRequest$1$$ExternalSyntheticLambda0(this.$call));
        }
    }

    /* access modifiers changed from: private */
    public static final void onCancellationRequested$lambda$0(Call call) {
        call.cancel();
    }
}
