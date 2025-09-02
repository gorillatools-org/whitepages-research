package com.facebook.imagepipeline.backends.okhttp3;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import okhttp3.Response;

public final class OkHttpNetworkFetcherException extends Exception {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Integer responseCode;
    private final Headers responseHeaders;

    public OkHttpNetworkFetcherException(Integer num, Headers headers) {
        this.responseCode = num;
        this.responseHeaders = headers;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final OkHttpNetworkFetcherException fromResponse(Response response) {
            Intrinsics.checkNotNullParameter(response, "response");
            Response networkResponse = response.networkResponse();
            Headers headers = null;
            Integer valueOf = networkResponse != null ? Integer.valueOf(networkResponse.code()) : null;
            Response networkResponse2 = response.networkResponse();
            if (networkResponse2 != null) {
                headers = networkResponse2.headers();
            }
            return new OkHttpNetworkFetcherException(valueOf, headers);
        }
    }
}
