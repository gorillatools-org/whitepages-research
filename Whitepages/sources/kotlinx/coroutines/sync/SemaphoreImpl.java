package kotlinx.coroutines.sync;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.channels.ChannelSegment$$ExternalSyntheticBackportWithForwarding0;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;

public class SemaphoreImpl {
    private static final AtomicIntegerFieldUpdater _availablePermits$FU;
    private static final AtomicLongFieldUpdater deqIdx$FU;
    private static final AtomicLongFieldUpdater enqIdx$FU;
    private static final AtomicReferenceFieldUpdater head$FU;
    private static final AtomicReferenceFieldUpdater tail$FU;
    private volatile int _availablePermits;
    private volatile long deqIdx;
    private volatile long enqIdx;
    private volatile Object head;
    private final Function1 onCancellationRelease;
    private final int permits;
    private volatile Object tail;

    static {
        Class<SemaphoreImpl> cls = SemaphoreImpl.class;
        Class<Object> cls2 = Object.class;
        head$FU = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "head");
        deqIdx$FU = AtomicLongFieldUpdater.newUpdater(cls, "deqIdx");
        tail$FU = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "tail");
        enqIdx$FU = AtomicLongFieldUpdater.newUpdater(cls, "enqIdx");
        _availablePermits$FU = AtomicIntegerFieldUpdater.newUpdater(cls, "_availablePermits");
    }

    public SemaphoreImpl(int i, int i2) {
        this.permits = i;
        if (i <= 0) {
            throw new IllegalArgumentException(("Semaphore should have at least 1 permit, but had " + i).toString());
        } else if (i2 < 0 || i2 > i) {
            throw new IllegalArgumentException(("The number of acquired permits should be in 0.." + i).toString());
        } else {
            SemaphoreSegment semaphoreSegment = new SemaphoreSegment(0, (SemaphoreSegment) null, 2);
            this.head = semaphoreSegment;
            this.tail = semaphoreSegment;
            this._availablePermits = i - i2;
            this.onCancellationRelease = new SemaphoreImpl$onCancellationRelease$1(this);
        }
    }

    public int getAvailablePermits() {
        return Math.max(_availablePermits$FU.get(this), 0);
    }

    public boolean tryAcquire() {
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = _availablePermits$FU;
            int i = atomicIntegerFieldUpdater.get(this);
            if (i > this.permits) {
                coerceAvailablePermitsAtMaximum();
            } else if (i <= 0) {
                return false;
            } else {
                if (atomicIntegerFieldUpdater.compareAndSet(this, i, i - 1)) {
                    return true;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void acquire(CancellableContinuation cancellableContinuation) {
        while (decPermits() <= 0) {
            Intrinsics.checkNotNull(cancellableContinuation, "null cannot be cast to non-null type kotlinx.coroutines.Waiter");
            if (addAcquireToQueue((Waiter) cancellableContinuation)) {
                return;
            }
        }
        cancellableContinuation.resume(Unit.INSTANCE, this.onCancellationRelease);
    }

    private final int decPermits() {
        int andDecrement;
        do {
            andDecrement = _availablePermits$FU.getAndDecrement(this);
        } while (andDecrement > this.permits);
        return andDecrement;
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void release() {
        /*
            r3 = this;
        L_0x0000:
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = _availablePermits$FU
            int r0 = r0.getAndIncrement(r3)
            int r1 = r3.permits
            if (r0 >= r1) goto L_0x0014
            if (r0 < 0) goto L_0x000d
            return
        L_0x000d:
            boolean r0 = r3.tryResumeNextFromQueue()
            if (r0 == 0) goto L_0x0000
            return
        L_0x0014:
            r3.coerceAvailablePermitsAtMaximum()
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "The number of released permits cannot be greater than "
            r1.append(r2)
            int r2 = r3.permits
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.SemaphoreImpl.release():void");
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    private final void coerceAvailablePermitsAtMaximum() {
        /*
            r3 = this;
        L_0x0000:
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = _availablePermits$FU
            int r1 = r0.get(r3)
            int r2 = r3.permits
            if (r1 <= r2) goto L_0x0010
            boolean r0 = r0.compareAndSet(r3, r1, r2)
            if (r0 == 0) goto L_0x0000
        L_0x0010:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.SemaphoreImpl.coerceAvailablePermitsAtMaximum():void");
    }

    private final boolean addAcquireToQueue(Waiter waiter) {
        Object findSegmentInternal;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = tail$FU;
        SemaphoreSegment semaphoreSegment = (SemaphoreSegment) atomicReferenceFieldUpdater.get(this);
        long andIncrement = enqIdx$FU.getAndIncrement(this);
        SemaphoreImpl$addAcquireToQueue$createNewSegment$1 semaphoreImpl$addAcquireToQueue$createNewSegment$1 = SemaphoreImpl$addAcquireToQueue$createNewSegment$1.INSTANCE;
        long access$getSEGMENT_SIZE$p = andIncrement / ((long) SemaphoreKt.SEGMENT_SIZE);
        loop0:
        while (true) {
            findSegmentInternal = ConcurrentLinkedListKt.findSegmentInternal(semaphoreSegment, access$getSEGMENT_SIZE$p, semaphoreImpl$addAcquireToQueue$createNewSegment$1);
            if (SegmentOrClosed.m931isClosedimpl(findSegmentInternal)) {
                break;
            }
            Segment r8 = SegmentOrClosed.m930getSegmentimpl(findSegmentInternal);
            while (true) {
                Segment segment = (Segment) atomicReferenceFieldUpdater.get(this);
                if (segment.id >= r8.id) {
                    break loop0;
                } else if (r8.tryIncPointers$kotlinx_coroutines_core()) {
                    if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, segment, r8)) {
                        if (segment.decPointers$kotlinx_coroutines_core()) {
                            segment.remove();
                        }
                    } else if (r8.decPointers$kotlinx_coroutines_core()) {
                        r8.remove();
                    }
                }
            }
        }
        SemaphoreSegment semaphoreSegment2 = (SemaphoreSegment) SegmentOrClosed.m930getSegmentimpl(findSegmentInternal);
        int access$getSEGMENT_SIZE$p2 = (int) (andIncrement % ((long) SemaphoreKt.SEGMENT_SIZE));
        if (ChannelSegment$$ExternalSyntheticBackportWithForwarding0.m(semaphoreSegment2.getAcquirers(), access$getSEGMENT_SIZE$p2, (Object) null, waiter)) {
            waiter.invokeOnCancellation(semaphoreSegment2, access$getSEGMENT_SIZE$p2);
            return true;
        }
        if (!ChannelSegment$$ExternalSyntheticBackportWithForwarding0.m(semaphoreSegment2.getAcquirers(), access$getSEGMENT_SIZE$p2, SemaphoreKt.PERMIT, SemaphoreKt.TAKEN)) {
            return false;
        }
        if (waiter instanceof CancellableContinuation) {
            Intrinsics.checkNotNull(waiter, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
            ((CancellableContinuation) waiter).resume(Unit.INSTANCE, this.onCancellationRelease);
            return true;
        }
        throw new IllegalStateException(("unexpected: " + waiter).toString());
    }

    private final boolean tryResumeNextFromQueue() {
        Object findSegmentInternal;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = head$FU;
        SemaphoreSegment semaphoreSegment = (SemaphoreSegment) atomicReferenceFieldUpdater.get(this);
        long andIncrement = deqIdx$FU.getAndIncrement(this);
        long access$getSEGMENT_SIZE$p = andIncrement / ((long) SemaphoreKt.SEGMENT_SIZE);
        SemaphoreImpl$tryResumeNextFromQueue$createNewSegment$1 semaphoreImpl$tryResumeNextFromQueue$createNewSegment$1 = SemaphoreImpl$tryResumeNextFromQueue$createNewSegment$1.INSTANCE;
        loop0:
        while (true) {
            findSegmentInternal = ConcurrentLinkedListKt.findSegmentInternal(semaphoreSegment, access$getSEGMENT_SIZE$p, semaphoreImpl$tryResumeNextFromQueue$createNewSegment$1);
            if (SegmentOrClosed.m931isClosedimpl(findSegmentInternal)) {
                break;
            }
            Segment r8 = SegmentOrClosed.m930getSegmentimpl(findSegmentInternal);
            while (true) {
                Segment segment = (Segment) atomicReferenceFieldUpdater.get(this);
                if (segment.id >= r8.id) {
                    break loop0;
                } else if (r8.tryIncPointers$kotlinx_coroutines_core()) {
                    if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, segment, r8)) {
                        if (segment.decPointers$kotlinx_coroutines_core()) {
                            segment.remove();
                        }
                    } else if (r8.decPointers$kotlinx_coroutines_core()) {
                        r8.remove();
                    }
                }
            }
        }
        SemaphoreSegment semaphoreSegment2 = (SemaphoreSegment) SegmentOrClosed.m930getSegmentimpl(findSegmentInternal);
        semaphoreSegment2.cleanPrev();
        int i = (semaphoreSegment2.id > access$getSEGMENT_SIZE$p ? 1 : (semaphoreSegment2.id == access$getSEGMENT_SIZE$p ? 0 : -1));
        if (i > 0) {
            return false;
        }
        int access$getSEGMENT_SIZE$p2 = (int) (andIncrement % ((long) SemaphoreKt.SEGMENT_SIZE));
        Object andSet = semaphoreSegment2.getAcquirers().getAndSet(access$getSEGMENT_SIZE$p2, SemaphoreKt.PERMIT);
        if (andSet == null) {
            int access$getMAX_SPIN_CYCLES$p = SemaphoreKt.MAX_SPIN_CYCLES;
            for (int i2 = 0; i2 < access$getMAX_SPIN_CYCLES$p; i2++) {
                if (semaphoreSegment2.getAcquirers().get(access$getSEGMENT_SIZE$p2) == SemaphoreKt.TAKEN) {
                    return true;
                }
            }
            return !ChannelSegment$$ExternalSyntheticBackportWithForwarding0.m(semaphoreSegment2.getAcquirers(), access$getSEGMENT_SIZE$p2, SemaphoreKt.PERMIT, SemaphoreKt.BROKEN);
        } else if (andSet == SemaphoreKt.CANCELLED) {
            return false;
        } else {
            return tryResumeAcquire(andSet);
        }
    }

    private final boolean tryResumeAcquire(Object obj) {
        if (obj instanceof CancellableContinuation) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
            CancellableContinuation cancellableContinuation = (CancellableContinuation) obj;
            Object tryResume = cancellableContinuation.tryResume(Unit.INSTANCE, (Object) null, this.onCancellationRelease);
            if (tryResume == null) {
                return false;
            }
            cancellableContinuation.completeResume(tryResume);
            return true;
        }
        throw new IllegalStateException(("unexpected: " + obj).toString());
    }
}
