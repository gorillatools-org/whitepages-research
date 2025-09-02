package kotlinx.coroutines.flow;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;

final class SafeFlow extends AbstractFlow {
    private final Function2 block;

    public SafeFlow(Function2 function2) {
        this.block = function2;
    }

    public Object collectSafely(FlowCollector flowCollector, Continuation continuation) {
        Object invoke = this.block.invoke(flowCollector, continuation);
        return invoke == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke : Unit.INSTANCE;
    }
}
