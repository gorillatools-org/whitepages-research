package com.reactnativecommunity.webview;

import android.content.Context;
import android.view.View;
import android.view.ViewParent;
import android.webkit.WebView;
import android.widget.FrameLayout;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class RNCWebViewWrapper extends FrameLayout {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final RNCWebView webView;

    public static final int getReactTagFromWebView(WebView webView2) {
        return Companion.getReactTagFromWebView(webView2);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RNCWebViewWrapper(Context context, RNCWebView rNCWebView) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(rNCWebView, "webView");
        rNCWebView.setBackgroundColor(0);
        addView(rNCWebView);
        View childAt = getChildAt(0);
        Intrinsics.checkNotNull(childAt, "null cannot be cast to non-null type com.reactnativecommunity.webview.RNCWebView");
        this.webView = (RNCWebView) childAt;
    }

    public final RNCWebView getWebView() {
        return this.webView;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getReactTagFromWebView(WebView webView) {
            Intrinsics.checkNotNullParameter(webView, "webView");
            ViewParent parent = webView.getParent();
            View view = parent instanceof View ? (View) parent : null;
            if (view != null) {
                return view.getId();
            }
            return -1;
        }
    }
}
