package kotlinx.coroutines.internal;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.Intrinsics;

public abstract class ConcurrentLinkedListNode {
    private static final AtomicReferenceFieldUpdater _next$FU;
    private static final AtomicReferenceFieldUpdater _prev$FU;
    private volatile Object _next;
    private volatile Object _prev;

    static {
        Class<ConcurrentLinkedListNode> cls = ConcurrentLinkedListNode.class;
        Class<Object> cls2 = Object.class;
        _next$FU = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "_next");
        _prev$FU = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "_prev");
    }

    public abstract boolean isRemoved();

    public ConcurrentLinkedListNode(ConcurrentLinkedListNode concurrentLinkedListNode) {
        this._prev = concurrentLinkedListNode;
    }

    /* access modifiers changed from: private */
    public final Object getNextOrClosed() {
        return _next$FU.get(this);
    }

    public final ConcurrentLinkedListNode getNext() {
        Object access$getNextOrClosed = getNextOrClosed();
        if (access$getNextOrClosed == ConcurrentLinkedListKt.CLOSED) {
            return null;
        }
        return (ConcurrentLinkedListNode) access$getNextOrClosed;
    }

    public final boolean trySetNext(ConcurrentLinkedListNode concurrentLinkedListNode) {
        return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_next$FU, this, (Object) null, concurrentLinkedListNode);
    }

    public final boolean isTail() {
        return getNext() == null;
    }

    public final ConcurrentLinkedListNode getPrev() {
        return (ConcurrentLinkedListNode) _prev$FU.get(this);
    }

    public final void cleanPrev() {
        _prev$FU.lazySet(this, (Object) null);
    }

    public final boolean markAsClosed() {
        return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_next$FU, this, (Object) null, ConcurrentLinkedListKt.CLOSED);
    }

    public final void remove() {
        Object obj;
        if (!isTail()) {
            while (true) {
                ConcurrentLinkedListNode aliveSegmentLeft = getAliveSegmentLeft();
                ConcurrentLinkedListNode aliveSegmentRight = getAliveSegmentRight();
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _prev$FU;
                do {
                    obj = atomicReferenceFieldUpdater.get(aliveSegmentRight);
                } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, aliveSegmentRight, obj, ((ConcurrentLinkedListNode) obj) == null ? null : aliveSegmentLeft));
                if (aliveSegmentLeft != null) {
                    _next$FU.set(aliveSegmentLeft, aliveSegmentRight);
                }
                if ((!aliveSegmentRight.isRemoved() || aliveSegmentRight.isTail()) && (aliveSegmentLeft == null || !aliveSegmentLeft.isRemoved())) {
                    return;
                }
            }
        }
    }

    private final ConcurrentLinkedListNode getAliveSegmentLeft() {
        ConcurrentLinkedListNode prev = getPrev();
        while (prev != null && prev.isRemoved()) {
            prev = (ConcurrentLinkedListNode) _prev$FU.get(prev);
        }
        return prev;
    }

    private final ConcurrentLinkedListNode getAliveSegmentRight() {
        ConcurrentLinkedListNode next;
        ConcurrentLinkedListNode next2 = getNext();
        Intrinsics.checkNotNull(next2);
        while (next2.isRemoved() && (next = next2.getNext()) != null) {
            next2 = next;
        }
        return next2;
    }
}
