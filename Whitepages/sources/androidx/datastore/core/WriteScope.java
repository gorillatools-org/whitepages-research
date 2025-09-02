package androidx.datastore.core;

import kotlin.coroutines.Continuation;

public interface WriteScope extends ReadScope {
    Object writeData(Object obj, Continuation continuation);
}
