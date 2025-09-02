package kotlinx.coroutines;

import kotlin.Unit;

public final class ChildContinuation extends JobCancellingNode {
    public final CancellableContinuationImpl child;

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public ChildContinuation(CancellableContinuationImpl cancellableContinuationImpl) {
        this.child = cancellableContinuationImpl;
    }

    public void invoke(Throwable th) {
        CancellableContinuationImpl cancellableContinuationImpl = this.child;
        cancellableContinuationImpl.parentCancelled$kotlinx_coroutines_core(cancellableContinuationImpl.getContinuationCancellationCause(getJob()));
    }
}
