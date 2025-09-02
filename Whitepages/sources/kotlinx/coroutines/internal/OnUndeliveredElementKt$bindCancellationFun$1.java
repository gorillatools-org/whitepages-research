package kotlinx.coroutines.internal;

import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

final class OnUndeliveredElementKt$bindCancellationFun$1 extends Lambda implements Function1 {
    final /* synthetic */ CoroutineContext $context;
    final /* synthetic */ Object $element;
    final /* synthetic */ Function1 $this_bindCancellationFun;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OnUndeliveredElementKt$bindCancellationFun$1(Function1 function1, Object obj, CoroutineContext coroutineContext) {
        super(1);
        this.$this_bindCancellationFun = function1;
        this.$element = obj;
        this.$context = coroutineContext;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        OnUndeliveredElementKt.callUndeliveredElement(this.$this_bindCancellationFun, this.$element, this.$context);
    }
}
