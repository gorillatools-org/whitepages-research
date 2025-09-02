package kotlinx.coroutines.channels;

import kotlin.Result;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CancellableContinuation;

final class ProduceKt$awaitClose$4$1 extends Lambda implements Function1 {
    final /* synthetic */ CancellableContinuation $cont;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ProduceKt$awaitClose$4$1(CancellableContinuation cancellableContinuation) {
        super(1);
        this.$cont = cancellableContinuation;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        CancellableContinuation cancellableContinuation = this.$cont;
        Result.Companion companion = Result.Companion;
        cancellableContinuation.resumeWith(Result.m866constructorimpl(Unit.INSTANCE));
    }
}
