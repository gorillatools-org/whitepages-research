package kotlinx.coroutines.sync;

import kotlinx.coroutines.internal.Symbol;

public abstract class SemaphoreKt {
    /* access modifiers changed from: private */
    public static final Symbol BROKEN = new Symbol("BROKEN");
    /* access modifiers changed from: private */
    public static final Symbol CANCELLED = new Symbol("CANCELLED");
    /* access modifiers changed from: private */
    public static final int MAX_SPIN_CYCLES = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.semaphore.maxSpinCycles", 100, 0, 0, 12, (Object) null);
    /* access modifiers changed from: private */
    public static final Symbol PERMIT = new Symbol("PERMIT");
    /* access modifiers changed from: private */
    public static final int SEGMENT_SIZE = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.semaphore.segmentSize", 16, 0, 0, 12, (Object) null);
    /* access modifiers changed from: private */
    public static final Symbol TAKEN = new Symbol("TAKEN");

    /* access modifiers changed from: private */
    public static final SemaphoreSegment createSegment(long j, SemaphoreSegment semaphoreSegment) {
        return new SemaphoreSegment(j, semaphoreSegment, 0);
    }
}
