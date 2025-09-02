package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref$ObjectRef;

final class CoroutineContextKt$foldCopies$folded$1 extends Lambda implements Function2 {
    final /* synthetic */ boolean $isNewCoroutine;
    final /* synthetic */ Ref$ObjectRef $leftoverContext;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CoroutineContextKt$foldCopies$folded$1(Ref$ObjectRef ref$ObjectRef, boolean z) {
        super(2);
        this.$leftoverContext = ref$ObjectRef;
        this.$isNewCoroutine = z;
    }

    public final CoroutineContext invoke(CoroutineContext coroutineContext, CoroutineContext.Element element) {
        return coroutineContext.plus(element);
    }
}
