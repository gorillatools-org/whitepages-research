package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

public class Timeout {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final Timeout NONE = new Timeout$Companion$NONE$1();
    private long deadlineNanoTime;
    private boolean hasDeadline;
    private long timeoutNanos;

    public Timeout timeout(long j, TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter(timeUnit, "unit");
        if (j >= 0) {
            this.timeoutNanos = timeUnit.toNanos(j);
            return this;
        }
        throw new IllegalArgumentException(("timeout < 0: " + j).toString());
    }

    public long timeoutNanos() {
        return this.timeoutNanos;
    }

    public boolean hasDeadline() {
        return this.hasDeadline;
    }

    public long deadlineNanoTime() {
        if (this.hasDeadline) {
            return this.deadlineNanoTime;
        }
        throw new IllegalStateException("No deadline");
    }

    public Timeout deadlineNanoTime(long j) {
        this.hasDeadline = true;
        this.deadlineNanoTime = j;
        return this;
    }

    public final Timeout deadline(long j, TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter(timeUnit, "unit");
        if (j > 0) {
            return deadlineNanoTime(System.nanoTime() + timeUnit.toNanos(j));
        }
        throw new IllegalArgumentException(("duration <= 0: " + j).toString());
    }

    public Timeout clearTimeout() {
        this.timeoutNanos = 0;
        return this;
    }

    public Timeout clearDeadline() {
        this.hasDeadline = false;
        return this;
    }

    public void throwIfReached() throws IOException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedIOException("interrupted");
        } else if (this.hasDeadline && this.deadlineNanoTime - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public final void awaitSignal(Condition condition) throws InterruptedIOException {
        Intrinsics.checkNotNullParameter(condition, "condition");
        try {
            boolean hasDeadline2 = hasDeadline();
            long timeoutNanos2 = timeoutNanos();
            long j = 0;
            if (hasDeadline2 || timeoutNanos2 != 0) {
                long nanoTime = System.nanoTime();
                if (hasDeadline2 && timeoutNanos2 != 0) {
                    timeoutNanos2 = Math.min(timeoutNanos2, deadlineNanoTime() - nanoTime);
                } else if (hasDeadline2) {
                    timeoutNanos2 = deadlineNanoTime() - nanoTime;
                }
                if (timeoutNanos2 > 0) {
                    condition.await(timeoutNanos2, TimeUnit.NANOSECONDS);
                    j = System.nanoTime() - nanoTime;
                }
                if (j >= timeoutNanos2) {
                    throw new InterruptedIOException("timeout");
                }
                return;
            }
            condition.await();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
    }

    public final void waitUntilNotified(Object obj) throws InterruptedIOException {
        Intrinsics.checkNotNullParameter(obj, "monitor");
        try {
            boolean hasDeadline2 = hasDeadline();
            long timeoutNanos2 = timeoutNanos();
            long j = 0;
            if (hasDeadline2 || timeoutNanos2 != 0) {
                long nanoTime = System.nanoTime();
                if (hasDeadline2 && timeoutNanos2 != 0) {
                    timeoutNanos2 = Math.min(timeoutNanos2, deadlineNanoTime() - nanoTime);
                } else if (hasDeadline2) {
                    timeoutNanos2 = deadlineNanoTime() - nanoTime;
                }
                if (timeoutNanos2 > 0) {
                    long j2 = timeoutNanos2 / 1000000;
                    obj.wait(j2, (int) (timeoutNanos2 - (1000000 * j2)));
                    j = System.nanoTime() - nanoTime;
                }
                if (j >= timeoutNanos2) {
                    throw new InterruptedIOException("timeout");
                }
                return;
            }
            obj.wait();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
    }

    public final <T> T intersectWith(Timeout timeout, Function0 function0) {
        Intrinsics.checkNotNullParameter(timeout, "other");
        Intrinsics.checkNotNullParameter(function0, "block");
        long timeoutNanos2 = timeoutNanos();
        long minTimeout = Companion.minTimeout(timeout.timeoutNanos(), timeoutNanos());
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        timeout(minTimeout, timeUnit);
        if (hasDeadline()) {
            long deadlineNanoTime2 = deadlineNanoTime();
            if (timeout.hasDeadline()) {
                deadlineNanoTime(Math.min(deadlineNanoTime(), timeout.deadlineNanoTime()));
            }
            try {
                T invoke = function0.invoke();
                InlineMarker.finallyStart(1);
                timeout(timeoutNanos2, timeUnit);
                if (timeout.hasDeadline()) {
                    deadlineNanoTime(deadlineNanoTime2);
                }
                InlineMarker.finallyEnd(1);
                return invoke;
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                timeout(timeoutNanos2, TimeUnit.NANOSECONDS);
                if (timeout.hasDeadline()) {
                    deadlineNanoTime(deadlineNanoTime2);
                }
                InlineMarker.finallyEnd(1);
                throw th;
            }
        } else {
            if (timeout.hasDeadline()) {
                deadlineNanoTime(timeout.deadlineNanoTime());
            }
            try {
                T invoke2 = function0.invoke();
                InlineMarker.finallyStart(1);
                timeout(timeoutNanos2, timeUnit);
                if (timeout.hasDeadline()) {
                    clearDeadline();
                }
                InlineMarker.finallyEnd(1);
                return invoke2;
            } catch (Throwable th2) {
                InlineMarker.finallyStart(1);
                timeout(timeoutNanos2, TimeUnit.NANOSECONDS);
                if (timeout.hasDeadline()) {
                    clearDeadline();
                }
                InlineMarker.finallyEnd(1);
                throw th2;
            }
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final long minTimeout(long j, long j2) {
            return (j != 0 && (j2 == 0 || j < j2)) ? j : j2;
        }

        private Companion() {
        }
    }
}
