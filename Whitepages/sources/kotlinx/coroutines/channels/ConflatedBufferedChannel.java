package kotlinx.coroutines.channels;

import kotlin.ExceptionsKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;

public class ConflatedBufferedChannel extends BufferedChannel {
    private final int capacity;
    private final BufferOverflow onBufferOverflow;

    public Object send(Object obj, Continuation continuation) {
        return send$suspendImpl(this, obj, continuation);
    }

    public ConflatedBufferedChannel(int i, BufferOverflow bufferOverflow, Function1 function1) {
        super(i, function1);
        this.capacity = i;
        this.onBufferOverflow = bufferOverflow;
        if (bufferOverflow == BufferOverflow.SUSPEND) {
            throw new IllegalArgumentException(("This implementation does not support suspension for senders, use " + Reflection.getOrCreateKotlinClass(BufferedChannel.class).getSimpleName() + " instead").toString());
        } else if (i < 1) {
            throw new IllegalArgumentException(("Buffered channel capacity must be at least 1, but " + i + " was specified").toString());
        }
    }

    /* access modifiers changed from: protected */
    public boolean isConflatedDropOldest() {
        return this.onBufferOverflow == BufferOverflow.DROP_OLDEST;
    }

    static /* synthetic */ Object send$suspendImpl(ConflatedBufferedChannel conflatedBufferedChannel, Object obj, Continuation continuation) {
        UndeliveredElementException callUndeliveredElementCatchingException$default;
        Object r4 = conflatedBufferedChannel.m920trySendImplMj0NB7M(obj, true);
        if (!(r4 instanceof ChannelResult.Closed)) {
            return Unit.INSTANCE;
        }
        ChannelResult.m908exceptionOrNullimpl(r4);
        Function1 function1 = conflatedBufferedChannel.onUndeliveredElement;
        if (function1 == null || (callUndeliveredElementCatchingException$default = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, obj, (UndeliveredElementException) null, 2, (Object) null)) == null) {
            throw conflatedBufferedChannel.getSendException();
        }
        ExceptionsKt.addSuppressed(callUndeliveredElementCatchingException$default, conflatedBufferedChannel.getSendException());
        throw callUndeliveredElementCatchingException$default;
    }

    /* renamed from: trySend-JP2dKIU  reason: not valid java name */
    public Object m921trySendJP2dKIU(Object obj) {
        return m920trySendImplMj0NB7M(obj, false);
    }

    /* renamed from: trySendImpl-Mj0NB7M  reason: not valid java name */
    private final Object m920trySendImplMj0NB7M(Object obj, boolean z) {
        if (this.onBufferOverflow == BufferOverflow.DROP_LATEST) {
            return m918trySendDropLatestMj0NB7M(obj, z);
        }
        return m919trySendDropOldestJP2dKIU(obj);
    }

    /* renamed from: trySendDropLatest-Mj0NB7M  reason: not valid java name */
    private final Object m918trySendDropLatestMj0NB7M(Object obj, boolean z) {
        Function1 function1;
        UndeliveredElementException callUndeliveredElementCatchingException$default;
        Object r0 = super.m902trySendJP2dKIU(obj);
        if (ChannelResult.m912isSuccessimpl(r0) || ChannelResult.m911isClosedimpl(r0)) {
            return r0;
        }
        if (!z || (function1 = this.onUndeliveredElement) == null || (callUndeliveredElementCatchingException$default = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, obj, (UndeliveredElementException) null, 2, (Object) null)) == null) {
            return ChannelResult.Companion.m917successJP2dKIU(Unit.INSTANCE);
        }
        throw callUndeliveredElementCatchingException$default;
    }

    /* renamed from: trySendDropOldest-JP2dKIU  reason: not valid java name */
    private final Object m919trySendDropOldestJP2dKIU(Object obj) {
        ChannelSegment channelSegment;
        Symbol symbol = BufferedChannelKt.BUFFERED;
        ChannelSegment channelSegment2 = (ChannelSegment) BufferedChannel.sendSegment$FU.get(this);
        while (true) {
            long andIncrement = BufferedChannel.sendersAndCloseStatus$FU.getAndIncrement(this);
            long j = andIncrement & 1152921504606846975L;
            boolean access$isClosedForSend0 = isClosedForSend0(andIncrement);
            int i = BufferedChannelKt.SEGMENT_SIZE;
            long j2 = j / ((long) i);
            int i2 = (int) (j % ((long) i));
            if (channelSegment2.id != j2) {
                ChannelSegment access$findSegmentSend = findSegmentSend(j2, channelSegment2);
                if (access$findSegmentSend != null) {
                    channelSegment = access$findSegmentSend;
                } else if (access$isClosedForSend0) {
                    return ChannelResult.Companion.m915closedJP2dKIU(getSendException());
                }
            } else {
                channelSegment = channelSegment2;
            }
            int access$updateCellSend = updateCellSend(channelSegment, i2, obj, j, symbol, access$isClosedForSend0);
            if (access$updateCellSend == 0) {
                channelSegment.cleanPrev();
                return ChannelResult.Companion.m917successJP2dKIU(Unit.INSTANCE);
            } else if (access$updateCellSend == 1) {
                return ChannelResult.Companion.m917successJP2dKIU(Unit.INSTANCE);
            } else {
                if (access$updateCellSend != 2) {
                    if (access$updateCellSend == 3) {
                        throw new IllegalStateException("unexpected");
                    } else if (access$updateCellSend != 4) {
                        if (access$updateCellSend == 5) {
                            channelSegment.cleanPrev();
                        }
                        channelSegment2 = channelSegment;
                    } else {
                        if (j < getReceiversCounter$kotlinx_coroutines_core()) {
                            channelSegment.cleanPrev();
                        }
                        return ChannelResult.Companion.m915closedJP2dKIU(getSendException());
                    }
                } else if (access$isClosedForSend0) {
                    channelSegment.onSlotCleaned();
                    return ChannelResult.Companion.m915closedJP2dKIU(getSendException());
                } else {
                    Waiter waiter = symbol instanceof Waiter ? (Waiter) symbol : null;
                    if (waiter != null) {
                        prepareSenderForSuspension(waiter, channelSegment, i2);
                    }
                    dropFirstElementUntilTheSpecifiedCellIsInTheBuffer((channelSegment.id * ((long) i)) + ((long) i2));
                    return ChannelResult.Companion.m917successJP2dKIU(Unit.INSTANCE);
                }
            }
        }
    }
}
