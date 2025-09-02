package okio;

import java.io.IOException;
import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;

class InputStreamSource implements Source {
    private final InputStream input;
    private final Timeout timeout;

    public InputStreamSource(InputStream inputStream, Timeout timeout2) {
        Intrinsics.checkNotNullParameter(inputStream, "input");
        Intrinsics.checkNotNullParameter(timeout2, "timeout");
        this.input = inputStream;
        this.timeout = timeout2;
    }

    public long read(Buffer buffer, long j) {
        Intrinsics.checkNotNullParameter(buffer, "sink");
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i == 0) {
            return 0;
        }
        if (i >= 0) {
            try {
                this.timeout.throwIfReached();
                Segment writableSegment$okio = buffer.writableSegment$okio(1);
                int read = this.input.read(writableSegment$okio.data, writableSegment$okio.limit, (int) Math.min(j, (long) (8192 - writableSegment$okio.limit)));
                if (read != -1) {
                    writableSegment$okio.limit += read;
                    long j2 = (long) read;
                    buffer.setSize$okio(buffer.size() + j2);
                    return j2;
                } else if (writableSegment$okio.pos != writableSegment$okio.limit) {
                    return -1;
                } else {
                    buffer.head = writableSegment$okio.pop();
                    SegmentPool.recycle(writableSegment$okio);
                    return -1;
                }
            } catch (AssertionError e) {
                if (Okio.isAndroidGetsocknameError(e)) {
                    throw new IOException(e);
                }
                throw e;
            }
        } else {
            throw new IllegalArgumentException(("byteCount < 0: " + j).toString());
        }
    }

    public void close() {
        this.input.close();
    }

    public Timeout timeout() {
        return this.timeout;
    }

    public String toString() {
        return "source(" + this.input + ')';
    }
}
