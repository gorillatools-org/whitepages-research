package com.airbnb.lottie;

import com.airbnb.lottie.utils.Utils;
import java.util.zip.ZipInputStream;

public final /* synthetic */ class LottieCompositionFactory$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ ZipInputStream f$0;

    public /* synthetic */ LottieCompositionFactory$$ExternalSyntheticLambda4(ZipInputStream zipInputStream) {
        this.f$0 = zipInputStream;
    }

    public final void run() {
        Utils.closeQuietly(this.f$0);
    }
}
