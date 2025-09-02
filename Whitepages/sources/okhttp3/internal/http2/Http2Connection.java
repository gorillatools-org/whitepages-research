package okhttp3.internal.http2;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskQueue$execute$1;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http2.Http2Reader;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;

public final class Http2Connection implements Closeable {
    public static final int AWAIT_PING = 3;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Settings DEFAULT_SETTINGS;
    public static final int DEGRADED_PING = 2;
    public static final int DEGRADED_PONG_TIMEOUT_NS = 1000000000;
    public static final int INTERVAL_PING = 1;
    public static final int OKHTTP_CLIENT_WINDOW_SIZE = 16777216;
    private long awaitPingsSent;
    /* access modifiers changed from: private */
    public long awaitPongsReceived;
    private final boolean client;
    private final String connectionName;
    /* access modifiers changed from: private */
    public final Set<Integer> currentPushRequests;
    private long degradedPingsSent;
    private long degradedPongDeadlineNs;
    /* access modifiers changed from: private */
    public long degradedPongsReceived;
    /* access modifiers changed from: private */
    public long intervalPingsSent;
    /* access modifiers changed from: private */
    public long intervalPongsReceived;
    /* access modifiers changed from: private */
    public boolean isShutdown;
    private int lastGoodStreamId;
    private final Listener listener;
    private int nextStreamId;
    private final Settings okHttpSettings;
    private Settings peerSettings;
    /* access modifiers changed from: private */
    public final PushObserver pushObserver;
    private final TaskQueue pushQueue;
    private long readBytesAcknowledged;
    private long readBytesTotal;
    private final ReaderRunnable readerRunnable;
    /* access modifiers changed from: private */
    public final TaskQueue settingsListenerQueue;
    private final Socket socket;
    private final Map<Integer, Http2Stream> streams = new LinkedHashMap();
    /* access modifiers changed from: private */
    public final TaskRunner taskRunner;
    /* access modifiers changed from: private */
    public long writeBytesMaximum;
    private long writeBytesTotal;
    private final Http2Writer writer;
    /* access modifiers changed from: private */
    public final TaskQueue writerQueue;

    public final boolean pushedStream$okhttp(int i) {
        return i != 0 && (i & 1) == 0;
    }

    public final void start() throws IOException {
        start$default(this, false, (TaskRunner) null, 3, (Object) null);
    }

    public final void start(boolean z) throws IOException {
        start$default(this, z, (TaskRunner) null, 2, (Object) null);
    }

    public Http2Connection(Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        boolean client$okhttp = builder.getClient$okhttp();
        this.client = client$okhttp;
        this.listener = builder.getListener$okhttp();
        String connectionName$okhttp = builder.getConnectionName$okhttp();
        this.connectionName = connectionName$okhttp;
        this.nextStreamId = builder.getClient$okhttp() ? 3 : 2;
        TaskRunner taskRunner$okhttp = builder.getTaskRunner$okhttp();
        this.taskRunner = taskRunner$okhttp;
        TaskQueue newQueue = taskRunner$okhttp.newQueue();
        this.writerQueue = newQueue;
        this.pushQueue = taskRunner$okhttp.newQueue();
        this.settingsListenerQueue = taskRunner$okhttp.newQueue();
        this.pushObserver = builder.getPushObserver$okhttp();
        Settings settings = new Settings();
        if (builder.getClient$okhttp()) {
            settings.set(7, 16777216);
        }
        Unit unit = Unit.INSTANCE;
        this.okHttpSettings = settings;
        Settings settings2 = DEFAULT_SETTINGS;
        this.peerSettings = settings2;
        this.writeBytesMaximum = (long) settings2.getInitialWindowSize();
        this.socket = builder.getSocket$okhttp();
        this.writer = new Http2Writer(builder.getSink$okhttp(), client$okhttp);
        this.readerRunnable = new ReaderRunnable(this, new Http2Reader(builder.getSource$okhttp(), client$okhttp));
        this.currentPushRequests = new LinkedHashSet();
        if (builder.getPingIntervalMillis$okhttp() != 0) {
            long nanos = TimeUnit.MILLISECONDS.toNanos((long) builder.getPingIntervalMillis$okhttp());
            String str = connectionName$okhttp + " ping";
            newQueue.schedule(new Http2Connection$$special$$inlined$schedule$1(str, str, this, nanos), nanos);
        }
    }

    public final boolean getClient$okhttp() {
        return this.client;
    }

    public final Listener getListener$okhttp() {
        return this.listener;
    }

    public final Map<Integer, Http2Stream> getStreams$okhttp() {
        return this.streams;
    }

    public final String getConnectionName$okhttp() {
        return this.connectionName;
    }

    public final int getLastGoodStreamId$okhttp() {
        return this.lastGoodStreamId;
    }

    public final void setLastGoodStreamId$okhttp(int i) {
        this.lastGoodStreamId = i;
    }

    public final int getNextStreamId$okhttp() {
        return this.nextStreamId;
    }

    public final void setNextStreamId$okhttp(int i) {
        this.nextStreamId = i;
    }

    public final Settings getOkHttpSettings() {
        return this.okHttpSettings;
    }

    public final Settings getPeerSettings() {
        return this.peerSettings;
    }

    public final void setPeerSettings(Settings settings) {
        Intrinsics.checkNotNullParameter(settings, "<set-?>");
        this.peerSettings = settings;
    }

    public final long getReadBytesTotal() {
        return this.readBytesTotal;
    }

    public final long getReadBytesAcknowledged() {
        return this.readBytesAcknowledged;
    }

    public final long getWriteBytesTotal() {
        return this.writeBytesTotal;
    }

    public final long getWriteBytesMaximum() {
        return this.writeBytesMaximum;
    }

    public final Socket getSocket$okhttp() {
        return this.socket;
    }

    public final Http2Writer getWriter() {
        return this.writer;
    }

    public final ReaderRunnable getReaderRunnable() {
        return this.readerRunnable;
    }

