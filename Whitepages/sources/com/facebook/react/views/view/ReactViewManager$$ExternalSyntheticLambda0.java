package com.facebook.react.views.view;

import android.view.View;

public final /* synthetic */ class ReactViewManager$$ExternalSyntheticLambda0 implements View.OnClickListener {
    public final /* synthetic */ ReactViewGroup f$0;

    public /* synthetic */ ReactViewManager$$ExternalSyntheticLambda0(ReactViewGroup reactViewGroup) {
        this.f$0 = reactViewGroup;
    }

    public final void onClick(View view) {
        ReactViewManager.setFocusable$lambda$2(this.f$0, view);
    }
}
