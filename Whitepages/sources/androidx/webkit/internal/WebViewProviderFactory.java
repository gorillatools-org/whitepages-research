package androidx.webkit.internal;

import android.webkit.WebView;
import org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface;
import org.chromium.support_lib_boundary.WebkitToCompatConverterBoundaryInterface;

public interface WebViewProviderFactory {
    WebViewProviderBoundaryInterface createWebView(WebView webView);

    String[] getWebViewFeatures();

    WebkitToCompatConverterBoundaryInterface getWebkitToCompatConverter();
}