    public final synchronized int openStreamCount() {
        return this.streams.size();
    }

    public final synchronized Http2Stream getStream(int i) {
        return this.streams.get(Integer.valueOf(i));
    }

    public final synchronized Http2Stream removeStream$okhttp(int i) {
        Http2Stream remove;
        remove = this.streams.remove(Integer.valueOf(i));
        notifyAll();
        return remove;
    }

    public final synchronized void updateConnectionFlowControl$okhttp(long j) {
        long j2 = this.readBytesTotal + j;
        this.readBytesTotal = j2;
        long j3 = j2 - this.readBytesAcknowledged;
        if (j3 >= ((long) (this.okHttpSettings.getInitialWindowSize() / 2))) {
            writeWindowUpdateLater$okhttp(0, j3);
            this.readBytesAcknowledged += j3;
        }
    }

    public final Http2Stream pushStream(int i, List<Header> list, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(list, "requestHeaders");
        if (!this.client) {
            return newStream(i, list, z);
        }
        throw new IllegalStateException("Client cannot push requests.");
    }

    public final Http2Stream newStream(List<Header> list, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(list, "requestHeaders");
        return newStream(0, list, z);
    }

    private final Http2Stream newStream(int i, List<Header> list, boolean z) throws IOException {
        int i2;
        Http2Stream http2Stream;
        boolean z2 = true;
        boolean z3 = !z;
        synchronized (this.writer) {
            try {
                synchronized (this) {
                    if (this.nextStreamId > 1073741823) {
                        shutdown(ErrorCode.REFUSED_STREAM);
                    }
                    if (!this.isShutdown) {
                        i2 = this.nextStreamId;
                        this.nextStreamId = i2 + 2;
                        http2Stream = new Http2Stream(i2, this, z3, false, (Headers) null);
                        if (z && this.writeBytesTotal < this.writeBytesMaximum) {
                            if (http2Stream.getWriteBytesTotal() < http2Stream.getWriteBytesMaximum()) {
                                z2 = false;
                            }
                        }
                        if (http2Stream.isOpen()) {
                            this.streams.put(Integer.valueOf(i2), http2Stream);
                        }
                        Unit unit = Unit.INSTANCE;
                    } else {
                        throw new ConnectionShutdownException();
                    }
                }
                if (i == 0) {
                    this.writer.headers(z3, i2, list);
                } else if (!this.client) {
                    this.writer.pushPromise(i, i2, list);
                } else {
                    throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
                }
            } catch (Throwable th) {
                throw th;
            } finally {
            }
        }
        if (z2) {
            this.writer.flush();
        }
        return http2Stream;
    }

    public final void writeHeaders$okhttp(int i, boolean z, List<Header> list) throws IOException {
        Intrinsics.checkNotNullParameter(list, "alternating");
        this.writer.headers(z, i, list);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:28|29|30) */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002a, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r2 = java.lang.Math.min((int) java.lang.Math.min(r12, r6 - r4), r8.writer.maxDataLength());
        r6 = (long) r2;
        r8.writeBytesTotal += r6;
        r4 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0069, code lost:
        throw new java.io.InterruptedIOException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x006b, code lost:
        throw r9;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x005d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void writeData(int r9, boolean r10, okio.Buffer r11, long r12) throws java.io.IOException {
        /*
            r8 = this;
            r0 = 0
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            r3 = 0
            if (r2 != 0) goto L_0x000d
            okhttp3.internal.http2.Http2Writer r12 = r8.writer
            r12.data(r10, r9, r11, r3)
            return
        L_0x000d:
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r2 <= 0) goto L_0x006c
            monitor-enter(r8)
        L_0x0012:
            long r4 = r8.writeBytesTotal     // Catch:{ InterruptedException -> 0x005d }
            long r6 = r8.writeBytesMaximum     // Catch:{ InterruptedException -> 0x005d }
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 < 0) goto L_0x0034
            java.util.Map<java.lang.Integer, okhttp3.internal.http2.Http2Stream> r2 = r8.streams     // Catch:{ InterruptedException -> 0x005d }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r9)     // Catch:{ InterruptedException -> 0x005d }
            boolean r2 = r2.containsKey(r4)     // Catch:{ InterruptedException -> 0x005d }
            if (r2 == 0) goto L_0x002c
            r8.wait()     // Catch:{ InterruptedException -> 0x005d }
            goto L_0x0012
        L_0x002a:
            r9 = move-exception
            goto L_0x006a
        L_0x002c:
            java.io.IOException r9 = new java.io.IOException     // Catch:{ InterruptedException -> 0x005d }
            java.lang.String r10 = "stream closed"
            r9.<init>(r10)     // Catch:{ InterruptedException -> 0x005d }
            throw r9     // Catch:{ InterruptedException -> 0x005d }
        L_0x0034:
            long r6 = r6 - r4
            long r4 = java.lang.Math.min(r12, r6)     // Catch:{ all -> 0x002a }
            int r2 = (int) r4     // Catch:{ all -> 0x002a }
            okhttp3.internal.http2.Http2Writer r4 = r8.writer     // Catch:{ all -> 0x002a }
            int r4 = r4.maxDataLength()     // Catch:{ all -> 0x002a }
            int r2 = java.lang.Math.min(r2, r4)     // Catch:{ all -> 0x002a }
            long r4 = r8.writeBytesTotal     // Catch:{ all -> 0x002a }
            long r6 = (long) r2     // Catch:{ all -> 0x002a }
            long r4 = r4 + r6
            r8.writeBytesTotal = r4     // Catch:{ all -> 0x002a }
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x002a }
            monitor-exit(r8)
            long r12 = r12 - r6
            okhttp3.internal.http2.Http2Writer r4 = r8.writer
            if (r10 == 0) goto L_0x0058
            int r5 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r5 != 0) goto L_0x0058
            r5 = 1
            goto L_0x0059
        L_0x0058:
            r5 = r3
        L_0x0059:
            r4.data(r5, r9, r11, r2)
            goto L_0x000d
        L_0x005d:
            java.lang.Thread r9 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x002a }
            r9.interrupt()     // Catch:{ all -> 0x002a }
            java.io.InterruptedIOException r9 = new java.io.InterruptedIOException     // Catch:{ all -> 0x002a }
            r9.<init>()     // Catch:{ all -> 0x002a }
            throw r9     // Catch:{ all -> 0x002a }
        L_0x006a:
            monitor-exit(r8)
            throw r9
        L_0x006c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Connection.writeData(int, boolean, okio.Buffer, long):void");
    }

    public final void writeSynResetLater$okhttp(int i, ErrorCode errorCode) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        String str = this.connectionName + '[' + i + "] writeSynReset";
        this.writerQueue.schedule(new Http2Connection$writeSynResetLater$$inlined$execute$1(str, true, str, true, this, i, errorCode), 0);
    }

    public final void writeSynReset$okhttp(int i, ErrorCode errorCode) throws IOException {
        Intrinsics.checkNotNullParameter(errorCode, "statusCode");
        this.writer.rstStream(i, errorCode);
    }

    public final void writeWindowUpdateLater$okhttp(int i, long j) {
        String str = this.connectionName + '[' + i + "] windowUpdate";
        this.writerQueue.schedule(new Http2Connection$writeWindowUpdateLater$$inlined$execute$1(str, true, str, true, this, i, j), 0);
    }

    public final void writePing(boolean z, int i, int i2) {
        try {
            this.writer.ping(z, i, i2);
        } catch (IOException e) {
            failConnection(e);
        }
    }

    public final void writePingAndAwaitPong() throws InterruptedException {
        writePing();
        awaitPong();
    }

    public final void writePing() throws InterruptedException {
        synchronized (this) {
            this.awaitPingsSent++;
        }
        writePing(false, 3, 1330343787);
    }

    public final synchronized void awaitPong() throws InterruptedException {
        while (this.awaitPongsReceived < this.awaitPingsSent) {
            wait();
        }
    }

    public final void flush() throws IOException {
        this.writer.flush();
    }

    public final void shutdown(ErrorCode errorCode) throws IOException {
        Intrinsics.checkNotNullParameter(errorCode, "statusCode");
        synchronized (this.writer) {
            synchronized (this) {
                if (!this.isShutdown) {
                    this.isShutdown = true;
                    int i = this.lastGoodStreamId;
                    Unit unit = Unit.INSTANCE;
                    this.writer.goAway(i, errorCode, Util.EMPTY_BYTE_ARRAY);
                }
            }
        }
    }

    public void close() {
        close$okhttp(ErrorCode.NO_ERROR, ErrorCode.CANCEL, (IOException) null);
    }

    /* access modifiers changed from: private */
    public final void failConnection(IOException iOException) {
        ErrorCode errorCode = ErrorCode.PROTOCOL_ERROR;
        close$okhttp(errorCode, errorCode, iOException);
    }

    public static /* synthetic */ void start$default(Http2Connection http2Connection, boolean z, TaskRunner taskRunner2, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            taskRunner2 = TaskRunner.INSTANCE;
        }
        http2Connection.start(z, taskRunner2);
    }

