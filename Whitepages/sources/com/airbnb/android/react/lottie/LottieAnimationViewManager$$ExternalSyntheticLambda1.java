package com.airbnb.android.react.lottie;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieOnCompositionLoadedListener;

public final /* synthetic */ class LottieAnimationViewManager$$ExternalSyntheticLambda1 implements LottieOnCompositionLoadedListener {
    public final /* synthetic */ LottieAnimationView f$0;

    public /* synthetic */ LottieAnimationViewManager$$ExternalSyntheticLambda1(LottieAnimationView lottieAnimationView) {
        this.f$0 = lottieAnimationView;
    }

    public final void onCompositionLoaded(LottieComposition lottieComposition) {
        LottieAnimationViewManager.createViewInstance$lambda$1(this.f$0, lottieComposition);
    }
}
