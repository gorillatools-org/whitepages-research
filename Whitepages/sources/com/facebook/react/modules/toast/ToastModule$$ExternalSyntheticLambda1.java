package com.facebook.react.modules.toast;

public final /* synthetic */ class ToastModule$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ToastModule f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ ToastModule$$ExternalSyntheticLambda1(ToastModule toastModule, String str, int i, int i2) {
        this.f$0 = toastModule;
        this.f$1 = str;
        this.f$2 = i;
        this.f$3 = i2;
    }

    public final void run() {
        ToastModule.showWithGravity$lambda$1(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
