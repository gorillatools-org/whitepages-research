package com.reactnativecommunity.webview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import kotlin.jvm.internal.Intrinsics;

public final class RNCWebViewManagerImpl$setupWebChromeClient$webChromeClient$1 extends RNCWebChromeClient {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ int $initialRequestedOrientation;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RNCWebViewManagerImpl$setupWebChromeClient$webChromeClient$1(RNCWebView rNCWebView, Activity activity, int i) {
        super(rNCWebView);
        this.$activity = activity;
        this.$initialRequestedOrientation = i;
    }

    public Bitmap getDefaultVideoPoster() {
        return Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
    }

    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(customViewCallback, "callback");
        if (this.mVideoView != null) {
            customViewCallback.onCustomViewHidden();
            return;
        }
        this.mVideoView = view;
        this.mCustomViewCallback = customViewCallback;
        this.$activity.setRequestedOrientation(-1);
        this.mVideoView.setSystemUiVisibility(7942);
        this.$activity.getWindow().setFlags(512, 512);
        this.mVideoView.setBackgroundColor(-16777216);
        ViewGroup rootView = getRootView();
        rootView.addView(this.mVideoView, RNCWebChromeClient.FULLSCREEN_LAYOUT_PARAMS);
        if (rootView.getRootView() != this.mWebView.getRootView()) {
            this.mWebView.getRootView().setVisibility(8);
        } else {
            this.mWebView.setVisibility(8);
        }
        this.mWebView.getThemedReactContext().addLifecycleEventListener(this);
    }

    public void onHideCustomView() {
        if (this.mVideoView != null) {
            ViewGroup rootView = getRootView();
            if (rootView.getRootView() != this.mWebView.getRootView()) {
                this.mWebView.getRootView().setVisibility(0);
            } else {
                this.mWebView.setVisibility(0);
            }
            this.$activity.getWindow().clearFlags(512);
            rootView.removeView(this.mVideoView);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mVideoView = null;
            this.mCustomViewCallback = null;
            this.$activity.setRequestedOrientation(this.$initialRequestedOrientation);
            this.mWebView.getThemedReactContext().removeLifecycleEventListener(this);
        }
    }
}
