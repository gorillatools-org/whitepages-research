package com.airbnb.lottie;

import android.content.Context;
import java.util.concurrent.Callable;
import java.util.zip.ZipInputStream;

public final /* synthetic */ class LottieCompositionFactory$$ExternalSyntheticLambda3 implements Callable {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ ZipInputStream f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ LottieCompositionFactory$$ExternalSyntheticLambda3(Context context, ZipInputStream zipInputStream, String str) {
        this.f$0 = context;
        this.f$1 = zipInputStream;
        this.f$2 = str;
    }

    public final Object call() {
        return LottieCompositionFactory.fromZipStreamSync(this.f$0, this.f$1, this.f$2);
    }
}
