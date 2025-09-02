package okio;

import kotlin.jvm.internal.Intrinsics;

final class BlackholeSink implements Sink {
    public void close() {
    }

    public void flush() {
    }

    public void write(Buffer buffer, long j) {
        Intrinsics.checkNotNullParameter(buffer, "source");
        buffer.skip(j);
    }

    public Timeout timeout() {
        return Timeout.NONE;
    }
}
