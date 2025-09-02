package com.airbnb.android.react.lottie;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieListener;

public final /* synthetic */ class LottieAnimationViewManager$$ExternalSyntheticLambda0 implements LottieListener {
    public final /* synthetic */ LottieAnimationView f$0;

    public /* synthetic */ LottieAnimationViewManager$$ExternalSyntheticLambda0(LottieAnimationView lottieAnimationView) {
        this.f$0 = lottieAnimationView;
    }

    public final void onResult(Object obj) {
        LottieAnimationViewManager.createViewInstance$lambda$0(this.f$0, (Throwable) obj);
    }
}
