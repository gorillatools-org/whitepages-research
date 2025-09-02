package androidx.webkit;

import android.net.Uri;
import android.webkit.WebView;
import androidx.webkit.internal.WebViewFeatureInternal;
import androidx.webkit.internal.WebViewGlueCommunicator;
import androidx.webkit.internal.WebViewProviderAdapter;
import androidx.webkit.internal.WebViewProviderFactory;
import java.util.Set;
import org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface;

public abstract class WebViewCompat {
    private static final Uri EMPTY_URI = Uri.parse("");
    private static final Uri WILDCARD_URI = Uri.parse("*");

    public interface WebMessageListener {
        void onPostMessage(WebView webView, WebMessageCompat webMessageCompat, Uri uri, boolean z, JavaScriptReplyProxy javaScriptReplyProxy);
    }

    private static WebViewProviderAdapter getProvider(WebView webView) {
        return new WebViewProviderAdapter(createProvider(webView));
    }

    public static void addWebMessageListener(WebView webView, String str, Set set, WebMessageListener webMessageListener) {
        if (WebViewFeatureInternal.WEB_MESSAGE_LISTENER.isSupportedByWebView()) {
            getProvider(webView).addWebMessageListener(str, (String[]) set.toArray(new String[0]), webMessageListener);
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    private static WebViewProviderFactory getFactory() {
        return WebViewGlueCommunicator.getFactory();
    }

    private static WebViewProviderBoundaryInterface createProvider(WebView webView) {
        return getFactory().createWebView(webView);
    }
}
