package kotlinx.coroutines.flow;

import kotlin.jvm.functions.Function2;

abstract /* synthetic */ class FlowKt__LimitKt {
    public static final Flow dropWhile(Flow flow, Function2 function2) {
        return new FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1(flow, function2);
    }

    public static final Flow takeWhile(Flow flow, Function2 function2) {
        return new FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1(flow, function2);
    }
}
