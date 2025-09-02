package kotlin.coroutines.jvm.internal;

import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

public abstract class DebugProbesKt {
    public static final Continuation probeCoroutineCreated(Continuation continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        return continuation;
    }

    public static final void probeCoroutineResumed(Continuation continuation) {
        Intrinsics.checkNotNullParameter(continuation, "frame");
    }

    public static final void probeCoroutineSuspended(Continuation continuation) {
        Intrinsics.checkNotNullParameter(continuation, "frame");
    }
}
