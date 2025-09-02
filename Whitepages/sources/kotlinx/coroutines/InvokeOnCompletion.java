package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

final class InvokeOnCompletion extends JobNode {
    private final Function1 handler;

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public InvokeOnCompletion(Function1 function1) {
        this.handler = function1;
    }

    public void invoke(Throwable th) {
        this.handler.invoke(th);
    }
}
