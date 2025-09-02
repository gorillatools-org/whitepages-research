package com.swmansion.rnscreens;

import android.view.Window;

public final /* synthetic */ class ScreenWindowTraits$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ Window f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ ScreenWindowTraits$$ExternalSyntheticLambda2(Window window, int i) {
        this.f$0 = window;
        this.f$1 = i;
    }

    public final void run() {
        ScreenWindowTraits.setNavigationBarColor$lambda$2(this.f$0, this.f$1);
    }
}
