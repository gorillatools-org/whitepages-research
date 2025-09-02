package kotlinx.coroutines.channels;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.ExceptionsKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import kotlinx.coroutines.internal.InlineList;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;

public class BufferedChannel implements Channel {
    private static final AtomicReferenceFieldUpdater _closeCause$FU;
    private static final AtomicLongFieldUpdater bufferEnd$FU;
    private static final AtomicReferenceFieldUpdater bufferEndSegment$FU;
    private static final AtomicReferenceFieldUpdater closeHandler$FU;
    private static final AtomicLongFieldUpdater completedExpandBuffersAndPauseFlag$FU;
    /* access modifiers changed from: private */
    public static final AtomicReferenceFieldUpdater receiveSegment$FU;
    /* access modifiers changed from: private */
    public static final AtomicLongFieldUpdater receivers$FU;
    /* access modifiers changed from: private */
    public static final AtomicReferenceFieldUpdater sendSegment$FU;
    /* access modifiers changed from: private */
    public static final AtomicLongFieldUpdater sendersAndCloseStatus$FU;
    private volatile Object _closeCause;
    private volatile long bufferEnd;
    private volatile Object bufferEndSegment;
    private final int capacity;
    private volatile Object closeHandler;
    private volatile long completedExpandBuffersAndPauseFlag;
    public final Function1 onUndeliveredElement;
    private final Function3 onUndeliveredElementReceiveCancellationConstructor;
    private volatile Object receiveSegment;
    private volatile long receivers;
    private volatile Object sendSegment;
    private volatile long sendersAndCloseStatus;

