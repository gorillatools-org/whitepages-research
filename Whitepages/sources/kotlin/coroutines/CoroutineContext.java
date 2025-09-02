package kotlin.coroutines;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

public interface CoroutineContext {

    public interface Key {
    }

    Object fold(Object obj, Function2 function2);

    Element get(Key key);

    CoroutineContext minusKey(Key key);

    CoroutineContext plus(CoroutineContext coroutineContext);

    public static final class DefaultImpls {
        public static CoroutineContext plus(CoroutineContext coroutineContext, CoroutineContext coroutineContext2) {
            Intrinsics.checkNotNullParameter(coroutineContext2, "context");
            return coroutineContext2 == EmptyCoroutineContext.INSTANCE ? coroutineContext : (CoroutineContext) coroutineContext2.fold(coroutineContext, CoroutineContext$plus$1.INSTANCE);
        }
    }

    public interface Element extends CoroutineContext {
        Element get(Key key);

        Key getKey();

        public static final class DefaultImpls {
            public static CoroutineContext plus(Element element, CoroutineContext coroutineContext) {
                Intrinsics.checkNotNullParameter(coroutineContext, "context");
                return DefaultImpls.plus(element, coroutineContext);
            }

            public static Element get(Element element, Key key) {
                Intrinsics.checkNotNullParameter(key, "key");
                if (!Intrinsics.areEqual((Object) element.getKey(), (Object) key)) {
                    return null;
                }
                Intrinsics.checkNotNull(element, "null cannot be cast to non-null type E of kotlin.coroutines.CoroutineContext.Element.get");
                return element;
            }

            public static Object fold(Element element, Object obj, Function2 function2) {
                Intrinsics.checkNotNullParameter(function2, "operation");
                return function2.invoke(obj, element);
            }

            public static CoroutineContext minusKey(Element element, Key key) {
                Intrinsics.checkNotNullParameter(key, "key");
                return Intrinsics.areEqual((Object) element.getKey(), (Object) key) ? EmptyCoroutineContext.INSTANCE : element;
            }
        }
    }
}
