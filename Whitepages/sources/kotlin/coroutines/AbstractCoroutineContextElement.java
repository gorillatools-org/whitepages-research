package kotlin.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

public abstract class AbstractCoroutineContextElement implements CoroutineContext.Element {
    private final CoroutineContext.Key key;

    public AbstractCoroutineContextElement(CoroutineContext.Key key2) {
        Intrinsics.checkNotNullParameter(key2, "key");
        this.key = key2;
    }

    public Object fold(Object obj, Function2 function2) {
        return CoroutineContext.Element.DefaultImpls.fold(this, obj, function2);
    }

    public CoroutineContext.Element get(CoroutineContext.Key key2) {
        return CoroutineContext.Element.DefaultImpls.get(this, key2);
    }

    public CoroutineContext minusKey(CoroutineContext.Key key2) {
        return CoroutineContext.Element.DefaultImpls.minusKey(this, key2);
    }

    public CoroutineContext plus(CoroutineContext coroutineContext) {
        return CoroutineContext.Element.DefaultImpls.plus(this, coroutineContext);
    }

    public CoroutineContext.Key getKey() {
        return this.key;
    }
}
