package okhttp3;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

public final class Cache implements Closeable, Flushable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int ENTRY_BODY = 1;
    private static final int ENTRY_COUNT = 2;
    private static final int ENTRY_METADATA = 0;
    private static final int VERSION = 201105;
    private final DiskLruCache cache;
    private int hitCount;
    private int networkCount;
    private int requestCount;
    private int writeAbortCount;
    private int writeSuccessCount;

    public static final String key(HttpUrl httpUrl) {
        return Companion.key(httpUrl);
    }

    public Cache(File file, long j, FileSystem fileSystem) {
        Intrinsics.checkNotNullParameter(file, "directory");
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        this.cache = new DiskLruCache(fileSystem, file, VERSION, 2, j, TaskRunner.INSTANCE);
    }

    public final DiskLruCache getCache$okhttp() {
        return this.cache;
    }

    public final int getWriteSuccessCount$okhttp() {
        return this.writeSuccessCount;
    }

    public final void setWriteSuccessCount$okhttp(int i) {
        this.writeSuccessCount = i;
    }

    public final int getWriteAbortCount$okhttp() {
        return this.writeAbortCount;
    }

    public final void setWriteAbortCount$okhttp(int i) {
        this.writeAbortCount = i;
    }

    public final boolean isClosed() {
        return this.cache.isClosed();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Cache(File file, long j) {
        this(file, j, FileSystem.SYSTEM);
        Intrinsics.checkNotNullParameter(file, "directory");
    }

    public final Response get$okhttp(Request request) {
        Intrinsics.checkNotNullParameter(request, "request");
        try {
            DiskLruCache.Snapshot snapshot = this.cache.get(Companion.key(request.url()));
            if (snapshot != null) {
                try {
                    Entry entry = new Entry(snapshot.getSource(0));
                    Response response = entry.response(snapshot);
                    if (entry.matches(request, response)) {
                        return response;
                    }
                    ResponseBody body = response.body();
                    if (body != null) {
                        Util.closeQuietly((Closeable) body);
                    }
                    return null;
                } catch (IOException unused) {
                    Util.closeQuietly((Closeable) snapshot);
                }
            }
        } catch (IOException unused2) {
        }
        return null;
    }

    public final CacheRequest put$okhttp(Response response) {
        DiskLruCache.Editor editor;
        Intrinsics.checkNotNullParameter(response, "response");
        String method = response.request().method();
        if (HttpMethod.INSTANCE.invalidatesCache(response.request().method())) {
            try {
                remove$okhttp(response.request());
            } catch (IOException unused) {
            }
            return null;
        } else if (!Intrinsics.areEqual((Object) method, (Object) "GET")) {
            return null;
        } else {
            Companion companion = Companion;
            if (companion.hasVaryAll(response)) {
                return null;
            }
            Entry entry = new Entry(response);
            try {
                editor = DiskLruCache.edit$default(this.cache, companion.key(response.request().url()), 0, 2, (Object) null);
                if (editor == null) {
                    return null;
                }
                try {
                    entry.writeTo(editor);
                    return new RealCacheRequest(this, editor);
                } catch (IOException unused2) {
                    abortQuietly(editor);
                    return null;
                }
            } catch (IOException unused3) {
                editor = null;
                abortQuietly(editor);
                return null;
            }
        }
    }

    public final void remove$okhttp(Request request) throws IOException {
        Intrinsics.checkNotNullParameter(request, "request");
        this.cache.remove(Companion.key(request.url()));
    }

    public final void update$okhttp(Response response, Response response2) {
        DiskLruCache.Editor editor;
        Intrinsics.checkNotNullParameter(response, "cached");
        Intrinsics.checkNotNullParameter(response2, "network");
        Entry entry = new Entry(response2);
        ResponseBody body = response.body();
        if (body != null) {
            try {
                editor = ((CacheResponseBody) body).getSnapshot().edit();
                if (editor != null) {
                    try {
                        entry.writeTo(editor);
                        editor.commit();
                    } catch (IOException unused) {
                    }
                }
            } catch (IOException unused2) {
                editor = null;
                abortQuietly(editor);
            }
        } else {
            throw new NullPointerException("null cannot be cast to non-null type okhttp3.Cache.CacheResponseBody");
        }
    }

    private final void abortQuietly(DiskLruCache.Editor editor) {
        if (editor != null) {
            try {
                editor.abort();
            } catch (IOException unused) {
            }
        }
    }

    public final void initialize() throws IOException {
        this.cache.initialize();
    }

    public final void delete() throws IOException {
        this.cache.delete();
    }

    public final void evictAll() throws IOException {
        this.cache.evictAll();
    }

    public final Iterator<String> urls() throws IOException {
        return new Cache$urls$1(this);
    }

    public final synchronized int writeAbortCount() {
        return this.writeAbortCount;
    }

    public final synchronized int writeSuccessCount() {
        return this.writeSuccessCount;
    }

    public final long size() throws IOException {
        return this.cache.size();
    }

    public final long maxSize() {
        return this.cache.getMaxSize();
    }

    public void flush() throws IOException {
        this.cache.flush();
    }

    public void close() throws IOException {
        this.cache.close();
    }

    public final File directory() {
        return this.cache.getDirectory();
    }

    /* renamed from: -deprecated_directory  reason: not valid java name */
    public final File m943deprecated_directory() {
        return this.cache.getDirectory();
    }

    public final synchronized void trackResponse$okhttp(CacheStrategy cacheStrategy) {
        try {
            Intrinsics.checkNotNullParameter(cacheStrategy, "cacheStrategy");
            this.requestCount++;
            if (cacheStrategy.getNetworkRequest() != null) {
                this.networkCount++;
            } else if (cacheStrategy.getCacheResponse() != null) {
                this.hitCount++;
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public final synchronized void trackConditionalCacheHit$okhttp() {
        this.hitCount++;
    }

    public final synchronized int networkCount() {
        return this.networkCount;
    }

    public final synchronized int hitCount() {
        return this.hitCount;
    }

    public final synchronized int requestCount() {
        return this.requestCount;
    }

    private final class RealCacheRequest implements CacheRequest {
        private final Sink body;
        private final Sink cacheOut;
        private boolean done;
        /* access modifiers changed from: private */
        public final DiskLruCache.Editor editor;
        final /* synthetic */ Cache this$0;

        public RealCacheRequest(Cache cache, DiskLruCache.Editor editor2) {
            Intrinsics.checkNotNullParameter(editor2, "editor");
            this.this$0 = cache;
            this.editor = editor2;
            Sink newSink = editor2.newSink(1);
            this.cacheOut = newSink;
            this.body = new ForwardingSink(this, newSink) {
                final /* synthetic */ RealCacheRequest this$0;

                {
                    this.this$0 = r1;
                }

                public void close() throws IOException {
                    synchronized (this.this$0.this$0) {
                        if (!this.this$0.getDone()) {
                            this.this$0.setDone(true);
                            Cache cache = this.this$0.this$0;
                            cache.setWriteSuccessCount$okhttp(cache.getWriteSuccessCount$okhttp() + 1);
                            super.close();
                            this.this$0.editor.commit();
                        }
                    }
                }
            };
        }

        public final boolean getDone() {
            return this.done;
        }

        public final void setDone(boolean z) {
            this.done = z;
        }

        public void abort() {
            synchronized (this.this$0) {
                if (!this.done) {
                    this.done = true;
                    Cache cache = this.this$0;
                    cache.setWriteAbortCount$okhttp(cache.getWriteAbortCount$okhttp() + 1);
                    Util.closeQuietly((Closeable) this.cacheOut);
                    try {
                        this.editor.abort();
                    } catch (IOException unused) {
                    }
                }
            }
        }

        public Sink body() {
            return this.body;
        }
    }

    private static final class Entry {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private static final String RECEIVED_MILLIS;
        private static final String SENT_MILLIS;
        private final int code;
        private final Handshake handshake;
        private final String message;
        private final Protocol protocol;
        private final long receivedResponseMillis;
        private final String requestMethod;
        private final Headers responseHeaders;
        private final long sentRequestMillis;
        private final String url;
        private final Headers varyHeaders;

        private final boolean isHttps() {
            return StringsKt.startsWith$default(this.url, "https://", false, 2, (Object) null);
        }

        public Entry(Source source) throws IOException {
            TlsVersion tlsVersion;
            Intrinsics.checkNotNullParameter(source, "rawSource");
            try {
                BufferedSource buffer = Okio.buffer(source);
                this.url = buffer.readUtf8LineStrict();
                this.requestMethod = buffer.readUtf8LineStrict();
                Headers.Builder builder = new Headers.Builder();
                int readInt$okhttp = Cache.Companion.readInt$okhttp(buffer);
                for (int i = 0; i < readInt$okhttp; i++) {
                    builder.addLenient$okhttp(buffer.readUtf8LineStrict());
                }
                this.varyHeaders = builder.build();
                StatusLine parse = StatusLine.Companion.parse(buffer.readUtf8LineStrict());
                this.protocol = parse.protocol;
                this.code = parse.code;
                this.message = parse.message;
                Headers.Builder builder2 = new Headers.Builder();
                int readInt$okhttp2 = Cache.Companion.readInt$okhttp(buffer);
                for (int i2 = 0; i2 < readInt$okhttp2; i2++) {
                    builder2.addLenient$okhttp(buffer.readUtf8LineStrict());
                }
                String str = SENT_MILLIS;
                String str2 = builder2.get(str);
                String str3 = RECEIVED_MILLIS;
                String str4 = builder2.get(str3);
                builder2.removeAll(str);
                builder2.removeAll(str3);
                long j = 0;
                this.sentRequestMillis = str2 != null ? Long.parseLong(str2) : 0;
                this.receivedResponseMillis = str4 != null ? Long.parseLong(str4) : j;
                this.responseHeaders = builder2.build();
                if (isHttps()) {
                    String readUtf8LineStrict = buffer.readUtf8LineStrict();
                    if (readUtf8LineStrict.length() <= 0) {
                        CipherSuite forJavaName = CipherSuite.Companion.forJavaName(buffer.readUtf8LineStrict());
                        List<Certificate> readCertificateList = readCertificateList(buffer);
                        List<Certificate> readCertificateList2 = readCertificateList(buffer);
                        if (!buffer.exhausted()) {
                            tlsVersion = TlsVersion.Companion.forJavaName(buffer.readUtf8LineStrict());
                        } else {
                            tlsVersion = TlsVersion.SSL_3_0;
                        }
                        this.handshake = Handshake.Companion.get(tlsVersion, forJavaName, readCertificateList, readCertificateList2);
                    } else {
                        throw new IOException("expected \"\" but was \"" + readUtf8LineStrict + '\"');
                    }
                } else {
                    this.handshake = null;
                }
            } finally {
                source.close();
            }
        }

        public Entry(Response response) {
            Intrinsics.checkNotNullParameter(response, "response");
            this.url = response.request().url().toString();
            this.varyHeaders = Cache.Companion.varyHeaders(response);
            this.requestMethod = response.request().method();
            this.protocol = response.protocol();
            this.code = response.code();
            this.message = response.message();
            this.responseHeaders = response.headers();
            this.handshake = response.handshake();
            this.sentRequestMillis = response.sentRequestAtMillis();
            this.receivedResponseMillis = response.receivedResponseAtMillis();
        }

        public final void writeTo(DiskLruCache.Editor editor) throws IOException {
            Intrinsics.checkNotNullParameter(editor, "editor");
            BufferedSink buffer = Okio.buffer(editor.newSink(0));
            try {
                buffer.writeUtf8(this.url).writeByte(10);
                buffer.writeUtf8(this.requestMethod).writeByte(10);
                buffer.writeDecimalLong((long) this.varyHeaders.size()).writeByte(10);
                int size = this.varyHeaders.size();
                for (int i = 0; i < size; i++) {
                    buffer.writeUtf8(this.varyHeaders.name(i)).writeUtf8(": ").writeUtf8(this.varyHeaders.value(i)).writeByte(10);
                }
                buffer.writeUtf8(new StatusLine(this.protocol, this.code, this.message).toString()).writeByte(10);
                buffer.writeDecimalLong((long) (this.responseHeaders.size() + 2)).writeByte(10);
                int size2 = this.responseHeaders.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    buffer.writeUtf8(this.responseHeaders.name(i2)).writeUtf8(": ").writeUtf8(this.responseHeaders.value(i2)).writeByte(10);
                }
                buffer.writeUtf8(SENT_MILLIS).writeUtf8(": ").writeDecimalLong(this.sentRequestMillis).writeByte(10);
                buffer.writeUtf8(RECEIVED_MILLIS).writeUtf8(": ").writeDecimalLong(this.receivedResponseMillis).writeByte(10);
                if (isHttps()) {
                    buffer.writeByte(10);
                    Handshake handshake2 = this.handshake;
                    Intrinsics.checkNotNull(handshake2);
                    buffer.writeUtf8(handshake2.cipherSuite().javaName()).writeByte(10);
                    writeCertList(buffer, this.handshake.peerCertificates());
                    writeCertList(buffer, this.handshake.localCertificates());
                    buffer.writeUtf8(this.handshake.tlsVersion().javaName()).writeByte(10);
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(buffer, (Throwable) null);
            } catch (Throwable th) {
                CloseableKt.closeFinally(buffer, th);
                throw th;
            }
        }

        private final List<Certificate> readCertificateList(BufferedSource bufferedSource) throws IOException {
            int readInt$okhttp = Cache.Companion.readInt$okhttp(bufferedSource);
            if (readInt$okhttp == -1) {
                return CollectionsKt.emptyList();
            }
            try {
                CertificateFactory instance = CertificateFactory.getInstance("X.509");
                ArrayList arrayList = new ArrayList(readInt$okhttp);
                for (int i = 0; i < readInt$okhttp; i++) {
                    String readUtf8LineStrict = bufferedSource.readUtf8LineStrict();
                    Buffer buffer = new Buffer();
                    ByteString decodeBase64 = ByteString.Companion.decodeBase64(readUtf8LineStrict);
                    Intrinsics.checkNotNull(decodeBase64);
                    buffer.write(decodeBase64);
                    arrayList.add(instance.generateCertificate(buffer.inputStream()));
                }
                return arrayList;
            } catch (CertificateException e) {
                throw new IOException(e.getMessage());
            }
        }

        private final void writeCertList(BufferedSink bufferedSink, List<? extends Certificate> list) throws IOException {
            try {
                bufferedSink.writeDecimalLong((long) list.size()).writeByte(10);
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    byte[] encoded = ((Certificate) list.get(i)).getEncoded();
                    ByteString.Companion companion = ByteString.Companion;
                    Intrinsics.checkNotNullExpressionValue(encoded, "bytes");
                    bufferedSink.writeUtf8(ByteString.Companion.of$default(companion, encoded, 0, 0, 3, (Object) null).base64()).writeByte(10);
                }
            } catch (CertificateEncodingException e) {
                throw new IOException(e.getMessage());
            }
        }

        public final boolean matches(Request request, Response response) {
            Intrinsics.checkNotNullParameter(request, "request");
            Intrinsics.checkNotNullParameter(response, "response");
            return Intrinsics.areEqual((Object) this.url, (Object) request.url().toString()) && Intrinsics.areEqual((Object) this.requestMethod, (Object) request.method()) && Cache.Companion.varyMatches(response, this.varyHeaders, request);
        }

        public final Response response(DiskLruCache.Snapshot snapshot) {
            Intrinsics.checkNotNullParameter(snapshot, "snapshot");
            String str = this.responseHeaders.get("Content-Type");
            String str2 = this.responseHeaders.get("Content-Length");
            return new Response.Builder().request(new Request.Builder().url(this.url).method(this.requestMethod, (RequestBody) null).headers(this.varyHeaders).build()).protocol(this.protocol).code(this.code).message(this.message).headers(this.responseHeaders).body(new CacheResponseBody(snapshot, str, str2)).handshake(this.handshake).sentRequestAtMillis(this.sentRequestMillis).receivedResponseAtMillis(this.receivedResponseMillis).build();
        }

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        static {
            StringBuilder sb = new StringBuilder();
            Platform.Companion companion = Platform.Companion;
            sb.append(companion.get().getPrefix());
            sb.append("-Sent-Millis");
            SENT_MILLIS = sb.toString();
            RECEIVED_MILLIS = companion.get().getPrefix() + "-Received-Millis";
        }
    }

    private static final class CacheResponseBody extends ResponseBody {
        private final BufferedSource bodySource;
        private final String contentLength;
        private final String contentType;
        private final DiskLruCache.Snapshot snapshot;

        public final DiskLruCache.Snapshot getSnapshot() {
            return this.snapshot;
        }

        public CacheResponseBody(DiskLruCache.Snapshot snapshot2, String str, String str2) {
            Intrinsics.checkNotNullParameter(snapshot2, "snapshot");
            this.snapshot = snapshot2;
            this.contentType = str;
            this.contentLength = str2;
            Source source = snapshot2.getSource(1);
            this.bodySource = Okio.buffer((Source) new ForwardingSource(this, source, source) {
                final /* synthetic */ Source $source;
                final /* synthetic */ CacheResponseBody this$0;

                {
                    this.this$0 = r1;
                    this.$source = r2;
                }

                public void close() throws IOException {
                    this.this$0.getSnapshot().close();
                    super.close();
                }
            });
        }

        public MediaType contentType() {
            String str = this.contentType;
            if (str != null) {
                return MediaType.Companion.parse(str);
            }
            return null;
        }

        public long contentLength() {
            String str = this.contentLength;
            if (str != null) {
                return Util.toLongOrDefault(str, -1);
            }
            return -1;
        }

        public BufferedSource source() {
            return this.bodySource;
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String key(HttpUrl httpUrl) {
            Intrinsics.checkNotNullParameter(httpUrl, "url");
            return ByteString.Companion.encodeUtf8(httpUrl.toString()).md5().hex();
        }

        public final int readInt$okhttp(BufferedSource bufferedSource) throws IOException {
            Intrinsics.checkNotNullParameter(bufferedSource, "source");
            try {
                long readDecimalLong = bufferedSource.readDecimalLong();
                String readUtf8LineStrict = bufferedSource.readUtf8LineStrict();
                if (readDecimalLong >= 0 && readDecimalLong <= ((long) Integer.MAX_VALUE) && readUtf8LineStrict.length() <= 0) {
                    return (int) readDecimalLong;
                }
                throw new IOException("expected an int but was \"" + readDecimalLong + readUtf8LineStrict + '\"');
            } catch (NumberFormatException e) {
                throw new IOException(e.getMessage());
            }
        }

        public final boolean varyMatches(Response response, Headers headers, Request request) {
            Intrinsics.checkNotNullParameter(response, "cachedResponse");
            Intrinsics.checkNotNullParameter(headers, "cachedRequest");
            Intrinsics.checkNotNullParameter(request, "newRequest");
            Iterable<String> varyFields = varyFields(response.headers());
            if ((varyFields instanceof Collection) && ((Collection) varyFields).isEmpty()) {
                return true;
            }
            for (String str : varyFields) {
                if (!Intrinsics.areEqual((Object) headers.values(str), (Object) request.headers(str))) {
                    return false;
                }
            }
            return true;
        }

        public final boolean hasVaryAll(Response response) {
            Intrinsics.checkNotNullParameter(response, "$this$hasVaryAll");
            return varyFields(response.headers()).contains("*");
        }

        private final Set<String> varyFields(Headers headers) {
            int size = headers.size();
            TreeSet treeSet = null;
            for (int i = 0; i < size; i++) {
                if (StringsKt.equals("Vary", headers.name(i), true)) {
                    String value = headers.value(i);
                    if (treeSet == null) {
                        treeSet = new TreeSet(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE));
                    }
                    for (String str : StringsKt.split$default((CharSequence) value, new char[]{','}, false, 0, 6, (Object) null)) {
                        if (str != null) {
                            treeSet.add(StringsKt.trim(str).toString());
                        } else {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
                        }
                    }
                    continue;
                }
            }
            return treeSet != null ? treeSet : SetsKt.emptySet();
        }

        public final Headers varyHeaders(Response response) {
            Intrinsics.checkNotNullParameter(response, "$this$varyHeaders");
            Response networkResponse = response.networkResponse();
            Intrinsics.checkNotNull(networkResponse);
            return varyHeaders(networkResponse.request().headers(), response.headers());
        }

        private final Headers varyHeaders(Headers headers, Headers headers2) {
            Set<String> varyFields = varyFields(headers2);
            if (varyFields.isEmpty()) {
                return Util.EMPTY_HEADERS;
            }
            Headers.Builder builder = new Headers.Builder();
            int size = headers.size();
            for (int i = 0; i < size; i++) {
                String name = headers.name(i);
                if (varyFields.contains(name)) {
                    builder.add(name, headers.value(i));
                }
            }
            return builder.build();
        }
    }
}
