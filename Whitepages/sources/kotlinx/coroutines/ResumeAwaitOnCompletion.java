package kotlinx.coroutines;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;

final class ResumeAwaitOnCompletion extends JobNode {
    private final CancellableContinuationImpl continuation;

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public ResumeAwaitOnCompletion(CancellableContinuationImpl cancellableContinuationImpl) {
        this.continuation = cancellableContinuationImpl;
    }

    public void invoke(Throwable th) {
        Object state$kotlinx_coroutines_core = getJob().getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            CancellableContinuationImpl cancellableContinuationImpl = this.continuation;
            Result.Companion companion = Result.Companion;
            cancellableContinuationImpl.resumeWith(Result.m866constructorimpl(ResultKt.createFailure(((CompletedExceptionally) state$kotlinx_coroutines_core).cause)));
            return;
        }
        CancellableContinuationImpl cancellableContinuationImpl2 = this.continuation;
        Result.Companion companion2 = Result.Companion;
        cancellableContinuationImpl2.resumeWith(Result.m866constructorimpl(JobSupportKt.unboxState(state$kotlinx_coroutines_core)));
    }
}
