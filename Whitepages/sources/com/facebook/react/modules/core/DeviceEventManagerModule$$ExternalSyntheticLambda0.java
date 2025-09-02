package com.facebook.react.modules.core;

public final /* synthetic */ class DeviceEventManagerModule$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DefaultHardwareBackBtnHandler f$0;

    public /* synthetic */ DeviceEventManagerModule$$ExternalSyntheticLambda0(DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler) {
        this.f$0 = defaultHardwareBackBtnHandler;
    }

    public final void run() {
        DeviceEventManagerModule.invokeDefaultBackPressRunnable$lambda$0(this.f$0);
    }
}
