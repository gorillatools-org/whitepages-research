package com.airbnb.lottie;

import android.content.Context;
import java.util.concurrent.Callable;

public final /* synthetic */ class LottieCompositionFactory$$ExternalSyntheticLambda5 implements Callable {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ LottieCompositionFactory$$ExternalSyntheticLambda5(Context context, String str, String str2) {
        this.f$0 = context;
        this.f$1 = str;
        this.f$2 = str2;
    }

    public final Object call() {
        return LottieCompositionFactory.fromAssetSync(this.f$0, this.f$1, this.f$2);
    }
}
