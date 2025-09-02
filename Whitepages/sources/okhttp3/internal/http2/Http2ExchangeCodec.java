package okhttp3.internal.http2;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.Sink;
import okio.Source;

public final class Http2ExchangeCodec implements ExchangeCodec {
    private static final String CONNECTION = "connection";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String ENCODING = "encoding";
    private static final String HOST = "host";
    /* access modifiers changed from: private */
    public static final List<String> HTTP_2_SKIPPED_REQUEST_HEADERS = Util.immutableListOf(CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, TE, TRANSFER_ENCODING, ENCODING, UPGRADE, Header.TARGET_METHOD_UTF8, Header.TARGET_PATH_UTF8, Header.TARGET_SCHEME_UTF8, Header.TARGET_AUTHORITY_UTF8);
    /* access modifiers changed from: private */
    public static final List<String> HTTP_2_SKIPPED_RESPONSE_HEADERS = Util.immutableListOf(CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, TE, TRANSFER_ENCODING, ENCODING, UPGRADE);
    private static final String KEEP_ALIVE = "keep-alive";
    private static final String PROXY_CONNECTION = "proxy-connection";
    private static final String TE = "te";
    private static final String TRANSFER_ENCODING = "transfer-encoding";
    private static final String UPGRADE = "upgrade";
    private volatile boolean canceled;
    private final RealInterceptorChain chain;
    private final RealConnection connection;
    private final Http2Connection http2Connection;
    private final Protocol protocol;
    private volatile Http2Stream stream;

    public Http2ExchangeCodec(OkHttpClient okHttpClient, RealConnection realConnection, RealInterceptorChain realInterceptorChain, Http2Connection http2Connection2) {
        Intrinsics.checkNotNullParameter(okHttpClient, "client");
        Intrinsics.checkNotNullParameter(realConnection, CONNECTION);
        Intrinsics.checkNotNullParameter(realInterceptorChain, "chain");
        Intrinsics.checkNotNullParameter(http2Connection2, "http2Connection");
        this.connection = realConnection;
        this.chain = realInterceptorChain;
        this.http2Connection = http2Connection2;
        List<Protocol> protocols = okHttpClient.protocols();
        Protocol protocol2 = Protocol.H2_PRIOR_KNOWLEDGE;
        this.protocol = !protocols.contains(protocol2) ? Protocol.HTTP_2 : protocol2;
    }

    public RealConnection getConnection() {
        return this.connection;
    }

    public Sink createRequestBody(Request request, long j) {
        Intrinsics.checkNotNullParameter(request, "request");
        Http2Stream http2Stream = this.stream;
        Intrinsics.checkNotNull(http2Stream);
        return http2Stream.getSink();
    }

    public void writeRequestHeaders(Request request) {
        Intrinsics.checkNotNullParameter(request, "request");
        if (this.stream == null) {
            this.stream = this.http2Connection.newStream(Companion.http2HeadersList(request), request.body() != null);
            if (!this.canceled) {
                Http2Stream http2Stream = this.stream;
                Intrinsics.checkNotNull(http2Stream);
                TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                http2Stream.readTimeout().timeout((long) this.chain.getReadTimeoutMillis$okhttp(), timeUnit);
                Http2Stream http2Stream2 = this.stream;
                Intrinsics.checkNotNull(http2Stream2);
                http2Stream2.writeTimeout().timeout((long) this.chain.getWriteTimeoutMillis$okhttp(), timeUnit);
                return;
            }
            Http2Stream http2Stream3 = this.stream;
            Intrinsics.checkNotNull(http2Stream3);
            http2Stream3.closeLater(ErrorCode.CANCEL);
            throw new IOException("Canceled");
        }
    }

    public void flushRequest() {
        this.http2Connection.flush();
    }

    public void finishRequest() {
        Http2Stream http2Stream = this.stream;
        Intrinsics.checkNotNull(http2Stream);
        http2Stream.getSink().close();
    }

    public Response.Builder readResponseHeaders(boolean z) {
        Http2Stream http2Stream = this.stream;
        Intrinsics.checkNotNull(http2Stream);
        Response.Builder readHttp2HeadersList = Companion.readHttp2HeadersList(http2Stream.takeHeaders(), this.protocol);
        if (!z || readHttp2HeadersList.getCode$okhttp() != 100) {
            return readHttp2HeadersList;
        }
        return null;
    }

    public long reportedContentLength(Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        if (!HttpHeaders.promisesBody(response)) {
            return 0;
        }
        return Util.headersContentLength(response);
    }

    public Source openResponseBodySource(Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        Http2Stream http2Stream = this.stream;
        Intrinsics.checkNotNull(http2Stream);
        return http2Stream.getSource$okhttp();
    }

    public Headers trailers() {
        Http2Stream http2Stream = this.stream;
        Intrinsics.checkNotNull(http2Stream);
        return http2Stream.trailers();
    }

    public void cancel() {
        this.canceled = true;
        Http2Stream http2Stream = this.stream;
        if (http2Stream != null) {
            http2Stream.closeLater(ErrorCode.CANCEL);
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final List<Header> http2HeadersList(Request request) {
            Intrinsics.checkNotNullParameter(request, "request");
            Headers headers = request.headers();
            ArrayList arrayList = new ArrayList(headers.size() + 4);
            arrayList.add(new Header(Header.TARGET_METHOD, request.method()));
            arrayList.add(new Header(Header.TARGET_PATH, RequestLine.INSTANCE.requestPath(request.url())));
            String header = request.header("Host");
            if (header != null) {
                arrayList.add(new Header(Header.TARGET_AUTHORITY, header));
            }
            arrayList.add(new Header(Header.TARGET_SCHEME, request.url().scheme()));
            int size = headers.size();
            int i = 0;
            while (i < size) {
                String name = headers.name(i);
                Locale locale = Locale.US;
                Intrinsics.checkNotNullExpressionValue(locale, "Locale.US");
                if (name != null) {
                    String lowerCase = name.toLowerCase(locale);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
                    if (!Http2ExchangeCodec.HTTP_2_SKIPPED_REQUEST_HEADERS.contains(lowerCase) || (Intrinsics.areEqual((Object) lowerCase, (Object) Http2ExchangeCodec.TE) && Intrinsics.areEqual((Object) headers.value(i), (Object) "trailers"))) {
                        arrayList.add(new Header(lowerCase, headers.value(i)));
                    }
                    i++;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
            }
            return arrayList;
        }

        public final Response.Builder readHttp2HeadersList(Headers headers, Protocol protocol) {
            Intrinsics.checkNotNullParameter(headers, "headerBlock");
            Intrinsics.checkNotNullParameter(protocol, "protocol");
            Headers.Builder builder = new Headers.Builder();
            int size = headers.size();
            StatusLine statusLine = null;
            for (int i = 0; i < size; i++) {
                String name = headers.name(i);
                String value = headers.value(i);
                if (Intrinsics.areEqual((Object) name, (Object) Header.RESPONSE_STATUS_UTF8)) {
                    StatusLine.Companion companion = StatusLine.Companion;
                    statusLine = companion.parse("HTTP/1.1 " + value);
                } else if (!Http2ExchangeCodec.HTTP_2_SKIPPED_RESPONSE_HEADERS.contains(name)) {
                    builder.addLenient$okhttp(name, value);
                }
            }
            if (statusLine != null) {
                return new Response.Builder().protocol(protocol).code(statusLine.code).message(statusLine.message).headers(builder.build());
            }
            throw new ProtocolException("Expected ':status' header not present");
        }
    }
}
