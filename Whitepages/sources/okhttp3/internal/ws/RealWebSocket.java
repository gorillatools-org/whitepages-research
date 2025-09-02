package okhttp3.internal.ws;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.messaging.Constants;
import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.ws.WebSocketReader;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;

public final class RealWebSocket implements WebSocket, WebSocketReader.FrameCallback {
    private static final long CANCEL_AFTER_CLOSE_MILLIS = 60000;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final long DEFAULT_MINIMUM_DEFLATE_SIZE = 1024;
    private static final long MAX_QUEUE_SIZE = 16777216;
    private static final List<Protocol> ONLY_HTTP1 = CollectionsKt.listOf(Protocol.HTTP_1_1);
    private boolean awaitingPong;
    private Call call;
    private boolean enqueuedClose;
    /* access modifiers changed from: private */
    public WebSocketExtensions extensions;
    private boolean failed;
    private final String key;
    private final WebSocketListener listener;
    /* access modifiers changed from: private */
    public final ArrayDeque<Object> messageAndCloseQueue = new ArrayDeque<>();
    private long minimumDeflateSize;
    /* access modifiers changed from: private */
    public String name;
    private final Request originalRequest;
    private final long pingIntervalMillis;
    private final ArrayDeque<ByteString> pongQueue = new ArrayDeque<>();
    private long queueSize;
    private final Random random;
    private WebSocketReader reader;
    private int receivedCloseCode = -1;
    private String receivedCloseReason;
    private int receivedPingCount;
    private int receivedPongCount;
    private int sentPingCount;
    private Streams streams;
    private TaskQueue taskQueue;
    private WebSocketWriter writer;
    private Task writerTask;

