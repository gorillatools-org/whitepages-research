package kotlinx.coroutines.flow;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$BooleanRef;

public final class FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1 implements Flow {
    final /* synthetic */ Function2 $predicate$inlined;
    final /* synthetic */ Flow $this_dropWhile$inlined;

    public FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1(Flow flow, Function2 function2) {
        this.$this_dropWhile$inlined = flow;
        this.$predicate$inlined = function2;
    }

    public Object collect(FlowCollector flowCollector, Continuation continuation) {
        Object collect = this.$this_dropWhile$inlined.collect(new FlowKt__LimitKt$dropWhile$1$1(new Ref$BooleanRef(), flowCollector, this.$predicate$inlined), continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }
}
