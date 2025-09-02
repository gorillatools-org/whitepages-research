package okio;

import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;

public abstract class ForwardingSink implements Sink {
    private final Sink delegate;

    public ForwardingSink(Sink sink) {
        Intrinsics.checkNotNullParameter(sink, "delegate");
        this.delegate = sink;
    }

    public final Sink delegate() {
        return this.delegate;
    }

    public void write(Buffer buffer, long j) throws IOException {
        Intrinsics.checkNotNullParameter(buffer, "source");
        this.delegate.write(buffer, j);
    }

    public void flush() throws IOException {
        this.delegate.flush();
    }

    public Timeout timeout() {
        return this.delegate.timeout();
    }

    public void close() {
        this.delegate.close();
    }

    public String toString() {
        return getClass().getSimpleName() + '(' + this.delegate + ')';
    }

    /* renamed from: -deprecated_delegate  reason: not valid java name */
    public final Sink m1067deprecated_delegate() {
        return this.delegate;
    }
}
