package com.airbnb.android.react.lottie;

import com.airbnb.lottie.LottieAnimationView;

public final /* synthetic */ class LottieAnimationViewManagerImpl$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ boolean f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ LottieAnimationView f$3;

    public /* synthetic */ LottieAnimationViewManagerImpl$$ExternalSyntheticLambda2(boolean z, int i, int i2, LottieAnimationView lottieAnimationView) {
        this.f$0 = z;
        this.f$1 = i;
        this.f$2 = i2;
        this.f$3 = lottieAnimationView;
    }

    public final void run() {
        LottieAnimationViewManagerImpl.play$lambda$1(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
