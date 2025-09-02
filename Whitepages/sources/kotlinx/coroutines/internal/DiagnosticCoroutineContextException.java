package kotlinx.coroutines.internal;

import kotlin.coroutines.CoroutineContext;

public final class DiagnosticCoroutineContextException extends RuntimeException {
    private final transient CoroutineContext context;

    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    public DiagnosticCoroutineContextException(CoroutineContext coroutineContext) {
        this.context = coroutineContext;
    }

    public String getLocalizedMessage() {
        return this.context.toString();
    }
}
