package okhttp3;

import com.facebook.react.devsupport.StackTraceHelper;
import com.facebook.react.views.image.ReactImageView;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;
import okio.Source;

public final class Response implements Closeable {
    private final ResponseBody body;
    private final Response cacheResponse;
    private final int code;
    private final Exchange exchange;
    private final Handshake handshake;
    private final Headers headers;
    private CacheControl lazyCacheControl;
    private final String message;
    private final Response networkResponse;
    private final Response priorResponse;
    private final Protocol protocol;
    private final long receivedResponseAtMillis;
    private final Request request;
    private final long sentRequestAtMillis;

    public final String header(String str) {
        return header$default(this, str, (String) null, 2, (Object) null);
    }

    public Response(Request request2, Protocol protocol2, String str, int i, Handshake handshake2, Headers headers2, ResponseBody responseBody, Response response, Response response2, Response response3, long j, long j2, Exchange exchange2) {
        Intrinsics.checkNotNullParameter(request2, "request");
        Intrinsics.checkNotNullParameter(protocol2, "protocol");
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
        Intrinsics.checkNotNullParameter(headers2, "headers");
        this.request = request2;
        this.protocol = protocol2;
        this.message = str;
        this.code = i;
        this.handshake = handshake2;
        this.headers = headers2;
        this.body = responseBody;
        this.networkResponse = response;
        this.cacheResponse = response2;
        this.priorResponse = response3;
        this.sentRequestAtMillis = j;
        this.receivedResponseAtMillis = j2;
        this.exchange = exchange2;
    }

    public final Request request() {
        return this.request;
    }

    public final Protocol protocol() {
        return this.protocol;
    }

    public final String message() {
        return this.message;
    }

    public final int code() {
        return this.code;
    }

    public final Handshake handshake() {
        return this.handshake;
    }

    public final Headers headers() {
        return this.headers;
    }

    public final ResponseBody body() {
        return this.body;
    }

    public final Response networkResponse() {
        return this.networkResponse;
    }

    public final Response cacheResponse() {
        return this.cacheResponse;
    }

    public final Response priorResponse() {
        return this.priorResponse;
    }

    public final long sentRequestAtMillis() {
        return this.sentRequestAtMillis;
    }

    public final long receivedResponseAtMillis() {
        return this.receivedResponseAtMillis;
    }

    public final Exchange exchange() {
        return this.exchange;
    }

    /* renamed from: -deprecated_request  reason: not valid java name */
    public final Request m1060deprecated_request() {
        return this.request;
    }

    /* renamed from: -deprecated_protocol  reason: not valid java name */
    public final Protocol m1058deprecated_protocol() {
        return this.protocol;
    }

    /* renamed from: -deprecated_code  reason: not valid java name */
    public final int m1052deprecated_code() {
        return this.code;
    }

    public final boolean isSuccessful() {
        int i = this.code;
        return 200 <= i && 299 >= i;
    }

    /* renamed from: -deprecated_message  reason: not valid java name */
    public final String m1055deprecated_message() {
        return this.message;
    }

    /* renamed from: -deprecated_handshake  reason: not valid java name */
    public final Handshake m1053deprecated_handshake() {
        return this.handshake;
    }

    public final List<String> headers(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return this.headers.values(str);
    }

    public static /* synthetic */ String header$default(Response response, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        return response.header(str, str2);
    }

