package kotlinx.coroutines.internal;

import java.util.Collection;
import java.util.ServiceLoader;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.CoroutineExceptionHandler;

public abstract class CoroutineExceptionHandlerImplKt {
    private static final Collection platformExceptionHandlers;

    public static final Collection getPlatformExceptionHandlers() {
        return platformExceptionHandlers;
    }

    static {
        Class<CoroutineExceptionHandler> cls = CoroutineExceptionHandler.class;
        platformExceptionHandlers = SequencesKt.toList(SequencesKt.asSequence(ServiceLoader.load(cls, cls.getClassLoader()).iterator()));
    }

    public static final void propagateExceptionFinalResort(Throwable th) {
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
    }
}
