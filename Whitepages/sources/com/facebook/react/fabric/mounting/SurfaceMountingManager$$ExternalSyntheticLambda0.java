package com.facebook.react.fabric.mounting;

import android.view.View;

public final /* synthetic */ class SurfaceMountingManager$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ SurfaceMountingManager f$0;
    public final /* synthetic */ View f$1;

    public /* synthetic */ SurfaceMountingManager$$ExternalSyntheticLambda0(SurfaceMountingManager surfaceMountingManager, View view) {
        this.f$0 = surfaceMountingManager;
        this.f$1 = view;
    }

    public final void run() {
        this.f$0.lambda$addRootView$0(this.f$1);
    }
}
