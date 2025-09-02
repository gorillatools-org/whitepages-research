package kotlin.coroutines;

import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref$IntRef;

final class CombinedContext$writeReplace$1 extends Lambda implements Function2 {
    final /* synthetic */ CoroutineContext[] $elements;
    final /* synthetic */ Ref$IntRef $index;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CombinedContext$writeReplace$1(CoroutineContext[] coroutineContextArr, Ref$IntRef ref$IntRef) {
        super(2);
        this.$elements = coroutineContextArr;
        this.$index = ref$IntRef;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Unit) obj, (CoroutineContext.Element) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(Unit unit, CoroutineContext.Element element) {
        Intrinsics.checkNotNullParameter(unit, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(element, "element");
        CoroutineContext[] coroutineContextArr = this.$elements;
        Ref$IntRef ref$IntRef = this.$index;
        int i = ref$IntRef.element;
        ref$IntRef.element = i + 1;
        coroutineContextArr[i] = element;
    }
}
