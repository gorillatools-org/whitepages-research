package androidx.datastore.core;

import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;

public interface InitializerApi {
    Object updateData(Function2 function2, Continuation continuation);
}
