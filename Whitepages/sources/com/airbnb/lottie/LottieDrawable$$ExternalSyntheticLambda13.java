package com.airbnb.lottie;

import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;

public final /* synthetic */ class LottieDrawable$$ExternalSyntheticLambda13 implements LottieDrawable.LazyCompositionTask {
    public final /* synthetic */ LottieDrawable f$0;
    public final /* synthetic */ KeyPath f$1;
    public final /* synthetic */ Object f$2;
    public final /* synthetic */ LottieValueCallback f$3;

    public /* synthetic */ LottieDrawable$$ExternalSyntheticLambda13(LottieDrawable lottieDrawable, KeyPath keyPath, Object obj, LottieValueCallback lottieValueCallback) {
        this.f$0 = lottieDrawable;
        this.f$1 = keyPath;
        this.f$2 = obj;
        this.f$3 = lottieValueCallback;
    }

    public final void run(LottieComposition lottieComposition) {
        this.f$0.lambda$addValueCallback$17(this.f$1, this.f$2, this.f$3, lottieComposition);
    }
}
