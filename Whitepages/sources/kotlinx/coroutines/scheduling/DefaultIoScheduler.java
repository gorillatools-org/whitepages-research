package kotlinx.coroutines.scheduling;

import java.util.concurrent.Executor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.internal.SystemPropsKt;

public final class DefaultIoScheduler extends ExecutorCoroutineDispatcher implements Executor {
    public static final DefaultIoScheduler INSTANCE = new DefaultIoScheduler();

    /* renamed from: default  reason: not valid java name */
    private static final CoroutineDispatcher f3default = UnlimitedIoScheduler.INSTANCE.limitedParallelism(SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.io.parallelism", RangesKt.coerceAtLeast(64, SystemPropsKt.getAVAILABLE_PROCESSORS()), 0, 0, 12, (Object) null));

    private DefaultIoScheduler() {
    }

    public void execute(Runnable runnable) {
        dispatch(EmptyCoroutineContext.INSTANCE, runnable);
    }

    public void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        f3default.dispatch(coroutineContext, runnable);
    }

    public void close() {
        throw new IllegalStateException("Cannot be invoked on Dispatchers.IO");
    }

    public String toString() {
        return "Dispatchers.IO";
    }
}
