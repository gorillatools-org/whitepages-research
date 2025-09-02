package androidx.datastore.core;

import kotlin.coroutines.Continuation;

public interface CorruptionHandler {
    Object handleCorruption(CorruptionException corruptionException, Continuation continuation);
}
