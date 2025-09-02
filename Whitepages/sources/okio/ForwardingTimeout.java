package okio;

import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Intrinsics;

public class ForwardingTimeout extends Timeout {
    private Timeout delegate;

    public final Timeout delegate() {
        return this.delegate;
    }

    public ForwardingTimeout(Timeout timeout) {
        Intrinsics.checkNotNullParameter(timeout, "delegate");
        this.delegate = timeout;
    }

    public final ForwardingTimeout setDelegate(Timeout timeout) {
        Intrinsics.checkNotNullParameter(timeout, "delegate");
        this.delegate = timeout;
        return this;
    }

    public Timeout timeout(long j, TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter(timeUnit, "unit");
        return this.delegate.timeout(j, timeUnit);
    }

    public long timeoutNanos() {
        return this.delegate.timeoutNanos();
    }

    public boolean hasDeadline() {
        return this.delegate.hasDeadline();
    }

    public long deadlineNanoTime() {
        return this.delegate.deadlineNanoTime();
    }

    public Timeout deadlineNanoTime(long j) {
        return this.delegate.deadlineNanoTime(j);
    }

    public Timeout clearTimeout() {
        return this.delegate.clearTimeout();
    }

    public Timeout clearDeadline() {
        return this.delegate.clearDeadline();
    }

    public void throwIfReached() {
        this.delegate.throwIfReached();
    }
}
