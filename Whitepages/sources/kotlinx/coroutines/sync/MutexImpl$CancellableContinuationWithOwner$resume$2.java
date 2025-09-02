package kotlinx.coroutines.sync;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.sync.MutexImpl;

final class MutexImpl$CancellableContinuationWithOwner$resume$2 extends Lambda implements Function1 {
    final /* synthetic */ MutexImpl this$0;
    final /* synthetic */ MutexImpl.CancellableContinuationWithOwner this$1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MutexImpl$CancellableContinuationWithOwner$resume$2(MutexImpl mutexImpl, MutexImpl.CancellableContinuationWithOwner cancellableContinuationWithOwner) {
        super(1);
        this.this$0 = mutexImpl;
        this.this$1 = cancellableContinuationWithOwner;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        this.this$0.unlock(this.this$1.owner);
    }
}
