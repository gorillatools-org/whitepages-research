package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.flow.internal.AbortFlowException;

public final class FlowKt__ReduceKt$first$$inlined$collectWhile$1 implements FlowCollector {
    final /* synthetic */ Ref$ObjectRef $result$inlined;

    public FlowKt__ReduceKt$first$$inlined$collectWhile$1(Ref$ObjectRef ref$ObjectRef) {
        this.$result$inlined = ref$ObjectRef;
    }

    public Object emit(Object obj, Continuation continuation) {
        this.$result$inlined.element = obj;
        throw new AbortFlowException(this);
    }
}
