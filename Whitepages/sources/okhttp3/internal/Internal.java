package okhttp3.internal;

import javax.net.ssl.SSLSocket;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Cache;
import okhttp3.ConnectionSpec;
import okhttp3.Cookie;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

public final class Internal {
    public static final Cookie parseCookie(long j, HttpUrl httpUrl, String str) {
        Intrinsics.checkNotNullParameter(httpUrl, "url");
        Intrinsics.checkNotNullParameter(str, "setCookie");
        return Cookie.Companion.parse$okhttp(j, httpUrl, str);
    }

    public static final String cookieToString(Cookie cookie, boolean z) {
        Intrinsics.checkNotNullParameter(cookie, "cookie");
        return cookie.toString$okhttp(z);
    }

    public static final Headers.Builder addHeaderLenient(Headers.Builder builder, String str) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        Intrinsics.checkNotNullParameter(str, "line");
        return builder.addLenient$okhttp(str);
    }

    public static final Headers.Builder addHeaderLenient(Headers.Builder builder, String str, String str2) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "value");
        return builder.addLenient$okhttp(str, str2);
    }

    public static final Response cacheGet(Cache cache, Request request) {
        Intrinsics.checkNotNullParameter(cache, "cache");
        Intrinsics.checkNotNullParameter(request, "request");
        return cache.get$okhttp(request);
    }

    public static final void applyConnectionSpec(ConnectionSpec connectionSpec, SSLSocket sSLSocket, boolean z) {
        Intrinsics.checkNotNullParameter(connectionSpec, "connectionSpec");
        Intrinsics.checkNotNullParameter(sSLSocket, "sslSocket");
        connectionSpec.apply$okhttp(sSLSocket, z);
    }
}
