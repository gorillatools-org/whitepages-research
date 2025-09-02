package okhttp3.internal.cache2;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

public final class Relay {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long FILE_HEADER_SIZE = 32;
    public static final ByteString PREFIX_CLEAN;
    public static final ByteString PREFIX_DIRTY;
    private static final int SOURCE_FILE = 2;
    private static final int SOURCE_UPSTREAM = 1;
    private final Buffer buffer;
    private final long bufferMaxSize;
    private boolean complete;
    private RandomAccessFile file;
    private final ByteString metadata;
    private int sourceCount;
    private Source upstream;
    private final Buffer upstreamBuffer;
    private long upstreamPos;
    private Thread upstreamReader;

    private Relay(RandomAccessFile randomAccessFile, Source source, long j, ByteString byteString, long j2) {
        this.file = randomAccessFile;
        this.upstream = source;
        this.upstreamPos = j;
        this.metadata = byteString;
        this.bufferMaxSize = j2;
        this.upstreamBuffer = new Buffer();
        this.complete = this.upstream == null;
        this.buffer = new Buffer();
    }

    public /* synthetic */ Relay(RandomAccessFile randomAccessFile, Source source, long j, ByteString byteString, long j2, DefaultConstructorMarker defaultConstructorMarker) {
        this(randomAccessFile, source, j, byteString, j2);
    }

    public final RandomAccessFile getFile() {
        return this.file;
    }

    public final void setFile(RandomAccessFile randomAccessFile) {
        this.file = randomAccessFile;
    }

    public final Source getUpstream() {
        return this.upstream;
    }

    public final void setUpstream(Source source) {
        this.upstream = source;
    }

    public final long getUpstreamPos() {
        return this.upstreamPos;
    }

    public final void setUpstreamPos(long j) {
        this.upstreamPos = j;
    }

    public final long getBufferMaxSize() {
        return this.bufferMaxSize;
    }

    public final Thread getUpstreamReader() {
        return this.upstreamReader;
    }

    public final void setUpstreamReader(Thread thread) {
        this.upstreamReader = thread;
    }

    public final Buffer getUpstreamBuffer() {
        return this.upstreamBuffer;
    }

    public final boolean getComplete() {
        return this.complete;
    }

    public final void setComplete(boolean z) {
        this.complete = z;
    }

    public final Buffer getBuffer() {
        return this.buffer;
    }

    public final int getSourceCount() {
        return this.sourceCount;
    }

    public final void setSourceCount(int i) {
        this.sourceCount = i;
    }

    public final boolean isClosed() {
        return this.file == null;
    }