    public final void start(boolean z, TaskRunner taskRunner2) throws IOException {
        Intrinsics.checkNotNullParameter(taskRunner2, "taskRunner");
        if (z) {
            this.writer.connectionPreface();
            this.writer.settings(this.okHttpSettings);
            int initialWindowSize = this.okHttpSettings.getInitialWindowSize();
            if (initialWindowSize != 65535) {
                this.writer.windowUpdate(0, (long) (initialWindowSize - Settings.DEFAULT_INITIAL_WINDOW_SIZE));
            }
        }
        TaskQueue newQueue = taskRunner2.newQueue();
        String str = this.connectionName;
        newQueue.schedule(new TaskQueue$execute$1(this.readerRunnable, str, true, str, true), 0);
    }

    public final void setSettings(Settings settings) throws IOException {
        Intrinsics.checkNotNullParameter(settings, "settings");
        synchronized (this.writer) {
            synchronized (this) {
                if (!this.isShutdown) {
                    this.okHttpSettings.merge(settings);
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new ConnectionShutdownException();
                }
            }
            this.writer.settings(settings);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001b, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean isHealthy(long r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.isShutdown     // Catch:{ all -> 0x0018 }
            r1 = 0
            if (r0 == 0) goto L_0x0008
            monitor-exit(r6)
            return r1
        L_0x0008:
            long r2 = r6.degradedPongsReceived     // Catch:{ all -> 0x0018 }
            long r4 = r6.degradedPingsSent     // Catch:{ all -> 0x0018 }
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 >= 0) goto L_0x001a
            long r2 = r6.degradedPongDeadlineNs     // Catch:{ all -> 0x0018 }
            int r7 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r7 < 0) goto L_0x001a
            monitor-exit(r6)
            return r1
        L_0x0018:
            r7 = move-exception
            goto L_0x001d
        L_0x001a:
            monitor-exit(r6)
            r7 = 1
            return r7
        L_0x001d:
            monitor-exit(r6)     // Catch:{ all -> 0x0018 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Connection.isHealthy(long):boolean");
    }

    public final void sendDegradedPingLater$okhttp() {
        synchronized (this) {
            long j = this.degradedPongsReceived;
            long j2 = this.degradedPingsSent;
            if (j >= j2) {
                this.degradedPingsSent = j2 + 1;
                this.degradedPongDeadlineNs = System.nanoTime() + ((long) DEGRADED_PONG_TIMEOUT_NS);
                Unit unit = Unit.INSTANCE;
                String str = this.connectionName + " ping";
                this.writerQueue.schedule(new Http2Connection$sendDegradedPingLater$$inlined$execute$1(str, true, str, true, this), 0);
            }
        }
    }

