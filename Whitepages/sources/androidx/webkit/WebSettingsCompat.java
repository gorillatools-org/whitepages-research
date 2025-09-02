package androidx.webkit;

import android.webkit.WebSettings;
import androidx.webkit.internal.WebSettingsAdapter;
import androidx.webkit.internal.WebViewFeatureInternal;
import androidx.webkit.internal.WebViewGlueCommunicator;

public abstract class WebSettingsCompat {
    public static void setForceDark(WebSettings webSettings, int i) {
        WebViewFeatureInternal webViewFeatureInternal = WebViewFeatureInternal.FORCE_DARK;
        if (webViewFeatureInternal.isSupportedByFramework()) {
            webSettings.setForceDark(i);
        } else if (webViewFeatureInternal.isSupportedByWebView()) {
            getAdapter(webSettings).setForceDark(i);
        } else {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }

    public static void setForceDarkStrategy(WebSettings webSettings, int i) {
        if (WebViewFeatureInternal.FORCE_DARK_STRATEGY.isSupportedByWebView()) {
            getAdapter(webSettings).setForceDarkStrategy(i);
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    private static WebSettingsAdapter getAdapter(WebSettings webSettings) {
        return WebViewGlueCommunicator.getCompatConverter().convertSettings(webSettings);
    }
}
