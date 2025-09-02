package com.facebook.react.modules.network;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;

public final class ReactCookieJarContainer implements CookieJarContainer {
    private CookieJar cookieJar;

    public void setCookieJar(CookieJar cookieJar2) {
        Intrinsics.checkNotNullParameter(cookieJar2, "cookieJar");
        this.cookieJar = cookieJar2;
    }

    public void removeCookieJar() {
        this.cookieJar = null;
    }

    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        Intrinsics.checkNotNullParameter(httpUrl, "url");
        Intrinsics.checkNotNullParameter(list, "cookies");
        CookieJar cookieJar2 = this.cookieJar;
        if (cookieJar2 != null) {
            cookieJar2.saveFromResponse(httpUrl, list);
        }
    }

    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        Intrinsics.checkNotNullParameter(httpUrl, "url");
        CookieJar cookieJar2 = this.cookieJar;
        if (cookieJar2 == null) {
            return CollectionsKt.emptyList();
        }
        List<Cookie> loadForRequest = cookieJar2.loadForRequest(httpUrl);
        ArrayList arrayList = new ArrayList();
        for (Cookie next : loadForRequest) {
            try {
                new Headers.Builder().add(next.m966deprecated_name(), next.m970deprecated_value());
                arrayList.add(next);
            } catch (IllegalArgumentException unused) {
            }
        }
        return arrayList;
    }
}
