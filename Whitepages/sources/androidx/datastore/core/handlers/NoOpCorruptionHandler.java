package androidx.datastore.core.handlers;

import androidx.datastore.core.CorruptionException;
import androidx.datastore.core.CorruptionHandler;
import kotlin.coroutines.Continuation;

public final class NoOpCorruptionHandler implements CorruptionHandler {
    public Object handleCorruption(CorruptionException corruptionException, Continuation continuation) {
        throw corruptionException;
    }
}
