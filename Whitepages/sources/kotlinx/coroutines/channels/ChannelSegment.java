package kotlinx.coroutines.channels;

import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Segment;

public final class ChannelSegment extends Segment {
    private final BufferedChannel _channel;
    private final AtomicReferenceArray data = new AtomicReferenceArray(BufferedChannelKt.SEGMENT_SIZE * 2);

    public ChannelSegment(long j, ChannelSegment channelSegment, BufferedChannel bufferedChannel, int i) {
        super(j, channelSegment, i);
        this._channel = bufferedChannel;
    }

    public final BufferedChannel getChannel() {
        BufferedChannel bufferedChannel = this._channel;
        Intrinsics.checkNotNull(bufferedChannel);
        return bufferedChannel;
    }

    public int getNumberOfSlots() {
        return BufferedChannelKt.SEGMENT_SIZE;
    }

    public final void storeElement$kotlinx_coroutines_core(int i, Object obj) {
        setElementLazy(i, obj);
    }

    public final Object getElement$kotlinx_coroutines_core(int i) {
        return this.data.get(i * 2);
    }

    public final Object retrieveElement$kotlinx_coroutines_core(int i) {
        Object element$kotlinx_coroutines_core = getElement$kotlinx_coroutines_core(i);
        cleanElement$kotlinx_coroutines_core(i);
        return element$kotlinx_coroutines_core;
    }

    public final void cleanElement$kotlinx_coroutines_core(int i) {
        setElementLazy(i, (Object) null);
    }

    private final void setElementLazy(int i, Object obj) {
        this.data.lazySet(i * 2, obj);
    }

    public final Object getState$kotlinx_coroutines_core(int i) {
        return this.data.get((i * 2) + 1);
    }

    public final void setState$kotlinx_coroutines_core(int i, Object obj) {
        this.data.set((i * 2) + 1, obj);
    }

    public final boolean casState$kotlinx_coroutines_core(int i, Object obj, Object obj2) {
        return ChannelSegment$$ExternalSyntheticBackportWithForwarding0.m(this.data, (i * 2) + 1, obj, obj2);
    }

    public final Object getAndSetState$kotlinx_coroutines_core(int i, Object obj) {
        return this.data.getAndSet((i * 2) + 1, obj);
    }

    public void onCancellation(int i, Throwable th, CoroutineContext coroutineContext) {
        Function1 function1;
        Function1 function12;
        int i2 = BufferedChannelKt.SEGMENT_SIZE;
        boolean z = i >= i2;
        if (z) {
            i -= i2;
        }
        Object element$kotlinx_coroutines_core = getElement$kotlinx_coroutines_core(i);
        while (true) {
            Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core(i);
            if ((state$kotlinx_coroutines_core instanceof Waiter) || (state$kotlinx_coroutines_core instanceof WaiterEB)) {
                if (casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, z ? BufferedChannelKt.INTERRUPTED_SEND : BufferedChannelKt.INTERRUPTED_RCV)) {
                    cleanElement$kotlinx_coroutines_core(i);
                    onCancelledRequest(i, !z);
                    if (z && (function1 = getChannel().onUndeliveredElement) != null) {
                        OnUndeliveredElementKt.callUndeliveredElement(function1, element$kotlinx_coroutines_core, coroutineContext);
                        return;
                    }
                    return;
                }
            } else if (state$kotlinx_coroutines_core == BufferedChannelKt.INTERRUPTED_SEND || state$kotlinx_coroutines_core == BufferedChannelKt.INTERRUPTED_RCV) {
                cleanElement$kotlinx_coroutines_core(i);
            } else if (!(state$kotlinx_coroutines_core == BufferedChannelKt.RESUMING_BY_EB || state$kotlinx_coroutines_core == BufferedChannelKt.RESUMING_BY_RCV)) {
                if (state$kotlinx_coroutines_core != BufferedChannelKt.DONE_RCV && state$kotlinx_coroutines_core != BufferedChannelKt.BUFFERED && state$kotlinx_coroutines_core != BufferedChannelKt.getCHANNEL_CLOSED()) {
                    throw new IllegalStateException(("unexpected state: " + state$kotlinx_coroutines_core).toString());
                }
                return;
            }
        }
        cleanElement$kotlinx_coroutines_core(i);
        if (z && (function12 = getChannel().onUndeliveredElement) != null) {
            OnUndeliveredElementKt.callUndeliveredElement(function12, element$kotlinx_coroutines_core, coroutineContext);
        }
    }

    public final void onCancelledRequest(int i, boolean z) {
        if (z) {
            getChannel().waitExpandBufferCompletion$kotlinx_coroutines_core((this.id * ((long) BufferedChannelKt.SEGMENT_SIZE)) + ((long) i));
        }
        onSlotCleaned();
    }
}