    static {
        Class<BufferedChannel> cls = BufferedChannel.class;
        sendersAndCloseStatus$FU = AtomicLongFieldUpdater.newUpdater(cls, "sendersAndCloseStatus");
        receivers$FU = AtomicLongFieldUpdater.newUpdater(cls, "receivers");
        bufferEnd$FU = AtomicLongFieldUpdater.newUpdater(cls, "bufferEnd");
        completedExpandBuffersAndPauseFlag$FU = AtomicLongFieldUpdater.newUpdater(cls, "completedExpandBuffersAndPauseFlag");
        Class<Object> cls2 = Object.class;
        sendSegment$FU = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "sendSegment");
        receiveSegment$FU = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "receiveSegment");
        bufferEndSegment$FU = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "bufferEndSegment");
        _closeCause$FU = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "_closeCause");
        closeHandler$FU = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "closeHandler");
    }

    /* access modifiers changed from: protected */
    public boolean isConflatedDropOldest() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onClosedIdempotent() {
    }

    /* access modifiers changed from: protected */
    public void onReceiveDequeued() {
    }

    /* access modifiers changed from: protected */
    public void onReceiveEnqueued() {
    }

    public Object receive(Continuation continuation) {
        return receive$suspendImpl(this, continuation);
    }

    public Object send(Object obj, Continuation continuation) {
        return send$suspendImpl(this, obj, continuation);
    }

    public BufferedChannel(int i, Function1 function1) {
        this.capacity = i;
        this.onUndeliveredElement = function1;
        if (i >= 0) {
            this.bufferEnd = BufferedChannelKt.initialBufferEnd(i);
            this.completedExpandBuffersAndPauseFlag = getBufferEndCounter();
            ChannelSegment channelSegment = new ChannelSegment(0, (ChannelSegment) null, this, 3);
            this.sendSegment = channelSegment;
            this.receiveSegment = channelSegment;
            if (isRendezvousOrUnlimited()) {
                channelSegment = BufferedChannelKt.NULL_SEGMENT;
                Intrinsics.checkNotNull(channelSegment, "null cannot be cast to non-null type kotlinx.coroutines.channels.ChannelSegment<E of kotlinx.coroutines.channels.BufferedChannel>");
            }
            this.bufferEndSegment = channelSegment;
            this.onUndeliveredElementReceiveCancellationConstructor = function1 != null ? new BufferedChannel$onUndeliveredElementReceiveCancellationConstructor$1$1(this) : null;
            this._closeCause = BufferedChannelKt.NO_CLOSE_CAUSE;
            return;
        }
        throw new IllegalArgumentException(("Invalid channel capacity: " + i + ", should be >=0").toString());
    }

    public final long getSendersCounter$kotlinx_coroutines_core() {
        return sendersAndCloseStatus$FU.get(this) & 1152921504606846975L;
    }

    public final long getReceiversCounter$kotlinx_coroutines_core() {
        return receivers$FU.get(this);
    }

    private final long getBufferEndCounter() {
        return bufferEnd$FU.get(this);
    }

    private final boolean isRendezvousOrUnlimited() {
        long bufferEndCounter = getBufferEndCounter();
        return bufferEndCounter == 0 || bufferEndCounter == Long.MAX_VALUE;
    }

    static /* synthetic */ Object send$suspendImpl(BufferedChannel bufferedChannel, Object obj, Continuation continuation) {
        ChannelSegment channelSegment = (ChannelSegment) sendSegment$FU.get(bufferedChannel);
        while (true) {
            long andIncrement = sendersAndCloseStatus$FU.getAndIncrement(bufferedChannel);
            long j = 1152921504606846975L & andIncrement;
            boolean access$isClosedForSend0 = bufferedChannel.isClosedForSend0(andIncrement);
            int i = BufferedChannelKt.SEGMENT_SIZE;
            long j2 = j / ((long) i);
            int i2 = (int) (j % ((long) i));
            if (channelSegment.id != j2) {
                ChannelSegment access$findSegmentSend = bufferedChannel.findSegmentSend(j2, channelSegment);
                if (access$findSegmentSend != null) {
                    channelSegment = access$findSegmentSend;
                } else if (access$isClosedForSend0) {
                    Object onClosedSend = bufferedChannel.onClosedSend(obj, continuation);
                    if (onClosedSend == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return onClosedSend;
                    }
                }
            }
            int access$updateCellSend = bufferedChannel.updateCellSend(channelSegment, i2, obj, j, (Object) null, access$isClosedForSend0);
            if (access$updateCellSend == 0) {
                channelSegment.cleanPrev();
                break;
            } else if (access$updateCellSend == 1) {
                break;
            } else if (access$updateCellSend != 2) {
                if (access$updateCellSend == 3) {
                    Object sendOnNoWaiterSuspend = bufferedChannel.sendOnNoWaiterSuspend(channelSegment, i2, obj, j, continuation);
                    if (sendOnNoWaiterSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return sendOnNoWaiterSuspend;
                    }
                } else if (access$updateCellSend == 4) {
                    if (j < bufferedChannel.getReceiversCounter$kotlinx_coroutines_core()) {
                        channelSegment.cleanPrev();
                    }
                    Object onClosedSend2 = bufferedChannel.onClosedSend(obj, continuation);
                    if (onClosedSend2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return onClosedSend2;
                    }
                } else if (access$updateCellSend == 5) {
                    channelSegment.cleanPrev();
                }
            } else if (access$isClosedForSend0) {
                channelSegment.onSlotCleaned();
                Object onClosedSend3 = bufferedChannel.onClosedSend(obj, continuation);
                if (onClosedSend3 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    return onClosedSend3;
                }
            }
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public final void prepareSenderForSuspension(Waiter waiter, ChannelSegment channelSegment, int i) {
        waiter.invokeOnCancellation(channelSegment, i + BufferedChannelKt.SEGMENT_SIZE);
    }

    /* access modifiers changed from: private */
    public final void onClosedSendOnNoWaiterSuspend(Object obj, CancellableContinuation cancellableContinuation) {
        Function1 function1 = this.onUndeliveredElement;
        if (function1 != null) {
            OnUndeliveredElementKt.callUndeliveredElement(function1, obj, cancellableContinuation.getContext());
        }
        Throwable sendException = getSendException();
        Result.Companion companion = Result.Companion;
        cancellableContinuation.resumeWith(Result.m866constructorimpl(ResultKt.createFailure(sendException)));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        return kotlinx.coroutines.channels.ChannelResult.Companion.m917successJP2dKIU(kotlin.Unit.INSTANCE);
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00b6 A[SYNTHETIC] */
    /* renamed from: trySend-JP2dKIU  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object m902trySendJP2dKIU(java.lang.Object r15) {
        /*
            r14 = this;
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = sendersAndCloseStatus$FU
            long r0 = r0.get(r14)
            boolean r0 = r14.shouldSendSuspend(r0)
            if (r0 == 0) goto L_0x0013
            kotlinx.coroutines.channels.ChannelResult$Companion r15 = kotlinx.coroutines.channels.ChannelResult.Companion
            java.lang.Object r15 = r15.m916failurePtdJZtk()
            return r15
        L_0x0013:
            kotlinx.coroutines.internal.Symbol r8 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_SEND
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = sendSegment$FU
            java.lang.Object r0 = r0.get(r14)
            kotlinx.coroutines.channels.ChannelSegment r0 = (kotlinx.coroutines.channels.ChannelSegment) r0
        L_0x0021:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r1 = sendersAndCloseStatus$FU
            long r1 = r1.getAndIncrement(r14)
            r3 = 1152921504606846975(0xfffffffffffffff, double:1.2882297539194265E-231)
            long r9 = r1 & r3
            boolean r11 = r14.isClosedForSend0(r1)
            int r1 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r2 = (long) r1
            long r2 = r9 / r2
            long r4 = (long) r1
            long r4 = r9 % r4
            int r12 = (int) r4
            long r4 = r0.id
            int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r1 == 0) goto L_0x0059
            kotlinx.coroutines.channels.ChannelSegment r1 = r14.findSegmentSend(r2, r0)
            if (r1 != 0) goto L_0x0057
            if (r11 == 0) goto L_0x0021
        L_0x004b:
            kotlinx.coroutines.channels.ChannelResult$Companion r15 = kotlinx.coroutines.channels.ChannelResult.Companion
            java.lang.Throwable r0 = r14.getSendException()
            java.lang.Object r15 = r15.m915closedJP2dKIU(r0)
            goto L_0x00ba
        L_0x0057:
            r13 = r1
            goto L_0x005a
        L_0x0059:
            r13 = r0
        L_0x005a:
            r0 = r14
            r1 = r13
            r2 = r12
            r3 = r15
            r4 = r9
            r6 = r8
            r7 = r11
            int r0 = r0.updateCellSend(r1, r2, r3, r4, r6, r7)
            if (r0 == 0) goto L_0x00b6
            r1 = 1
            if (r0 == r1) goto L_0x00ad
            r1 = 2
            if (r0 == r1) goto L_0x0090
            r1 = 3
            if (r0 == r1) goto L_0x0088
            r1 = 4
            if (r0 == r1) goto L_0x007c
            r1 = 5
            if (r0 == r1) goto L_0x0077
            goto L_0x007a
        L_0x0077:
            r13.cleanPrev()
        L_0x007a:
            r0 = r13
            goto L_0x0021
        L_0x007c:
            long r0 = r14.getReceiversCounter$kotlinx_coroutines_core()
            int r15 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r15 >= 0) goto L_0x004b
            r13.cleanPrev()
            goto L_0x004b
        L_0x0088:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "unexpected"
            r15.<init>(r0)
            throw r15
        L_0x0090:
            if (r11 == 0) goto L_0x0096
            r13.onSlotCleaned()
            goto L_0x004b
        L_0x0096:
            boolean r15 = r8 instanceof kotlinx.coroutines.Waiter
            if (r15 == 0) goto L_0x009d
            kotlinx.coroutines.Waiter r8 = (kotlinx.coroutines.Waiter) r8
            goto L_0x009e
        L_0x009d:
            r8 = 0
        L_0x009e:
            if (r8 == 0) goto L_0x00a3
            r14.prepareSenderForSuspension(r8, r13, r12)
        L_0x00a3:
            r13.onSlotCleaned()
            kotlinx.coroutines.channels.ChannelResult$Companion r15 = kotlinx.coroutines.channels.ChannelResult.Companion
            java.lang.Object r15 = r15.m916failurePtdJZtk()
            goto L_0x00ba
        L_0x00ad:
            kotlinx.coroutines.channels.ChannelResult$Companion r15 = kotlinx.coroutines.channels.ChannelResult.Companion
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            java.lang.Object r15 = r15.m917successJP2dKIU(r0)
            goto L_0x00ba
        L_0x00b6:
            r13.cleanPrev()
            goto L_0x00ad
        L_0x00ba:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.m902trySendJP2dKIU(java.lang.Object):java.lang.Object");
    }

    private final Object onClosedSend(Object obj, Continuation continuation) {
        UndeliveredElementException callUndeliveredElementCatchingException$default;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        Function1 function1 = this.onUndeliveredElement;
        if (function1 == null || (callUndeliveredElementCatchingException$default = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, obj, (UndeliveredElementException) null, 2, (Object) null)) == null) {
            Throwable sendException = getSendException();
            Result.Companion companion = Result.Companion;
            cancellableContinuationImpl.resumeWith(Result.m866constructorimpl(ResultKt.createFailure(sendException)));
        } else {
            ExceptionsKt.addSuppressed(callUndeliveredElementCatchingException$default, getSendException());
            Result.Companion companion2 = Result.Companion;
            cancellableContinuationImpl.resumeWith(Result.m866constructorimpl(ResultKt.createFailure(callUndeliveredElementCatchingException$default)));
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return result;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARNING: type inference failed for: r7v0 */
    /* JADX WARNING: type inference failed for: r7v2, types: [kotlin.jvm.functions.Function1] */
    /* JADX WARNING: type inference failed for: r7v3, types: [kotlinx.coroutines.Waiter] */
    /* JADX WARNING: type inference failed for: r7v4 */
    /* JADX WARNING: type inference failed for: r7v6 */
    /* JADX WARNING: type inference failed for: r7v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.Object receiveOnNoWaiterSuspend(kotlinx.coroutines.channels.ChannelSegment r9, int r10, long r11, kotlin.coroutines.Continuation r13) {
        /*
            r8 = this;
            kotlin.coroutines.Continuation r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r13)
            kotlinx.coroutines.CancellableContinuationImpl r0 = kotlinx.coroutines.CancellableContinuationKt.getOrCreateCancellableContinuation(r0)
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r6 = r0
            java.lang.Object r1 = r1.updateCellReceive(r2, r3, r4, r6)     // Catch:{ all -> 0x001c }
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.BufferedChannelKt.SUSPEND     // Catch:{ all -> 0x001c }
            if (r1 != r2) goto L_0x001f
            r8.prepareReceiverForSuspension(r0, r9, r10)     // Catch:{ all -> 0x001c }
            goto L_0x00c0
        L_0x001c:
            r9 = move-exception
            goto L_0x00ce
        L_0x001f:
            kotlinx.coroutines.internal.Symbol r10 = kotlinx.coroutines.channels.BufferedChannelKt.FAILED     // Catch:{ all -> 0x001c }
            r7 = 0
            if (r1 != r10) goto L_0x00b0
            long r1 = r8.getSendersCounter$kotlinx_coroutines_core()     // Catch:{ all -> 0x001c }
            int r10 = (r11 > r1 ? 1 : (r11 == r1 ? 0 : -1))
            if (r10 >= 0) goto L_0x0031
            r9.cleanPrev()     // Catch:{ all -> 0x001c }
        L_0x0031:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r9 = receiveSegment$FU     // Catch:{ all -> 0x001c }
            java.lang.Object r9 = r9.get(r8)     // Catch:{ all -> 0x001c }
            kotlinx.coroutines.channels.ChannelSegment r9 = (kotlinx.coroutines.channels.ChannelSegment) r9     // Catch:{ all -> 0x001c }
        L_0x003b:
            boolean r10 = r8.isClosedForReceive()     // Catch:{ all -> 0x001c }
            if (r10 == 0) goto L_0x0046
            r8.onClosedReceiveOnNoWaiterSuspend(r0)     // Catch:{ all -> 0x001c }
            goto L_0x00c0
        L_0x0046:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r10 = receivers$FU     // Catch:{ all -> 0x001c }
            long r10 = r10.getAndIncrement(r8)     // Catch:{ all -> 0x001c }
            int r12 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE     // Catch:{ all -> 0x001c }
            long r1 = (long) r12     // Catch:{ all -> 0x001c }
            long r1 = r10 / r1
            long r3 = (long) r12     // Catch:{ all -> 0x001c }
            long r3 = r10 % r3
            int r12 = (int) r3     // Catch:{ all -> 0x001c }
            long r3 = r9.id     // Catch:{ all -> 0x001c }
            int r3 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r3 == 0) goto L_0x0065
            kotlinx.coroutines.channels.ChannelSegment r1 = r8.findSegmentReceive(r1, r9)     // Catch:{ all -> 0x001c }
            if (r1 != 0) goto L_0x0064
            goto L_0x003b
        L_0x0064:
            r9 = r1
        L_0x0065:
            r1 = r8
            r2 = r9
            r3 = r12
            r4 = r10
            r6 = r0
            java.lang.Object r1 = r1.updateCellReceive(r2, r3, r4, r6)     // Catch:{ all -> 0x001c }
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.BufferedChannelKt.SUSPEND     // Catch:{ all -> 0x001c }
            if (r1 != r2) goto L_0x007d
            if (r0 == 0) goto L_0x0077
            r7 = r0
        L_0x0077:
            if (r7 == 0) goto L_0x00c0
            r8.prepareReceiverForSuspension(r7, r9, r12)     // Catch:{ all -> 0x001c }
            goto L_0x00c0
        L_0x007d:
            kotlinx.coroutines.internal.Symbol r12 = kotlinx.coroutines.channels.BufferedChannelKt.FAILED     // Catch:{ all -> 0x001c }
            if (r1 != r12) goto L_0x008f
            long r1 = r8.getSendersCounter$kotlinx_coroutines_core()     // Catch:{ all -> 0x001c }
            int r10 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r10 >= 0) goto L_0x003b
            r9.cleanPrev()     // Catch:{ all -> 0x001c }
            goto L_0x003b
        L_0x008f:
            kotlinx.coroutines.internal.Symbol r10 = kotlinx.coroutines.channels.BufferedChannelKt.SUSPEND_NO_WAITER     // Catch:{ all -> 0x001c }
            if (r1 == r10) goto L_0x00a8
            r9.cleanPrev()     // Catch:{ all -> 0x001c }
            kotlin.jvm.functions.Function1 r9 = r8.onUndeliveredElement     // Catch:{ all -> 0x001c }
            if (r9 == 0) goto L_0x00a4
            kotlin.coroutines.CoroutineContext r10 = r0.getContext()     // Catch:{ all -> 0x001c }
            kotlin.jvm.functions.Function1 r7 = kotlinx.coroutines.internal.OnUndeliveredElementKt.bindCancellationFun(r9, r1, r10)     // Catch:{ all -> 0x001c }
        L_0x00a4:
            r0.resume(r1, r7)     // Catch:{ all -> 0x001c }
            goto L_0x00c0
        L_0x00a8:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch:{ all -> 0x001c }
            java.lang.String r10 = "unexpected"
            r9.<init>(r10)     // Catch:{ all -> 0x001c }
            throw r9     // Catch:{ all -> 0x001c }
        L_0x00b0:
            r9.cleanPrev()     // Catch:{ all -> 0x001c }
            kotlin.jvm.functions.Function1 r9 = r8.onUndeliveredElement     // Catch:{ all -> 0x001c }
            if (r9 == 0) goto L_0x00a4
            kotlin.coroutines.CoroutineContext r10 = r0.getContext()     // Catch:{ all -> 0x001c }
            kotlin.jvm.functions.Function1 r7 = kotlinx.coroutines.internal.OnUndeliveredElementKt.bindCancellationFun(r9, r1, r10)     // Catch:{ all -> 0x001c }
            goto L_0x00a4
        L_0x00c0:
            java.lang.Object r9 = r0.getResult()
            java.lang.Object r10 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r9 != r10) goto L_0x00cd
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r13)
        L_0x00cd:
            return r9
        L_0x00ce:
            r0.releaseClaimedReusableContinuation$kotlinx_coroutines_core()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.receiveOnNoWaiterSuspend(kotlinx.coroutines.channels.ChannelSegment, int, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:60:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x011b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x011c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.Object sendOnNoWaiterSuspend(kotlinx.coroutines.channels.ChannelSegment r21, int r22, java.lang.Object r23, long r24, kotlin.coroutines.Continuation r26) {
        /*
            r20 = this;
            r9 = r20
            r0 = r23
            kotlin.coroutines.Continuation r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r26)
            kotlinx.coroutines.CancellableContinuationImpl r10 = kotlinx.coroutines.CancellableContinuationKt.getOrCreateCancellableContinuation(r1)
            r8 = 0
            r1 = r20
            r2 = r21
            r3 = r22
            r4 = r23
            r5 = r24
            r7 = r10
            int r1 = r1.updateCellSend(r2, r3, r4, r5, r7, r8)     // Catch:{ all -> 0x0068 }
            if (r1 == 0) goto L_0x00fa
            r11 = 1
            if (r1 == r11) goto L_0x00f1
            r12 = 2
            if (r1 == r12) goto L_0x00e9
            r13 = 4
            if (r1 == r13) goto L_0x00dc
            java.lang.String r14 = "unexpected"
            r15 = 5
            if (r1 != r15) goto L_0x00d6
            r21.cleanPrev()     // Catch:{ all -> 0x0068 }
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = sendSegment$FU     // Catch:{ all -> 0x0068 }
            java.lang.Object r1 = r1.get(r9)     // Catch:{ all -> 0x0068 }
            kotlinx.coroutines.channels.ChannelSegment r1 = (kotlinx.coroutines.channels.ChannelSegment) r1     // Catch:{ all -> 0x0068 }
        L_0x0039:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r2 = sendersAndCloseStatus$FU     // Catch:{ all -> 0x0068 }
            long r2 = r2.getAndIncrement(r9)     // Catch:{ all -> 0x0068 }
            r4 = 1152921504606846975(0xfffffffffffffff, double:1.2882297539194265E-231)
            long r16 = r2 & r4
            boolean r18 = r9.isClosedForSend0(r2)     // Catch:{ all -> 0x0068 }
            int r2 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE     // Catch:{ all -> 0x0068 }
            long r3 = (long) r2     // Catch:{ all -> 0x0068 }
            long r3 = r16 / r3
            long r5 = (long) r2     // Catch:{ all -> 0x0068 }
            long r5 = r16 % r5
            int r8 = (int) r5     // Catch:{ all -> 0x0068 }
            long r5 = r1.id     // Catch:{ all -> 0x0068 }
            int r2 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r2 == 0) goto L_0x006d
            kotlinx.coroutines.channels.ChannelSegment r2 = r9.findSegmentSend(r3, r1)     // Catch:{ all -> 0x0068 }
            if (r2 != 0) goto L_0x006b
            if (r18 == 0) goto L_0x0039
        L_0x0063:
            r9.onClosedSendOnNoWaiterSuspend(r0, r10)     // Catch:{ all -> 0x0068 }
            goto L_0x0108
        L_0x0068:
            r0 = move-exception
            goto L_0x011f
        L_0x006b:
            r7 = r2
            goto L_0x006e
        L_0x006d:
            r7 = r1
        L_0x006e:
            r1 = r20
            r2 = r7
            r3 = r8
            r4 = r23
            r5 = r16
            r21 = r7
            r7 = r10
            r19 = r8
            r8 = r18
            int r1 = r1.updateCellSend(r2, r3, r4, r5, r7, r8)     // Catch:{ all -> 0x0068 }
            if (r1 == 0) goto L_0x00c8
            if (r1 == r11) goto L_0x00bc
            if (r1 == r12) goto L_0x00a7
            r2 = 3
            if (r1 == r2) goto L_0x00a1
            if (r1 == r13) goto L_0x0095
            if (r1 == r15) goto L_0x008f
            goto L_0x0092
        L_0x008f:
            r21.cleanPrev()     // Catch:{ all -> 0x0068 }
        L_0x0092:
            r1 = r21
            goto L_0x0039
        L_0x0095:
            long r1 = r20.getReceiversCounter$kotlinx_coroutines_core()     // Catch:{ all -> 0x0068 }
            int r1 = (r16 > r1 ? 1 : (r16 == r1 ? 0 : -1))
            if (r1 >= 0) goto L_0x0063
            r21.cleanPrev()     // Catch:{ all -> 0x0068 }
            goto L_0x0063
        L_0x00a1:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0068 }
            r0.<init>(r14)     // Catch:{ all -> 0x0068 }
            throw r0     // Catch:{ all -> 0x0068 }
        L_0x00a7:
            if (r18 == 0) goto L_0x00ad
            r21.onSlotCleaned()     // Catch:{ all -> 0x0068 }
            goto L_0x0063
        L_0x00ad:
            if (r10 == 0) goto L_0x00b1
            r0 = r10
            goto L_0x00b2
        L_0x00b1:
            r0 = 0
        L_0x00b2:
            if (r0 == 0) goto L_0x0108
            r2 = r21
            r1 = r19
            r9.prepareSenderForSuspension(r0, r2, r1)     // Catch:{ all -> 0x0068 }
            goto L_0x0108
        L_0x00bc:
            kotlin.Result$Companion r0 = kotlin.Result.Companion     // Catch:{ all -> 0x0068 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0068 }
            java.lang.Object r0 = kotlin.Result.m866constructorimpl(r0)     // Catch:{ all -> 0x0068 }
        L_0x00c4:
            r10.resumeWith(r0)     // Catch:{ all -> 0x0068 }
            goto L_0x0108
        L_0x00c8:
            r2 = r21
            r2.cleanPrev()     // Catch:{ all -> 0x0068 }
            kotlin.Result$Companion r0 = kotlin.Result.Companion     // Catch:{ all -> 0x0068 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0068 }
            java.lang.Object r0 = kotlin.Result.m866constructorimpl(r0)     // Catch:{ all -> 0x0068 }
            goto L_0x00c4
        L_0x00d6:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0068 }
            r0.<init>(r14)     // Catch:{ all -> 0x0068 }
            throw r0     // Catch:{ all -> 0x0068 }
        L_0x00dc:
            long r1 = r20.getReceiversCounter$kotlinx_coroutines_core()     // Catch:{ all -> 0x0068 }
            int r1 = (r24 > r1 ? 1 : (r24 == r1 ? 0 : -1))
            if (r1 >= 0) goto L_0x0063
            r21.cleanPrev()     // Catch:{ all -> 0x0068 }
            goto L_0x0063
        L_0x00e9:
            r0 = r21
            r1 = r22
            r9.prepareSenderForSuspension(r10, r0, r1)     // Catch:{ all -> 0x0068 }
            goto L_0x0108
        L_0x00f1:
            kotlin.Result$Companion r0 = kotlin.Result.Companion     // Catch:{ all -> 0x0068 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0068 }
            java.lang.Object r0 = kotlin.Result.m866constructorimpl(r0)     // Catch:{ all -> 0x0068 }
            goto L_0x00c4
        L_0x00fa:
            r0 = r21
            r21.cleanPrev()     // Catch:{ all -> 0x0068 }
            kotlin.Result$Companion r0 = kotlin.Result.Companion     // Catch:{ all -> 0x0068 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0068 }
            java.lang.Object r0 = kotlin.Result.m866constructorimpl(r0)     // Catch:{ all -> 0x0068 }
            goto L_0x00c4
        L_0x0108:
            java.lang.Object r0 = r10.getResult()
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r0 != r1) goto L_0x0115
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r26)
        L_0x0115:
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r0 != r1) goto L_0x011c
            return r0
        L_0x011c:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x011f:
            r10.releaseClaimedReusableContinuation$kotlinx_coroutines_core()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.sendOnNoWaiterSuspend(kotlinx.coroutines.channels.ChannelSegment, int, java.lang.Object, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final int updateCellSend(ChannelSegment channelSegment, int i, Object obj, long j, Object obj2, boolean z) {
        channelSegment.storeElement$kotlinx_coroutines_core(i, obj);
        if (z) {
            return updateCellSendSlow(channelSegment, i, obj, j, obj2, z);
        }
        Object state$kotlinx_coroutines_core = channelSegment.getState$kotlinx_coroutines_core(i);
        if (state$kotlinx_coroutines_core == null) {
            if (bufferOrRendezvousSend(j)) {
                if (channelSegment.casState$kotlinx_coroutines_core(i, (Object) null, BufferedChannelKt.BUFFERED)) {
                    return 1;
                }
            } else if (obj2 == null) {
                return 3;
            } else {
                if (channelSegment.casState$kotlinx_coroutines_core(i, (Object) null, obj2)) {
                    return 2;
                }
            }
        } else if (state$kotlinx_coroutines_core instanceof Waiter) {
            channelSegment.cleanElement$kotlinx_coroutines_core(i);
            if (tryResumeReceiver(state$kotlinx_coroutines_core, obj)) {
                channelSegment.setState$kotlinx_coroutines_core(i, BufferedChannelKt.DONE_RCV);
                onReceiveDequeued();
                return 0;
            }
            if (channelSegment.getAndSetState$kotlinx_coroutines_core(i, BufferedChannelKt.INTERRUPTED_RCV) != BufferedChannelKt.INTERRUPTED_RCV) {
                channelSegment.onCancelledRequest(i, true);
            }
            return 5;
        }
        return updateCellSendSlow(channelSegment, i, obj, j, obj2, z);
    }

    private final int updateCellSendSlow(ChannelSegment channelSegment, int i, Object obj, long j, Object obj2, boolean z) {
        while (true) {
            Object state$kotlinx_coroutines_core = channelSegment.getState$kotlinx_coroutines_core(i);
            if (state$kotlinx_coroutines_core == null) {
                if (!bufferOrRendezvousSend(j) || z) {
                    if (z) {
                        if (channelSegment.casState$kotlinx_coroutines_core(i, (Object) null, BufferedChannelKt.INTERRUPTED_SEND)) {
                            channelSegment.onCancelledRequest(i, false);
                            return 4;
                        }
                    } else if (obj2 == null) {
                        return 3;
                    } else {
                        if (channelSegment.casState$kotlinx_coroutines_core(i, (Object) null, obj2)) {
                            return 2;
                        }
                    }
                } else if (channelSegment.casState$kotlinx_coroutines_core(i, (Object) null, BufferedChannelKt.BUFFERED)) {
                    return 1;
                }
            } else if (state$kotlinx_coroutines_core == BufferedChannelKt.IN_BUFFER) {
                if (channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, BufferedChannelKt.BUFFERED)) {
                    return 1;
                }
            } else if (state$kotlinx_coroutines_core == BufferedChannelKt.INTERRUPTED_RCV) {
                channelSegment.cleanElement$kotlinx_coroutines_core(i);
                return 5;
            } else if (state$kotlinx_coroutines_core == BufferedChannelKt.POISONED) {
                channelSegment.cleanElement$kotlinx_coroutines_core(i);
                return 5;
            } else if (state$kotlinx_coroutines_core == BufferedChannelKt.getCHANNEL_CLOSED()) {
                channelSegment.cleanElement$kotlinx_coroutines_core(i);
                completeCloseOrCancel();
                return 4;
            } else {
                channelSegment.cleanElement$kotlinx_coroutines_core(i);
                if (state$kotlinx_coroutines_core instanceof WaiterEB) {
                    state$kotlinx_coroutines_core = ((WaiterEB) state$kotlinx_coroutines_core).waiter;
                }
                if (tryResumeReceiver(state$kotlinx_coroutines_core, obj)) {
                    channelSegment.setState$kotlinx_coroutines_core(i, BufferedChannelKt.DONE_RCV);
                    onReceiveDequeued();
                    return 0;
                }
                if (channelSegment.getAndSetState$kotlinx_coroutines_core(i, BufferedChannelKt.INTERRUPTED_RCV) != BufferedChannelKt.INTERRUPTED_RCV) {
                    channelSegment.onCancelledRequest(i, true);
                }
                return 5;
            }
        }
    }

    private final boolean shouldSendSuspend(long j) {
        if (isClosedForSend0(j)) {
            return false;
        }
        return !bufferOrRendezvousSend(j & 1152921504606846975L);
    }

    private final boolean bufferOrRendezvousSend(long j) {
        return j < getBufferEndCounter() || j < getReceiversCounter$kotlinx_coroutines_core() + ((long) this.capacity);
    }

    private final boolean tryResumeReceiver(Object obj, Object obj2) {
        if (obj instanceof BufferedChannelIterator) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator<E of kotlinx.coroutines.channels.BufferedChannel>");
            return ((BufferedChannelIterator) obj).tryResumeHasNext(obj2);
        } else if (obj instanceof CancellableContinuation) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<E of kotlinx.coroutines.channels.BufferedChannel>");
            CancellableContinuation cancellableContinuation = (CancellableContinuation) obj;
            Function1 function1 = this.onUndeliveredElement;
            return BufferedChannelKt.tryResume0(cancellableContinuation, obj2, function1 != null ? OnUndeliveredElementKt.bindCancellationFun(function1, obj2, cancellableContinuation.getContext()) : null);
        } else {
            throw new IllegalStateException(("Unexpected receiver type: " + obj).toString());
        }
    }

    static /* synthetic */ Object receive$suspendImpl(BufferedChannel bufferedChannel, Continuation continuation) {
        ChannelSegment channelSegment = (ChannelSegment) receiveSegment$FU.get(bufferedChannel);
        while (!bufferedChannel.isClosedForReceive()) {
            long andIncrement = receivers$FU.getAndIncrement(bufferedChannel);
            int i = BufferedChannelKt.SEGMENT_SIZE;
            long j = andIncrement / ((long) i);
            int i2 = (int) (andIncrement % ((long) i));
            if (channelSegment.id != j) {
                ChannelSegment access$findSegmentReceive = bufferedChannel.findSegmentReceive(j, channelSegment);
                if (access$findSegmentReceive == null) {
                    continue;
                } else {
                    channelSegment = access$findSegmentReceive;
                }
            }
            Object access$updateCellReceive = bufferedChannel.updateCellReceive(channelSegment, i2, andIncrement, (Object) null);
            if (access$updateCellReceive == BufferedChannelKt.SUSPEND) {
                throw new IllegalStateException("unexpected");
            } else if (access$updateCellReceive == BufferedChannelKt.FAILED) {
                if (andIncrement < bufferedChannel.getSendersCounter$kotlinx_coroutines_core()) {
                    channelSegment.cleanPrev();
                }
            } else if (access$updateCellReceive == BufferedChannelKt.SUSPEND_NO_WAITER) {
                return bufferedChannel.receiveOnNoWaiterSuspend(channelSegment, i2, andIncrement, continuation);
            } else {
                channelSegment.cleanPrev();
                return access$updateCellReceive;
            }
        }
        throw StackTraceRecoveryKt.recoverStackTrace(bufferedChannel.getReceiveException());
    }

    /* access modifiers changed from: private */
    public final void prepareReceiverForSuspension(Waiter waiter, ChannelSegment channelSegment, int i) {
        onReceiveEnqueued();
        waiter.invokeOnCancellation(channelSegment, i);
    }

    /* access modifiers changed from: private */
    public final void onClosedReceiveOnNoWaiterSuspend(CancellableContinuation cancellableContinuation) {
        Result.Companion companion = Result.Companion;
        cancellableContinuation.resumeWith(Result.m866constructorimpl(ResultKt.createFailure(getReceiveException())));
    }

    /* renamed from: tryReceive-PtdJZtk  reason: not valid java name */
    public Object m901tryReceivePtdJZtk() {
        ChannelSegment channelSegment;
        long j = receivers$FU.get(this);
        long j2 = sendersAndCloseStatus$FU.get(this);
        if (isClosedForReceive0(j2)) {
            return ChannelResult.Companion.m915closedJP2dKIU(getCloseCause());
        }
        if (j >= (j2 & 1152921504606846975L)) {
            return ChannelResult.Companion.m916failurePtdJZtk();
        }
        Symbol access$getINTERRUPTED_RCV$p = BufferedChannelKt.INTERRUPTED_RCV;
        ChannelSegment channelSegment2 = (ChannelSegment) receiveSegment$FU.get(this);
        while (!isClosedForReceive()) {
            long andIncrement = receivers$FU.getAndIncrement(this);
            int i = BufferedChannelKt.SEGMENT_SIZE;
            long j3 = andIncrement / ((long) i);
            int i2 = (int) (andIncrement % ((long) i));
            if (channelSegment2.id != j3) {
                ChannelSegment access$findSegmentReceive = findSegmentReceive(j3, channelSegment2);
                if (access$findSegmentReceive == null) {
                    continue;
                } else {
                    channelSegment = access$findSegmentReceive;
                }
            } else {
                channelSegment = channelSegment2;
            }
            Object access$updateCellReceive = updateCellReceive(channelSegment, i2, andIncrement, access$getINTERRUPTED_RCV$p);
            if (access$updateCellReceive == BufferedChannelKt.SUSPEND) {
                Waiter waiter = access$getINTERRUPTED_RCV$p instanceof Waiter ? (Waiter) access$getINTERRUPTED_RCV$p : null;
                if (waiter != null) {
                    prepareReceiverForSuspension(waiter, channelSegment, i2);
                }
                waitExpandBufferCompletion$kotlinx_coroutines_core(andIncrement);
                channelSegment.onSlotCleaned();
                return ChannelResult.Companion.m916failurePtdJZtk();
            } else if (access$updateCellReceive == BufferedChannelKt.FAILED) {
                if (andIncrement < getSendersCounter$kotlinx_coroutines_core()) {
                    channelSegment.cleanPrev();
                }
                channelSegment2 = channelSegment;
            } else if (access$updateCellReceive != BufferedChannelKt.SUSPEND_NO_WAITER) {
                channelSegment.cleanPrev();
                return ChannelResult.Companion.m917successJP2dKIU(access$updateCellReceive);
            } else {
                throw new IllegalStateException("unexpected");
            }
        }
        return ChannelResult.Companion.m915closedJP2dKIU(getCloseCause());
    }

    /* access modifiers changed from: protected */
    public final void dropFirstElementUntilTheSpecifiedCellIsInTheBuffer(long j) {
        UndeliveredElementException callUndeliveredElementCatchingException$default;
        ChannelSegment channelSegment = (ChannelSegment) receiveSegment$FU.get(this);
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = receivers$FU;
            long j2 = atomicLongFieldUpdater.get(this);
            if (j >= Math.max(((long) this.capacity) + j2, getBufferEndCounter())) {
                if (atomicLongFieldUpdater.compareAndSet(this, j2, j2 + 1)) {
                    int i = BufferedChannelKt.SEGMENT_SIZE;
                    long j3 = j2 / ((long) i);
                    int i2 = (int) (j2 % ((long) i));
                    if (channelSegment.id != j3) {
                        ChannelSegment findSegmentReceive = findSegmentReceive(j3, channelSegment);
                        if (findSegmentReceive == null) {
                            continue;
                        } else {
                            channelSegment = findSegmentReceive;
                        }
                    }
                    Object updateCellReceive = updateCellReceive(channelSegment, i2, j2, (Object) null);
                    if (updateCellReceive != BufferedChannelKt.FAILED) {
                        channelSegment.cleanPrev();
                        Function1 function1 = this.onUndeliveredElement;
                        if (!(function1 == null || (callUndeliveredElementCatchingException$default = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, updateCellReceive, (UndeliveredElementException) null, 2, (Object) null)) == null)) {
                            throw callUndeliveredElementCatchingException$default;
                        }
                    } else if (j2 < getSendersCounter$kotlinx_coroutines_core()) {
                        channelSegment.cleanPrev();
                    }
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public final Object updateCellReceive(ChannelSegment channelSegment, int i, long j, Object obj) {
        Object state$kotlinx_coroutines_core = channelSegment.getState$kotlinx_coroutines_core(i);
        if (state$kotlinx_coroutines_core == null) {
            if (j >= (sendersAndCloseStatus$FU.get(this) & 1152921504606846975L)) {
                if (obj == null) {
                    return BufferedChannelKt.SUSPEND_NO_WAITER;
                }
                if (channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, obj)) {
                    expandBuffer();
                    return BufferedChannelKt.SUSPEND;
                }
            }
        } else if (state$kotlinx_coroutines_core == BufferedChannelKt.BUFFERED && channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, BufferedChannelKt.DONE_RCV)) {
            expandBuffer();
            return channelSegment.retrieveElement$kotlinx_coroutines_core(i);
        }
        return updateCellReceiveSlow(channelSegment, i, j, obj);
    }

    private final Object updateCellReceiveSlow(ChannelSegment channelSegment, int i, long j, Object obj) {
        while (true) {
            Object state$kotlinx_coroutines_core = channelSegment.getState$kotlinx_coroutines_core(i);
            if (state$kotlinx_coroutines_core == null || state$kotlinx_coroutines_core == BufferedChannelKt.IN_BUFFER) {
                if (j < (sendersAndCloseStatus$FU.get(this) & 1152921504606846975L)) {
                    if (channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, BufferedChannelKt.POISONED)) {
                        expandBuffer();
                        return BufferedChannelKt.FAILED;
                    }
                } else if (obj == null) {
                    return BufferedChannelKt.SUSPEND_NO_WAITER;
                } else {
                    if (channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, obj)) {
                        expandBuffer();
                        return BufferedChannelKt.SUSPEND;
                    }
                }
            } else if (state$kotlinx_coroutines_core == BufferedChannelKt.BUFFERED) {
                if (channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, BufferedChannelKt.DONE_RCV)) {
                    expandBuffer();
                    return channelSegment.retrieveElement$kotlinx_coroutines_core(i);
                }
            } else if (state$kotlinx_coroutines_core == BufferedChannelKt.INTERRUPTED_SEND) {
                return BufferedChannelKt.FAILED;
            } else {
                if (state$kotlinx_coroutines_core == BufferedChannelKt.POISONED) {
                    return BufferedChannelKt.FAILED;
                }
                if (state$kotlinx_coroutines_core == BufferedChannelKt.getCHANNEL_CLOSED()) {
                    expandBuffer();
                    return BufferedChannelKt.FAILED;
                } else if (state$kotlinx_coroutines_core != BufferedChannelKt.RESUMING_BY_EB && channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, BufferedChannelKt.RESUMING_BY_RCV)) {
                    boolean z = state$kotlinx_coroutines_core instanceof WaiterEB;
                    if (z) {
                        state$kotlinx_coroutines_core = ((WaiterEB) state$kotlinx_coroutines_core).waiter;
                    }
                    if (tryResumeSender(state$kotlinx_coroutines_core, channelSegment, i)) {
                        channelSegment.setState$kotlinx_coroutines_core(i, BufferedChannelKt.DONE_RCV);
                        expandBuffer();
                        return channelSegment.retrieveElement$kotlinx_coroutines_core(i);
                    }
                    channelSegment.setState$kotlinx_coroutines_core(i, BufferedChannelKt.INTERRUPTED_SEND);
                    channelSegment.onCancelledRequest(i, false);
                    if (z) {
                        expandBuffer();
                    }
                    return BufferedChannelKt.FAILED;
                }
            }
        }
    }

    private final boolean tryResumeSender(Object obj, ChannelSegment channelSegment, int i) {
        if (obj instanceof CancellableContinuation) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
            return BufferedChannelKt.tryResume0$default((CancellableContinuation) obj, Unit.INSTANCE, (Function1) null, 2, (Object) null);
        }
        throw new IllegalStateException(("Unexpected waiter: " + obj).toString());
    }

    private final void expandBuffer() {
        if (!isRendezvousOrUnlimited()) {
            ChannelSegment channelSegment = (ChannelSegment) bufferEndSegment$FU.get(this);
            while (true) {
                long andIncrement = bufferEnd$FU.getAndIncrement(this);
                int i = BufferedChannelKt.SEGMENT_SIZE;
                long j = andIncrement / ((long) i);
                if (getSendersCounter$kotlinx_coroutines_core() <= andIncrement) {
                    if (channelSegment.id < j && channelSegment.getNext() != null) {
                        moveSegmentBufferEndToSpecifiedOrLast(j, channelSegment);
                    }
                    incCompletedExpandBufferAttempts$default(this, 0, 1, (Object) null);
                    return;
                }
                if (channelSegment.id != j) {
                    ChannelSegment findSegmentBufferEnd = findSegmentBufferEnd(j, channelSegment, andIncrement);
                    if (findSegmentBufferEnd == null) {
                        continue;
                    } else {
                        channelSegment = findSegmentBufferEnd;
                    }
                }
                if (updateCellExpandBuffer(channelSegment, (int) (andIncrement % ((long) i)), andIncrement)) {
                    incCompletedExpandBufferAttempts$default(this, 0, 1, (Object) null);
                    return;
                }
                incCompletedExpandBufferAttempts$default(this, 0, 1, (Object) null);
            }
        }
    }

    private final boolean updateCellExpandBuffer(ChannelSegment channelSegment, int i, long j) {
        Object state$kotlinx_coroutines_core = channelSegment.getState$kotlinx_coroutines_core(i);
        if (!(state$kotlinx_coroutines_core instanceof Waiter) || j < receivers$FU.get(this) || !channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, BufferedChannelKt.RESUMING_BY_EB)) {
            return updateCellExpandBufferSlow(channelSegment, i, j);
        }
        if (tryResumeSender(state$kotlinx_coroutines_core, channelSegment, i)) {
            channelSegment.setState$kotlinx_coroutines_core(i, BufferedChannelKt.BUFFERED);
            return true;
        }
        channelSegment.setState$kotlinx_coroutines_core(i, BufferedChannelKt.INTERRUPTED_SEND);
        channelSegment.onCancelledRequest(i, false);
        return false;
    }

    private final boolean updateCellExpandBufferSlow(ChannelSegment channelSegment, int i, long j) {
        while (true) {
            Object state$kotlinx_coroutines_core = channelSegment.getState$kotlinx_coroutines_core(i);
            if (state$kotlinx_coroutines_core instanceof Waiter) {
                if (j < receivers$FU.get(this)) {
                    if (channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, new WaiterEB((Waiter) state$kotlinx_coroutines_core))) {
                        return true;
                    }
                } else if (channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, BufferedChannelKt.RESUMING_BY_EB)) {
                    if (tryResumeSender(state$kotlinx_coroutines_core, channelSegment, i)) {
                        channelSegment.setState$kotlinx_coroutines_core(i, BufferedChannelKt.BUFFERED);
                        return true;
                    }
                    channelSegment.setState$kotlinx_coroutines_core(i, BufferedChannelKt.INTERRUPTED_SEND);
                    channelSegment.onCancelledRequest(i, false);
                    return false;
                }
            } else if (state$kotlinx_coroutines_core == BufferedChannelKt.INTERRUPTED_SEND) {
                return false;
            } else {
                if (state$kotlinx_coroutines_core == null) {
                    if (channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, BufferedChannelKt.IN_BUFFER)) {
                        return true;
                    }
                } else if (state$kotlinx_coroutines_core == BufferedChannelKt.BUFFERED || state$kotlinx_coroutines_core == BufferedChannelKt.POISONED || state$kotlinx_coroutines_core == BufferedChannelKt.DONE_RCV || state$kotlinx_coroutines_core == BufferedChannelKt.INTERRUPTED_RCV || state$kotlinx_coroutines_core == BufferedChannelKt.getCHANNEL_CLOSED()) {
                    return true;
                } else {
                    if (state$kotlinx_coroutines_core != BufferedChannelKt.RESUMING_BY_RCV) {
                        throw new IllegalStateException(("Unexpected cell state: " + state$kotlinx_coroutines_core).toString());
                    }
                }
            }
        }
        return true;
    }

    static /* synthetic */ void incCompletedExpandBufferAttempts$default(BufferedChannel bufferedChannel, long j, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                j = 1;
            }
            bufferedChannel.incCompletedExpandBufferAttempts(j);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: incCompletedExpandBufferAttempts");
    }

    private final void incCompletedExpandBufferAttempts(long j) {
        if ((completedExpandBuffersAndPauseFlag$FU.addAndGet(this, j) & 4611686018427387904L) != 0) {
            do {
            } while ((completedExpandBuffersAndPauseFlag$FU.get(this) & 4611686018427387904L) != 0);
        }
    }

    public final void waitExpandBufferCompletion$kotlinx_coroutines_core(long j) {
        long j2;
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j3;
        if (!isRendezvousOrUnlimited()) {
            do {
            } while (getBufferEndCounter() <= j);
            int access$getEXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS$p = BufferedChannelKt.EXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS;
            int i = 0;
            while (i < access$getEXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS$p) {
                long bufferEndCounter = getBufferEndCounter();
                if (bufferEndCounter != (4611686018427387903L & completedExpandBuffersAndPauseFlag$FU.get(this)) || bufferEndCounter != getBufferEndCounter()) {
                    i++;
                } else {
                    return;
                }
            }
            AtomicLongFieldUpdater atomicLongFieldUpdater2 = completedExpandBuffersAndPauseFlag$FU;
            do {
                j2 = atomicLongFieldUpdater2.get(this);
            } while (!atomicLongFieldUpdater2.compareAndSet(this, j2, BufferedChannelKt.constructEBCompletedAndPauseFlag(j2 & 4611686018427387903L, true)));
            while (true) {
                long bufferEndCounter2 = getBufferEndCounter();
                atomicLongFieldUpdater = completedExpandBuffersAndPauseFlag$FU;
                long j4 = atomicLongFieldUpdater.get(this);
                long j5 = j4 & 4611686018427387903L;
                boolean z = (4611686018427387904L & j4) != 0;
                if (bufferEndCounter2 == j5 && bufferEndCounter2 == getBufferEndCounter()) {
                    break;
                } else if (!z) {
                    atomicLongFieldUpdater.compareAndSet(this, j4, BufferedChannelKt.constructEBCompletedAndPauseFlag(j5, true));
                }
            }
            do {
                j3 = atomicLongFieldUpdater.get(this);
            } while (!atomicLongFieldUpdater.compareAndSet(this, j3, BufferedChannelKt.constructEBCompletedAndPauseFlag(j3 & 4611686018427387903L, false)));
        }
    }

    public ChannelIterator iterator() {
        return new BufferedChannelIterator();
    }

    private final class BufferedChannelIterator implements ChannelIterator, Waiter {
        /* access modifiers changed from: private */
        public CancellableContinuationImpl continuation;
        /* access modifiers changed from: private */
        public Object receiveResult = BufferedChannelKt.NO_RECEIVE_RESULT;

        public BufferedChannelIterator() {
        }

        public Object hasNext(Continuation continuation2) {
            ChannelSegment channelSegment;
            BufferedChannel bufferedChannel = BufferedChannel.this;
            ChannelSegment channelSegment2 = (ChannelSegment) BufferedChannel.receiveSegment$FU.get(bufferedChannel);
            while (!bufferedChannel.isClosedForReceive()) {
                long andIncrement = BufferedChannel.receivers$FU.getAndIncrement(bufferedChannel);
                int i = BufferedChannelKt.SEGMENT_SIZE;
                long j = andIncrement / ((long) i);
                int i2 = (int) (andIncrement % ((long) i));
                if (channelSegment2.id != j) {
                    ChannelSegment access$findSegmentReceive = bufferedChannel.findSegmentReceive(j, channelSegment2);
                    if (access$findSegmentReceive == null) {
                        continue;
                    } else {
                        channelSegment = access$findSegmentReceive;
                    }
                } else {
                    channelSegment = channelSegment2;
                }
                Object access$updateCellReceive = bufferedChannel.updateCellReceive(channelSegment, i2, andIncrement, (Object) null);
                if (access$updateCellReceive == BufferedChannelKt.SUSPEND) {
                    throw new IllegalStateException("unreachable");
                } else if (access$updateCellReceive == BufferedChannelKt.FAILED) {
                    if (andIncrement < bufferedChannel.getSendersCounter$kotlinx_coroutines_core()) {
                        channelSegment.cleanPrev();
                    }
                    channelSegment2 = channelSegment;
                } else if (access$updateCellReceive == BufferedChannelKt.SUSPEND_NO_WAITER) {
                    return hasNextOnNoWaiterSuspend(channelSegment, i2, andIncrement, continuation2);
                } else {
                    channelSegment.cleanPrev();
                    this.receiveResult = access$updateCellReceive;
                    return Boxing.boxBoolean(true);
                }
            }
            return Boxing.boxBoolean(onClosedHasNext());
        }

        private final boolean onClosedHasNext() {
            this.receiveResult = BufferedChannelKt.getCHANNEL_CLOSED();
            Throwable closeCause = BufferedChannel.this.getCloseCause();
            if (closeCause == null) {
                return false;
            }
            throw StackTraceRecoveryKt.recoverStackTrace(closeCause);
        }

        private final Object hasNextOnNoWaiterSuspend(ChannelSegment channelSegment, int i, long j, Continuation continuation2) {
            Boolean boxBoolean;
            BufferedChannel bufferedChannel = BufferedChannel.this;
            CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation2));
            try {
                this.continuation = orCreateCancellableContinuation;
                Object access$updateCellReceive = bufferedChannel.updateCellReceive(channelSegment, i, j, this);
                if (access$updateCellReceive == BufferedChannelKt.SUSPEND) {
                    bufferedChannel.prepareReceiverForSuspension(this, channelSegment, i);
                } else {
                    Function1 function1 = null;
                    if (access$updateCellReceive == BufferedChannelKt.FAILED) {
                        if (j < bufferedChannel.getSendersCounter$kotlinx_coroutines_core()) {
                            channelSegment.cleanPrev();
                        }
                        ChannelSegment channelSegment2 = (ChannelSegment) BufferedChannel.receiveSegment$FU.get(bufferedChannel);
                        while (true) {
                            if (bufferedChannel.isClosedForReceive()) {
                                onClosedHasNextNoWaiterSuspend();
                                break;
                            }
                            long andIncrement = BufferedChannel.receivers$FU.getAndIncrement(bufferedChannel);
                            int i2 = BufferedChannelKt.SEGMENT_SIZE;
                            long j2 = andIncrement / ((long) i2);
                            int i3 = (int) (andIncrement % ((long) i2));
                            if (channelSegment2.id != j2) {
                                ChannelSegment access$findSegmentReceive = bufferedChannel.findSegmentReceive(j2, channelSegment2);
                                if (access$findSegmentReceive != null) {
                                    channelSegment2 = access$findSegmentReceive;
                                }
                            }
                            Object access$updateCellReceive2 = bufferedChannel.updateCellReceive(channelSegment2, i3, andIncrement, this);
                            if (access$updateCellReceive2 == BufferedChannelKt.SUSPEND) {
                                bufferedChannel.prepareReceiverForSuspension(this, channelSegment2, i3);
                                break;
                            } else if (access$updateCellReceive2 == BufferedChannelKt.FAILED) {
                                if (andIncrement < bufferedChannel.getSendersCounter$kotlinx_coroutines_core()) {
                                    channelSegment2.cleanPrev();
                                }
                            } else if (access$updateCellReceive2 != BufferedChannelKt.SUSPEND_NO_WAITER) {
                                channelSegment2.cleanPrev();
                                this.receiveResult = access$updateCellReceive2;
                                this.continuation = null;
                                boxBoolean = Boxing.boxBoolean(true);
                                Function1 function12 = bufferedChannel.onUndeliveredElement;
                                if (function12 != null) {
                                    function1 = OnUndeliveredElementKt.bindCancellationFun(function12, access$updateCellReceive2, orCreateCancellableContinuation.getContext());
                                }
                            } else {
                                throw new IllegalStateException("unexpected");
                            }
                        }
                    } else {
                        channelSegment.cleanPrev();
                        this.receiveResult = access$updateCellReceive;
                        this.continuation = null;
                        boxBoolean = Boxing.boxBoolean(true);
                        Function1 function13 = bufferedChannel.onUndeliveredElement;
                        if (function13 != null) {
                            function1 = OnUndeliveredElementKt.bindCancellationFun(function13, access$updateCellReceive, orCreateCancellableContinuation.getContext());
                        }
                    }
                    orCreateCancellableContinuation.resume(boxBoolean, function1);
                }
                Object result = orCreateCancellableContinuation.getResult();
                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(continuation2);
                }
                return result;
            } catch (Throwable th) {
                orCreateCancellableContinuation.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                throw th;
            }
        }

        public void invokeOnCancellation(Segment segment, int i) {
            CancellableContinuationImpl cancellableContinuationImpl = this.continuation;
            if (cancellableContinuationImpl != null) {
                cancellableContinuationImpl.invokeOnCancellation(segment, i);
            }
        }

        /* access modifiers changed from: private */
        public final void onClosedHasNextNoWaiterSuspend() {
            CancellableContinuationImpl cancellableContinuationImpl = this.continuation;
            Intrinsics.checkNotNull(cancellableContinuationImpl);
            this.continuation = null;
            this.receiveResult = BufferedChannelKt.getCHANNEL_CLOSED();
            Throwable closeCause = BufferedChannel.this.getCloseCause();
            if (closeCause == null) {
                Result.Companion companion = Result.Companion;
                cancellableContinuationImpl.resumeWith(Result.m866constructorimpl(Boolean.FALSE));
                return;
            }
            Result.Companion companion2 = Result.Companion;
            cancellableContinuationImpl.resumeWith(Result.m866constructorimpl(ResultKt.createFailure(closeCause)));
        }

        public Object next() {
            Object obj = this.receiveResult;
            if (obj != BufferedChannelKt.NO_RECEIVE_RESULT) {
                this.receiveResult = BufferedChannelKt.NO_RECEIVE_RESULT;
                if (obj != BufferedChannelKt.getCHANNEL_CLOSED()) {
                    return obj;
                }
                throw StackTraceRecoveryKt.recoverStackTrace(BufferedChannel.this.getReceiveException());
            }
            throw new IllegalStateException("`hasNext()` has not been invoked");
        }

        public final boolean tryResumeHasNext(Object obj) {
            CancellableContinuationImpl cancellableContinuationImpl = this.continuation;
            Intrinsics.checkNotNull(cancellableContinuationImpl);
            Function1 function1 = null;
            this.continuation = null;
            this.receiveResult = obj;
            Boolean bool = Boolean.TRUE;
            Function1 function12 = BufferedChannel.this.onUndeliveredElement;
            if (function12 != null) {
                function1 = OnUndeliveredElementKt.bindCancellationFun(function12, obj, cancellableContinuationImpl.getContext());
            }
            return BufferedChannelKt.tryResume0(cancellableContinuationImpl, bool, function1);
        }

        public final void tryResumeHasNextOnClosedChannel() {
            CancellableContinuationImpl cancellableContinuationImpl = this.continuation;
            Intrinsics.checkNotNull(cancellableContinuationImpl);
            this.continuation = null;
            this.receiveResult = BufferedChannelKt.getCHANNEL_CLOSED();
            Throwable closeCause = BufferedChannel.this.getCloseCause();
            if (closeCause == null) {
                Result.Companion companion = Result.Companion;
                cancellableContinuationImpl.resumeWith(Result.m866constructorimpl(Boolean.FALSE));
                return;
            }
            Result.Companion companion2 = Result.Companion;
            cancellableContinuationImpl.resumeWith(Result.m866constructorimpl(ResultKt.createFailure(closeCause)));
        }
    }

    /* access modifiers changed from: protected */
    public final Throwable getCloseCause() {
        return (Throwable) _closeCause$FU.get(this);
    }

    /* access modifiers changed from: protected */
    public final Throwable getSendException() {
        Throwable closeCause = getCloseCause();
        return closeCause == null ? new ClosedSendChannelException("Channel was closed") : closeCause;
    }

    /* access modifiers changed from: private */
    public final Throwable getReceiveException() {
        Throwable closeCause = getCloseCause();
        return closeCause == null ? new ClosedReceiveChannelException("Channel was closed") : closeCause;
    }

    public boolean close(Throwable th) {
        return closeOrCancelImpl(th, false);
    }

    public final void cancel(CancellationException cancellationException) {
        cancelImpl$kotlinx_coroutines_core(cancellationException);
    }

    public boolean cancelImpl$kotlinx_coroutines_core(Throwable th) {
        if (th == null) {
            th = new CancellationException("Channel was cancelled");
        }
        return closeOrCancelImpl(th, true);
    }

    /* access modifiers changed from: protected */
    public boolean closeOrCancelImpl(Throwable th, boolean z) {
        if (z) {
            markCancellationStarted();
        }
        boolean m = AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_closeCause$FU, this, BufferedChannelKt.NO_CLOSE_CAUSE, th);
        if (z) {
            markCancelled();
        } else {
            markClosed();
        }
        completeCloseOrCancel();
        onClosedIdempotent();
        if (m) {
            invokeCloseHandler();
        }
        return m;
    }

    private final void invokeCloseHandler() {
        Object obj;
        Symbol symbol;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = closeHandler$FU;
        do {
            obj = atomicReferenceFieldUpdater.get(this);
            if (obj == null) {
                symbol = BufferedChannelKt.CLOSE_HANDLER_CLOSED;
            } else {
                symbol = BufferedChannelKt.CLOSE_HANDLER_INVOKED;
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, obj, symbol));
        if (obj != null) {
            Function1 function1 = (Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj, 1);
            ((Function1) obj).invoke(getCloseCause());
        }
    }

    public void invokeOnClose(Function1 function1) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = closeHandler$FU;
        if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, (Object) null, function1)) {
            do {
                Object obj = atomicReferenceFieldUpdater.get(this);
                if (obj != BufferedChannelKt.CLOSE_HANDLER_CLOSED) {
                    if (obj == BufferedChannelKt.CLOSE_HANDLER_INVOKED) {
                        throw new IllegalStateException("Another handler was already registered and successfully invoked");
                    }
                    throw new IllegalStateException(("Another handler is already registered: " + obj).toString());
                }
            } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(closeHandler$FU, this, BufferedChannelKt.CLOSE_HANDLER_CLOSED, BufferedChannelKt.CLOSE_HANDLER_INVOKED));
            function1.invoke(getCloseCause());
        }
    }

    private final void markClosed() {
        long j;
        long access$constructSendersAndCloseStatus;
        AtomicLongFieldUpdater atomicLongFieldUpdater = sendersAndCloseStatus$FU;
        do {
            j = atomicLongFieldUpdater.get(this);
            int i = (int) (j >> 60);
            if (i == 0) {
                access$constructSendersAndCloseStatus = BufferedChannelKt.constructSendersAndCloseStatus(j & 1152921504606846975L, 2);
            } else if (i == 1) {
                access$constructSendersAndCloseStatus = BufferedChannelKt.constructSendersAndCloseStatus(j & 1152921504606846975L, 3);
            } else {
                return;
            }
        } while (!atomicLongFieldUpdater.compareAndSet(this, j, access$constructSendersAndCloseStatus));
    }

    private final void markCancelled() {
        long j;
        AtomicLongFieldUpdater atomicLongFieldUpdater = sendersAndCloseStatus$FU;
        do {
            j = atomicLongFieldUpdater.get(this);
        } while (!atomicLongFieldUpdater.compareAndSet(this, j, BufferedChannelKt.constructSendersAndCloseStatus(1152921504606846975L & j, 3)));
    }

    private final void markCancellationStarted() {
        long j;
        AtomicLongFieldUpdater atomicLongFieldUpdater = sendersAndCloseStatus$FU;
        do {
            j = atomicLongFieldUpdater.get(this);
            if (((int) (j >> 60)) == 0) {
            } else {
                return;
            }
        } while (!atomicLongFieldUpdater.compareAndSet(this, j, BufferedChannelKt.constructSendersAndCloseStatus(1152921504606846975L & j, 1)));
    }

    private final void completeCloseOrCancel() {
        isClosedForSend();
    }

    private final ChannelSegment completeClose(long j) {
        ChannelSegment closeLinkedList = closeLinkedList();
        if (isConflatedDropOldest()) {
            long markAllEmptyCellsAsClosed = markAllEmptyCellsAsClosed(closeLinkedList);
            if (markAllEmptyCellsAsClosed != -1) {
                dropFirstElementUntilTheSpecifiedCellIsInTheBuffer(markAllEmptyCellsAsClosed);
            }
        }
        cancelSuspendedReceiveRequests(closeLinkedList, j);
        return closeLinkedList;
    }

    private final void completeCancel(long j) {
        removeUnprocessedElements(completeClose(j));
    }

    private final ChannelSegment closeLinkedList() {
        ChannelSegment channelSegment = bufferEndSegment$FU.get(this);
        ChannelSegment channelSegment2 = (ChannelSegment) sendSegment$FU.get(this);
        if (channelSegment2.id > ((ChannelSegment) channelSegment).id) {
            channelSegment = channelSegment2;
        }
        ChannelSegment channelSegment3 = (ChannelSegment) receiveSegment$FU.get(this);
        if (channelSegment3.id > ((ChannelSegment) channelSegment).id) {
            channelSegment = channelSegment3;
        }
        return (ChannelSegment) ConcurrentLinkedListKt.close((ConcurrentLinkedListNode) channelSegment);
    }

    private final long markAllEmptyCellsAsClosed(ChannelSegment channelSegment) {
        do {
            int i = BufferedChannelKt.SEGMENT_SIZE;
            while (true) {
                i--;
                if (-1 < i) {
                    long j = (channelSegment.id * ((long) BufferedChannelKt.SEGMENT_SIZE)) + ((long) i);
                    if (j < getReceiversCounter$kotlinx_coroutines_core()) {
                        return -1;
                    }
                    while (true) {
                        Object state$kotlinx_coroutines_core = channelSegment.getState$kotlinx_coroutines_core(i);
                        if (state$kotlinx_coroutines_core == null || state$kotlinx_coroutines_core == BufferedChannelKt.IN_BUFFER) {
                            if (channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, BufferedChannelKt.getCHANNEL_CLOSED())) {
                                channelSegment.onSlotCleaned();
                                break;
                            }
                        } else if (state$kotlinx_coroutines_core == BufferedChannelKt.BUFFERED) {
                            return j;
                        }
                    }
                } else {
                    channelSegment = (ChannelSegment) channelSegment.getPrev();
                }
            }
        } while (channelSegment != null);
        return -1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b3, code lost:
        r12 = (kotlinx.coroutines.channels.ChannelSegment) r12.getPrev();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void removeUnprocessedElements(kotlinx.coroutines.channels.ChannelSegment r12) {
        /*
            r11 = this;
            kotlin.jvm.functions.Function1 r0 = r11.onUndeliveredElement
            r1 = 0
            r2 = 1
            java.lang.Object r3 = kotlinx.coroutines.internal.InlineList.m927constructorimpl$default(r1, r2, r1)
        L_0x0008:
            int r4 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            int r4 = r4 - r2
        L_0x000b:
            r5 = -1
            if (r5 >= r4) goto L_0x00b3
            long r6 = r12.id
            int r8 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r8 = (long) r8
            long r6 = r6 * r8
            long r8 = (long) r4
            long r6 = r6 + r8
        L_0x0016:
            java.lang.Object r8 = r12.getState$kotlinx_coroutines_core(r4)
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.DONE_RCV
            if (r8 == r9) goto L_0x00bb
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.BUFFERED
            if (r8 != r9) goto L_0x0048
            long r9 = r11.getReceiversCounter$kotlinx_coroutines_core()
            int r9 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r9 < 0) goto L_0x00bb
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.getCHANNEL_CLOSED()
            boolean r8 = r12.casState$kotlinx_coroutines_core(r4, r8, r9)
            if (r8 == 0) goto L_0x0016
            if (r0 == 0) goto L_0x0040
            java.lang.Object r5 = r12.getElement$kotlinx_coroutines_core(r4)
            kotlinx.coroutines.internal.UndeliveredElementException r1 = kotlinx.coroutines.internal.OnUndeliveredElementKt.callUndeliveredElementCatchingException(r0, r5, r1)
        L_0x0040:
            r12.cleanElement$kotlinx_coroutines_core(r4)
            r12.onSlotCleaned()
            goto L_0x00af
        L_0x0048:
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.IN_BUFFER
            if (r8 == r9) goto L_0x00a2
            if (r8 != 0) goto L_0x0051
            goto L_0x00a2
        L_0x0051:
            boolean r9 = r8 instanceof kotlinx.coroutines.Waiter
            if (r9 != 0) goto L_0x006e
            boolean r9 = r8 instanceof kotlinx.coroutines.channels.WaiterEB
            if (r9 == 0) goto L_0x005a
            goto L_0x006e
        L_0x005a:
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.RESUMING_BY_EB
            if (r8 == r9) goto L_0x00bb
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.RESUMING_BY_RCV
            if (r8 != r9) goto L_0x0067
            goto L_0x00bb
        L_0x0067:
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.RESUMING_BY_EB
            if (r8 == r9) goto L_0x0016
            goto L_0x00af
        L_0x006e:
            long r9 = r11.getReceiversCounter$kotlinx_coroutines_core()
            int r9 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r9 < 0) goto L_0x00bb
            boolean r9 = r8 instanceof kotlinx.coroutines.channels.WaiterEB
            if (r9 == 0) goto L_0x0080
            r9 = r8
            kotlinx.coroutines.channels.WaiterEB r9 = (kotlinx.coroutines.channels.WaiterEB) r9
            kotlinx.coroutines.Waiter r9 = r9.waiter
            goto L_0x0083
        L_0x0080:
            r9 = r8
            kotlinx.coroutines.Waiter r9 = (kotlinx.coroutines.Waiter) r9
        L_0x0083:
            kotlinx.coroutines.internal.Symbol r10 = kotlinx.coroutines.channels.BufferedChannelKt.getCHANNEL_CLOSED()
            boolean r8 = r12.casState$kotlinx_coroutines_core(r4, r8, r10)
            if (r8 == 0) goto L_0x0016
            if (r0 == 0) goto L_0x0097
            java.lang.Object r5 = r12.getElement$kotlinx_coroutines_core(r4)
            kotlinx.coroutines.internal.UndeliveredElementException r1 = kotlinx.coroutines.internal.OnUndeliveredElementKt.callUndeliveredElementCatchingException(r0, r5, r1)
        L_0x0097:
            java.lang.Object r3 = kotlinx.coroutines.internal.InlineList.m928plusFjFbRPM(r3, r9)
            r12.cleanElement$kotlinx_coroutines_core(r4)
            r12.onSlotCleaned()
            goto L_0x00af
        L_0x00a2:
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.getCHANNEL_CLOSED()
            boolean r8 = r12.casState$kotlinx_coroutines_core(r4, r8, r9)
            if (r8 == 0) goto L_0x0016
            r12.onSlotCleaned()
        L_0x00af:
            int r4 = r4 + -1
            goto L_0x000b
        L_0x00b3:
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r12 = r12.getPrev()
            kotlinx.coroutines.channels.ChannelSegment r12 = (kotlinx.coroutines.channels.ChannelSegment) r12
            if (r12 != 0) goto L_0x0008
        L_0x00bb:
            if (r3 == 0) goto L_0x00e1
            boolean r12 = r3 instanceof java.util.ArrayList
            if (r12 != 0) goto L_0x00c7
            kotlinx.coroutines.Waiter r3 = (kotlinx.coroutines.Waiter) r3
            r11.resumeSenderOnCancelledChannel(r3)
            goto L_0x00e1
        L_0x00c7:
            java.lang.String r12 = "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r12)
            java.util.ArrayList r3 = (java.util.ArrayList) r3
            int r12 = r3.size()
            int r12 = r12 - r2
        L_0x00d3:
            if (r5 >= r12) goto L_0x00e1
            java.lang.Object r0 = r3.get(r12)
            kotlinx.coroutines.Waiter r0 = (kotlinx.coroutines.Waiter) r0
            r11.resumeSenderOnCancelledChannel(r0)
            int r12 = r12 + -1
            goto L_0x00d3
        L_0x00e1:
            if (r1 != 0) goto L_0x00e4
            return
        L_0x00e4:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.removeUnprocessedElements(kotlinx.coroutines.channels.ChannelSegment):void");
    }

    private final void cancelSuspendedReceiveRequests(ChannelSegment channelSegment, long j) {
        Object r0 = InlineList.m927constructorimpl$default((Object) null, 1, (DefaultConstructorMarker) null);
        loop0:
        while (channelSegment != null) {
            for (int i = BufferedChannelKt.SEGMENT_SIZE - 1; -1 < i; i--) {
                if ((channelSegment.id * ((long) BufferedChannelKt.SEGMENT_SIZE)) + ((long) i) < j) {
                    break loop0;
                }
                while (true) {
                    Object state$kotlinx_coroutines_core = channelSegment.getState$kotlinx_coroutines_core(i);
                    if (state$kotlinx_coroutines_core == null || state$kotlinx_coroutines_core == BufferedChannelKt.IN_BUFFER) {
                        if (channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, BufferedChannelKt.getCHANNEL_CLOSED())) {
                            channelSegment.onSlotCleaned();
                            break;
                        }
                    } else if (state$kotlinx_coroutines_core instanceof WaiterEB) {
                        if (channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, BufferedChannelKt.getCHANNEL_CLOSED())) {
                            r0 = InlineList.m928plusFjFbRPM(r0, ((WaiterEB) state$kotlinx_coroutines_core).waiter);
                            channelSegment.onCancelledRequest(i, true);
                            break;
                        }
                    } else if (!(state$kotlinx_coroutines_core instanceof Waiter)) {
                        break;
                    } else if (channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, BufferedChannelKt.getCHANNEL_CLOSED())) {
                        r0 = InlineList.m928plusFjFbRPM(r0, state$kotlinx_coroutines_core);
                        channelSegment.onCancelledRequest(i, true);
                        break;
                    }
                }
            }
            channelSegment = (ChannelSegment) channelSegment.getPrev();
        }
        if (r0 == null) {
            return;
        }
        if (!(r0 instanceof ArrayList)) {
            resumeReceiverOnClosedChannel((Waiter) r0);
            return;
        }
        Intrinsics.checkNotNull(r0, "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }");
        ArrayList arrayList = (ArrayList) r0;
        for (int size = arrayList.size() - 1; -1 < size; size--) {
            resumeReceiverOnClosedChannel((Waiter) arrayList.get(size));
        }
    }

    private final void resumeReceiverOnClosedChannel(Waiter waiter) {
        resumeWaiterOnClosedChannel(waiter, true);
    }

    private final void resumeSenderOnCancelledChannel(Waiter waiter) {
        resumeWaiterOnClosedChannel(waiter, false);
    }

    private final void resumeWaiterOnClosedChannel(Waiter waiter, boolean z) {
        if (waiter instanceof CancellableContinuation) {
            Continuation continuation = (Continuation) waiter;
            Result.Companion companion = Result.Companion;
            continuation.resumeWith(Result.m866constructorimpl(ResultKt.createFailure(z ? getReceiveException() : getSendException())));
        } else if (waiter instanceof BufferedChannelIterator) {
            ((BufferedChannelIterator) waiter).tryResumeHasNextOnClosedChannel();
        } else {
            throw new IllegalStateException(("Unexpected waiter: " + waiter).toString());
        }
    }

    public boolean isClosedForSend() {
        return isClosedForSend0(sendersAndCloseStatus$FU.get(this));
    }

    /* access modifiers changed from: private */
    public final boolean isClosedForSend0(long j) {
        return isClosed(j, false);
    }

    public boolean isClosedForReceive() {
        return isClosedForReceive0(sendersAndCloseStatus$FU.get(this));
    }

    private final boolean isClosedForReceive0(long j) {
        return isClosed(j, true);
    }

    private final boolean isClosed(long j, boolean z) {
        int i = (int) (j >> 60);
        if (i == 0 || i == 1) {
            return false;
        }
        if (i == 2) {
            completeClose(j & 1152921504606846975L);
            if (z && hasElements$kotlinx_coroutines_core()) {
                return false;
            }
        } else if (i == 3) {
            completeCancel(j & 1152921504606846975L);
        } else {
            throw new IllegalStateException(("unexpected close status: " + i).toString());
        }
        return true;
    }

    public final boolean hasElements$kotlinx_coroutines_core() {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = receiveSegment$FU;
            ChannelSegment channelSegment = (ChannelSegment) atomicReferenceFieldUpdater.get(this);
            long receiversCounter$kotlinx_coroutines_core = getReceiversCounter$kotlinx_coroutines_core();
            if (getSendersCounter$kotlinx_coroutines_core() <= receiversCounter$kotlinx_coroutines_core) {
                return false;
            }
            int i = BufferedChannelKt.SEGMENT_SIZE;
            long j = receiversCounter$kotlinx_coroutines_core / ((long) i);
            if (channelSegment.id == j || (channelSegment = findSegmentReceive(j, channelSegment)) != null) {
                channelSegment.cleanPrev();
                if (isCellNonEmpty(channelSegment, (int) (receiversCounter$kotlinx_coroutines_core % ((long) i)), receiversCounter$kotlinx_coroutines_core)) {
                    return true;
                }
                receivers$FU.compareAndSet(this, receiversCounter$kotlinx_coroutines_core, receiversCounter$kotlinx_coroutines_core + 1);
            } else if (((ChannelSegment) atomicReferenceFieldUpdater.get(this)).id < j) {
                return false;
            }
        }
    }

    private final boolean isCellNonEmpty(ChannelSegment channelSegment, int i, long j) {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = channelSegment.getState$kotlinx_coroutines_core(i);
            if (state$kotlinx_coroutines_core != null && state$kotlinx_coroutines_core != BufferedChannelKt.IN_BUFFER) {
                if (state$kotlinx_coroutines_core == BufferedChannelKt.BUFFERED) {
                    return true;
                }
                if (state$kotlinx_coroutines_core == BufferedChannelKt.INTERRUPTED_SEND || state$kotlinx_coroutines_core == BufferedChannelKt.getCHANNEL_CLOSED() || state$kotlinx_coroutines_core == BufferedChannelKt.DONE_RCV || state$kotlinx_coroutines_core == BufferedChannelKt.POISONED) {
                    return false;
                }
                if (state$kotlinx_coroutines_core == BufferedChannelKt.RESUMING_BY_EB) {
                    return true;
                }
                if (state$kotlinx_coroutines_core != BufferedChannelKt.RESUMING_BY_RCV && j == getReceiversCounter$kotlinx_coroutines_core()) {
                    return true;
                }
                return false;
            }
        } while (!channelSegment.casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, BufferedChannelKt.POISONED));
        expandBuffer();
        return false;
    }

    /* access modifiers changed from: private */
    public final ChannelSegment findSegmentSend(long j, ChannelSegment channelSegment) {
        Object findSegmentInternal;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = sendSegment$FU;
        Function2 function2 = (Function2) BufferedChannelKt.createSegmentFunction();
        loop0:
        while (true) {
            findSegmentInternal = ConcurrentLinkedListKt.findSegmentInternal(channelSegment, j, function2);
            if (SegmentOrClosed.m931isClosedimpl(findSegmentInternal)) {
                break;
            }
            Segment r3 = SegmentOrClosed.m930getSegmentimpl(findSegmentInternal);
            while (true) {
                Segment segment = (Segment) atomicReferenceFieldUpdater.get(this);
                if (segment.id >= r3.id) {
                    break loop0;
                } else if (r3.tryIncPointers$kotlinx_coroutines_core()) {
                    if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, segment, r3)) {
                        if (segment.decPointers$kotlinx_coroutines_core()) {
                            segment.remove();
                        }
                    } else if (r3.decPointers$kotlinx_coroutines_core()) {
                        r3.remove();
                    }
                }
            }
        }
        if (SegmentOrClosed.m931isClosedimpl(findSegmentInternal)) {
            completeCloseOrCancel();
            if (channelSegment.id * ((long) BufferedChannelKt.SEGMENT_SIZE) >= getReceiversCounter$kotlinx_coroutines_core()) {
                return null;
            }
            channelSegment.cleanPrev();
            return null;
        }
        ChannelSegment channelSegment2 = (ChannelSegment) SegmentOrClosed.m930getSegmentimpl(findSegmentInternal);
        long j2 = channelSegment2.id;
        if (j2 <= j) {
            return channelSegment2;
        }
        int i = BufferedChannelKt.SEGMENT_SIZE;
        updateSendersCounterIfLower(j2 * ((long) i));
        if (channelSegment2.id * ((long) i) >= getReceiversCounter$kotlinx_coroutines_core()) {
            return null;
        }
        channelSegment2.cleanPrev();
        return null;
    }

    /* access modifiers changed from: private */
    public final ChannelSegment findSegmentReceive(long j, ChannelSegment channelSegment) {
        Object findSegmentInternal;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = receiveSegment$FU;
        Function2 function2 = (Function2) BufferedChannelKt.createSegmentFunction();
        loop0:
        while (true) {
            findSegmentInternal = ConcurrentLinkedListKt.findSegmentInternal(channelSegment, j, function2);
            if (SegmentOrClosed.m931isClosedimpl(findSegmentInternal)) {
                break;
            }
            Segment r3 = SegmentOrClosed.m930getSegmentimpl(findSegmentInternal);
            while (true) {
                Segment segment = (Segment) atomicReferenceFieldUpdater.get(this);
                if (segment.id >= r3.id) {
                    break loop0;
                } else if (r3.tryIncPointers$kotlinx_coroutines_core()) {
                    if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, segment, r3)) {
                        if (segment.decPointers$kotlinx_coroutines_core()) {
                            segment.remove();
                        }
                    } else if (r3.decPointers$kotlinx_coroutines_core()) {
                        r3.remove();
                    }
                }
            }
        }
        if (SegmentOrClosed.m931isClosedimpl(findSegmentInternal)) {
            completeCloseOrCancel();
            if (channelSegment.id * ((long) BufferedChannelKt.SEGMENT_SIZE) >= getSendersCounter$kotlinx_coroutines_core()) {
                return null;
            }
            channelSegment.cleanPrev();
            return null;
        }
        ChannelSegment channelSegment2 = (ChannelSegment) SegmentOrClosed.m930getSegmentimpl(findSegmentInternal);
        if (!isRendezvousOrUnlimited() && j <= getBufferEndCounter() / ((long) BufferedChannelKt.SEGMENT_SIZE)) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = bufferEndSegment$FU;
            while (true) {
                Segment segment2 = (Segment) atomicReferenceFieldUpdater2.get(this);
                if (segment2.id >= channelSegment2.id || !channelSegment2.tryIncPointers$kotlinx_coroutines_core()) {
                    break;
                } else if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater2, this, segment2, channelSegment2)) {
                    if (segment2.decPointers$kotlinx_coroutines_core()) {
                        segment2.remove();
                    }
                } else if (channelSegment2.decPointers$kotlinx_coroutines_core()) {
                    channelSegment2.remove();
                }
            }
        }
        long j2 = channelSegment2.id;
        if (j2 <= j) {
            return channelSegment2;
        }
        int i = BufferedChannelKt.SEGMENT_SIZE;
        updateReceiversCounterIfLower(j2 * ((long) i));
        if (channelSegment2.id * ((long) i) >= getSendersCounter$kotlinx_coroutines_core()) {
            return null;
        }
        channelSegment2.cleanPrev();
        return null;
    }

    private final ChannelSegment findSegmentBufferEnd(long j, ChannelSegment channelSegment, long j2) {
        Object findSegmentInternal;
        long j3 = j;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = bufferEndSegment$FU;
        Function2 function2 = (Function2) BufferedChannelKt.createSegmentFunction();
        ChannelSegment channelSegment2 = channelSegment;
        loop0:
        while (true) {
            findSegmentInternal = ConcurrentLinkedListKt.findSegmentInternal(channelSegment2, j3, function2);
            if (SegmentOrClosed.m931isClosedimpl(findSegmentInternal)) {
                break;
            }
            Segment r7 = SegmentOrClosed.m930getSegmentimpl(findSegmentInternal);
            while (true) {
                Segment segment = (Segment) atomicReferenceFieldUpdater.get(this);
                if (segment.id >= r7.id) {
                    break loop0;
                } else if (r7.tryIncPointers$kotlinx_coroutines_core()) {
                    if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, segment, r7)) {
                        if (segment.decPointers$kotlinx_coroutines_core()) {
                            segment.remove();
                        }
                    } else if (r7.decPointers$kotlinx_coroutines_core()) {
                        r7.remove();
                    }
                }
            }
        }
        if (SegmentOrClosed.m931isClosedimpl(findSegmentInternal)) {
            completeCloseOrCancel();
            moveSegmentBufferEndToSpecifiedOrLast(j, channelSegment);
            incCompletedExpandBufferAttempts$default(this, 0, 1, (Object) null);
            return null;
        }
        ChannelSegment channelSegment3 = (ChannelSegment) SegmentOrClosed.m930getSegmentimpl(findSegmentInternal);
        long j4 = channelSegment3.id;
        if (j4 <= j3) {
            return channelSegment3;
        }
        AtomicLongFieldUpdater atomicLongFieldUpdater = bufferEnd$FU;
        int i = BufferedChannelKt.SEGMENT_SIZE;
        if (atomicLongFieldUpdater.compareAndSet(this, j2 + 1, ((long) i) * j4)) {
            incCompletedExpandBufferAttempts((channelSegment3.id * ((long) i)) - j2);
            return null;
        }
        incCompletedExpandBufferAttempts$default(this, 0, 1, (Object) null);
        return null;
    }

    private final void moveSegmentBufferEndToSpecifiedOrLast(long j, ChannelSegment channelSegment) {
        ChannelSegment channelSegment2;
        ChannelSegment channelSegment3;
        while (channelSegment.id < j && (channelSegment3 = (ChannelSegment) channelSegment.getNext()) != null) {
            channelSegment = channelSegment3;
        }
        while (true) {
            if (!channelSegment.isRemoved() || (channelSegment2 = (ChannelSegment) channelSegment.getNext()) == null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = bufferEndSegment$FU;
                while (true) {
                    Segment segment = (Segment) atomicReferenceFieldUpdater.get(this);
                    if (segment.id < channelSegment.id) {
                        if (!channelSegment.tryIncPointers$kotlinx_coroutines_core()) {
                            continue;
                            break;
                        } else if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, segment, channelSegment)) {
                            if (segment.decPointers$kotlinx_coroutines_core()) {
                                segment.remove();
                                return;
                            }
                            return;
                        } else if (channelSegment.decPointers$kotlinx_coroutines_core()) {
                            channelSegment.remove();
                        }
                    } else {
                        return;
                    }
                }
            } else {
                channelSegment = channelSegment2;
            }
        }
    }

    private final void updateSendersCounterIfLower(long j) {
        long j2;
        long j3;
        AtomicLongFieldUpdater atomicLongFieldUpdater = sendersAndCloseStatus$FU;
        do {
            j2 = atomicLongFieldUpdater.get(this);
            j3 = 1152921504606846975L & j2;
            if (j3 < j) {
            } else {
                return;
            }
        } while (!sendersAndCloseStatus$FU.compareAndSet(this, j2, BufferedChannelKt.constructSendersAndCloseStatus(j3, (int) (j2 >> 60))));
    }

    private final void updateReceiversCounterIfLower(long j) {
        long j2;
        AtomicLongFieldUpdater atomicLongFieldUpdater = receivers$FU;
        do {
            j2 = atomicLongFieldUpdater.get(this);
            if (j2 >= j || receivers$FU.compareAndSet(this, j2, j)) {
            }
            j2 = atomicLongFieldUpdater.get(this);
            return;
        } while (receivers$FU.compareAndSet(this, j2, j));
    }

    /* JADX WARNING: type inference failed for: r2v13, types: [kotlinx.coroutines.internal.ConcurrentLinkedListNode] */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01b2, code lost:
        r3 = r3.getNext();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01b9, code lost:
        if (r3 != null) goto L_0x01da;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toString() {
        /*
            r16 = this;
            r0 = r16
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.util.concurrent.atomic.AtomicLongFieldUpdater r2 = sendersAndCloseStatus$FU
            long r2 = r2.get(r0)
            r4 = 60
            long r2 = r2 >> r4
            int r2 = (int) r2
            r3 = 3
            r4 = 2
            if (r2 == r4) goto L_0x001e
            if (r2 == r3) goto L_0x0018
            goto L_0x0023
        L_0x0018:
            java.lang.String r2 = "cancelled,"
            r1.append(r2)
            goto L_0x0023
        L_0x001e:
            java.lang.String r2 = "closed,"
            r1.append(r2)
        L_0x0023:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = "capacity="
            r2.append(r5)
            int r5 = r0.capacity
            r2.append(r5)
            r5 = 44
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            r1.append(r2)
            java.lang.String r2 = "data=["
            r1.append(r2)
            kotlinx.coroutines.channels.ChannelSegment[] r2 = new kotlinx.coroutines.channels.ChannelSegment[r3]
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r3 = receiveSegment$FU
            java.lang.Object r3 = r3.get(r0)
            r6 = 0
            r2[r6] = r3
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r3 = sendSegment$FU
            java.lang.Object r3 = r3.get(r0)
            r7 = 1
            r2[r7] = r3
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r3 = bufferEndSegment$FU
            java.lang.Object r3 = r3.get(r0)
            r2[r4] = r3
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r2 = r2.iterator()
        L_0x006e:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0085
            java.lang.Object r4 = r2.next()
            r8 = r4
            kotlinx.coroutines.channels.ChannelSegment r8 = (kotlinx.coroutines.channels.ChannelSegment) r8
            kotlinx.coroutines.channels.ChannelSegment r9 = kotlinx.coroutines.channels.BufferedChannelKt.NULL_SEGMENT
            if (r8 == r9) goto L_0x006e
            r3.add(r4)
            goto L_0x006e
        L_0x0085:
            java.util.Iterator r2 = r3.iterator()
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x01de
            java.lang.Object r3 = r2.next()
            boolean r4 = r2.hasNext()
            if (r4 != 0) goto L_0x009a
            goto L_0x00b4
        L_0x009a:
            r4 = r3
            kotlinx.coroutines.channels.ChannelSegment r4 = (kotlinx.coroutines.channels.ChannelSegment) r4
            long r8 = r4.id
        L_0x009f:
            java.lang.Object r4 = r2.next()
            r10 = r4
            kotlinx.coroutines.channels.ChannelSegment r10 = (kotlinx.coroutines.channels.ChannelSegment) r10
            long r10 = r10.id
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 <= 0) goto L_0x00ae
            r3 = r4
            r8 = r10
        L_0x00ae:
            boolean r4 = r2.hasNext()
            if (r4 != 0) goto L_0x009f
        L_0x00b4:
            kotlinx.coroutines.channels.ChannelSegment r3 = (kotlinx.coroutines.channels.ChannelSegment) r3
            long r10 = r16.getReceiversCounter$kotlinx_coroutines_core()
            long r12 = r16.getSendersCounter$kotlinx_coroutines_core()
        L_0x00be:
            int r2 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            r4 = r6
        L_0x00c1:
            if (r4 >= r2) goto L_0x01b2
            long r8 = r3.id
            int r14 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r14 = (long) r14
            long r8 = r8 * r14
            long r14 = (long) r4
            long r8 = r8 + r14
            int r14 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r14 < 0) goto L_0x00d3
            int r15 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r15 >= 0) goto L_0x01bb
        L_0x00d3:
            java.lang.Object r15 = r3.getState$kotlinx_coroutines_core(r4)
            java.lang.Object r6 = r3.getElement$kotlinx_coroutines_core(r4)
            boolean r7 = r15 instanceof kotlinx.coroutines.CancellableContinuation
            if (r7 == 0) goto L_0x00f5
            int r7 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r7 >= 0) goto L_0x00e9
            if (r14 < 0) goto L_0x00e9
            java.lang.String r7 = "receive"
            goto L_0x0178
        L_0x00e9:
            if (r14 >= 0) goto L_0x00f1
            if (r7 < 0) goto L_0x00f1
            java.lang.String r7 = "send"
            goto L_0x0178
        L_0x00f1:
            java.lang.String r7 = "cont"
            goto L_0x0178
        L_0x00f5:
            boolean r7 = r15 instanceof kotlinx.coroutines.channels.WaiterEB
            if (r7 == 0) goto L_0x0111
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "EB("
            r7.append(r8)
            r7.append(r15)
            r8 = 41
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            goto L_0x0178
        L_0x0111:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.RESUMING_BY_RCV
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r15, (java.lang.Object) r7)
            if (r7 == 0) goto L_0x011d
            r7 = 1
            goto L_0x0125
        L_0x011d:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.RESUMING_BY_EB
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r15, (java.lang.Object) r7)
        L_0x0125:
            if (r7 == 0) goto L_0x012a
            java.lang.String r7 = "resuming_sender"
            goto L_0x0178
        L_0x012a:
            if (r15 != 0) goto L_0x012e
            r7 = 1
            goto L_0x0136
        L_0x012e:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.IN_BUFFER
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r15, (java.lang.Object) r7)
        L_0x0136:
            if (r7 == 0) goto L_0x013a
            r7 = 1
            goto L_0x0142
        L_0x013a:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.DONE_RCV
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r15, (java.lang.Object) r7)
        L_0x0142:
            if (r7 == 0) goto L_0x0146
            r7 = 1
            goto L_0x014e
        L_0x0146:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.POISONED
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r15, (java.lang.Object) r7)
        L_0x014e:
            if (r7 == 0) goto L_0x0152
            r7 = 1
            goto L_0x015a
        L_0x0152:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_RCV
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r15, (java.lang.Object) r7)
        L_0x015a:
            if (r7 == 0) goto L_0x015e
            r7 = 1
            goto L_0x0166
        L_0x015e:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_SEND
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r15, (java.lang.Object) r7)
        L_0x0166:
            if (r7 == 0) goto L_0x016a
            r7 = 1
            goto L_0x0172
        L_0x016a:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.getCHANNEL_CLOSED()
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r15, (java.lang.Object) r7)
        L_0x0172:
            if (r7 != 0) goto L_0x01ac
            java.lang.String r7 = r15.toString()
        L_0x0178:
            if (r6 == 0) goto L_0x019a
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r9 = 40
            r8.append(r9)
            r8.append(r7)
            r8.append(r5)
            r8.append(r6)
            java.lang.String r6 = "),"
            r8.append(r6)
            java.lang.String r6 = r8.toString()
            r1.append(r6)
            goto L_0x01ac
        L_0x019a:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r7)
            r6.append(r5)
            java.lang.String r6 = r6.toString()
            r1.append(r6)
        L_0x01ac:
            int r4 = r4 + 1
            r6 = 0
            r7 = 1
            goto L_0x00c1
        L_0x01b2:
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r2 = r3.getNext()
            r3 = r2
            kotlinx.coroutines.channels.ChannelSegment r3 = (kotlinx.coroutines.channels.ChannelSegment) r3
            if (r3 != 0) goto L_0x01da
        L_0x01bb:
            char r2 = kotlin.text.StringsKt.last(r1)
            if (r2 != r5) goto L_0x01d0
            int r2 = r1.length()
            r4 = 1
            int r2 = r2 - r4
            java.lang.StringBuilder r2 = r1.deleteCharAt(r2)
            java.lang.String r3 = "this.deleteCharAt(index)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
        L_0x01d0:
            java.lang.String r2 = "]"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            return r1
        L_0x01da:
            r6 = 0
            r7 = 1
            goto L_0x00be
        L_0x01de:
            java.util.NoSuchElementException r1 = new java.util.NoSuchElementException
            r1.<init>()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.toString():java.lang.String");
    }
}
