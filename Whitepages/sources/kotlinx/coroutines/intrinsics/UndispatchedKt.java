package kotlinx.coroutines.intrinsics;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.JobSupportKt;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;

public abstract class UndispatchedKt {
    public static final void startCoroutineUndispatched(Function2 function2, Object obj, Continuation continuation) {
        CoroutineContext context;
        Object updateThreadContext;
        Continuation probeCoroutineCreated = DebugProbesKt.probeCoroutineCreated(continuation);
        try {
            context = continuation.getContext();
            updateThreadContext = ThreadContextKt.updateThreadContext(context, (Object) null);
            Object invoke = ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(obj, probeCoroutineCreated);
            ThreadContextKt.restoreThreadContext(context, updateThreadContext);
            if (invoke != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                probeCoroutineCreated.resumeWith(Result.m866constructorimpl(invoke));
            }
        } catch (Throwable th) {
            Result.Companion companion = Result.Companion;
            probeCoroutineCreated.resumeWith(Result.m866constructorimpl(ResultKt.createFailure(th)));
        }
    }

    public static final Object startUndispatchedOrReturn(ScopeCoroutine scopeCoroutine, Object obj, Function2 function2) {
        Object obj2;
        try {
            obj2 = ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(obj, scopeCoroutine);
        } catch (Throwable th) {
            obj2 = new CompletedExceptionally(th, false, 2, (DefaultConstructorMarker) null);
        }
        if (obj2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        Object makeCompletingOnce$kotlinx_coroutines_core = scopeCoroutine.makeCompletingOnce$kotlinx_coroutines_core(obj2);
        if (makeCompletingOnce$kotlinx_coroutines_core == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        if (!(makeCompletingOnce$kotlinx_coroutines_core instanceof CompletedExceptionally)) {
            return JobSupportKt.unboxState(makeCompletingOnce$kotlinx_coroutines_core);
        }
        throw ((CompletedExceptionally) makeCompletingOnce$kotlinx_coroutines_core).cause;
    }
}
