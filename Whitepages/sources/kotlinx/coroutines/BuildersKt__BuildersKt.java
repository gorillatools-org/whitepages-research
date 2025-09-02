package kotlinx.coroutines;

import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function2;

abstract /* synthetic */ class BuildersKt__BuildersKt {
    public static /* synthetic */ Object runBlocking$default(CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return BuildersKt.runBlocking(coroutineContext, function2);
    }

    public static final Object runBlocking(CoroutineContext coroutineContext, Function2 function2) {
        CoroutineContext coroutineContext2;
        EventLoop eventLoop;
        Thread currentThread = Thread.currentThread();
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) coroutineContext.get(ContinuationInterceptor.Key);
        if (continuationInterceptor == null) {
            eventLoop = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
            coroutineContext2 = CoroutineContextKt.newCoroutineContext((CoroutineScope) GlobalScope.INSTANCE, coroutineContext.plus(eventLoop));
        } else {
            EventLoop eventLoop2 = null;
            EventLoop eventLoop3 = continuationInterceptor instanceof EventLoop ? (EventLoop) continuationInterceptor : null;
            if (eventLoop3 != null) {
                if (eventLoop3.shouldBeProcessedFromContext()) {
                    eventLoop2 = eventLoop3;
                }
                if (eventLoop2 != null) {
                    eventLoop = eventLoop2;
                    coroutineContext2 = CoroutineContextKt.newCoroutineContext((CoroutineScope) GlobalScope.INSTANCE, coroutineContext);
                }
            }
            eventLoop = ThreadLocalEventLoop.INSTANCE.currentOrNull$kotlinx_coroutines_core();
            coroutineContext2 = CoroutineContextKt.newCoroutineContext((CoroutineScope) GlobalScope.INSTANCE, coroutineContext);
        }
        BlockingCoroutine blockingCoroutine = new BlockingCoroutine(coroutineContext2, currentThread, eventLoop);
        blockingCoroutine.start(CoroutineStart.DEFAULT, blockingCoroutine, function2);
        return blockingCoroutine.joinBlocking();
    }
}
