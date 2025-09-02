package com.google.firebase.perf.util;

import android.view.View;
import android.view.ViewTreeObserver;

public final /* synthetic */ class FirstDrawDoneListener$$ExternalSyntheticLambda0 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ FirstDrawDoneListener f$0;
    public final /* synthetic */ View f$1;

    public /* synthetic */ FirstDrawDoneListener$$ExternalSyntheticLambda0(FirstDrawDoneListener firstDrawDoneListener, View view) {
        this.f$0 = firstDrawDoneListener;
        this.f$1 = view;
    }

    public final void onGlobalLayout() {
        this.f$0.lambda$onDraw$0(this.f$1);
    }
}
