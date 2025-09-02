package kotlin.coroutines;

import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

public abstract class ContinuationKt {
    public static final void startCoroutine(Function2 function2, Object obj, Continuation continuation) {
        Intrinsics.checkNotNullParameter(function2, "<this>");
        Intrinsics.checkNotNullParameter(continuation, "completion");
        Continuation intercepted = IntrinsicsKt.intercepted(IntrinsicsKt.createCoroutineUnintercepted(function2, obj, continuation));
        Result.Companion companion = Result.Companion;
        intercepted.resumeWith(Result.m866constructorimpl(Unit.INSTANCE));
    }
}
