package com.facebook.react.modules.camera;

import com.facebook.react.bridge.Callback;

public final /* synthetic */ class ImageStoreManager$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ImageStoreManager f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Callback f$2;
    public final /* synthetic */ Callback f$3;

    public /* synthetic */ ImageStoreManager$$ExternalSyntheticLambda0(ImageStoreManager imageStoreManager, String str, Callback callback, Callback callback2) {
        this.f$0 = imageStoreManager;
        this.f$1 = str;
        this.f$2 = callback;
        this.f$3 = callback2;
    }

    public final void run() {
        ImageStoreManager.getBase64ForTag$lambda$0(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
