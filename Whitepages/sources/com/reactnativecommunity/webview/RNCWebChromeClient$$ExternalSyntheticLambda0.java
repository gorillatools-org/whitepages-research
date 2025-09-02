package com.reactnativecommunity.webview;

import com.facebook.react.modules.core.PermissionListener;

public final /* synthetic */ class RNCWebChromeClient$$ExternalSyntheticLambda0 implements PermissionListener {
    public final /* synthetic */ RNCWebChromeClient f$0;

    public /* synthetic */ RNCWebChromeClient$$ExternalSyntheticLambda0(RNCWebChromeClient rNCWebChromeClient) {
        this.f$0 = rNCWebChromeClient;
    }

    public final boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        return this.f$0.lambda$new$0(i, strArr, iArr);
    }
}
