package kotlinx.coroutines.sync;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

final class SemaphoreImpl$onCancellationRelease$1 extends Lambda implements Function1 {
    final /* synthetic */ SemaphoreImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SemaphoreImpl$onCancellationRelease$1(SemaphoreImpl semaphoreImpl) {
        super(1);
        this.this$0 = semaphoreImpl;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        this.this$0.release();
    }
}
