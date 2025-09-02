package com.facebook.react.modules.statusbar;

import android.app.Activity;

public final /* synthetic */ class StatusBarModule$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ Activity f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ StatusBarModule$$ExternalSyntheticLambda1(Activity activity, String str) {
        this.f$0 = activity;
        this.f$1 = str;
    }

    public final void run() {
        StatusBarModule.setStyle$lambda$2(this.f$0, this.f$1);
    }
}
