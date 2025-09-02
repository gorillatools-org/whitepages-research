package com.reactnativecommunity.webview;

import android.graphics.Bitmap;

public final class RNCWebViewManagerImpl$setupWebChromeClient$1 extends RNCWebChromeClient {
    RNCWebViewManagerImpl$setupWebChromeClient$1(RNCWebView rNCWebView) {
        super(rNCWebView);
    }

    public Bitmap getDefaultVideoPoster() {
        return Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
    }
}