    public final String header(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "name");
        String str3 = this.headers.get(str);
        return str3 != null ? str3 : str2;
    }

    /* renamed from: -deprecated_headers  reason: not valid java name */
    public final Headers m1054deprecated_headers() {
        return this.headers;
    }

    public final Headers trailers() throws IOException {
        Exchange exchange2 = this.exchange;
        if (exchange2 != null) {
            return exchange2.trailers();
        }
        throw new IllegalStateException("trailers not available");
    }

    public final ResponseBody peekBody(long j) throws IOException {
        ResponseBody responseBody = this.body;
        Intrinsics.checkNotNull(responseBody);
        BufferedSource peek = responseBody.source().peek();
        Buffer buffer = new Buffer();
        peek.request(j);
        buffer.write((Source) peek, Math.min(j, peek.getBuffer().size()));
        return ResponseBody.Companion.create((BufferedSource) buffer, this.body.contentType(), buffer.size());
    }

    /* renamed from: -deprecated_body  reason: not valid java name */
    public final ResponseBody m1049deprecated_body() {
        return this.body;
    }

    public final Builder newBuilder() {
        return new Builder(this);
    }

    public final boolean isRedirect() {
        int i = this.code;
        if (!(i == 307 || i == 308)) {
            switch (i) {
                case ReactImageView.REMOTE_IMAGE_FADE_DURATION_MS:
                case 301:
                case 302:
                case 303:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    /* renamed from: -deprecated_networkResponse  reason: not valid java name */
    public final Response m1056deprecated_networkResponse() {
        return this.networkResponse;
    }

    /* renamed from: -deprecated_cacheResponse  reason: not valid java name */
    public final Response m1051deprecated_cacheResponse() {
        return this.cacheResponse;
    }

    /* renamed from: -deprecated_priorResponse  reason: not valid java name */
    public final Response m1057deprecated_priorResponse() {
        return this.priorResponse;
    }

    public final List<Challenge> challenges() {
        String str;
        Headers headers2 = this.headers;
        int i = this.code;
        if (i == 401) {
            str = "WWW-Authenticate";
        } else if (i != 407) {
            return CollectionsKt.emptyList();
        } else {
            str = "Proxy-Authenticate";
        }
        return HttpHeaders.parseChallenges(headers2, str);
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

    /* renamed from: -deprecated_cacheControl  reason: not valid java name */
    public final CacheControl m1050deprecated_cacheControl() {
        return cacheControl();
    }

    /* renamed from: -deprecated_sentRequestAtMillis  reason: not valid java name */
    public final long m1061deprecated_sentRequestAtMillis() {
        return this.sentRequestAtMillis;
    }

    /* renamed from: -deprecated_receivedResponseAtMillis  reason: not valid java name */
    public final long m1059deprecated_receivedResponseAtMillis() {
        return this.receivedResponseAtMillis;
    }

    public void close() {
        ResponseBody responseBody = this.body;
        if (responseBody != null) {
            responseBody.close();
            return;
        }
        throw new IllegalStateException("response is not eligible for a body and must not be closed");
    }

    public String toString() {
        return "Response{protocol=" + this.protocol + ", code=" + this.code + ", message=" + this.message + ", url=" + this.request.url() + '}';
    }

    public static class Builder {
        private ResponseBody body;
        private Response cacheResponse;
        private int code;
        private Exchange exchange;
        private Handshake handshake;
        private Headers.Builder headers;
        private String message;
        private Response networkResponse;
        private Response priorResponse;
        private Protocol protocol;
        private long receivedResponseAtMillis;
        private Request request;
        private long sentRequestAtMillis;

        public final Request getRequest$okhttp() {
            return this.request;
        }

        public final void setRequest$okhttp(Request request2) {
            this.request = request2;
        }

        public final Protocol getProtocol$okhttp() {
            return this.protocol;
        }

        public final void setProtocol$okhttp(Protocol protocol2) {
            this.protocol = protocol2;
        }

        public final int getCode$okhttp() {
            return this.code;
        }

        public final void setCode$okhttp(int i) {
            this.code = i;
        }

        public final String getMessage$okhttp() {
            return this.message;
        }

        public final void setMessage$okhttp(String str) {
            this.message = str;
        }

        public final Handshake getHandshake$okhttp() {
            return this.handshake;
        }

        public final void setHandshake$okhttp(Handshake handshake2) {
            this.handshake = handshake2;
        }

        public final Headers.Builder getHeaders$okhttp() {
            return this.headers;
        }

        public final void setHeaders$okhttp(Headers.Builder builder) {
            Intrinsics.checkNotNullParameter(builder, "<set-?>");
            this.headers = builder;
        }

        public final ResponseBody getBody$okhttp() {
            return this.body;
        }

        public final void setBody$okhttp(ResponseBody responseBody) {
            this.body = responseBody;
        }

        public final Response getNetworkResponse$okhttp() {
            return this.networkResponse;
        }

        public final void setNetworkResponse$okhttp(Response response) {
            this.networkResponse = response;
        }

        public final Response getCacheResponse$okhttp() {
            return this.cacheResponse;
        }

        public final void setCacheResponse$okhttp(Response response) {
            this.cacheResponse = response;
        }

        public final Response getPriorResponse$okhttp() {
            return this.priorResponse;
        }

        public final void setPriorResponse$okhttp(Response response) {
            this.priorResponse = response;
        }

        public final long getSentRequestAtMillis$okhttp() {
            return this.sentRequestAtMillis;
        }

        public final void setSentRequestAtMillis$okhttp(long j) {
            this.sentRequestAtMillis = j;
        }

        public final long getReceivedResponseAtMillis$okhttp() {
            return this.receivedResponseAtMillis;
        }

        public final void setReceivedResponseAtMillis$okhttp(long j) {
            this.receivedResponseAtMillis = j;
        }

        public final Exchange getExchange$okhttp() {
            return this.exchange;
        }

        public final void setExchange$okhttp(Exchange exchange2) {
            this.exchange = exchange2;
        }

        public Builder() {
            this.code = -1;
            this.headers = new Headers.Builder();
        }

        public Builder(Response response) {
            Intrinsics.checkNotNullParameter(response, "response");
            this.code = -1;
            this.request = response.request();
            this.protocol = response.protocol();
            this.code = response.code();
            this.message = response.message();
            this.handshake = response.handshake();
            this.headers = response.headers().newBuilder();
            this.body = response.body();
            this.networkResponse = response.networkResponse();
            this.cacheResponse = response.cacheResponse();
            this.priorResponse = response.priorResponse();
            this.sentRequestAtMillis = response.sentRequestAtMillis();
            this.receivedResponseAtMillis = response.receivedResponseAtMillis();
            this.exchange = response.exchange();
        }

        public Builder request(Request request2) {
            Intrinsics.checkNotNullParameter(request2, "request");
            this.request = request2;
            return this;
        }

        public Builder protocol(Protocol protocol2) {
            Intrinsics.checkNotNullParameter(protocol2, "protocol");
            this.protocol = protocol2;
            return this;
        }

        public Builder code(int i) {
            this.code = i;
            return this;
        }

        public Builder message(String str) {
            Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
            this.message = str;
            return this;
        }

        public Builder handshake(Handshake handshake2) {
            this.handshake = handshake2;
            return this;
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

        public Builder body(ResponseBody responseBody) {
            this.body = responseBody;
            return this;
        }

        public Builder networkResponse(Response response) {
            checkSupportResponse("networkResponse", response);
            this.networkResponse = response;
            return this;
        }

        public Builder cacheResponse(Response response) {
            checkSupportResponse("cacheResponse", response);
            this.cacheResponse = response;
            return this;
        }

        private final void checkSupportResponse(String str, Response response) {
            if (response != null) {
                boolean z = false;
                if (response.body() == null) {
                    if (response.networkResponse() == null) {
                        if (response.cacheResponse() == null) {
                            if (response.priorResponse() == null) {
                                z = true;
                            }
                            if (!z) {
                                throw new IllegalArgumentException((str + ".priorResponse != null").toString());
                            }
                            return;
                        }
                        throw new IllegalArgumentException((str + ".cacheResponse != null").toString());
                    }
                    throw new IllegalArgumentException((str + ".networkResponse != null").toString());
                }
                throw new IllegalArgumentException((str + ".body != null").toString());
            }
        }

        public Builder priorResponse(Response response) {
            checkPriorResponse(response);
            this.priorResponse = response;
            return this;
        }

        private final void checkPriorResponse(Response response) {
            if (response != null) {
                if (!(response.body() == null)) {
                    throw new IllegalArgumentException("priorResponse.body != null");
                }
            }
        }

        public Builder sentRequestAtMillis(long j) {
            this.sentRequestAtMillis = j;
            return this;
        }

        public Builder receivedResponseAtMillis(long j) {
            this.receivedResponseAtMillis = j;
            return this;
        }

        public final void initExchange$okhttp(Exchange exchange2) {
            Intrinsics.checkNotNullParameter(exchange2, "deferredTrailers");
            this.exchange = exchange2;
        }

        public Response build() {
            int i = this.code;
            if (i >= 0) {
                Request request2 = this.request;
                if (request2 != null) {
                    Protocol protocol2 = this.protocol;
                    if (protocol2 != null) {
                        String str = this.message;
                        if (str != null) {
                            return new Response(request2, protocol2, str, i, this.handshake, this.headers.build(), this.body, this.networkResponse, this.cacheResponse, this.priorResponse, this.sentRequestAtMillis, this.receivedResponseAtMillis, this.exchange);
                        }
                        throw new IllegalStateException("message == null");
                    }
                    throw new IllegalStateException("protocol == null");
                }
                throw new IllegalStateException("request == null");
            }
            throw new IllegalStateException(("code < 0: " + this.code).toString());
        }
    }
}
