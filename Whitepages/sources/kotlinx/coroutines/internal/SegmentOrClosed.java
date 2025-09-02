package kotlinx.coroutines.internal;

import kotlin.jvm.internal.Intrinsics;

public abstract class SegmentOrClosed {
    /* renamed from: constructor-impl  reason: not valid java name */
    public static Object m929constructorimpl(Object obj) {
        return obj;
    }

    /* renamed from: isClosed-impl  reason: not valid java name */
    public static final boolean m931isClosedimpl(Object obj) {
        return obj == ConcurrentLinkedListKt.CLOSED;
    }

    /* renamed from: getSegment-impl  reason: not valid java name */
    public static final Segment m930getSegmentimpl(Object obj) {
        if (obj != ConcurrentLinkedListKt.CLOSED) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type S of kotlinx.coroutines.internal.SegmentOrClosed");
            return (Segment) obj;
        }
        throw new IllegalStateException("Does not contain segment");
    }
}
