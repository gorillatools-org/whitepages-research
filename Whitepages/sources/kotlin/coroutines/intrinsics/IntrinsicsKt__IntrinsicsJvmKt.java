package kotlin.coroutines.intrinsics;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

abstract class IntrinsicsKt__IntrinsicsJvmKt {
    public static Continuation createCoroutineUnintercepted(Function2 function2, Object obj, Continuation continuation) {
        Intrinsics.checkNotNullParameter(function2, "<this>");
        Intrinsics.checkNotNullParameter(continuation, "completion");
        Continuation probeCoroutineCreated = DebugProbesKt.probeCoroutineCreated(continuation);
        if (function2 instanceof BaseContinuationImpl) {
            return ((BaseContinuationImpl) function2).create(obj, probeCoroutineCreated);
        }
        CoroutineContext context = probeCoroutineCreated.getContext();
        return context == EmptyCoroutineContext.INSTANCE ? new IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$3(probeCoroutineCreated, function2, obj) : new IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$4(probeCoroutineCreated, context, function2, obj);
    }

    public static Continuation intercepted(Continuation continuation) {
        Continuation intercepted;
        Intrinsics.checkNotNullParameter(continuation, "<this>");
        ContinuationImpl continuationImpl = continuation instanceof ContinuationImpl ? (ContinuationImpl) continuation : null;
        return (continuationImpl == null || (intercepted = continuationImpl.intercepted()) == null) ? continuation : intercepted;
    }
}