    public static final class Builder {
        private boolean client;
        public String connectionName;
        private Listener listener = Listener.REFUSE_INCOMING_STREAMS;
        private int pingIntervalMillis;
        private PushObserver pushObserver = PushObserver.CANCEL;
        public BufferedSink sink;
        public Socket socket;
        public BufferedSource source;
        private final TaskRunner taskRunner;

        public final Builder socket(Socket socket2) throws IOException {
            return socket$default(this, socket2, (String) null, (BufferedSource) null, (BufferedSink) null, 14, (Object) null);
        }

        public final Builder socket(Socket socket2, String str) throws IOException {
            return socket$default(this, socket2, str, (BufferedSource) null, (BufferedSink) null, 12, (Object) null);
        }

        public final Builder socket(Socket socket2, String str, BufferedSource bufferedSource) throws IOException {
            return socket$default(this, socket2, str, bufferedSource, (BufferedSink) null, 8, (Object) null);
        }

        public Builder(boolean z, TaskRunner taskRunner2) {
            Intrinsics.checkNotNullParameter(taskRunner2, "taskRunner");
            this.client = z;
            this.taskRunner = taskRunner2;
        }

        public final boolean getClient$okhttp() {
            return this.client;
        }

        public final void setClient$okhttp(boolean z) {
            this.client = z;
        }

        public final TaskRunner getTaskRunner$okhttp() {
            return this.taskRunner;
        }

        public final Socket getSocket$okhttp() {
            Socket socket2 = this.socket;
            if (socket2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("socket");
            }
            return socket2;
        }

        public final void setSocket$okhttp(Socket socket2) {
            Intrinsics.checkNotNullParameter(socket2, "<set-?>");
            this.socket = socket2;
        }

        public final String getConnectionName$okhttp() {
            String str = this.connectionName;
            if (str == null) {
                Intrinsics.throwUninitializedPropertyAccessException("connectionName");
            }
            return str;
        }

        public final void setConnectionName$okhttp(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.connectionName = str;
        }

        public final BufferedSource getSource$okhttp() {
            BufferedSource bufferedSource = this.source;
            if (bufferedSource == null) {
                Intrinsics.throwUninitializedPropertyAccessException("source");
            }
            return bufferedSource;
        }

        public final void setSource$okhttp(BufferedSource bufferedSource) {
            Intrinsics.checkNotNullParameter(bufferedSource, "<set-?>");
            this.source = bufferedSource;
        }

        public final BufferedSink getSink$okhttp() {
            BufferedSink bufferedSink = this.sink;
            if (bufferedSink == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sink");
            }
            return bufferedSink;
        }

        public final void setSink$okhttp(BufferedSink bufferedSink) {
            Intrinsics.checkNotNullParameter(bufferedSink, "<set-?>");
            this.sink = bufferedSink;
        }

        public final Listener getListener$okhttp() {
            return this.listener;
        }

        public final void setListener$okhttp(Listener listener2) {
            Intrinsics.checkNotNullParameter(listener2, "<set-?>");
            this.listener = listener2;
        }

        public final PushObserver getPushObserver$okhttp() {
            return this.pushObserver;
        }

        public final void setPushObserver$okhttp(PushObserver pushObserver2) {
            Intrinsics.checkNotNullParameter(pushObserver2, "<set-?>");
            this.pushObserver = pushObserver2;
        }

        public final int getPingIntervalMillis$okhttp() {
            return this.pingIntervalMillis;
        }

        public final void setPingIntervalMillis$okhttp(int i) {
            this.pingIntervalMillis = i;
        }

        public static /* synthetic */ Builder socket$default(Builder builder, Socket socket2, String str, BufferedSource bufferedSource, BufferedSink bufferedSink, int i, Object obj) throws IOException {
            if ((i & 2) != 0) {
                str = Util.peerName(socket2);
            }
            if ((i & 4) != 0) {
                bufferedSource = Okio.buffer(Okio.source(socket2));
            }
            if ((i & 8) != 0) {
                bufferedSink = Okio.buffer(Okio.sink(socket2));
            }
            return builder.socket(socket2, str, bufferedSource, bufferedSink);
        }

        public final Builder socket(Socket socket2, String str, BufferedSource bufferedSource, BufferedSink bufferedSink) throws IOException {
            String str2;
            Intrinsics.checkNotNullParameter(socket2, "socket");
            Intrinsics.checkNotNullParameter(str, "peerName");
            Intrinsics.checkNotNullParameter(bufferedSource, "source");
            Intrinsics.checkNotNullParameter(bufferedSink, "sink");
            this.socket = socket2;
            if (this.client) {
                str2 = Util.okHttpName + ' ' + str;
            } else {
                str2 = "MockWebServer " + str;
            }
            this.connectionName = str2;
            this.source = bufferedSource;
            this.sink = bufferedSink;
            return this;
        }

        public final Builder listener(Listener listener2) {
            Intrinsics.checkNotNullParameter(listener2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.listener = listener2;
            return this;
        }

        public final Builder pushObserver(PushObserver pushObserver2) {
            Intrinsics.checkNotNullParameter(pushObserver2, "pushObserver");
            this.pushObserver = pushObserver2;
            return this;
        }

        public final Builder pingIntervalMillis(int i) {
            this.pingIntervalMillis = i;
            return this;
        }

        public final Http2Connection build() {
            return new Http2Connection(this);
        }
    }

    public final class ReaderRunnable implements Http2Reader.Handler, Function0 {
        private final Http2Reader reader;
        final /* synthetic */ Http2Connection this$0;

        public void ackSettings() {
        }

        public void alternateService(int i, String str, ByteString byteString, String str2, int i2, long j) {
            Intrinsics.checkNotNullParameter(str, "origin");
            Intrinsics.checkNotNullParameter(byteString, "protocol");
            Intrinsics.checkNotNullParameter(str2, "host");
        }

        public void priority(int i, int i2, int i3, boolean z) {
        }

