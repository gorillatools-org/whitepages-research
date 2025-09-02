package androidx.datastore.core.handlers;

import androidx.datastore.core.CorruptionException;
import androidx.datastore.core.CorruptionHandler;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class ReplaceFileCorruptionHandler implements CorruptionHandler {
    private final Function1 produceNewData;

    public ReplaceFileCorruptionHandler(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "produceNewData");
        this.produceNewData = function1;
    }

    public Object handleCorruption(CorruptionException corruptionException, Continuation continuation) {
        return this.produceNewData.invoke(corruptionException);
    }
}
