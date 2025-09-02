package androidx.datastore.core;

import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;

public interface InterProcessCoordinator {
    Flow getUpdateNotifications();

    Object getVersion(Continuation continuation);

    Object incrementAndGetVersion(Continuation continuation);

    Object lock(Function1 function1, Continuation continuation);

    Object tryLock(Function2 function2, Continuation continuation);
}
