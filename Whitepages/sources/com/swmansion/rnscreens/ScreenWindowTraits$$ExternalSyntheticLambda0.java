package com.swmansion.rnscreens;

import androidx.core.view.WindowInsetsControllerCompat;

public final /* synthetic */ class ScreenWindowTraits$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ boolean f$0;
    public final /* synthetic */ WindowInsetsControllerCompat f$1;

    public /* synthetic */ ScreenWindowTraits$$ExternalSyntheticLambda0(boolean z, WindowInsetsControllerCompat windowInsetsControllerCompat) {
        this.f$0 = z;
        this.f$1 = windowInsetsControllerCompat;
    }

    public final void run() {
        ScreenWindowTraits.setHidden$lambda$1(this.f$0, this.f$1);
    }
}
