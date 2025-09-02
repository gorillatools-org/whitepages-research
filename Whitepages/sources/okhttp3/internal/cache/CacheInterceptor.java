package okhttp3.internal.cache;

import java.io.Closeable;
import java.io.IOException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.RealResponseBody;
import okio.Okio;
import okio.Sink;
import okio.Source;

public final class CacheInterceptor implements Interceptor {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Cache cache;

    public CacheInterceptor(Cache cache2) {
        this.cache = cache2;
    }

    public final Cache getCache$okhttp() {
        return this.cache;
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        EventListener eventListener;
        ResponseBody body;
        ResponseBody body2;
        Intrinsics.checkNotNullParameter(chain, "chain");
        Call call = chain.call();
        Cache cache2 = this.cache;
        RealCall realCall = null;
        Response response = cache2 != null ? cache2.get$okhttp(chain.request()) : null;
        CacheStrategy compute = new CacheStrategy.Factory(System.currentTimeMillis(), chain.request(), response).compute();
        Request networkRequest = compute.getNetworkRequest();
        Response cacheResponse = compute.getCacheResponse();
        Cache cache3 = this.cache;
        if (cache3 != null) {
            cache3.trackResponse$okhttp(compute);
        }
        if (call instanceof RealCall) {
            realCall = call;
        }
        RealCall realCall2 = realCall;
        if (realCall2 == null || (eventListener = realCall2.getEventListener$okhttp()) == null) {
            eventListener = EventListener.NONE;
        }
        if (!(response == null || cacheResponse != null || (body2 = response.body()) == null)) {
            Util.closeQuietly((Closeable) body2);
        }
        if (networkRequest == null && cacheResponse == null) {
            Response build = new Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1).code(504).message("Unsatisfiable Request (only-if-cached)").body(Util.EMPTY_RESPONSE).sentRequestAtMillis(-1).receivedResponseAtMillis(System.currentTimeMillis()).build();
            eventListener.satisfactionFailure(call, build);
            return build;
        } else if (networkRequest == null) {
            Intrinsics.checkNotNull(cacheResponse);
            Response build2 = cacheResponse.newBuilder().cacheResponse(Companion.stripBody(cacheResponse)).build();
            eventListener.cacheHit(call, build2);
            return build2;
        } else {
            if (cacheResponse != null) {
                eventListener.cacheConditionalHit(call, cacheResponse);
            } else if (this.cache != null) {
                eventListener.cacheMiss(call);
            }
            try {
                Response proceed = chain.proceed(networkRequest);
                if (!(proceed != null || response == null || body == null)) {
                }
                if (cacheResponse != null) {
                    if (proceed == null || proceed.code() != 304) {
                        ResponseBody body3 = cacheResponse.body();
                        if (body3 != null) {
                            Util.closeQuietly((Closeable) body3);
                        }
                    } else {
                        Response.Builder newBuilder = cacheResponse.newBuilder();
                        Companion companion = Companion;
                        Response build3 = newBuilder.headers(companion.combine(cacheResponse.headers(), proceed.headers())).sentRequestAtMillis(proceed.sentRequestAtMillis()).receivedResponseAtMillis(proceed.receivedResponseAtMillis()).cacheResponse(companion.stripBody(cacheResponse)).networkResponse(companion.stripBody(proceed)).build();
                        ResponseBody body4 = proceed.body();
                        Intrinsics.checkNotNull(body4);
                        body4.close();
                        Cache cache4 = this.cache;
                        Intrinsics.checkNotNull(cache4);
                        cache4.trackConditionalCacheHit$okhttp();
                        this.cache.update$okhttp(cacheResponse, build3);
                        eventListener.cacheHit(call, build3);
                        return build3;
                    }
                }
                Intrinsics.checkNotNull(proceed);
                Response.Builder newBuilder2 = proceed.newBuilder();
                Companion companion2 = Companion;
                Response build4 = newBuilder2.cacheResponse(companion2.stripBody(cacheResponse)).networkResponse(companion2.stripBody(proceed)).build();
                if (this.cache != null) {
                    if (HttpHeaders.promisesBody(build4) && CacheStrategy.Companion.isCacheable(build4, networkRequest)) {
                        Response cacheWritingResponse = cacheWritingResponse(this.cache.put$okhttp(build4), build4);
                        if (cacheResponse != null) {
                            eventListener.cacheMiss(call);
                        }
                        return cacheWritingResponse;
                    } else if (HttpMethod.INSTANCE.invalidatesCache(networkRequest.method())) {
                        try {
                            this.cache.remove$okhttp(networkRequest);
                        } catch (IOException unused) {
                        }
                    }
                }
                return build4;
            } finally {
                if (!(response == null || (body = response.body()) == null)) {
                    Util.closeQuietly((Closeable) body);
                }
            }
        }
    }

    private final Response cacheWritingResponse(CacheRequest cacheRequest, Response response) throws IOException {
        if (cacheRequest == null) {
            return response;
        }
        Sink body = cacheRequest.body();
        ResponseBody body2 = response.body();
        Intrinsics.checkNotNull(body2);
        CacheInterceptor$cacheWritingResponse$cacheWritingSource$1 cacheInterceptor$cacheWritingResponse$cacheWritingSource$1 = new CacheInterceptor$cacheWritingResponse$cacheWritingSource$1(body2.source(), cacheRequest, Okio.buffer(body));
        return response.newBuilder().body(new RealResponseBody(Response.header$default(response, "Content-Type", (String) null, 2, (Object) null), response.body().contentLength(), Okio.buffer((Source) cacheInterceptor$cacheWritingResponse$cacheWritingSource$1))).build();
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final Response stripBody(Response response) {
            return (response != null ? response.body() : null) != null ? response.newBuilder().body((ResponseBody) null).build() : response;
        }

        /* access modifiers changed from: private */
        public final Headers combine(Headers headers, Headers headers2) {
            Headers.Builder builder = new Headers.Builder();
            int size = headers.size();
            for (int i = 0; i < size; i++) {
                String name = headers.name(i);
                String value = headers.value(i);
                if ((!StringsKt.equals("Warning", name, true) || !StringsKt.startsWith$default(value, "1", false, 2, (Object) null)) && (isContentSpecificHeader(name) || !isEndToEnd(name) || headers2.get(name) == null)) {
                    builder.addLenient$okhttp(name, value);
                }
            }
            int size2 = headers2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                String name2 = headers2.name(i2);
                if (!isContentSpecificHeader(name2) && isEndToEnd(name2)) {
                    builder.addLenient$okhttp(name2, headers2.value(i2));
                }
            }
            return builder.build();
        }

        private final boolean isEndToEnd(String str) {
            if (StringsKt.equals("Connection", str, true) || StringsKt.equals("Keep-Alive", str, true) || StringsKt.equals("Proxy-Authenticate", str, true) || StringsKt.equals("Proxy-Authorization", str, true) || StringsKt.equals("TE", str, true) || StringsKt.equals("Trailers", str, true) || StringsKt.equals("Transfer-Encoding", str, true) || StringsKt.equals("Upgrade", str, true)) {
                return false;
            }
            return true;
        }

        private final boolean isContentSpecificHeader(String str) {
            if (StringsKt.equals("Content-Length", str, true) || StringsKt.equals("Content-Encoding", str, true) || StringsKt.equals("Content-Type", str, true)) {
                return true;
            }
            return false;
        }
    }
}
