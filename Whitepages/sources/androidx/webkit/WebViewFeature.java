package androidx.webkit;

import androidx.webkit.internal.WebViewFeatureInternal;

public abstract class WebViewFeature {
    public static boolean isFeatureSupported(String str) {
        return WebViewFeatureInternal.isSupported(str);
    }
}
