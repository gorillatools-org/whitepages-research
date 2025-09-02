package androidx.datastore.core;

import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

public interface StorageConnection extends Closeable {
    InterProcessCoordinator getCoordinator();

    Object readScope(Function3 function3, Continuation continuation);

    Object writeScope(Function2 function2, Continuation continuation);
}
