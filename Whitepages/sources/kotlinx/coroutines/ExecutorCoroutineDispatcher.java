package kotlinx.coroutines;

import java.io.Closeable;
import kotlin.coroutines.AbstractCoroutineContextKey;
import kotlin.jvm.internal.DefaultConstructorMarker;

public abstract class ExecutorCoroutineDispatcher extends CoroutineDispatcher implements Closeable {
    public static final Key Key = new Key((DefaultConstructorMarker) null);

    public static final class Key extends AbstractCoroutineContextKey {
        public /* synthetic */ Key(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Key() {
            super(CoroutineDispatcher.Key, AnonymousClass1.INSTANCE);
        }
    }
}
