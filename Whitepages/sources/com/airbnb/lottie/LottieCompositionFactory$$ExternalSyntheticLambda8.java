package com.airbnb.lottie;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;

public final /* synthetic */ class LottieCompositionFactory$$ExternalSyntheticLambda8 implements Callable {
    public final /* synthetic */ WeakReference f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ String f$3;

    public /* synthetic */ LottieCompositionFactory$$ExternalSyntheticLambda8(WeakReference weakReference, Context context, int i, String str) {
        this.f$0 = weakReference;
        this.f$1 = context;
        this.f$2 = i;
        this.f$3 = str;
    }

    public final Object call() {
        return LottieCompositionFactory.lambda$fromRawRes$2(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
