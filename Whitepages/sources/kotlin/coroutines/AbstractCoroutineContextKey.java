package kotlin.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public abstract class AbstractCoroutineContextKey implements CoroutineContext.Key {
    private final Function1 safeCast;
    private final CoroutineContext.Key topmostKey;

    public AbstractCoroutineContextKey(CoroutineContext.Key key, Function1 function1) {
        Intrinsics.checkNotNullParameter(key, "baseKey");
        Intrinsics.checkNotNullParameter(function1, "safeCast");
        this.safeCast = function1;
        this.topmostKey = key instanceof AbstractCoroutineContextKey ? ((AbstractCoroutineContextKey) key).topmostKey : key;
    }

    public final CoroutineContext.Element tryCast$kotlin_stdlib(CoroutineContext.Element element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return (CoroutineContext.Element) this.safeCast.invoke(element);
    }

    public final boolean isSubKey$kotlin_stdlib(CoroutineContext.Key key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return key == this || this.topmostKey == key;
    }
}
