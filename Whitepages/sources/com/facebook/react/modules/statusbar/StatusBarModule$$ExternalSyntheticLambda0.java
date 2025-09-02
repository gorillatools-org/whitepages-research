package com.facebook.react.modules.statusbar;

import android.app.Activity;

public final /* synthetic */ class StatusBarModule$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Activity f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ StatusBarModule$$ExternalSyntheticLambda0(Activity activity, boolean z) {
        this.f$0 = activity;
        this.f$1 = z;
    }

    public final void run() {
        StatusBarModule.setHidden$lambda$1(this.f$0, this.f$1);
    }
}
