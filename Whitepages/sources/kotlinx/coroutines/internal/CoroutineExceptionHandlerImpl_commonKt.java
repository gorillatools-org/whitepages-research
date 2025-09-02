package kotlinx.coroutines.internal;

import kotlin.ExceptionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;

public abstract class CoroutineExceptionHandlerImpl_commonKt {
    public static final void handleUncaughtCoroutineException(CoroutineContext coroutineContext, Throwable th) {
        for (CoroutineExceptionHandler handleException : CoroutineExceptionHandlerImplKt.getPlatformExceptionHandlers()) {
            try {
                handleException.handleException(coroutineContext, th);
            } catch (Throwable th2) {
                CoroutineExceptionHandlerImplKt.propagateExceptionFinalResort(CoroutineExceptionHandlerKt.handlerException(th, th2));
            }
        }
        try {
            ExceptionsKt.addSuppressed(th, new DiagnosticCoroutineContextException(coroutineContext));
        } catch (Throwable unused) {
        }
        CoroutineExceptionHandlerImplKt.propagateExceptionFinalResort(th);
    }
}