    /* access modifiers changed from: private */
    public final void writeHeader(ByteString byteString, long j, long j2) throws IOException {
        Buffer buffer2 = new Buffer();
        buffer2.write(byteString);
        buffer2.writeLong(j);
        buffer2.writeLong(j2);
        if (buffer2.size() == FILE_HEADER_SIZE) {
            RandomAccessFile randomAccessFile = this.file;
            Intrinsics.checkNotNull(randomAccessFile);
            FileChannel channel = randomAccessFile.getChannel();
            Intrinsics.checkNotNullExpressionValue(channel, "file!!.channel");
            new FileOperator(channel).write(0, buffer2, FILE_HEADER_SIZE);
            return;
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    private final void writeMetadata(long j) throws IOException {
        Buffer buffer2 = new Buffer();
        buffer2.write(this.metadata);
        RandomAccessFile randomAccessFile = this.file;
        Intrinsics.checkNotNull(randomAccessFile);
        FileChannel channel = randomAccessFile.getChannel();
        Intrinsics.checkNotNullExpressionValue(channel, "file!!.channel");
        new FileOperator(channel).write(FILE_HEADER_SIZE + j, buffer2, (long) this.metadata.size());
    }

    public final void commit(long j) throws IOException {
        writeMetadata(j);
        RandomAccessFile randomAccessFile = this.file;
        Intrinsics.checkNotNull(randomAccessFile);
        randomAccessFile.getChannel().force(false);
        writeHeader(PREFIX_CLEAN, j, (long) this.metadata.size());
        RandomAccessFile randomAccessFile2 = this.file;
        Intrinsics.checkNotNull(randomAccessFile2);
        randomAccessFile2.getChannel().force(false);
        synchronized (this) {
            this.complete = true;
            Unit unit = Unit.INSTANCE;
        }
        Source source = this.upstream;
        if (source != null) {
            Util.closeQuietly((Closeable) source);
        }
        this.upstream = null;
    }

    public final ByteString metadata() {
        return this.metadata;
    }

    public final Source newSource() {
        synchronized (this) {
            if (this.file == null) {
                return null;
            }
            this.sourceCount++;
            return new RelaySource();
        }
    }

    public final class RelaySource implements Source {
        private FileOperator fileOperator;
        private long sourcePos;
        private final Timeout timeout = new Timeout();

        public RelaySource() {
            RandomAccessFile file = Relay.this.getFile();
            Intrinsics.checkNotNull(file);
            FileChannel channel = file.getChannel();
            Intrinsics.checkNotNullExpressionValue(channel, "file!!.channel");
            this.fileOperator = new FileOperator(channel);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:29:0x008e, code lost:
            if (r4 != true) goto L_0x00b1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x0090, code lost:
            r8 = java.lang.Math.min(r2, r1.this$0.getUpstreamPos() - r1.sourcePos);
            r2 = r1.fileOperator;
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2);
            r2.read(r1.sourcePos + okhttp3.internal.cache2.Relay.FILE_HEADER_SIZE, r20, r8);
            r1.sourcePos += r8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x00b0, code lost:
            return r8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
            r0 = r1.this$0.getUpstream();
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0);
            r14 = r0.read(r1.this$0.getUpstreamBuffer(), r1.this$0.getBufferMaxSize());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x00cd, code lost:
            if (r14 != -1) goto L_0x00fa;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x00cf, code lost:
            r0 = r1.this$0;
            r0.commit(r0.getUpstreamPos());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d8, code lost:
            r2 = r1.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x00da, code lost:
            monitor-enter(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
            r1.this$0.setUpstreamReader((java.lang.Thread) null);
            r0 = r1.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x00e2, code lost:
            if (r0 == null) goto L_0x00ed;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x00e4, code lost:
            r0.notifyAll();
            r0 = kotlin.Unit.INSTANCE;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x00e9, code lost:
            monitor-exit(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ea, code lost:
            return -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x00f4, code lost:
            throw new java.lang.NullPointerException("null cannot be cast to non-null type java.lang.Object");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x00f7, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
            r9 = java.lang.Math.min(r14, r2);
            r1.this$0.getUpstreamBuffer().copyTo(r20, 0, r9);
            r1.sourcePos += r9;
            r13 = r1.fileOperator;
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13);
            r4 = r14;
            r13.write(r1.this$0.getUpstreamPos() + okhttp3.internal.cache2.Relay.FILE_HEADER_SIZE, r1.this$0.getUpstreamBuffer().clone(), r4);
            r2 = r1.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x0131, code lost:
            monitor-enter(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
            r1.this$0.getBuffer().write(r1.this$0.getUpstreamBuffer(), r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x0153, code lost:
            if (r1.this$0.getBuffer().size() <= r1.this$0.getBufferMaxSize()) goto L_0x0172;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:58:0x0155, code lost:
            r1.this$0.getBuffer().skip(r1.this$0.getBuffer().size() - r1.this$0.getBufferMaxSize());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x0170, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:61:0x0172, code lost:
            r0 = r1.this$0;
            r0.setUpstreamPos(r0.getUpstreamPos() + r4);
            r0 = kotlin.Unit.INSTANCE;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
            monitor-exit(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:64:0x017f, code lost:
            r2 = r1.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:65:0x0181, code lost:
            monitor-enter(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
            r1.this$0.setUpstreamReader((java.lang.Thread) null);
            r0 = r1.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:68:0x0189, code lost:
            if (r0 == null) goto L_0x0192;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:69:0x018b, code lost:
            r0.notifyAll();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:70:0x018e, code lost:
            monitor-exit(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:71:0x018f, code lost:
            return r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:75:0x0199, code lost:
            throw new java.lang.NullPointerException("null cannot be cast to non-null type java.lang.Object");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:80:0x019d, code lost:
            throw r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:82:0x01a0, code lost:
            monitor-enter(r1.this$0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:84:?, code lost:
            r1.this$0.setUpstreamReader((java.lang.Thread) null);
            r3 = r1.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:85:0x01a8, code lost:
            if (r3 == null) goto L_0x01aa;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:87:0x01b1, code lost:
            throw new java.lang.NullPointerException("null cannot be cast to non-null type java.lang.Object");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:88:0x01b2, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:90:0x01b4, code lost:
            r3.notifyAll();
            r3 = kotlin.Unit.INSTANCE;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:92:0x01ba, code lost:
            throw r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:94:0x01bc, code lost:
            throw r0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public long read(okio.Buffer r20, long r21) throws java.io.IOException {
            /*
                r19 = this;
                r1 = r19
                r2 = r21
                java.lang.String r0 = "sink"
                r5 = r20
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                okhttp3.internal.cache2.FileOperator r0 = r1.fileOperator
                r4 = 1
                if (r0 == 0) goto L_0x0012
                r0 = r4
                goto L_0x0013
            L_0x0012:
                r0 = 0
            L_0x0013:
                if (r0 == 0) goto L_0x01bf
                okhttp3.internal.cache2.Relay r8 = okhttp3.internal.cache2.Relay.this
                monitor-enter(r8)
            L_0x0018:
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x0065 }
                long r6 = r0.getUpstreamPos()     // Catch:{ all -> 0x0065 }
                long r9 = r1.sourcePos     // Catch:{ all -> 0x0065 }
                int r0 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
                r6 = 2
                r9 = -1
                if (r0 == 0) goto L_0x0068
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x0065 }
                long r11 = r0.getUpstreamPos()     // Catch:{ all -> 0x0065 }
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x0065 }
                okio.Buffer r0 = r0.getBuffer()     // Catch:{ all -> 0x0065 }
                long r13 = r0.size()     // Catch:{ all -> 0x0065 }
                long r11 = r11 - r13
                long r13 = r1.sourcePos     // Catch:{ all -> 0x0065 }
                int r0 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
                if (r0 >= 0) goto L_0x0040
                r4 = r6
                goto L_0x008b
            L_0x0040:
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x0065 }
                long r6 = r0.getUpstreamPos()     // Catch:{ all -> 0x0065 }
                long r9 = r1.sourcePos     // Catch:{ all -> 0x0065 }
                long r6 = r6 - r9
                long r9 = java.lang.Math.min(r2, r6)     // Catch:{ all -> 0x0065 }
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x0065 }
                okio.Buffer r2 = r0.getBuffer()     // Catch:{ all -> 0x0065 }
                long r3 = r1.sourcePos     // Catch:{ all -> 0x0065 }
                long r6 = r3 - r11
                r3 = r20
                r4 = r6
                r6 = r9
                r2.copyTo(r3, r4, r6)     // Catch:{ all -> 0x0065 }
                long r2 = r1.sourcePos     // Catch:{ all -> 0x0065 }
                long r2 = r2 + r9
                r1.sourcePos = r2     // Catch:{ all -> 0x0065 }
                monitor-exit(r8)
                return r9
            L_0x0065:
                r0 = move-exception
                goto L_0x01bd
            L_0x0068:
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x0065 }
                boolean r0 = r0.getComplete()     // Catch:{ all -> 0x0065 }
                if (r0 == 0) goto L_0x0072
                monitor-exit(r8)
                return r9
            L_0x0072:
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x0065 }
                java.lang.Thread r0 = r0.getUpstreamReader()     // Catch:{ all -> 0x0065 }
                if (r0 == 0) goto L_0x0082
                okio.Timeout r0 = r1.timeout     // Catch:{ all -> 0x0065 }
                okhttp3.internal.cache2.Relay r6 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x0065 }
                r0.waitUntilNotified(r6)     // Catch:{ all -> 0x0065 }
                goto L_0x0018
            L_0x0082:
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x0065 }
                java.lang.Thread r7 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0065 }
                r0.setUpstreamReader(r7)     // Catch:{ all -> 0x0065 }
            L_0x008b:
                monitor-exit(r8)
                r11 = 32
                if (r4 != r6) goto L_0x00b1
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this
                long r6 = r0.getUpstreamPos()
                long r8 = r1.sourcePos
                long r6 = r6 - r8
                long r8 = java.lang.Math.min(r2, r6)
                okhttp3.internal.cache2.FileOperator r2 = r1.fileOperator
                kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
                long r3 = r1.sourcePos
                long r3 = r3 + r11
                r5 = r20
                r6 = r8
                r2.read(r3, r5, r6)
                long r2 = r1.sourcePos
                long r2 = r2 + r8
                r1.sourcePos = r2
                return r8
            L_0x00b1:
                r8 = 0
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x00f7 }
                okio.Source r0 = r0.getUpstream()     // Catch:{ all -> 0x00f7 }
                kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ all -> 0x00f7 }
                okhttp3.internal.cache2.Relay r4 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x00f7 }
                okio.Buffer r4 = r4.getUpstreamBuffer()     // Catch:{ all -> 0x00f7 }
                okhttp3.internal.cache2.Relay r6 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x00f7 }
                long r6 = r6.getBufferMaxSize()     // Catch:{ all -> 0x00f7 }
                long r14 = r0.read(r4, r6)     // Catch:{ all -> 0x00f7 }
                int r0 = (r14 > r9 ? 1 : (r14 == r9 ? 0 : -1))
                if (r0 != 0) goto L_0x00fa
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x00f7 }
                long r2 = r0.getUpstreamPos()     // Catch:{ all -> 0x00f7 }
                r0.commit(r2)     // Catch:{ all -> 0x00f7 }
                okhttp3.internal.cache2.Relay r2 = okhttp3.internal.cache2.Relay.this
                monitor-enter(r2)
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x00eb }
                r0.setUpstreamReader(r8)     // Catch:{ all -> 0x00eb }
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x00eb }
                if (r0 == 0) goto L_0x00ed
                r0.notifyAll()     // Catch:{ all -> 0x00eb }
                kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00eb }
                monitor-exit(r2)
                return r9
            L_0x00eb:
                r0 = move-exception
                goto L_0x00f5
            L_0x00ed:
                java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ all -> 0x00eb }
                java.lang.String r3 = "null cannot be cast to non-null type java.lang.Object"
                r0.<init>(r3)     // Catch:{ all -> 0x00eb }
                throw r0     // Catch:{ all -> 0x00eb }
            L_0x00f5:
                monitor-exit(r2)
                throw r0
            L_0x00f7:
                r0 = move-exception
                goto L_0x019e
            L_0x00fa:
                long r9 = java.lang.Math.min(r14, r2)     // Catch:{ all -> 0x00f7 }
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x00f7 }
                okio.Buffer r2 = r0.getUpstreamBuffer()     // Catch:{ all -> 0x00f7 }
                r6 = 0
                r3 = r20
                r4 = r6
                r6 = r9
                r2.copyTo(r3, r4, r6)     // Catch:{ all -> 0x00f7 }
                long r2 = r1.sourcePos     // Catch:{ all -> 0x00f7 }
                long r2 = r2 + r9
                r1.sourcePos = r2     // Catch:{ all -> 0x00f7 }
                okhttp3.internal.cache2.FileOperator r13 = r1.fileOperator     // Catch:{ all -> 0x00f7 }
                kotlin.jvm.internal.Intrinsics.checkNotNull(r13)     // Catch:{ all -> 0x00f7 }
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x00f7 }
                long r2 = r0.getUpstreamPos()     // Catch:{ all -> 0x00f7 }
                long r2 = r2 + r11
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x00f7 }
                okio.Buffer r0 = r0.getUpstreamBuffer()     // Catch:{ all -> 0x00f7 }
                okio.Buffer r16 = r0.clone()     // Catch:{ all -> 0x00f7 }
                r4 = r14
                r14 = r2
                r17 = r4
                r13.write(r14, r16, r17)     // Catch:{ all -> 0x00f7 }
                okhttp3.internal.cache2.Relay r2 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x00f7 }
                monitor-enter(r2)     // Catch:{ all -> 0x00f7 }
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x0170 }
                okio.Buffer r0 = r0.getBuffer()     // Catch:{ all -> 0x0170 }
                okhttp3.internal.cache2.Relay r3 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x0170 }
                okio.Buffer r3 = r3.getUpstreamBuffer()     // Catch:{ all -> 0x0170 }
                r0.write((okio.Buffer) r3, (long) r4)     // Catch:{ all -> 0x0170 }
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x0170 }
                okio.Buffer r0 = r0.getBuffer()     // Catch:{ all -> 0x0170 }
                long r6 = r0.size()     // Catch:{ all -> 0x0170 }
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x0170 }
                long r11 = r0.getBufferMaxSize()     // Catch:{ all -> 0x0170 }
                int r0 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
                if (r0 <= 0) goto L_0x0172
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x0170 }
                okio.Buffer r0 = r0.getBuffer()     // Catch:{ all -> 0x0170 }
                okhttp3.internal.cache2.Relay r3 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x0170 }
                okio.Buffer r3 = r3.getBuffer()     // Catch:{ all -> 0x0170 }
                long r6 = r3.size()     // Catch:{ all -> 0x0170 }
                okhttp3.internal.cache2.Relay r3 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x0170 }
                long r11 = r3.getBufferMaxSize()     // Catch:{ all -> 0x0170 }
                long r6 = r6 - r11
                r0.skip(r6)     // Catch:{ all -> 0x0170 }
                goto L_0x0172
            L_0x0170:
                r0 = move-exception
                goto L_0x019c
            L_0x0172:
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x0170 }
                long r6 = r0.getUpstreamPos()     // Catch:{ all -> 0x0170 }
                long r6 = r6 + r4
                r0.setUpstreamPos(r6)     // Catch:{ all -> 0x0170 }
                kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0170 }
                monitor-exit(r2)     // Catch:{ all -> 0x00f7 }
                okhttp3.internal.cache2.Relay r2 = okhttp3.internal.cache2.Relay.this
                monitor-enter(r2)
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x0190 }
                r0.setUpstreamReader(r8)     // Catch:{ all -> 0x0190 }
                okhttp3.internal.cache2.Relay r0 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x0190 }
                if (r0 == 0) goto L_0x0192
                r0.notifyAll()     // Catch:{ all -> 0x0190 }
                monitor-exit(r2)
                return r9
            L_0x0190:
                r0 = move-exception
                goto L_0x019a
            L_0x0192:
                java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ all -> 0x0190 }
                java.lang.String r3 = "null cannot be cast to non-null type java.lang.Object"
                r0.<init>(r3)     // Catch:{ all -> 0x0190 }
                throw r0     // Catch:{ all -> 0x0190 }
            L_0x019a:
                monitor-exit(r2)
                throw r0
            L_0x019c:
                monitor-exit(r2)     // Catch:{ all -> 0x00f7 }
                throw r0     // Catch:{ all -> 0x00f7 }
            L_0x019e:
                okhttp3.internal.cache2.Relay r2 = okhttp3.internal.cache2.Relay.this
                monitor-enter(r2)
                okhttp3.internal.cache2.Relay r3 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01b2 }
                r3.setUpstreamReader(r8)     // Catch:{ all -> 0x01b2 }
                okhttp3.internal.cache2.Relay r3 = okhttp3.internal.cache2.Relay.this     // Catch:{ all -> 0x01b2 }
                if (r3 != 0) goto L_0x01b4
                java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ all -> 0x01b2 }
                java.lang.String r3 = "null cannot be cast to non-null type java.lang.Object"
                r0.<init>(r3)     // Catch:{ all -> 0x01b2 }
                throw r0     // Catch:{ all -> 0x01b2 }
            L_0x01b2:
                r0 = move-exception
                goto L_0x01bb
            L_0x01b4:
                r3.notifyAll()     // Catch:{ all -> 0x01b2 }
                kotlin.Unit r3 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x01b2 }
                monitor-exit(r2)
                throw r0
            L_0x01bb:
                monitor-exit(r2)
                throw r0
            L_0x01bd:
                monitor-exit(r8)
                throw r0
            L_0x01bf:
                java.lang.String r0 = "Check failed."
                java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
                r2.<init>(r0)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache2.Relay.RelaySource.read(okio.Buffer, long):long");
        }

        public Timeout timeout() {
            return this.timeout;
        }

        public void close() throws IOException {
            if (this.fileOperator != null) {
                RandomAccessFile randomAccessFile = null;
                this.fileOperator = null;
                synchronized (Relay.this) {
                    try {
                        Relay relay = Relay.this;
                        relay.setSourceCount(relay.getSourceCount() - 1);
                        if (Relay.this.getSourceCount() == 0) {
                            RandomAccessFile file = Relay.this.getFile();
                            Relay.this.setFile((RandomAccessFile) null);
                            randomAccessFile = file;
                        }
                        Unit unit = Unit.INSTANCE;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                if (randomAccessFile != null) {
                    Util.closeQuietly((Closeable) randomAccessFile);
                }
            }
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Relay edit(File file, Source source, ByteString byteString, long j) throws IOException {
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(source, "upstream");
            Intrinsics.checkNotNullParameter(byteString, "metadata");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            Relay relay = new Relay(randomAccessFile, source, 0, byteString, j, (DefaultConstructorMarker) null);
            randomAccessFile.setLength(0);
            relay.writeHeader(Relay.PREFIX_DIRTY, -1, -1);
            return relay;
        }

        public final Relay read(File file) throws IOException {
            Intrinsics.checkNotNullParameter(file, "file");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            FileChannel channel = randomAccessFile.getChannel();
            Intrinsics.checkNotNullExpressionValue(channel, "randomAccessFile.channel");
            FileOperator fileOperator = new FileOperator(channel);
            Buffer buffer = new Buffer();
            fileOperator.read(0, buffer, Relay.FILE_HEADER_SIZE);
            ByteString byteString = Relay.PREFIX_CLEAN;
            if (Intrinsics.areEqual((Object) buffer.readByteString((long) byteString.size()), (Object) byteString)) {
                long readLong = buffer.readLong();
                long readLong2 = buffer.readLong();
                Buffer buffer2 = new Buffer();
                fileOperator.read(readLong + Relay.FILE_HEADER_SIZE, buffer2, readLong2);
                return new Relay(randomAccessFile, (Source) null, readLong, buffer2.readByteString(), 0, (DefaultConstructorMarker) null);
            }
            throw new IOException("unreadable cache file");
        }
    }

    static {
        ByteString.Companion companion = ByteString.Companion;
        PREFIX_CLEAN = companion.encodeUtf8("OkHttp cache v1\n");
        PREFIX_DIRTY = companion.encodeUtf8("OkHttp DIRTY :(\n");
    }
}
