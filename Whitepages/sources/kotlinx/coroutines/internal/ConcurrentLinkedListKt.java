package kotlinx.coroutines.internal;

import kotlin.jvm.functions.Function2;

public abstract class ConcurrentLinkedListKt {
    /* access modifiers changed from: private */
    public static final Symbol CLOSED = new Symbol("CLOSED");

    public static final Object findSegmentInternal(Segment segment, long j, Function2 function2) {
        while (true) {
            if (segment.id >= j && !segment.isRemoved()) {
                return SegmentOrClosed.m929constructorimpl(segment);
            }
            Object access$getNextOrClosed = segment.getNextOrClosed();
            if (access$getNextOrClosed == CLOSED) {
                return SegmentOrClosed.m929constructorimpl(CLOSED);
            }
            Segment segment2 = (Segment) ((ConcurrentLinkedListNode) access$getNextOrClosed);
            if (segment2 == null) {
                segment2 = (Segment) function2.invoke(Long.valueOf(segment.id + 1), segment);
                if (segment.trySetNext(segment2)) {
                    if (segment.isRemoved()) {
                        segment.remove();
                    }
                }
            }
            segment = segment2;
        }
    }

    public static final ConcurrentLinkedListNode close(ConcurrentLinkedListNode concurrentLinkedListNode) {
        while (true) {
            Object access$getNextOrClosed = concurrentLinkedListNode.getNextOrClosed();
            if (access$getNextOrClosed == CLOSED) {
                return concurrentLinkedListNode;
            }
            ConcurrentLinkedListNode concurrentLinkedListNode2 = (ConcurrentLinkedListNode) access$getNextOrClosed;
            if (concurrentLinkedListNode2 != null) {
                concurrentLinkedListNode = concurrentLinkedListNode2;
            } else if (concurrentLinkedListNode.markAsClosed()) {
                return concurrentLinkedListNode;
            }
        }
    }
}
