package okio;

import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Intrinsics;

public final class Timeout$Companion$NONE$1 extends Timeout {
    public Timeout deadlineNanoTime(long j) {
        return this;
    }

    public void throwIfReached() {
    }

    public Timeout timeout(long j, TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter(timeUnit, "unit");
        return this;
    }

    Timeout$Companion$NONE$1() {
    }
}