        public ReaderRunnable(Http2Connection http2Connection, Http2Reader http2Reader) {
            Intrinsics.checkNotNullParameter(http2Reader, "reader");
            this.this$0 = http2Connection;
            this.reader = http2Reader;
        }

        public final Http2Reader getReader$okhttp() {
            return this.reader;
        }

        public void invoke() {
            ErrorCode errorCode;
            ErrorCode errorCode2 = ErrorCode.INTERNAL_ERROR;
            e = null;
            try {
                this.reader.readConnectionPreface(this);
                while (this.reader.nextFrame(false, this)) {
                }
                errorCode = ErrorCode.NO_ERROR;
                try {
                    this.this$0.close$okhttp(errorCode, ErrorCode.CANCEL, (IOException) null);
                } catch (IOException e) {
                    e = e;
                    try {
                        ErrorCode errorCode3 = ErrorCode.PROTOCOL_ERROR;
                        this.this$0.close$okhttp(errorCode3, errorCode3, e);
                        Util.closeQuietly((Closeable) this.reader);
                    } catch (Throwable th) {
                        th = th;
                        this.this$0.close$okhttp(errorCode, errorCode2, e);
                        Util.closeQuietly((Closeable) this.reader);
                        throw th;
                    }
                }
            } catch (IOException e2) {
                e = e2;
                errorCode = errorCode2;
                ErrorCode errorCode32 = ErrorCode.PROTOCOL_ERROR;
                this.this$0.close$okhttp(errorCode32, errorCode32, e);
                Util.closeQuietly((Closeable) this.reader);
            } catch (Throwable th2) {
                th = th2;
                errorCode = errorCode2;
                this.this$0.close$okhttp(errorCode, errorCode2, e);
                Util.closeQuietly((Closeable) this.reader);
                throw th;
            }
            Util.closeQuietly((Closeable) this.reader);
        }

        public void data(boolean z, int i, BufferedSource bufferedSource, int i2) throws IOException {
            Intrinsics.checkNotNullParameter(bufferedSource, "source");
            if (this.this$0.pushedStream$okhttp(i)) {
                this.this$0.pushDataLater$okhttp(i, bufferedSource, i2, z);
                return;
            }
            Http2Stream stream = this.this$0.getStream(i);
            if (stream == null) {
                this.this$0.writeSynResetLater$okhttp(i, ErrorCode.PROTOCOL_ERROR);
                long j = (long) i2;
                this.this$0.updateConnectionFlowControl$okhttp(j);
                bufferedSource.skip(j);
                return;
            }
            stream.receiveData(bufferedSource, i2);
            if (z) {
                stream.receiveHeaders(Util.EMPTY_HEADERS, true);
            }
        }

        public void headers(boolean z, int i, int i2, List<Header> list) {
            boolean z2 = z;
            int i3 = i;
            List<Header> list2 = list;
            Intrinsics.checkNotNullParameter(list2, "headerBlock");
            if (this.this$0.pushedStream$okhttp(i3)) {
                this.this$0.pushHeadersLater$okhttp(i3, list2, z2);
                return;
            }
            synchronized (this.this$0) {
                Http2Stream stream = this.this$0.getStream(i3);
                if (stream != null) {
                    Unit unit = Unit.INSTANCE;
                    stream.receiveHeaders(Util.toHeaders(list), z2);
                } else if (!this.this$0.isShutdown) {
                    if (i3 > this.this$0.getLastGoodStreamId$okhttp()) {
                        if (i3 % 2 != this.this$0.getNextStreamId$okhttp() % 2) {
                            Http2Stream http2Stream = new Http2Stream(i, this.this$0, false, z, Util.toHeaders(list));
                            this.this$0.setLastGoodStreamId$okhttp(i3);
                            this.this$0.getStreams$okhttp().put(Integer.valueOf(i), http2Stream);
                            String str = this.this$0.getConnectionName$okhttp() + '[' + i3 + "] onStream";
                            this.this$0.taskRunner.newQueue().schedule(new Http2Connection$ReaderRunnable$headers$$inlined$synchronized$lambda$1(str, true, str, true, http2Stream, this, stream, i, list, z), 0);
                        }
                    }
                }
            }
        }

        public void rstStream(int i, ErrorCode errorCode) {
            Intrinsics.checkNotNullParameter(errorCode, "errorCode");
            if (this.this$0.pushedStream$okhttp(i)) {
                this.this$0.pushResetLater$okhttp(i, errorCode);
                return;
            }
            Http2Stream removeStream$okhttp = this.this$0.removeStream$okhttp(i);
            if (removeStream$okhttp != null) {
                removeStream$okhttp.receiveRstStream(errorCode);
            }
        }

        public void settings(boolean z, Settings settings) {
            Intrinsics.checkNotNullParameter(settings, "settings");
            String str = this.this$0.getConnectionName$okhttp() + " applyAndAckSettings";
            this.this$0.writerQueue.schedule(new Http2Connection$ReaderRunnable$settings$$inlined$execute$1(str, true, str, true, this, z, settings), 0);
        }

