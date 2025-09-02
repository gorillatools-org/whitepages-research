package okhttp3;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.perf.FirebasePerformance;
import com.salesforce.marketingcloud.storage.db.k;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpMethod;

public final class Request {
    private final RequestBody body;
    private final Headers headers;
    private CacheControl lazyCacheControl;
    private final String method;
    private final Map<Class<?>, Object> tags;
    private final HttpUrl url;

    public Request(HttpUrl httpUrl, String str, Headers headers2, RequestBody requestBody, Map<Class<?>, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(httpUrl, "url");
        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(headers2, "headers");
        Intrinsics.checkNotNullParameter(map, k.a.g);
        this.url = httpUrl;
        this.method = str;
        this.headers = headers2;
        this.body = requestBody;
        this.tags = map;
    }

    public final HttpUrl url() {
        return this.url;
    }

    public final String method() {
        return this.method;
    }

    public final Headers headers() {
        return this.headers;
    }

    public final RequestBody body() {
        return this.body;
    }

    public final Map<Class<?>, Object> getTags$okhttp() {
        return this.tags;
    }

    public final boolean isHttps() {
        return this.url.isHttps();
    }

    public final String header(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return this.headers.get(str);
    }

    public final List<String> headers(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return this.headers.values(str);
    }

    public final Object tag() {
        return tag(Object.class);
    }

    public final <T> T tag(Class<? extends T> cls) {
        Intrinsics.checkNotNullParameter(cls, "type");
        return cls.cast(this.tags.get(cls));
    }

    public final Builder newBuilder() {
        return new Builder(this);
    }

    public final CacheControl cacheControl() {
        CacheControl cacheControl = this.lazyCacheControl;
        if (cacheControl != null) {
            return cacheControl;
        }
        CacheControl parse = CacheControl.Companion.parse(this.headers);
        this.lazyCacheControl = parse;
        return parse;
    }

    /* renamed from: -deprecated_url  reason: not valid java name */
    public final HttpUrl m1048deprecated_url() {
        return this.url;
    }

    /* renamed from: -deprecated_method  reason: not valid java name */
    public final String m1047deprecated_method() {
        return this.method;
    }

    /* renamed from: -deprecated_headers  reason: not valid java name */
    public final Headers m1046deprecated_headers() {
        return this.headers;
    }

    /* renamed from: -deprecated_body  reason: not valid java name */
    public final RequestBody m1044deprecated_body() {
        return this.body;
    }