    public RealWebSocket(TaskRunner taskRunner, Request request, WebSocketListener webSocketListener, Random random2, long j, WebSocketExtensions webSocketExtensions, long j2) {
        Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        Intrinsics.checkNotNullParameter(request, "originalRequest");
        Intrinsics.checkNotNullParameter(webSocketListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Intrinsics.checkNotNullParameter(random2, "random");
        this.originalRequest = request;
        this.listener = webSocketListener;
        this.random = random2;
        this.pingIntervalMillis = j;
        this.extensions = webSocketExtensions;
        this.minimumDeflateSize = j2;
        this.taskQueue = taskRunner.newQueue();
        if (Intrinsics.areEqual((Object) "GET", (Object) request.method())) {
            ByteString.Companion companion = ByteString.Companion;
            byte[] bArr = new byte[16];
            random2.nextBytes(bArr);
            Unit unit = Unit.INSTANCE;
            this.key = ByteString.Companion.of$default(companion, bArr, 0, 0, 3, (Object) null).base64();
            return;
        }
        throw new IllegalArgumentException(("Request must be GET: " + request.method()).toString());
    }

    public final WebSocketListener getListener$okhttp() {
        return this.listener;
    }

    public Request request() {
        return this.originalRequest;
    }

    public synchronized long queueSize() {
        return this.queueSize;
    }

    public void cancel() {
        Call call2 = this.call;
        Intrinsics.checkNotNull(call2);
        call2.cancel();
    }

    public final void connect(OkHttpClient okHttpClient) {
        Intrinsics.checkNotNullParameter(okHttpClient, "client");
        if (this.originalRequest.header("Sec-WebSocket-Extensions") != null) {
            failWebSocket(new ProtocolException("Request header not permitted: 'Sec-WebSocket-Extensions'"), (Response) null);
            return;
        }
        OkHttpClient build = okHttpClient.newBuilder().eventListener(EventListener.NONE).protocols(ONLY_HTTP1).build();
        Request build2 = this.originalRequest.newBuilder().header("Upgrade", "websocket").header("Connection", "Upgrade").header("Sec-WebSocket-Key", this.key).header("Sec-WebSocket-Version", "13").header("Sec-WebSocket-Extensions", "permessage-deflate").build();
        RealCall realCall = new RealCall(build, build2, true);
        this.call = realCall;
        Intrinsics.checkNotNull(realCall);
        realCall.enqueue(new RealWebSocket$connect$1(this, build2));
    }

    /* access modifiers changed from: private */
    public final boolean isValid(WebSocketExtensions webSocketExtensions) {
        if (webSocketExtensions.unknownValues || webSocketExtensions.clientMaxWindowBits != null) {
            return false;
        }
        Integer num = webSocketExtensions.serverMaxWindowBits;
        if (num == null) {
            return true;
        }
        int intValue = num.intValue();
        if (8 > intValue || 15 < intValue) {
            return false;
        }
        return true;
    }

    public final void checkUpgradeSuccess$okhttp(Response response, Exchange exchange) throws IOException {
        Intrinsics.checkNotNullParameter(response, "response");
        if (response.code() == 101) {
            String header$default = Response.header$default(response, "Connection", (String) null, 2, (Object) null);
            if (StringsKt.equals("Upgrade", header$default, true)) {
                String header$default2 = Response.header$default(response, "Upgrade", (String) null, 2, (Object) null);
                if (StringsKt.equals("websocket", header$default2, true)) {
                    String header$default3 = Response.header$default(response, "Sec-WebSocket-Accept", (String) null, 2, (Object) null);
                    ByteString.Companion companion = ByteString.Companion;
                    String base64 = companion.encodeUtf8(this.key + WebSocketProtocol.ACCEPT_MAGIC).sha1().base64();
                    if (!Intrinsics.areEqual((Object) base64, (Object) header$default3)) {
                        throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + base64 + "' but was '" + header$default3 + '\'');
                    } else if (exchange == null) {
                        throw new ProtocolException("Web Socket exchange missing: bad interceptor?");
                    }
                } else {
                    throw new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + header$default2 + '\'');
                }
            } else {
                throw new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + header$default + '\'');
            }
        } else {
            throw new ProtocolException("Expected HTTP 101 response but was '" + response.code() + ' ' + response.message() + '\'');
        }
    }

    public final void initReaderAndWriter(String str, Streams streams2) throws IOException {
        String str2 = str;
        Streams streams3 = streams2;
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(streams3, "streams");
        WebSocketExtensions webSocketExtensions = this.extensions;
        Intrinsics.checkNotNull(webSocketExtensions);
        synchronized (this) {
            try {
                this.name = str2;
                this.streams = streams3;
                this.writer = new WebSocketWriter(streams2.getClient(), streams2.getSink(), this.random, webSocketExtensions.perMessageDeflate, webSocketExtensions.noContextTakeover(streams2.getClient()), this.minimumDeflateSize);
                this.writerTask = new WriterTask();
                long j = this.pingIntervalMillis;
                if (j != 0) {
                    long nanos = TimeUnit.MILLISECONDS.toNanos(j);
                    TaskQueue taskQueue2 = this.taskQueue;
                    String str3 = str2 + " ping";
                    RealWebSocket$initReaderAndWriter$$inlined$synchronized$lambda$1 realWebSocket$initReaderAndWriter$$inlined$synchronized$lambda$1 = r1;
                    RealWebSocket$initReaderAndWriter$$inlined$synchronized$lambda$1 realWebSocket$initReaderAndWriter$$inlined$synchronized$lambda$12 = new RealWebSocket$initReaderAndWriter$$inlined$synchronized$lambda$1(str3, str3, nanos, this, str, streams2, webSocketExtensions);
                    taskQueue2.schedule(realWebSocket$initReaderAndWriter$$inlined$synchronized$lambda$1, nanos);
                }
                if (!this.messageAndCloseQueue.isEmpty()) {
                    runWriter();
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
        this.reader = new WebSocketReader(streams2.getClient(), streams2.getSource(), this, webSocketExtensions.perMessageDeflate, webSocketExtensions.noContextTakeover(!streams2.getClient()));
    }

    public final void loopReader() throws IOException {
        while (this.receivedCloseCode == -1) {
            WebSocketReader webSocketReader = this.reader;
            Intrinsics.checkNotNull(webSocketReader);
            webSocketReader.processNextFrame();
        }
    }

    public final boolean processNextFrame() throws IOException {
        try {
            WebSocketReader webSocketReader = this.reader;
            Intrinsics.checkNotNull(webSocketReader);
            webSocketReader.processNextFrame();
            if (this.receivedCloseCode == -1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            failWebSocket(e, (Response) null);
            return false;
        }
    }

    public final void awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
        this.taskQueue.idleLatch().await(j, timeUnit);
    }

    public final void tearDown() throws InterruptedException {
        this.taskQueue.shutdown();
        this.taskQueue.idleLatch().await(10, TimeUnit.SECONDS);
    }

    public final synchronized int sentPingCount() {
        return this.sentPingCount;
    }

    public final synchronized int receivedPingCount() {
        return this.receivedPingCount;
    }

    public final synchronized int receivedPongCount() {
        return this.receivedPongCount;
    }

    public void onReadMessage(String str) throws IOException {
        Intrinsics.checkNotNullParameter(str, "text");
        this.listener.onMessage((WebSocket) this, str);
    }

    public void onReadMessage(ByteString byteString) throws IOException {
        Intrinsics.checkNotNullParameter(byteString, "bytes");
        this.listener.onMessage((WebSocket) this, byteString);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onReadPing(okio.ByteString r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            java.lang.String r0 = "payload"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)     // Catch:{ all -> 0x0017 }
            boolean r0 = r1.failed     // Catch:{ all -> 0x0017 }
            if (r0 != 0) goto L_0x0029
            boolean r0 = r1.enqueuedClose     // Catch:{ all -> 0x0017 }
            if (r0 == 0) goto L_0x0019
            java.util.ArrayDeque<java.lang.Object> r0 = r1.messageAndCloseQueue     // Catch:{ all -> 0x0017 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0017 }
            if (r0 == 0) goto L_0x0019
            goto L_0x0029
        L_0x0017:
            r2 = move-exception
            goto L_0x002b
        L_0x0019:
            java.util.ArrayDeque<okio.ByteString> r0 = r1.pongQueue     // Catch:{ all -> 0x0017 }
            r0.add(r2)     // Catch:{ all -> 0x0017 }
            r1.runWriter()     // Catch:{ all -> 0x0017 }
            int r2 = r1.receivedPingCount     // Catch:{ all -> 0x0017 }
            int r2 = r2 + 1
            r1.receivedPingCount = r2     // Catch:{ all -> 0x0017 }
            monitor-exit(r1)
            return
        L_0x0029:
            monitor-exit(r1)
            return
        L_0x002b:
            monitor-exit(r1)     // Catch:{ all -> 0x0017 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.onReadPing(okio.ByteString):void");
    }

    public synchronized void onReadPong(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "payload");
        this.receivedPongCount++;
        this.awaitingPong = false;
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [okhttp3.internal.ws.WebSocketWriter, okhttp3.internal.ws.WebSocketReader, okhttp3.internal.ws.RealWebSocket$Streams] */
    /* JADX WARNING: type inference failed for: r1v2, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReadClose(int r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r0 = "reason"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            r0 = 0
            r1 = 1
            r2 = -1
            if (r5 == r2) goto L_0x000c
            r3 = r1
            goto L_0x000d
        L_0x000c:
            r3 = r0
        L_0x000d:
            if (r3 == 0) goto L_0x007b
            monitor-enter(r4)
            int r3 = r4.receivedCloseCode     // Catch:{ all -> 0x003b }
            if (r3 != r2) goto L_0x0015
            r0 = r1
        L_0x0015:
            if (r0 == 0) goto L_0x0071
            r4.receivedCloseCode = r5     // Catch:{ all -> 0x003b }
            r4.receivedCloseReason = r6     // Catch:{ all -> 0x003b }
            boolean r0 = r4.enqueuedClose     // Catch:{ all -> 0x003b }
            r1 = 0
            if (r0 == 0) goto L_0x003d
            java.util.ArrayDeque<java.lang.Object> r0 = r4.messageAndCloseQueue     // Catch:{ all -> 0x003b }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x003b }
            if (r0 == 0) goto L_0x003d
            okhttp3.internal.ws.RealWebSocket$Streams r0 = r4.streams     // Catch:{ all -> 0x003b }
            r4.streams = r1     // Catch:{ all -> 0x003b }
            okhttp3.internal.ws.WebSocketReader r2 = r4.reader     // Catch:{ all -> 0x003b }
            r4.reader = r1     // Catch:{ all -> 0x003b }
            okhttp3.internal.ws.WebSocketWriter r3 = r4.writer     // Catch:{ all -> 0x003b }
            r4.writer = r1     // Catch:{ all -> 0x003b }
            okhttp3.internal.concurrent.TaskQueue r1 = r4.taskQueue     // Catch:{ all -> 0x003b }
            r1.shutdown()     // Catch:{ all -> 0x003b }
            r1 = r0
            goto L_0x003f
        L_0x003b:
            r5 = move-exception
            goto L_0x0079
        L_0x003d:
            r2 = r1
            r3 = r2
        L_0x003f:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x003b }
            monitor-exit(r4)
            okhttp3.WebSocketListener r0 = r4.listener     // Catch:{ all -> 0x004f }
            r0.onClosing(r4, r5, r6)     // Catch:{ all -> 0x004f }
            if (r1 == 0) goto L_0x0051
            okhttp3.WebSocketListener r0 = r4.listener     // Catch:{ all -> 0x004f }
            r0.onClosed(r4, r5, r6)     // Catch:{ all -> 0x004f }
            goto L_0x0051
        L_0x004f:
            r5 = move-exception
            goto L_0x0061
        L_0x0051:
            if (r1 == 0) goto L_0x0056
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1)
        L_0x0056:
            if (r2 == 0) goto L_0x005b
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r2)
        L_0x005b:
            if (r3 == 0) goto L_0x0060
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r3)
        L_0x0060:
            return
        L_0x0061:
            if (r1 == 0) goto L_0x0066
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1)
        L_0x0066:
            if (r2 == 0) goto L_0x006b
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r2)
        L_0x006b:
            if (r3 == 0) goto L_0x0070
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r3)
        L_0x0070:
            throw r5
        L_0x0071:
            java.lang.String r5 = "already closed"
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch:{ all -> 0x003b }
            r6.<init>(r5)     // Catch:{ all -> 0x003b }
            throw r6     // Catch:{ all -> 0x003b }
        L_0x0079:
            monitor-exit(r4)
            throw r5
        L_0x007b:
            java.lang.String r5 = "Failed requirement."
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.onReadClose(int, java.lang.String):void");
    }

    public boolean send(String str) {
        Intrinsics.checkNotNullParameter(str, "text");
        return send(ByteString.Companion.encodeUtf8(str), 1);
    }

    public boolean send(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "bytes");
        return send(byteString, 2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003f, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized boolean send(okio.ByteString r7, int r8) {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.failed     // Catch:{ all -> 0x0022 }
            r1 = 0
            if (r0 != 0) goto L_0x003e
            boolean r0 = r6.enqueuedClose     // Catch:{ all -> 0x0022 }
            if (r0 == 0) goto L_0x000b
            goto L_0x003e
        L_0x000b:
            long r2 = r6.queueSize     // Catch:{ all -> 0x0022 }
            int r0 = r7.size()     // Catch:{ all -> 0x0022 }
            long r4 = (long) r0     // Catch:{ all -> 0x0022 }
            long r2 = r2 + r4
            r4 = 16777216(0x1000000, double:8.289046E-317)
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x0024
            r7 = 1001(0x3e9, float:1.403E-42)
            r8 = 0
            r6.close(r7, r8)     // Catch:{ all -> 0x0022 }
            monitor-exit(r6)
            return r1
        L_0x0022:
            r7 = move-exception
            goto L_0x0040
        L_0x0024:
            long r0 = r6.queueSize     // Catch:{ all -> 0x0022 }
            int r2 = r7.size()     // Catch:{ all -> 0x0022 }
            long r2 = (long) r2     // Catch:{ all -> 0x0022 }
            long r0 = r0 + r2
            r6.queueSize = r0     // Catch:{ all -> 0x0022 }
            java.util.ArrayDeque<java.lang.Object> r0 = r6.messageAndCloseQueue     // Catch:{ all -> 0x0022 }
            okhttp3.internal.ws.RealWebSocket$Message r1 = new okhttp3.internal.ws.RealWebSocket$Message     // Catch:{ all -> 0x0022 }
            r1.<init>(r8, r7)     // Catch:{ all -> 0x0022 }
            r0.add(r1)     // Catch:{ all -> 0x0022 }
            r6.runWriter()     // Catch:{ all -> 0x0022 }
            monitor-exit(r6)
            r7 = 1
            return r7
        L_0x003e:
            monitor-exit(r6)
            return r1
        L_0x0040:
            monitor-exit(r6)     // Catch:{ all -> 0x0022 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.send(okio.ByteString, int):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0025, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean pong(okio.ByteString r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            java.lang.String r0 = "payload"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)     // Catch:{ all -> 0x0017 }
            boolean r0 = r1.failed     // Catch:{ all -> 0x0017 }
            if (r0 != 0) goto L_0x0024
            boolean r0 = r1.enqueuedClose     // Catch:{ all -> 0x0017 }
            if (r0 == 0) goto L_0x0019
            java.util.ArrayDeque<java.lang.Object> r0 = r1.messageAndCloseQueue     // Catch:{ all -> 0x0017 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0017 }
            if (r0 == 0) goto L_0x0019
            goto L_0x0024
        L_0x0017:
            r2 = move-exception
            goto L_0x0027
        L_0x0019:
            java.util.ArrayDeque<okio.ByteString> r0 = r1.pongQueue     // Catch:{ all -> 0x0017 }
            r0.add(r2)     // Catch:{ all -> 0x0017 }
            r1.runWriter()     // Catch:{ all -> 0x0017 }
            monitor-exit(r1)
            r2 = 1
            return r2
        L_0x0024:
            monitor-exit(r1)
            r2 = 0
            return r2
        L_0x0027:
            monitor-exit(r1)     // Catch:{ all -> 0x0017 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.pong(okio.ByteString):boolean");
    }

    public boolean close(int i, String str) {
        return close(i, str, CANCEL_AFTER_CLOSE_MILLIS);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005a, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean close(int r8, java.lang.String r9, long r10) {
        /*
            r7 = this;
            monitor-enter(r7)
            okhttp3.internal.ws.WebSocketProtocol r0 = okhttp3.internal.ws.WebSocketProtocol.INSTANCE     // Catch:{ all -> 0x003c }
            r0.validateCloseCode(r8)     // Catch:{ all -> 0x003c }
            r0 = 0
            r1 = 1
            if (r9 == 0) goto L_0x003e
            okio.ByteString$Companion r2 = okio.ByteString.Companion     // Catch:{ all -> 0x003c }
            okio.ByteString r2 = r2.encodeUtf8(r9)     // Catch:{ all -> 0x003c }
            int r3 = r2.size()     // Catch:{ all -> 0x003c }
            long r3 = (long) r3     // Catch:{ all -> 0x003c }
            r5 = 123(0x7b, double:6.1E-322)
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 > 0) goto L_0x001d
            r3 = r1
            goto L_0x001e
        L_0x001d:
            r3 = r0
        L_0x001e:
            if (r3 == 0) goto L_0x0021
            goto L_0x003f
        L_0x0021:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x003c }
            r8.<init>()     // Catch:{ all -> 0x003c }
            java.lang.String r10 = "reason.size() > 123: "
            r8.append(r10)     // Catch:{ all -> 0x003c }
            r8.append(r9)     // Catch:{ all -> 0x003c }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x003c }
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x003c }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x003c }
            r9.<init>(r8)     // Catch:{ all -> 0x003c }
            throw r9     // Catch:{ all -> 0x003c }
        L_0x003c:
            r8 = move-exception
            goto L_0x005b
        L_0x003e:
            r2 = 0
        L_0x003f:
            boolean r9 = r7.failed     // Catch:{ all -> 0x003c }
            if (r9 != 0) goto L_0x0059
            boolean r9 = r7.enqueuedClose     // Catch:{ all -> 0x003c }
            if (r9 == 0) goto L_0x0048
            goto L_0x0059
        L_0x0048:
            r7.enqueuedClose = r1     // Catch:{ all -> 0x003c }
            java.util.ArrayDeque<java.lang.Object> r9 = r7.messageAndCloseQueue     // Catch:{ all -> 0x003c }
            okhttp3.internal.ws.RealWebSocket$Close r0 = new okhttp3.internal.ws.RealWebSocket$Close     // Catch:{ all -> 0x003c }
            r0.<init>(r8, r2, r10)     // Catch:{ all -> 0x003c }
            r9.add(r0)     // Catch:{ all -> 0x003c }
            r7.runWriter()     // Catch:{ all -> 0x003c }
            monitor-exit(r7)
            return r1
        L_0x0059:
            monitor-exit(r7)
            return r0
        L_0x005b:
            monitor-exit(r7)     // Catch:{ all -> 0x003c }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.close(int, java.lang.String, long):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00fa, code lost:
        if (r16 == null) goto L_0x0113;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        kotlin.jvm.internal.Intrinsics.checkNotNull(r21);
        r21.writePong(r16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0106, code lost:
        r1 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x010a, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x010b, code lost:
        r3 = r22;
        r2 = r23;
        r1 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0113, code lost:
        r1 = r21;
        r0 = r0.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0119, code lost:
        if ((r0 instanceof okhttp3.internal.ws.RealWebSocket.Message) == false) goto L_0x0149;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x011b, code lost:
        if (r0 == null) goto L_0x0141;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x011d, code lost:
        r0 = (okhttp3.internal.ws.RealWebSocket.Message) r0;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r1);
        r1.writeMessageFrame(r0.getFormatOpcode(), r0.getData());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x012d, code lost:
        monitor-enter(r27);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        r15.queueSize -= (long) r0.getData().size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        monitor-exit(r27);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0148, code lost:
        throw new java.lang.NullPointerException("null cannot be cast to non-null type okhttp3.internal.ws.RealWebSocket.Message");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x014b, code lost:
        if ((r0 instanceof okhttp3.internal.ws.RealWebSocket.Close) == false) goto L_0x01b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x014d, code lost:
        if (r0 == null) goto L_0x01a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x014f, code lost:
        r0 = (okhttp3.internal.ws.RealWebSocket.Close) r0;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r1);
        r1.writeClose(r0.getCode(), r0.getReason());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x015f, code lost:
        r1 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0165, code lost:
        if (((okhttp3.internal.ws.RealWebSocket.Streams) r1.element) == null) goto L_0x0180;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0167, code lost:
        r0 = r15.listener;
        r2 = r26.element;
        r3 = (java.lang.String) r25.element;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r3);
        r0.onClosed(r15, r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x017a, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x017b, code lost:
        r3 = r22;
        r2 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0180, code lost:
        r0 = (okhttp3.internal.ws.RealWebSocket.Streams) r1.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0184, code lost:
        if (r0 == null) goto L_0x0189;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0186, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0189, code lost:
        r0 = (okhttp3.internal.ws.WebSocketReader) r23.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x018f, code lost:
        if (r0 == null) goto L_0x0194;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0191, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0194, code lost:
        r0 = (okhttp3.internal.ws.WebSocketWriter) r22.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x019a, code lost:
        if (r0 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x019c, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01a1, code lost:
        r3 = r22;
        r2 = r23;
        r1 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01ae, code lost:
        throw new java.lang.NullPointerException("null cannot be cast to non-null type okhttp3.internal.ws.RealWebSocket.Close");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01af, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01b1, code lost:
        r3 = r22;
        r2 = r23;
        r1 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01bc, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01bd, code lost:
        r1 = (okhttp3.internal.ws.RealWebSocket.Streams) r1.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01c1, code lost:
        if (r1 != null) goto L_0x01c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01c3, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01c6, code lost:
        r1 = (okhttp3.internal.ws.WebSocketReader) r2.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01ca, code lost:
        if (r1 != null) goto L_0x01cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01cc, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01cf, code lost:
        r1 = (okhttp3.internal.ws.WebSocketWriter) r3.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01d3, code lost:
        if (r1 != null) goto L_0x01d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01d5, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01d8, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:?, code lost:
        return true;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01c3  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01cc  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01d5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean writeOneFrame$okhttp() throws java.io.IOException {
        /*
            r27 = this;
            r15 = r27
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r0.<init>()
            r1 = 0
            r0.element = r1
            kotlin.jvm.internal.Ref$IntRef r14 = new kotlin.jvm.internal.Ref$IntRef
            r14.<init>()
            r2 = -1
            r14.element = r2
            kotlin.jvm.internal.Ref$ObjectRef r13 = new kotlin.jvm.internal.Ref$ObjectRef
            r13.<init>()
            r13.element = r1
            kotlin.jvm.internal.Ref$ObjectRef r12 = new kotlin.jvm.internal.Ref$ObjectRef
            r12.<init>()
            r12.element = r1
            kotlin.jvm.internal.Ref$ObjectRef r11 = new kotlin.jvm.internal.Ref$ObjectRef
            r11.<init>()
            r11.element = r1
            kotlin.jvm.internal.Ref$ObjectRef r10 = new kotlin.jvm.internal.Ref$ObjectRef
            r10.<init>()
            r10.element = r1
            monitor-enter(r27)
            boolean r3 = r15.failed     // Catch:{ all -> 0x0080 }
            r4 = 0
            if (r3 == 0) goto L_0x0036
            monitor-exit(r27)
            return r4
        L_0x0036:
            okhttp3.internal.ws.WebSocketWriter r9 = r15.writer     // Catch:{ all -> 0x0080 }
            java.util.ArrayDeque<okio.ByteString> r3 = r15.pongQueue     // Catch:{ all -> 0x0080 }
            java.lang.Object r3 = r3.poll()     // Catch:{ all -> 0x0080 }
            r8 = r3
            okio.ByteString r8 = (okio.ByteString) r8     // Catch:{ all -> 0x0080 }
            if (r8 != 0) goto L_0x0070
            java.util.ArrayDeque<java.lang.Object> r3 = r15.messageAndCloseQueue     // Catch:{ all -> 0x0080 }
            java.lang.Object r3 = r3.poll()     // Catch:{ all -> 0x0080 }
            r0.element = r3     // Catch:{ all -> 0x0080 }
            boolean r5 = r3 instanceof okhttp3.internal.ws.RealWebSocket.Close     // Catch:{ all -> 0x0080 }
            if (r5 == 0) goto L_0x00e5
            int r3 = r15.receivedCloseCode     // Catch:{ all -> 0x0080 }
            r14.element = r3     // Catch:{ all -> 0x0080 }
            java.lang.String r4 = r15.receivedCloseReason     // Catch:{ all -> 0x0080 }
            r13.element = r4     // Catch:{ all -> 0x0080 }
            if (r3 == r2) goto L_0x0083
            okhttp3.internal.ws.RealWebSocket$Streams r2 = r15.streams     // Catch:{ all -> 0x0080 }
            r12.element = r2     // Catch:{ all -> 0x0080 }
            r15.streams = r1     // Catch:{ all -> 0x0080 }
            okhttp3.internal.ws.WebSocketReader r2 = r15.reader     // Catch:{ all -> 0x0080 }
            r11.element = r2     // Catch:{ all -> 0x0080 }
            r15.reader = r1     // Catch:{ all -> 0x0080 }
            okhttp3.internal.ws.WebSocketWriter r2 = r15.writer     // Catch:{ all -> 0x0080 }
            r10.element = r2     // Catch:{ all -> 0x0080 }
            r15.writer = r1     // Catch:{ all -> 0x0080 }
            okhttp3.internal.concurrent.TaskQueue r1 = r15.taskQueue     // Catch:{ all -> 0x0080 }
            r1.shutdown()     // Catch:{ all -> 0x0080 }
        L_0x0070:
            r16 = r8
            r21 = r9
            r22 = r10
            r23 = r11
            r24 = r12
            r25 = r13
            r26 = r14
            goto L_0x00f7
        L_0x0080:
            r0 = move-exception
            goto L_0x01d9
        L_0x0083:
            java.lang.Object r1 = r0.element     // Catch:{ all -> 0x0080 }
            if (r1 == 0) goto L_0x00dd
            okhttp3.internal.ws.RealWebSocket$Close r1 = (okhttp3.internal.ws.RealWebSocket.Close) r1     // Catch:{ all -> 0x0080 }
            long r1 = r1.getCancelAfterCloseMillis()     // Catch:{ all -> 0x0080 }
            okhttp3.internal.concurrent.TaskQueue r7 = r15.taskQueue     // Catch:{ all -> 0x0080 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0080 }
            r3.<init>()     // Catch:{ all -> 0x0080 }
            java.lang.String r4 = r15.name     // Catch:{ all -> 0x0080 }
            r3.append(r4)     // Catch:{ all -> 0x0080 }
            java.lang.String r4 = " cancel"
            r3.append(r4)     // Catch:{ all -> 0x0080 }
            java.lang.String r4 = r3.toString()     // Catch:{ all -> 0x0080 }
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x0080 }
            long r5 = r3.toNanos(r1)     // Catch:{ all -> 0x0080 }
            okhttp3.internal.ws.RealWebSocket$writeOneFrame$$inlined$synchronized$lambda$1 r3 = new okhttp3.internal.ws.RealWebSocket$writeOneFrame$$inlined$synchronized$lambda$1     // Catch:{ all -> 0x0080 }
            r16 = 1
            r1 = r3
            r2 = r4
            r17 = r3
            r3 = r16
            r18 = r5
            r5 = r16
            r6 = r27
            r20 = r7
            r7 = r9
            r16 = r8
            r21 = r9
            r9 = r0
            r22 = r10
            r10 = r14
            r23 = r11
            r11 = r13
            r24 = r12
            r25 = r13
            r13 = r23
            r26 = r14
            r14 = r22
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x0080 }
            r4 = r17
            r2 = r18
            r1 = r20
            r1.schedule(r4, r2)     // Catch:{ all -> 0x0080 }
            goto L_0x00f7
        L_0x00dd:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ all -> 0x0080 }
            java.lang.String r1 = "null cannot be cast to non-null type okhttp3.internal.ws.RealWebSocket.Close"
            r0.<init>(r1)     // Catch:{ all -> 0x0080 }
            throw r0     // Catch:{ all -> 0x0080 }
        L_0x00e5:
            r16 = r8
            r21 = r9
            r22 = r10
            r23 = r11
            r24 = r12
            r25 = r13
            r26 = r14
            if (r3 != 0) goto L_0x00f7
            monitor-exit(r27)
            return r4
        L_0x00f7:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0080 }
            monitor-exit(r27)
            if (r16 == 0) goto L_0x0113
            kotlin.jvm.internal.Intrinsics.checkNotNull(r21)     // Catch:{ all -> 0x010a }
            r3 = r16
            r1 = r21
            r1.writePong(r3)     // Catch:{ all -> 0x010a }
        L_0x0106:
            r1 = r24
            goto L_0x0180
        L_0x010a:
            r0 = move-exception
            r3 = r22
            r2 = r23
            r1 = r24
            goto L_0x01bd
        L_0x0113:
            r1 = r21
            java.lang.Object r0 = r0.element     // Catch:{ all -> 0x010a }
            boolean r2 = r0 instanceof okhttp3.internal.ws.RealWebSocket.Message     // Catch:{ all -> 0x010a }
            if (r2 == 0) goto L_0x0149
            if (r0 == 0) goto L_0x0141
            okhttp3.internal.ws.RealWebSocket$Message r0 = (okhttp3.internal.ws.RealWebSocket.Message) r0     // Catch:{ all -> 0x010a }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch:{ all -> 0x010a }
            int r2 = r0.getFormatOpcode()     // Catch:{ all -> 0x010a }
            okio.ByteString r3 = r0.getData()     // Catch:{ all -> 0x010a }
            r1.writeMessageFrame(r2, r3)     // Catch:{ all -> 0x010a }
            monitor-enter(r27)     // Catch:{ all -> 0x010a }
            long r1 = r15.queueSize     // Catch:{ all -> 0x013e }
            okio.ByteString r0 = r0.getData()     // Catch:{ all -> 0x013e }
            int r0 = r0.size()     // Catch:{ all -> 0x013e }
            long r3 = (long) r0     // Catch:{ all -> 0x013e }
            long r1 = r1 - r3
            r15.queueSize = r1     // Catch:{ all -> 0x013e }
            monitor-exit(r27)     // Catch:{ all -> 0x010a }
            goto L_0x0106
        L_0x013e:
            r0 = move-exception
            monitor-exit(r27)     // Catch:{ all -> 0x010a }
            throw r0     // Catch:{ all -> 0x010a }
        L_0x0141:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ all -> 0x010a }
            java.lang.String r1 = "null cannot be cast to non-null type okhttp3.internal.ws.RealWebSocket.Message"
            r0.<init>(r1)     // Catch:{ all -> 0x010a }
            throw r0     // Catch:{ all -> 0x010a }
        L_0x0149:
            boolean r2 = r0 instanceof okhttp3.internal.ws.RealWebSocket.Close     // Catch:{ all -> 0x010a }
            if (r2 == 0) goto L_0x01b1
            if (r0 == 0) goto L_0x01a1
            okhttp3.internal.ws.RealWebSocket$Close r0 = (okhttp3.internal.ws.RealWebSocket.Close) r0     // Catch:{ all -> 0x010a }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch:{ all -> 0x010a }
            int r2 = r0.getCode()     // Catch:{ all -> 0x010a }
            okio.ByteString r0 = r0.getReason()     // Catch:{ all -> 0x010a }
            r1.writeClose(r2, r0)     // Catch:{ all -> 0x010a }
            r1 = r24
            java.lang.Object r0 = r1.element     // Catch:{ all -> 0x017a }
            okhttp3.internal.ws.RealWebSocket$Streams r0 = (okhttp3.internal.ws.RealWebSocket.Streams) r0     // Catch:{ all -> 0x017a }
            if (r0 == 0) goto L_0x0180
            okhttp3.WebSocketListener r0 = r15.listener     // Catch:{ all -> 0x017a }
            r2 = r26
            int r2 = r2.element     // Catch:{ all -> 0x017a }
            r3 = r25
            java.lang.Object r3 = r3.element     // Catch:{ all -> 0x017a }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x017a }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)     // Catch:{ all -> 0x017a }
            r0.onClosed(r15, r2, r3)     // Catch:{ all -> 0x017a }
            goto L_0x0180
        L_0x017a:
            r0 = move-exception
            r3 = r22
            r2 = r23
            goto L_0x01bd
        L_0x0180:
            java.lang.Object r0 = r1.element
            okhttp3.internal.ws.RealWebSocket$Streams r0 = (okhttp3.internal.ws.RealWebSocket.Streams) r0
            if (r0 == 0) goto L_0x0189
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0)
        L_0x0189:
            r2 = r23
            java.lang.Object r0 = r2.element
            okhttp3.internal.ws.WebSocketReader r0 = (okhttp3.internal.ws.WebSocketReader) r0
            if (r0 == 0) goto L_0x0194
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0)
        L_0x0194:
            r3 = r22
            java.lang.Object r0 = r3.element
            okhttp3.internal.ws.WebSocketWriter r0 = (okhttp3.internal.ws.WebSocketWriter) r0
            if (r0 == 0) goto L_0x019f
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0)
        L_0x019f:
            r0 = 1
            return r0
        L_0x01a1:
            r3 = r22
            r2 = r23
            r1 = r24
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ all -> 0x01af }
            java.lang.String r4 = "null cannot be cast to non-null type okhttp3.internal.ws.RealWebSocket.Close"
            r0.<init>(r4)     // Catch:{ all -> 0x01af }
            throw r0     // Catch:{ all -> 0x01af }
        L_0x01af:
            r0 = move-exception
            goto L_0x01bd
        L_0x01b1:
            r3 = r22
            r2 = r23
            r1 = r24
            java.lang.AssertionError r0 = new java.lang.AssertionError     // Catch:{ all -> 0x01af }
            r0.<init>()     // Catch:{ all -> 0x01af }
            throw r0     // Catch:{ all -> 0x01af }
        L_0x01bd:
            java.lang.Object r1 = r1.element
            okhttp3.internal.ws.RealWebSocket$Streams r1 = (okhttp3.internal.ws.RealWebSocket.Streams) r1
            if (r1 == 0) goto L_0x01c6
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1)
        L_0x01c6:
            java.lang.Object r1 = r2.element
            okhttp3.internal.ws.WebSocketReader r1 = (okhttp3.internal.ws.WebSocketReader) r1
            if (r1 == 0) goto L_0x01cf
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1)
        L_0x01cf:
            java.lang.Object r1 = r3.element
            okhttp3.internal.ws.WebSocketWriter r1 = (okhttp3.internal.ws.WebSocketWriter) r1
            if (r1 == 0) goto L_0x01d8
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1)
        L_0x01d8:
            throw r0
        L_0x01d9:
            monitor-exit(r27)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.writeOneFrame$okhttp():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0022, code lost:
        if (r1 == -1) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0024, code lost:
        failWebSocket(new java.net.SocketTimeoutException("sent ping but didn't receive pong within " + r7.pingIntervalMillis + "ms (after " + (r1 - 1) + " successful ping/pongs)"), (okhttp3.Response) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r0.writePing(okio.ByteString.EMPTY);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0054, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0055, code lost:
        failWebSocket(r0, (okhttp3.Response) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void writePingFrame$okhttp() {
        /*
            r7 = this;
            monitor-enter(r7)
            boolean r0 = r7.failed     // Catch:{ all -> 0x0013 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r7)
            return
        L_0x0007:
            okhttp3.internal.ws.WebSocketWriter r0 = r7.writer     // Catch:{ all -> 0x0013 }
            if (r0 == 0) goto L_0x0059
            boolean r1 = r7.awaitingPong     // Catch:{ all -> 0x0013 }
            r2 = -1
            if (r1 == 0) goto L_0x0015
            int r1 = r7.sentPingCount     // Catch:{ all -> 0x0013 }
            goto L_0x0016
        L_0x0013:
            r0 = move-exception
            goto L_0x005b
        L_0x0015:
            r1 = r2
        L_0x0016:
            int r3 = r7.sentPingCount     // Catch:{ all -> 0x0013 }
            r4 = 1
            int r3 = r3 + r4
            r7.sentPingCount = r3     // Catch:{ all -> 0x0013 }
            r7.awaitingPong = r4     // Catch:{ all -> 0x0013 }
            kotlin.Unit r3 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0013 }
            monitor-exit(r7)
            r3 = 0
            if (r1 == r2) goto L_0x004e
            java.net.SocketTimeoutException r0 = new java.net.SocketTimeoutException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = "sent ping but didn't receive pong within "
            r2.append(r5)
            long r5 = r7.pingIntervalMillis
            r2.append(r5)
            java.lang.String r5 = "ms (after "
            r2.append(r5)
            int r1 = r1 - r4
            r2.append(r1)
            java.lang.String r1 = " successful ping/pongs)"
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r0.<init>(r1)
            r7.failWebSocket(r0, r3)
            return
        L_0x004e:
            okio.ByteString r1 = okio.ByteString.EMPTY     // Catch:{ IOException -> 0x0054 }
            r0.writePing(r1)     // Catch:{ IOException -> 0x0054 }
            goto L_0x0058
        L_0x0054:
            r0 = move-exception
            r7.failWebSocket(r0, r3)
        L_0x0058:
            return
        L_0x0059:
            monitor-exit(r7)
            return
        L_0x005b:
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.writePingFrame$okhttp():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r4.listener.onFailure(r4, r5, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0039, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003a, code lost:
        if (r0 != null) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003c, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003f, code lost:
        if (r2 != null) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0041, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0044, code lost:
        if (r3 != null) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0046, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0049, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void failWebSocket(java.lang.Exception r5, okhttp3.Response r6) {
        /*
            r4 = this;
            java.lang.String r0 = "e"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            monitor-enter(r4)
            boolean r0 = r4.failed     // Catch:{ all -> 0x004a }
            if (r0 == 0) goto L_0x000c
            monitor-exit(r4)
            return
        L_0x000c:
            r0 = 1
            r4.failed = r0     // Catch:{ all -> 0x004a }
            okhttp3.internal.ws.RealWebSocket$Streams r0 = r4.streams     // Catch:{ all -> 0x004a }
            r1 = 0
            r4.streams = r1     // Catch:{ all -> 0x004a }
            okhttp3.internal.ws.WebSocketReader r2 = r4.reader     // Catch:{ all -> 0x004a }
            r4.reader = r1     // Catch:{ all -> 0x004a }
            okhttp3.internal.ws.WebSocketWriter r3 = r4.writer     // Catch:{ all -> 0x004a }
            r4.writer = r1     // Catch:{ all -> 0x004a }
            okhttp3.internal.concurrent.TaskQueue r1 = r4.taskQueue     // Catch:{ all -> 0x004a }
            r1.shutdown()     // Catch:{ all -> 0x004a }
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x004a }
            monitor-exit(r4)
            okhttp3.WebSocketListener r1 = r4.listener     // Catch:{ all -> 0x0039 }
            r1.onFailure(r4, r5, r6)     // Catch:{ all -> 0x0039 }
            if (r0 == 0) goto L_0x002e
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0)
        L_0x002e:
            if (r2 == 0) goto L_0x0033
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r2)
        L_0x0033:
            if (r3 == 0) goto L_0x0038
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r3)
        L_0x0038:
            return
        L_0x0039:
            r5 = move-exception
            if (r0 == 0) goto L_0x003f
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0)
        L_0x003f:
            if (r2 == 0) goto L_0x0044
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r2)
        L_0x0044:
            if (r3 == 0) goto L_0x0049
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r3)
        L_0x0049:
            throw r5
        L_0x004a:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.failWebSocket(java.lang.Exception, okhttp3.Response):void");
    }

    public static final class Message {
        private final ByteString data;
        private final int formatOpcode;

        public Message(int i, ByteString byteString) {
            Intrinsics.checkNotNullParameter(byteString, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
            this.formatOpcode = i;
            this.data = byteString;
        }

        public final int getFormatOpcode() {
            return this.formatOpcode;
        }

        public final ByteString getData() {
            return this.data;
        }
    }

    public static final class Close {
        private final long cancelAfterCloseMillis;
        private final int code;
        private final ByteString reason;

        public Close(int i, ByteString byteString, long j) {
            this.code = i;
            this.reason = byteString;
            this.cancelAfterCloseMillis = j;
        }

        public final int getCode() {
            return this.code;
        }

        public final ByteString getReason() {
            return this.reason;
        }

        public final long getCancelAfterCloseMillis() {
            return this.cancelAfterCloseMillis;
        }
    }

    private final void runWriter() {
        if (!Util.assertionsEnabled || Thread.holdsLock(this)) {
            Task task = this.writerTask;
            if (task != null) {
                TaskQueue.schedule$default(this.taskQueue, task, 0, 2, (Object) null);
                return;
            }
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Thread ");
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkNotNullExpressionValue(currentThread, "Thread.currentThread()");
        sb.append(currentThread.getName());
        sb.append(" MUST hold lock on ");
        sb.append(this);
        throw new AssertionError(sb.toString());
    }

    public static abstract class Streams implements Closeable {
        private final boolean client;
        private final BufferedSink sink;
        private final BufferedSource source;

        public Streams(boolean z, BufferedSource bufferedSource, BufferedSink bufferedSink) {
            Intrinsics.checkNotNullParameter(bufferedSource, "source");
            Intrinsics.checkNotNullParameter(bufferedSink, "sink");
            this.client = z;
            this.source = bufferedSource;
            this.sink = bufferedSink;
        }

        public final boolean getClient() {
            return this.client;
        }

        public final BufferedSource getSource() {
            return this.source;
        }

        public final BufferedSink getSink() {
            return this.sink;
        }
    }

    private final class WriterTask extends Task {
        public WriterTask() {
            super(RealWebSocket.this.name + " writer", false, 2, (DefaultConstructorMarker) null);
        }

        public long runOnce() {
            try {
                if (RealWebSocket.this.writeOneFrame$okhttp()) {
                    return 0;
                }
                return -1;
            } catch (IOException e) {
                RealWebSocket.this.failWebSocket(e, (Response) null);
                return -1;
            }
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
