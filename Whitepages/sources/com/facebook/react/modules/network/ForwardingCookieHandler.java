package com.facebook.react.modules.network;

import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactContext;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.URI;
import java.util.List;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class ForwardingCookieHandler extends CookieHandler {
    private static final String COOKIE_HEADER = "Cookie";
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String VERSION_ONE_HEADER = "Set-cookie2";
    private static final String VERSION_ZERO_HEADER = "Set-cookie";
    private CookieManager cookieManager;

    public final void destroy() {
    }

    public ForwardingCookieHandler() {
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ForwardingCookieHandler(ReactContext reactContext) {
        this();
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
    }

    public Map<String, List<String>> get(URI uri, Map<String, ? extends List<String>> map) throws IOException {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(map, "headers");
        CookieManager cookieManager2 = getCookieManager();
        String cookie = cookieManager2 != null ? cookieManager2.getCookie(uri.toString()) : null;
        if (cookie == null || cookie.length() == 0) {
            return MapsKt.emptyMap();
        }
        return MapsKt.mapOf(TuplesKt.to(COOKIE_HEADER, CollectionsKt.listOf(cookie)));
    }

    public void put(URI uri, Map<String, ? extends List<String>> map) throws IOException {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(map, "headers");
        String uri2 = uri.toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
        for (Map.Entry next : map.entrySet()) {
            List list = (List) next.getValue();
            if (Companion.isCookieHeader((String) next.getKey())) {
                addCookies(uri2, list);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void clearCookies$lambda$0(Callback callback, Boolean bool) {
        callback.invoke(bool);
    }

    public final void clearCookies(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        CookieManager cookieManager2 = getCookieManager();
        if (cookieManager2 != null) {
            cookieManager2.removeAllCookies(new ForwardingCookieHandler$$ExternalSyntheticLambda0(callback));
        }
    }

    public final void addCookies(String str, List<String> list) {
        Intrinsics.checkNotNullParameter(str, "url");
        Intrinsics.checkNotNullParameter(list, "cookies");
        for (String addCookieAsync : list) {
            addCookieAsync(str, addCookieAsync);
        }
        CookieManager cookieManager2 = getCookieManager();
        if (cookieManager2 != null) {
            cookieManager2.flush();
        }
    }

    private final void addCookieAsync(String str, String str2) {
        CookieManager cookieManager2 = getCookieManager();
        if (cookieManager2 != null) {
            cookieManager2.setCookie(str, str2, (ValueCallback) null);
        }
    }

    private final CookieManager getCookieManager() {
        if (this.cookieManager == null) {
            try {
                this.cookieManager = CookieManager.getInstance();
            } catch (Exception | IllegalArgumentException unused) {
                return null;
            }
        }
        return this.cookieManager;
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final boolean isCookieHeader(String str) {
            if (StringsKt.equals(str, ForwardingCookieHandler.VERSION_ZERO_HEADER, true) || StringsKt.equals(str, ForwardingCookieHandler.VERSION_ONE_HEADER, true)) {
                return true;
            }
            return false;
        }
    }
}
