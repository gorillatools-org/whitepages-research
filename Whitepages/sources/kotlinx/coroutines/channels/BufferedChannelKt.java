package kotlinx.coroutines.channels;

import kotlin.jvm.functions.Function1;
import kotlin.reflect.KFunction;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.internal.Symbol;

public abstract class BufferedChannelKt {
    public static final Symbol BUFFERED = new Symbol("BUFFERED");
    private static final Symbol CHANNEL_CLOSED = new Symbol("CHANNEL_CLOSED");
    /* access modifiers changed from: private */
    public static final Symbol CLOSE_HANDLER_CLOSED = new Symbol("CLOSE_HANDLER_CLOSED");
    /* access modifiers changed from: private */
    public static final Symbol CLOSE_HANDLER_INVOKED = new Symbol("CLOSE_HANDLER_INVOKED");
    /* access modifiers changed from: private */
    public static final Symbol DONE_RCV = new Symbol("DONE_RCV");
    /* access modifiers changed from: private */
    public static final int EXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.bufferedChannel.expandBufferCompletionWaitIterations", 10000, 0, 0, 12, (Object) null);
    /* access modifiers changed from: private */
    public static final Symbol FAILED = new Symbol("FAILED");
    /* access modifiers changed from: private */
    public static final Symbol INTERRUPTED_RCV = new Symbol("INTERRUPTED_RCV");
    /* access modifiers changed from: private */
    public static final Symbol INTERRUPTED_SEND = new Symbol("INTERRUPTED_SEND");
    /* access modifiers changed from: private */
    public static final Symbol IN_BUFFER = new Symbol("SHOULD_BUFFER");
    /* access modifiers changed from: private */
    public static final Symbol NO_CLOSE_CAUSE = new Symbol("NO_CLOSE_CAUSE");
    /* access modifiers changed from: private */
    public static final Symbol NO_RECEIVE_RESULT = new Symbol("NO_RECEIVE_RESULT");
    /* access modifiers changed from: private */
    public static final ChannelSegment NULL_SEGMENT = new ChannelSegment(-1, (ChannelSegment) null, (BufferedChannel) null, 0);
    /* access modifiers changed from: private */
    public static final Symbol POISONED = new Symbol("POISONED");
    /* access modifiers changed from: private */
    public static final Symbol RESUMING_BY_EB = new Symbol("RESUMING_BY_EB");
    /* access modifiers changed from: private */
    public static final Symbol RESUMING_BY_RCV = new Symbol("S_RESUMING_BY_RCV");
    public static final int SEGMENT_SIZE = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.bufferedChannel.segmentSize", 32, 0, 0, 12, (Object) null);
    /* access modifiers changed from: private */
    public static final Symbol SUSPEND = new Symbol("SUSPEND");
    /* access modifiers changed from: private */
    public static final Symbol SUSPEND_NO_WAITER = new Symbol("SUSPEND_NO_WAITER");

    /* access modifiers changed from: private */
    public static final long constructEBCompletedAndPauseFlag(long j, boolean z) {
        return (z ? 4611686018427387904L : 0) + j;
    }

    /* access modifiers changed from: private */
    public static final long constructSendersAndCloseStatus(long j, int i) {
        return (((long) i) << 60) + j;
    }

    /* access modifiers changed from: private */
    public static final long initialBufferEnd(int i) {
        if (i == 0) {
            return 0;
        }
        if (i != Integer.MAX_VALUE) {
            return (long) i;
        }
        return Long.MAX_VALUE;
    }

    public static final KFunction createSegmentFunction() {
        return BufferedChannelKt$createSegmentFunction$1.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final ChannelSegment createSegment(long j, ChannelSegment channelSegment) {
        return new ChannelSegment(j, channelSegment, channelSegment.getChannel(), 0);
    }

    static /* synthetic */ boolean tryResume0$default(CancellableContinuation cancellableContinuation, Object obj, Function1 function1, int i, Object obj2) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        return tryResume0(cancellableContinuation, obj, function1);
    }

    /* access modifiers changed from: private */
    public static final boolean tryResume0(CancellableContinuation cancellableContinuation, Object obj, Function1 function1) {
        Object tryResume = cancellableContinuation.tryResume(obj, (Object) null, function1);
        if (tryResume == null) {
            return false;
        }
        cancellableContinuation.completeResume(tryResume);
        return true;
    }

    public static final Symbol getCHANNEL_CLOSED() {
        return CHANNEL_CLOSED;
    }
}
