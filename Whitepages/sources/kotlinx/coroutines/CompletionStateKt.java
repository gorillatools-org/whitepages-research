package kotlinx.coroutines;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

public abstract class CompletionStateKt {
    public static /* synthetic */ Object toState$default(Object obj, Function1 function1, int i, Object obj2) {
        if ((i & 1) != 0) {
            function1 = null;
        }
        return toState(obj, function1);
    }

    public static final Object toState(Object obj, Function1 function1) {
        Throwable r0 = Result.m867exceptionOrNullimpl(obj);
        if (r0 != null) {
            return new CompletedExceptionally(r0, false, 2, (DefaultConstructorMarker) null);
        }
        if (function1 != null) {
            return new CompletedWithCancellation(obj, function1);
        }
        return obj;
    }

    public static final Object toState(Object obj, CancellableContinuation cancellableContinuation) {
        Throwable r4 = Result.m867exceptionOrNullimpl(obj);
        return r4 == null ? obj : new CompletedExceptionally(r4, false, 2, (DefaultConstructorMarker) null);
    }

    public static final Object recoverResult(Object obj, Continuation continuation) {
        if (!(obj instanceof CompletedExceptionally)) {
            return Result.m866constructorimpl(obj);
        }
        Result.Companion companion = Result.Companion;
        return Result.m866constructorimpl(ResultKt.createFailure(((CompletedExceptionally) obj).cause));
    }
}