    /* renamed from: -deprecated_cacheControl  reason: not valid java name */
    public final CacheControl m1045deprecated_cacheControl() {
        return cacheControl();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request{method=");
        sb.append(this.method);
        sb.append(", url=");
        sb.append(this.url);
        if (this.headers.size() != 0) {
            sb.append(", headers=[");
            int i = 0;
            for (Object next : this.headers) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Pair pair = (Pair) next;
                String str = (String) pair.component1();
                String str2 = (String) pair.component2();
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(str);
                sb.append(':');
                sb.append(str2);
                i = i2;
            }
            sb.append(']');
        }
        if (!this.tags.isEmpty()) {
            sb.append(", tags=");
            sb.append(this.tags);
        }
        sb.append('}');
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    public static class Builder {
        private RequestBody body;
        private Headers.Builder headers;
        private String method;
        private Map<Class<?>, Object> tags;
        private HttpUrl url;

        public final Builder delete() {
            return delete$default(this, (RequestBody) null, 1, (Object) null);
        }

        public final HttpUrl getUrl$okhttp() {
            return this.url;
        }

        public final void setUrl$okhttp(HttpUrl httpUrl) {
            this.url = httpUrl;
        }

        public final String getMethod$okhttp() {
            return this.method;
        }

        public final void setMethod$okhttp(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.method = str;
        }

        public final Headers.Builder getHeaders$okhttp() {
            return this.headers;
        }

        public final void setHeaders$okhttp(Headers.Builder builder) {
            Intrinsics.checkNotNullParameter(builder, "<set-?>");
            this.headers = builder;
        }

        public final RequestBody getBody$okhttp() {
            return this.body;
        }

        public final void setBody$okhttp(RequestBody requestBody) {
            this.body = requestBody;
        }

        public final Map<Class<?>, Object> getTags$okhttp() {
            return this.tags;
        }

        public final void setTags$okhttp(Map<Class<?>, Object> map) {
            Intrinsics.checkNotNullParameter(map, "<set-?>");
            this.tags = map;
        }

        public Builder() {
            this.tags = new LinkedHashMap();
            this.method = "GET";
            this.headers = new Headers.Builder();
        }

        public Builder(Request request) {
            Map<Class<?>, Object> map;
            Intrinsics.checkNotNullParameter(request, "request");
            this.tags = new LinkedHashMap();
            this.url = request.url();
            this.method = request.method();
            this.body = request.body();
            if (request.getTags$okhttp().isEmpty()) {
                map = new LinkedHashMap<>();
            } else {
                map = MapsKt.toMutableMap(request.getTags$okhttp());
            }
            this.tags = map;
            this.headers = request.headers().newBuilder();
        }

        public Builder url(HttpUrl httpUrl) {
            Intrinsics.checkNotNullParameter(httpUrl, "url");
            this.url = httpUrl;
            return this;
        }

        public Builder url(String str) {
            Intrinsics.checkNotNullParameter(str, "url");
            if (StringsKt.startsWith(str, "ws:", true)) {
                StringBuilder sb = new StringBuilder();
                sb.append("http:");
                String substring = str.substring(3);
                Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.String).substring(startIndex)");
                sb.append(substring);
                str = sb.toString();
            } else if (StringsKt.startsWith(str, "wss:", true)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("https:");
                String substring2 = str.substring(4);
                Intrinsics.checkNotNullExpressionValue(substring2, "(this as java.lang.String).substring(startIndex)");
                sb2.append(substring2);
                str = sb2.toString();
            }
            return url(HttpUrl.Companion.get(str));
        }

        public Builder url(URL url2) {
            Intrinsics.checkNotNullParameter(url2, "url");
            HttpUrl.Companion companion = HttpUrl.Companion;
            String url3 = url2.toString();
            Intrinsics.checkNotNullExpressionValue(url3, "url.toString()");
            return url(companion.get(url3));
        }

        public Builder header(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(str2, "value");
            this.headers.set(str, str2);
            return this;
        }

        public Builder addHeader(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(str2, "value");
            this.headers.add(str, str2);
            return this;
        }

        public Builder removeHeader(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            this.headers.removeAll(str);
            return this;
        }

        public Builder headers(Headers headers2) {
            Intrinsics.checkNotNullParameter(headers2, "headers");
            this.headers = headers2.newBuilder();
            return this;
        }

        public Builder cacheControl(CacheControl cacheControl) {
            Intrinsics.checkNotNullParameter(cacheControl, "cacheControl");
            String cacheControl2 = cacheControl.toString();
            if (cacheControl2.length() == 0) {
                return removeHeader("Cache-Control");
            }
            return header("Cache-Control", cacheControl2);
        }

        public Builder get() {
            return method("GET", (RequestBody) null);
        }

        public Builder head() {
            return method(FirebasePerformance.HttpMethod.HEAD, (RequestBody) null);
        }

        public Builder post(RequestBody requestBody) {
            Intrinsics.checkNotNullParameter(requestBody, "body");
            return method("POST", requestBody);
        }

        public static /* synthetic */ Builder delete$default(Builder builder, RequestBody requestBody, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    requestBody = Util.EMPTY_REQUEST;
                }
                return builder.delete(requestBody);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: delete");
        }

        public Builder delete(RequestBody requestBody) {
            return method(FirebasePerformance.HttpMethod.DELETE, requestBody);
        }

        public Builder put(RequestBody requestBody) {
            Intrinsics.checkNotNullParameter(requestBody, "body");
            return method("PUT", requestBody);
        }

        public Builder patch(RequestBody requestBody) {
            Intrinsics.checkNotNullParameter(requestBody, "body");
            return method("PATCH", requestBody);
        }

        public Builder method(String str, RequestBody requestBody) {
            Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
            if (str.length() > 0) {
                if (requestBody == null) {
                    if (HttpMethod.requiresRequestBody(str)) {
                        throw new IllegalArgumentException(("method " + str + " must have a request body.").toString());
                    }
                } else if (!HttpMethod.permitsRequestBody(str)) {
                    throw new IllegalArgumentException(("method " + str + " must not have a request body.").toString());
                }
                this.method = str;
                this.body = requestBody;
                return this;
            }
            throw new IllegalArgumentException("method.isEmpty() == true");
        }

        public Builder tag(Object obj) {
            return tag(Object.class, obj);
        }

        public <T> Builder tag(Class<? super T> cls, T t) {
            Intrinsics.checkNotNullParameter(cls, "type");
            if (t == null) {
                this.tags.remove(cls);
            } else {
                if (this.tags.isEmpty()) {
                    this.tags = new LinkedHashMap();
                }
                Map<Class<?>, Object> map = this.tags;
                Object cast = cls.cast(t);
                Intrinsics.checkNotNull(cast);
                map.put(cls, cast);
            }
            return this;
        }

        public Request build() {
            HttpUrl httpUrl = this.url;
            if (httpUrl != null) {
                return new Request(httpUrl, this.method, this.headers.build(), this.body, Util.toImmutableMap(this.tags));
            }
            throw new IllegalStateException("url == null");
        }
    }
}