        /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
            java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
            	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
            	at java.util.ArrayList.get(ArrayList.java:435)
            	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
            	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
            	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
            	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
            */
        public final void applyAndAckSettings(boolean r22, okhttp3.internal.http2.Settings r23) {
            /*
                r21 = this;
                r12 = r21
                r0 = r23
                java.lang.String r1 = "settings"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                kotlin.jvm.internal.Ref$LongRef r13 = new kotlin.jvm.internal.Ref$LongRef
                r13.<init>()
                kotlin.jvm.internal.Ref$ObjectRef r14 = new kotlin.jvm.internal.Ref$ObjectRef
                r14.<init>()
                kotlin.jvm.internal.Ref$ObjectRef r15 = new kotlin.jvm.internal.Ref$ObjectRef
                r15.<init>()
                okhttp3.internal.http2.Http2Connection r1 = r12.this$0
                okhttp3.internal.http2.Http2Writer r16 = r1.getWriter()
                monitor-enter(r16)
                okhttp3.internal.http2.Http2Connection r11 = r12.this$0     // Catch:{ all -> 0x00db }
                monitor-enter(r11)     // Catch:{ all -> 0x00db }
                okhttp3.internal.http2.Http2Connection r1 = r12.this$0     // Catch:{ all -> 0x0071 }
                okhttp3.internal.http2.Settings r1 = r1.getPeerSettings()     // Catch:{ all -> 0x0071 }
                if (r22 == 0) goto L_0x002c
                r2 = r0
                goto L_0x0039
            L_0x002c:
                okhttp3.internal.http2.Settings r2 = new okhttp3.internal.http2.Settings     // Catch:{ all -> 0x0071 }
                r2.<init>()     // Catch:{ all -> 0x0071 }
                r2.merge(r1)     // Catch:{ all -> 0x0071 }
                r2.merge(r0)     // Catch:{ all -> 0x0071 }
                kotlin.Unit r3 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0071 }
            L_0x0039:
                r15.element = r2     // Catch:{ all -> 0x0071 }
                int r2 = r2.getInitialWindowSize()     // Catch:{ all -> 0x0071 }
                long r2 = (long) r2     // Catch:{ all -> 0x0071 }
                int r1 = r1.getInitialWindowSize()     // Catch:{ all -> 0x0071 }
                long r4 = (long) r1     // Catch:{ all -> 0x0071 }
                long r2 = r2 - r4
                r13.element = r2     // Catch:{ all -> 0x0071 }
                r9 = 0
                int r1 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
                r8 = 0
                if (r1 == 0) goto L_0x007e
                okhttp3.internal.http2.Http2Connection r1 = r12.this$0     // Catch:{ all -> 0x0071 }
                java.util.Map r1 = r1.getStreams$okhttp()     // Catch:{ all -> 0x0071 }
                boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0071 }
                if (r1 == 0) goto L_0x005c
                goto L_0x007e
            L_0x005c:
                okhttp3.internal.http2.Http2Connection r1 = r12.this$0     // Catch:{ all -> 0x0071 }
                java.util.Map r1 = r1.getStreams$okhttp()     // Catch:{ all -> 0x0071 }
                java.util.Collection r1 = r1.values()     // Catch:{ all -> 0x0071 }
                okhttp3.internal.http2.Http2Stream[] r2 = new okhttp3.internal.http2.Http2Stream[r8]     // Catch:{ all -> 0x0071 }
                java.lang.Object[] r1 = r1.toArray(r2)     // Catch:{ all -> 0x0071 }
                if (r1 == 0) goto L_0x0076
                okhttp3.internal.http2.Http2Stream[] r1 = (okhttp3.internal.http2.Http2Stream[]) r1     // Catch:{ all -> 0x0071 }
                goto L_0x007f
            L_0x0071:
                r0 = move-exception
                r20 = r11
                goto L_0x010a
            L_0x0076:
                java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ all -> 0x0071 }
                java.lang.String r1 = "null cannot be cast to non-null type kotlin.Array<T>"
                r0.<init>(r1)     // Catch:{ all -> 0x0071 }
                throw r0     // Catch:{ all -> 0x0071 }
            L_0x007e:
                r1 = 0
            L_0x007f:
                r14.element = r1     // Catch:{ all -> 0x0071 }
                okhttp3.internal.http2.Http2Connection r1 = r12.this$0     // Catch:{ all -> 0x0071 }
                java.lang.Object r2 = r15.element     // Catch:{ all -> 0x0071 }
                okhttp3.internal.http2.Settings r2 = (okhttp3.internal.http2.Settings) r2     // Catch:{ all -> 0x0071 }
                r1.setPeerSettings(r2)     // Catch:{ all -> 0x0071 }
                okhttp3.internal.http2.Http2Connection r1 = r12.this$0     // Catch:{ all -> 0x0071 }
                okhttp3.internal.concurrent.TaskQueue r7 = r1.settingsListenerQueue     // Catch:{ all -> 0x0071 }
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0071 }
                r1.<init>()     // Catch:{ all -> 0x0071 }
                okhttp3.internal.http2.Http2Connection r2 = r12.this$0     // Catch:{ all -> 0x0071 }
                java.lang.String r2 = r2.getConnectionName$okhttp()     // Catch:{ all -> 0x0071 }
                r1.append(r2)     // Catch:{ all -> 0x0071 }
                java.lang.String r2 = " onSettings"
                r1.append(r2)     // Catch:{ all -> 0x0071 }
                java.lang.String r4 = r1.toString()     // Catch:{ all -> 0x0071 }
                okhttp3.internal.http2.Http2Connection$ReaderRunnable$applyAndAckSettings$$inlined$synchronized$lambda$1 r6 = new okhttp3.internal.http2.Http2Connection$ReaderRunnable$applyAndAckSettings$$inlined$synchronized$lambda$1     // Catch:{ all -> 0x0071 }
                r5 = 1
                r1 = r6
                r2 = r4
                r3 = r5
                r17 = r6
                r6 = r21
                r18 = r7
                r7 = r15
                r19 = r8
                r8 = r22
                r9 = r23
                r10 = r13
                r20 = r11
                r11 = r14
                r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ all -> 0x0109 }
                r3 = r17
                r2 = r18
                r0 = 0
                r2.schedule(r3, r0)     // Catch:{ all -> 0x0109 }
                kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0109 }
                monitor-exit(r20)     // Catch:{ all -> 0x00db }
                okhttp3.internal.http2.Http2Connection r0 = r12.this$0     // Catch:{ IOException -> 0x00dd }
                okhttp3.internal.http2.Http2Writer r0 = r0.getWriter()     // Catch:{ IOException -> 0x00dd }
                java.lang.Object r1 = r15.element     // Catch:{ IOException -> 0x00dd }
                okhttp3.internal.http2.Settings r1 = (okhttp3.internal.http2.Settings) r1     // Catch:{ IOException -> 0x00dd }
                r0.applyAndAckSettings(r1)     // Catch:{ IOException -> 0x00dd }
                goto L_0x00e3
            L_0x00db:
                r0 = move-exception
                goto L_0x010c
            L_0x00dd:
                r0 = move-exception
                okhttp3.internal.http2.Http2Connection r1 = r12.this$0     // Catch:{ all -> 0x00db }
                r1.failConnection(r0)     // Catch:{ all -> 0x00db }
            L_0x00e3:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00db }
                monitor-exit(r16)
                java.lang.Object r0 = r14.element
                r1 = r0
                okhttp3.internal.http2.Http2Stream[] r1 = (okhttp3.internal.http2.Http2Stream[]) r1
                if (r1 == 0) goto L_0x0108
                okhttp3.internal.http2.Http2Stream[] r0 = (okhttp3.internal.http2.Http2Stream[]) r0
                kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
                int r1 = r0.length
                r8 = r19
            L_0x00f5:
                if (r8 >= r1) goto L_0x0108
                r2 = r0[r8]
                monitor-enter(r2)
                long r3 = r13.element     // Catch:{ all -> 0x0105 }
                r2.addBytesToWriteWindow(r3)     // Catch:{ all -> 0x0105 }
                kotlin.Unit r3 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0105 }
                monitor-exit(r2)
                int r8 = r8 + 1
                goto L_0x00f5
            L_0x0105:
                r0 = move-exception
                monitor-exit(r2)
                throw r0
            L_0x0108:
                return
            L_0x0109:
                r0 = move-exception
            L_0x010a:
                monitor-exit(r20)     // Catch:{ all -> 0x00db }
                throw r0     // Catch:{ all -> 0x00db }
            L_0x010c:
                monitor-exit(r16)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Connection.ReaderRunnable.applyAndAckSettings(boolean, okhttp3.internal.http2.Settings):void");
        }

        public void ping(boolean z, int i, int i2) {
            if (z) {
                synchronized (this.this$0) {
                    if (i == 1) {
                        Http2Connection http2Connection = this.this$0;
                        http2Connection.intervalPongsReceived = http2Connection.intervalPongsReceived + 1;
                    } else if (i != 2) {
                        if (i == 3) {
                            Http2Connection http2Connection2 = this.this$0;
                            http2Connection2.awaitPongsReceived = http2Connection2.awaitPongsReceived + 1;
                            Http2Connection http2Connection3 = this.this$0;
                            if (http2Connection3 != null) {
                                http2Connection3.notifyAll();
                            } else {
                                throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                            }
                        }
                        try {
                            Unit unit = Unit.INSTANCE;
                        } catch (Throwable th) {
                            throw th;
                        }
                    } else {
                        Http2Connection http2Connection4 = this.this$0;
                        http2Connection4.degradedPongsReceived = http2Connection4.degradedPongsReceived + 1;
                    }
                }
                return;
            }
            String str = this.this$0.getConnectionName$okhttp() + " ping";
            this.this$0.writerQueue.schedule(new Http2Connection$ReaderRunnable$ping$$inlined$execute$1(str, true, str, true, this, i, i2), 0);
        }

        public void goAway(int i, ErrorCode errorCode, ByteString byteString) {
            int i2;
            Http2Stream[] http2StreamArr;
            Intrinsics.checkNotNullParameter(errorCode, "errorCode");
            Intrinsics.checkNotNullParameter(byteString, "debugData");
            byteString.size();
            synchronized (this.this$0) {
                Object[] array = this.this$0.getStreams$okhttp().values().toArray(new Http2Stream[0]);
                if (array != null) {
                    http2StreamArr = (Http2Stream[]) array;
                    this.this$0.isShutdown = true;
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            }
            for (Http2Stream http2Stream : http2StreamArr) {
                if (http2Stream.getId() > i && http2Stream.isLocallyInitiated()) {
                    http2Stream.receiveRstStream(ErrorCode.REFUSED_STREAM);
                    this.this$0.removeStream$okhttp(http2Stream.getId());
                }
            }
        }

        public void windowUpdate(int i, long j) {
            if (i == 0) {
                synchronized (this.this$0) {
                    Http2Connection http2Connection = this.this$0;
                    http2Connection.writeBytesMaximum = http2Connection.getWriteBytesMaximum() + j;
                    Http2Connection http2Connection2 = this.this$0;
                    if (http2Connection2 != null) {
                        http2Connection2.notifyAll();
                        Unit unit = Unit.INSTANCE;
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                    }
                }
                return;
            }
            Http2Stream stream = this.this$0.getStream(i);
            if (stream != null) {
                synchronized (stream) {
                    stream.addBytesToWriteWindow(j);
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        }

        public void pushPromise(int i, int i2, List<Header> list) {
            Intrinsics.checkNotNullParameter(list, "requestHeaders");
            this.this$0.pushRequestLater$okhttp(i2, list);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(9:(2:6|7)|44|(2:24|(5:26|27|28|40|29))|31|32|33|34|35|37) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0084 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void close$okhttp(okhttp3.internal.http2.ErrorCode r4, okhttp3.internal.http2.ErrorCode r5, java.io.IOException r6) {
        /*
            r3 = this;
            java.lang.String r0 = "connectionCode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "streamCode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            boolean r0 = okhttp3.internal.Util.assertionsEnabled
            if (r0 == 0) goto L_0x0041
            boolean r0 = java.lang.Thread.holdsLock(r3)
            if (r0 != 0) goto L_0x0015
            goto L_0x0041
        L_0x0015:
            java.lang.AssertionError r4 = new java.lang.AssertionError
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Thread "
            r5.append(r6)
            java.lang.Thread r6 = java.lang.Thread.currentThread()
            java.lang.String r0 = "Thread.currentThread()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r0)
            java.lang.String r6 = r6.getName()
            r5.append(r6)
            java.lang.String r6 = " MUST NOT hold lock on "
            r5.append(r6)
            r5.append(r3)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L_0x0041:
            r3.shutdown(r4)     // Catch:{ IOException -> 0x0044 }
        L_0x0044:
            monitor-enter(r3)
            java.util.Map<java.lang.Integer, okhttp3.internal.http2.Http2Stream> r4 = r3.streams     // Catch:{ all -> 0x0064 }
            boolean r4 = r4.isEmpty()     // Catch:{ all -> 0x0064 }
            r0 = 0
            if (r4 != 0) goto L_0x006e
            java.util.Map<java.lang.Integer, okhttp3.internal.http2.Http2Stream> r4 = r3.streams     // Catch:{ all -> 0x0064 }
            java.util.Collection r4 = r4.values()     // Catch:{ all -> 0x0064 }
            okhttp3.internal.http2.Http2Stream[] r1 = new okhttp3.internal.http2.Http2Stream[r0]     // Catch:{ all -> 0x0064 }
            java.lang.Object[] r4 = r4.toArray(r1)     // Catch:{ all -> 0x0064 }
            if (r4 == 0) goto L_0x0066
            okhttp3.internal.http2.Http2Stream[] r4 = (okhttp3.internal.http2.Http2Stream[]) r4     // Catch:{ all -> 0x0064 }
            java.util.Map<java.lang.Integer, okhttp3.internal.http2.Http2Stream> r1 = r3.streams     // Catch:{ all -> 0x0064 }
            r1.clear()     // Catch:{ all -> 0x0064 }
            goto L_0x006f
        L_0x0064:
            r4 = move-exception
            goto L_0x0099
        L_0x0066:
            java.lang.NullPointerException r4 = new java.lang.NullPointerException     // Catch:{ all -> 0x0064 }
            java.lang.String r5 = "null cannot be cast to non-null type kotlin.Array<T>"
            r4.<init>(r5)     // Catch:{ all -> 0x0064 }
            throw r4     // Catch:{ all -> 0x0064 }
        L_0x006e:
            r4 = 0
        L_0x006f:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0064 }
            monitor-exit(r3)
            if (r4 == 0) goto L_0x007f
            int r1 = r4.length
        L_0x0075:
            if (r0 >= r1) goto L_0x007f
            r2 = r4[r0]
            r2.close(r5, r6)     // Catch:{ IOException -> 0x007c }
        L_0x007c:
            int r0 = r0 + 1
            goto L_0x0075
        L_0x007f:
            okhttp3.internal.http2.Http2Writer r4 = r3.writer     // Catch:{ IOException -> 0x0084 }
            r4.close()     // Catch:{ IOException -> 0x0084 }
        L_0x0084:
            java.net.Socket r4 = r3.socket     // Catch:{ IOException -> 0x0089 }
            r4.close()     // Catch:{ IOException -> 0x0089 }
        L_0x0089:
            okhttp3.internal.concurrent.TaskQueue r4 = r3.writerQueue
            r4.shutdown()
            okhttp3.internal.concurrent.TaskQueue r4 = r3.pushQueue
            r4.shutdown()
            okhttp3.internal.concurrent.TaskQueue r4 = r3.settingsListenerQueue
            r4.shutdown()
            return
        L_0x0099:
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Connection.close$okhttp(okhttp3.internal.http2.ErrorCode, okhttp3.internal.http2.ErrorCode, java.io.IOException):void");
    }

    public final void pushRequestLater$okhttp(int i, List<Header> list) {
        Intrinsics.checkNotNullParameter(list, "requestHeaders");
        synchronized (this) {
            if (this.currentPushRequests.contains(Integer.valueOf(i))) {
                writeSynResetLater$okhttp(i, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            this.currentPushRequests.add(Integer.valueOf(i));
            String str = this.connectionName + '[' + i + "] onRequest";
            this.pushQueue.schedule(new Http2Connection$pushRequestLater$$inlined$execute$1(str, true, str, true, this, i, list), 0);
        }
    }

    public final void pushHeadersLater$okhttp(int i, List<Header> list, boolean z) {
        Intrinsics.checkNotNullParameter(list, "requestHeaders");
        String str = this.connectionName + '[' + i + "] onHeaders";
        this.pushQueue.schedule(new Http2Connection$pushHeadersLater$$inlined$execute$1(str, true, str, true, this, i, list, z), 0);
    }

    public final void pushDataLater$okhttp(int i, BufferedSource bufferedSource, int i2, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSource, "source");
        Buffer buffer = new Buffer();
        long j = (long) i2;
        bufferedSource.require(j);
        bufferedSource.read(buffer, j);
        String str = this.connectionName + '[' + i + "] onData";
        this.pushQueue.schedule(new Http2Connection$pushDataLater$$inlined$execute$1(str, true, str, true, this, i, buffer, i2, z), 0);
    }

    public final void pushResetLater$okhttp(int i, ErrorCode errorCode) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        String str = this.connectionName + '[' + i + "] onReset";
        this.pushQueue.schedule(new Http2Connection$pushResetLater$$inlined$execute$1(str, true, str, true, this, i, errorCode), 0);
    }

    public static abstract class Listener {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public static final Listener REFUSE_INCOMING_STREAMS = new Http2Connection$Listener$Companion$REFUSE_INCOMING_STREAMS$1();

        public void onSettings(Http2Connection http2Connection, Settings settings) {
            Intrinsics.checkNotNullParameter(http2Connection, "connection");
            Intrinsics.checkNotNullParameter(settings, "settings");
        }

        public abstract void onStream(Http2Stream http2Stream) throws IOException;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Settings getDEFAULT_SETTINGS() {
            return Http2Connection.DEFAULT_SETTINGS;
        }
    }

    static {
        Settings settings = new Settings();
        settings.set(7, Settings.DEFAULT_INITIAL_WINDOW_SIZE);
        settings.set(5, Http2.INITIAL_MAX_FRAME_SIZE);
        DEFAULT_SETTINGS = settings;
    }
}
