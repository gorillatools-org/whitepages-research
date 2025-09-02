package com.facebook.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

public final class FacebookWebFallbackDialog extends WebDialog {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = FacebookWebFallbackDialog.class.getName();
    private boolean waitingForDialogToClose;

    public /* synthetic */ FacebookWebFallbackDialog(Context context, String str, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str, str2);
    }

    private FacebookWebFallbackDialog(Context context, String str, String str2) {
        super(context, str);
        setExpectedRedirectUrl(str2);
    }

    public Bundle parseResponseUri(String str) {
        Bundle parseUrlQueryString = Utility.parseUrlQueryString(Uri.parse(str).getQuery());
        String string = parseUrlQueryString.getString("bridge_args");
        parseUrlQueryString.remove("bridge_args");
        if (!Utility.isNullOrEmpty(string)) {
            try {
                parseUrlQueryString.putBundle("com.facebook.platform.protocol.BRIDGE_ARGS", BundleJSONConverter.convertToBundle(new JSONObject(string)));
            } catch (JSONException e) {
                Utility.logd(TAG, "Unable to parse bridge_args JSON", e);
            }
        }
        String string2 = parseUrlQueryString.getString("method_results");
        parseUrlQueryString.remove("method_results");
        if (!Utility.isNullOrEmpty(string2)) {
            try {
                parseUrlQueryString.putBundle("com.facebook.platform.protocol.RESULT_ARGS", BundleJSONConverter.convertToBundle(new JSONObject(string2)));
            } catch (JSONException e2) {
                Utility.logd(TAG, "Unable to parse bridge_args JSON", e2);
            }
        }
        parseUrlQueryString.remove("version");
        parseUrlQueryString.putInt("com.facebook.platform.protocol.PROTOCOL_VERSION", NativeProtocol.getLatestKnownVersion());
        return parseUrlQueryString;
    }

    public void cancel() {
        WebView webView = getWebView();
        if (!isPageFinished() || isListenerCalled() || webView == null || !webView.isShown()) {
            super.cancel();
        } else if (!this.waitingForDialogToClose) {
            this.waitingForDialogToClose = true;
            webView.loadUrl("javascript:" + "(function() {  var event = document.createEvent('Event');  event.initEvent('fbPlatformDialogMustClose',true,true);  document.dispatchEvent(event);})();");
            new Handler(Looper.getMainLooper()).postDelayed(new FacebookWebFallbackDialog$$ExternalSyntheticLambda0(this), 1500);
        }
    }

    /* access modifiers changed from: private */
    public static final void cancel$lambda$0(FacebookWebFallbackDialog facebookWebFallbackDialog) {
        Intrinsics.checkNotNullParameter(facebookWebFallbackDialog, "this$0");
        super.cancel();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FacebookWebFallbackDialog newInstance(Context context, String str, String str2) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(str, "url");
            Intrinsics.checkNotNullParameter(str2, "expectedRedirectUrl");
            WebDialog.initDefaultTheme(context);
            return new FacebookWebFallbackDialog(context, str, str2, (DefaultConstructorMarker) null);
        }
    }
}
