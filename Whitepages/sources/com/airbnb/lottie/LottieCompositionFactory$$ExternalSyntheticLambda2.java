package com.airbnb.lottie;

import com.airbnb.lottie.utils.Utils;
import java.io.InputStream;

public final /* synthetic */ class LottieCompositionFactory$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ InputStream f$0;

    public /* synthetic */ LottieCompositionFactory$$ExternalSyntheticLambda2(InputStream inputStream) {
        this.f$0 = inputStream;
    }

    public final void run() {
        Utils.closeQuietly(this.f$0);
    }
}
