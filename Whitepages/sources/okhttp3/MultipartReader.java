package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http1.HeadersReader;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Options;
import okio.Source;
import okio.Timeout;

public final class MultipartReader implements Closeable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Options afterBoundaryOptions;
    private final String boundary;
    private boolean closed;
    private final ByteString crlfDashDashBoundary;
    /* access modifiers changed from: private */
    public PartSource currentPart;
    private final ByteString dashDashBoundary;
    private boolean noMoreParts;
    private int partCount;
    /* access modifiers changed from: private */
    public final BufferedSource source;

    public MultipartReader(BufferedSource bufferedSource, String str) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSource, "source");
        Intrinsics.checkNotNullParameter(str, "boundary");
        this.source = bufferedSource;
        this.boundary = str;
        this.dashDashBoundary = new Buffer().writeUtf8("--").writeUtf8(str).readByteString();
        this.crlfDashDashBoundary = new Buffer().writeUtf8("\r\n--").writeUtf8(str).readByteString();
    }

    public final String boundary() {
        return this.boundary;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MultipartReader(okhttp3.ResponseBody r3) throws java.io.IOException {
        /*
            r2 = this;
            java.lang.String r0 = "response"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            okio.BufferedSource r0 = r3.source()
            okhttp3.MediaType r3 = r3.contentType()
            if (r3 == 0) goto L_0x001b
            java.lang.String r1 = "boundary"
            java.lang.String r3 = r3.parameter(r1)
            if (r3 == 0) goto L_0x001b
            r2.<init>(r0, r3)
            return
        L_0x001b:
            java.net.ProtocolException r3 = new java.net.ProtocolException
            java.lang.String r0 = "expected the Content-Type to have a boundary parameter"
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.MultipartReader.<init>(okhttp3.ResponseBody):void");
    }

    public final Part nextPart() throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        } else if (this.noMoreParts) {
            return null;
        } else {
            if (this.partCount != 0 || !this.source.rangeEquals(0, this.dashDashBoundary)) {
                while (true) {
                    long currentPartBytesRemaining = currentPartBytesRemaining(8192);
                    if (currentPartBytesRemaining == 0) {
                        break;
                    }
                    this.source.skip(currentPartBytesRemaining);
                }
                this.source.skip((long) this.crlfDashDashBoundary.size());
            } else {
                this.source.skip((long) this.dashDashBoundary.size());
            }
            boolean z = false;
            while (true) {
                int select = this.source.select(afterBoundaryOptions);
                if (select == -1) {
                    throw new ProtocolException("unexpected characters after boundary");
                } else if (select == 0) {
                    this.partCount++;
                    Headers readHeaders = new HeadersReader(this.source).readHeaders();
                    PartSource partSource = new PartSource();
                    this.currentPart = partSource;
                    return new Part(readHeaders, Okio.buffer((Source) partSource));
                } else if (select != 1) {
                    if (select == 2 || select == 3) {
                        z = true;
                    }
                } else if (z) {
                    throw new ProtocolException("unexpected characters after boundary");
                } else if (this.partCount != 0) {
                    this.noMoreParts = true;
                    return null;
                } else {
                    throw new ProtocolException("expected at least 1 part");
                }
            }
        }
    }

    private final class PartSource implements Source {
        private final Timeout timeout = new Timeout();

        public PartSource() {
        }

        public void close() {
            if (Intrinsics.areEqual((Object) MultipartReader.this.currentPart, (Object) this)) {
                MultipartReader.this.currentPart = null;
            }
        }

        public long read(Buffer buffer, long j) {
            long j2;
            long j3;
            Buffer buffer2 = buffer;
            long j4 = j;
            Intrinsics.checkNotNullParameter(buffer2, "sink");
            if (!(j4 >= 0)) {
                throw new IllegalArgumentException(("byteCount < 0: " + j4).toString());
            } else if (Intrinsics.areEqual((Object) MultipartReader.this.currentPart, (Object) this)) {
                Timeout timeout2 = MultipartReader.this.source.timeout();
                Timeout timeout3 = this.timeout;
                long timeoutNanos = timeout2.timeoutNanos();
                long minTimeout = Timeout.Companion.minTimeout(timeout3.timeoutNanos(), timeout2.timeoutNanos());
                TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                timeout2.timeout(minTimeout, timeUnit);
                if (timeout2.hasDeadline()) {
                    long deadlineNanoTime = timeout2.deadlineNanoTime();
                    if (timeout3.hasDeadline()) {
                        timeout2.deadlineNanoTime(Math.min(timeout2.deadlineNanoTime(), timeout3.deadlineNanoTime()));
                    }
                    try {
                        long access$currentPartBytesRemaining = MultipartReader.this.currentPartBytesRemaining(j4);
                        if (access$currentPartBytesRemaining == 0) {
                            j3 = -1;
                        } else {
                            j3 = MultipartReader.this.source.read(buffer2, access$currentPartBytesRemaining);
                        }
                        timeout2.timeout(timeoutNanos, timeUnit);
                        if (timeout3.hasDeadline()) {
                            timeout2.deadlineNanoTime(deadlineNanoTime);
                        }
                        return j3;
                    } catch (Throwable th) {
                        timeout2.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                        if (timeout3.hasDeadline()) {
                            timeout2.deadlineNanoTime(deadlineNanoTime);
                        }
                        throw th;
                    }
                } else {
                    if (timeout3.hasDeadline()) {
                        timeout2.deadlineNanoTime(timeout3.deadlineNanoTime());
                    }
                    try {
                        long access$currentPartBytesRemaining2 = MultipartReader.this.currentPartBytesRemaining(j4);
                        if (access$currentPartBytesRemaining2 == 0) {
                            j2 = -1;
                        } else {
                            j2 = MultipartReader.this.source.read(buffer2, access$currentPartBytesRemaining2);
                        }
                        timeout2.timeout(timeoutNanos, timeUnit);
                        if (timeout3.hasDeadline()) {
                            timeout2.clearDeadline();
                        }
                        return j2;
                    } catch (Throwable th2) {
                        timeout2.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                        if (timeout3.hasDeadline()) {
                            timeout2.clearDeadline();
                        }
                        throw th2;
                    }
                }
            } else {
                throw new IllegalStateException("closed");
            }
        }

        public Timeout timeout() {
            return this.timeout;
        }
    }

    /* access modifiers changed from: private */
    public final long currentPartBytesRemaining(long j) {
        this.source.require((long) this.crlfDashDashBoundary.size());
        long indexOf = this.source.getBuffer().indexOf(this.crlfDashDashBoundary);
        if (indexOf == -1) {
            return Math.min(j, (this.source.getBuffer().size() - ((long) this.crlfDashDashBoundary.size())) + 1);
        }
        return Math.min(j, indexOf);
    }

    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            this.currentPart = null;
            this.source.close();
        }
    }

    public static final class Part implements Closeable {
        private final BufferedSource body;
        private final Headers headers;

        public void close() {
            this.body.close();
        }

        public Part(Headers headers2, BufferedSource bufferedSource) {
            Intrinsics.checkNotNullParameter(headers2, "headers");
            Intrinsics.checkNotNullParameter(bufferedSource, "body");
            this.headers = headers2;
            this.body = bufferedSource;
        }

        public final Headers headers() {
            return this.headers;
        }

        public final BufferedSource body() {
            return this.body;
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Options getAfterBoundaryOptions() {
            return MultipartReader.afterBoundaryOptions;
        }
    }

    static {
        Options.Companion companion = Options.Companion;
        ByteString.Companion companion2 = ByteString.Companion;
        afterBoundaryOptions = companion.of(companion2.encodeUtf8("\r\n"), companion2.encodeUtf8("--"), companion2.encodeUtf8(" "), companion2.encodeUtf8("\t"));
    }
}
