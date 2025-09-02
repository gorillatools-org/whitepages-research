package okio;

import kotlin.jvm.internal.Intrinsics;

public final class PeekSource implements Source {
    private final Buffer buffer;
    private boolean closed;
    private int expectedPos;
    private Segment expectedSegment;
    private long pos;
    private final BufferedSource upstream;

    public PeekSource(BufferedSource bufferedSource) {
        Intrinsics.checkNotNullParameter(bufferedSource, "upstream");
        this.upstream = bufferedSource;
        Buffer buffer2 = bufferedSource.getBuffer();
        this.buffer = buffer2;
        Segment segment = buffer2.head;
        this.expectedSegment = segment;
        this.expectedPos = segment != null ? segment.pos : -1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0020, code lost:
        if (r3 == r4.pos) goto L_0x002b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long read(okio.Buffer r9, long r10) {
        /*
            r8 = this;
            java.lang.String r0 = "sink"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            r0 = 0
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r2 < 0) goto L_0x0075
            boolean r3 = r8.closed
            if (r3 != 0) goto L_0x006d
            okio.Segment r3 = r8.expectedSegment
            if (r3 == 0) goto L_0x002b
            okio.Buffer r4 = r8.buffer
            okio.Segment r4 = r4.head
            if (r3 != r4) goto L_0x0023
            int r3 = r8.expectedPos
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            int r4 = r4.pos
            if (r3 != r4) goto L_0x0023
            goto L_0x002b
        L_0x0023:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "Peek source is invalid because upstream source was used"
            r9.<init>(r10)
            throw r9
        L_0x002b:
            if (r2 != 0) goto L_0x002e
            return r0
        L_0x002e:
            okio.BufferedSource r0 = r8.upstream
            long r1 = r8.pos
            r3 = 1
            long r1 = r1 + r3
            boolean r0 = r0.request(r1)
            if (r0 != 0) goto L_0x003e
            r9 = -1
            return r9
        L_0x003e:
            okio.Segment r0 = r8.expectedSegment
            if (r0 != 0) goto L_0x0051
            okio.Buffer r0 = r8.buffer
            okio.Segment r0 = r0.head
            if (r0 == 0) goto L_0x0051
            r8.expectedSegment = r0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            int r0 = r0.pos
            r8.expectedPos = r0
        L_0x0051:
            okio.Buffer r0 = r8.buffer
            long r0 = r0.size()
            long r2 = r8.pos
            long r0 = r0 - r2
            long r10 = java.lang.Math.min(r10, r0)
            okio.Buffer r2 = r8.buffer
            long r4 = r8.pos
            r3 = r9
            r6 = r10
            r2.copyTo(r3, r4, r6)
            long r0 = r8.pos
            long r0 = r0 + r10
            r8.pos = r0
            return r10
        L_0x006d:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "closed"
            r9.<init>(r10)
            throw r9
        L_0x0075:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r0 = "byteCount < 0: "
            r9.append(r0)
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.String r9 = r9.toString()
            r10.<init>(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.PeekSource.read(okio.Buffer, long):long");
    }

    public Timeout timeout() {
        return this.upstream.timeout();
    }

    public void close() {
        this.closed = true;
    }
}
