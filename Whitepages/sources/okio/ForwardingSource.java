package okio;

import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;

public abstract class ForwardingSource implements Source {
    private final Source delegate;

    public ForwardingSource(Source source) {
        Intrinsics.checkNotNullParameter(source, "delegate");
        this.delegate = source;
    }

    public final Source delegate() {
        return this.delegate;
    }

    public long read(Buffer buffer, long j) throws IOException {
        Intrinsics.checkNotNullParameter(buffer, "sink");
        return this.delegate.read(buffer, j);
    }

    public Timeout timeout() {
        return this.delegate.timeout();
    }

    public void close() throws IOException {
        this.delegate.close();
    }

    public String toString() {
        return getClass().getSimpleName() + '(' + this.delegate + ')';
    }

    /* renamed from: -deprecated_delegate  reason: not valid java name */
    public final Source m1068deprecated_delegate() {
        return this.delegate;
    }
}
