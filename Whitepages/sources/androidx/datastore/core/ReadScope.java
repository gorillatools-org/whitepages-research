package androidx.datastore.core;

import kotlin.coroutines.Continuation;

public interface ReadScope extends Closeable {
    Object readData(Continuation continuation);
}
