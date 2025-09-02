package okio;

import java.util.zip.Deflater;
import kotlin.jvm.internal.Intrinsics;

public final class DeflaterSink implements Sink {
    private boolean closed;
    private final Deflater deflater;
    private final BufferedSink sink;

    public DeflaterSink(BufferedSink bufferedSink, Deflater deflater2) {
        Intrinsics.checkNotNullParameter(bufferedSink, "sink");
        Intrinsics.checkNotNullParameter(deflater2, "deflater");
        this.sink = bufferedSink;
        this.deflater = deflater2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DeflaterSink(Sink sink2, Deflater deflater2) {
        this(Okio.buffer(sink2), deflater2);
        Intrinsics.checkNotNullParameter(sink2, "sink");
        Intrinsics.checkNotNullParameter(deflater2, "deflater");
    }

    public void write(Buffer buffer, long j) {
        Intrinsics.checkNotNullParameter(buffer, "source");
        SegmentedByteString.checkOffsetAndCount(buffer.size(), 0, j);
        while (j > 0) {
            Segment segment = buffer.head;
            Intrinsics.checkNotNull(segment);
            int min = (int) Math.min(j, (long) (segment.limit - segment.pos));
            this.deflater.setInput(segment.data, segment.pos, min);
            deflate(false);
            long j2 = (long) min;
            buffer.setSize$okio(buffer.size() - j2);
            int i = segment.pos + min;
            segment.pos = i;
            if (i == segment.limit) {
                buffer.head = segment.pop();
                SegmentPool.recycle(segment);
            }
            j -= j2;
        }
    }

    private final void deflate(boolean z) {
        Segment writableSegment$okio;
        int i;
        Buffer buffer = this.sink.getBuffer();
        while (true) {
            writableSegment$okio = buffer.writableSegment$okio(1);
            if (z) {
                Deflater deflater2 = this.deflater;
                byte[] bArr = writableSegment$okio.data;
                int i2 = writableSegment$okio.limit;
                i = deflater2.deflate(bArr, i2, 8192 - i2, 2);
            } else {
                Deflater deflater3 = this.deflater;
                byte[] bArr2 = writableSegment$okio.data;
                int i3 = writableSegment$okio.limit;
                i = deflater3.deflate(bArr2, i3, 8192 - i3);
            }
            if (i > 0) {
                writableSegment$okio.limit += i;
                buffer.setSize$okio(buffer.size() + ((long) i));
                this.sink.emitCompleteSegments();
            } else if (this.deflater.needsInput()) {
                break;
            }
        }
        if (writableSegment$okio.pos == writableSegment$okio.limit) {
            buffer.head = writableSegment$okio.pop();
            SegmentPool.recycle(writableSegment$okio);
        }
    }

    public void flush() {
        deflate(true);
        this.sink.flush();
    }

    public final void finishDeflate$okio() {
        this.deflater.finish();
        deflate(false);
    }

    public void close() {
        if (!this.closed) {
            try {
                finishDeflate$okio();
                th = null;
            } catch (Throwable th) {
                th = th;
            }
            try {
                this.deflater.end();
            } catch (Throwable th2) {
                if (th == null) {
                    th = th2;
                }
            }
            try {
                this.sink.close();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                }
            }
            this.closed = true;
            if (th != null) {
                throw th;
            }
        }
    }

    public Timeout timeout() {
        return this.sink.timeout();
    }

    public String toString() {
        return "DeflaterSink(" + this.sink + ')';
    }
}
