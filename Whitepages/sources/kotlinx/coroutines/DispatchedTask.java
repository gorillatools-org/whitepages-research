package kotlinx.coroutines;

import kotlin.ExceptionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.scheduling.Task;

public abstract class DispatchedTask extends Task {
    public int resumeMode;

    public abstract void cancelCompletedResult$kotlinx_coroutines_core(Object obj, Throwable th);

    public abstract Continuation getDelegate$kotlinx_coroutines_core();

    public Object getSuccessfulResult$kotlinx_coroutines_core(Object obj) {
        return obj;
    }

    public abstract Object takeState$kotlinx_coroutines_core();

    public DispatchedTask(int i) {
        this.resumeMode = i;
    }

    public final void handleFatalException$kotlinx_coroutines_core(Throwable th, Throwable th2) {
        if (th != null || th2 != null) {
            if (!(th == null || th2 == null)) {
                ExceptionsKt.addSuppressed(th, th2);
            }
            if (th == null) {
                th = th2;
            }
            Intrinsics.checkNotNull(th);
            CoroutineExceptionHandlerKt.handleCoroutineException(getDelegate$kotlinx_coroutines_core().getContext(), new CoroutinesInternalError("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers", th));
        }
    }

    public Throwable getExceptionalResult$kotlinx_coroutines_core(Object obj) {
        CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
        if (completedExceptionally != null) {
            return completedExceptionally.cause;
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x008b, code lost:
        if (r4.clearThreadContext() != false) goto L_0x008d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00b3, code lost:
        if (r4.clearThreadContext() != false) goto L_0x00b5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r10 = this;
            kotlinx.coroutines.scheduling.TaskContext r0 = r10.taskContext
            kotlin.coroutines.Continuation r1 = r10.getDelegate$kotlinx_coroutines_core()     // Catch:{ all -> 0x0023 }
            java.lang.String r2 = "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTask>"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r2)     // Catch:{ all -> 0x0023 }
            kotlinx.coroutines.internal.DispatchedContinuation r1 = (kotlinx.coroutines.internal.DispatchedContinuation) r1     // Catch:{ all -> 0x0023 }
            kotlin.coroutines.Continuation r2 = r1.continuation     // Catch:{ all -> 0x0023 }
            java.lang.Object r1 = r1.countOrElement     // Catch:{ all -> 0x0023 }
            kotlin.coroutines.CoroutineContext r3 = r2.getContext()     // Catch:{ all -> 0x0023 }
            java.lang.Object r1 = kotlinx.coroutines.internal.ThreadContextKt.updateThreadContext(r3, r1)     // Catch:{ all -> 0x0023 }
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.internal.ThreadContextKt.NO_THREAD_ELEMENTS     // Catch:{ all -> 0x0023 }
            r5 = 0
            if (r1 == r4) goto L_0x0026
            kotlinx.coroutines.UndispatchedCoroutine r4 = kotlinx.coroutines.CoroutineContextKt.updateUndispatchedCompletion(r2, r3, r1)     // Catch:{ all -> 0x0023 }
            goto L_0x0027
        L_0x0023:
            r1 = move-exception
            goto L_0x00b9
        L_0x0026:
            r4 = r5
        L_0x0027:
            kotlin.coroutines.CoroutineContext r6 = r2.getContext()     // Catch:{ all -> 0x0046 }
            java.lang.Object r7 = r10.takeState$kotlinx_coroutines_core()     // Catch:{ all -> 0x0046 }
            java.lang.Throwable r8 = r10.getExceptionalResult$kotlinx_coroutines_core(r7)     // Catch:{ all -> 0x0046 }
            if (r8 != 0) goto L_0x0048
            int r9 = r10.resumeMode     // Catch:{ all -> 0x0046 }
            boolean r9 = kotlinx.coroutines.DispatchedTaskKt.isCancellableMode(r9)     // Catch:{ all -> 0x0046 }
            if (r9 == 0) goto L_0x0048
            kotlinx.coroutines.Job$Key r9 = kotlinx.coroutines.Job.Key     // Catch:{ all -> 0x0046 }
            kotlin.coroutines.CoroutineContext$Element r6 = r6.get(r9)     // Catch:{ all -> 0x0046 }
            kotlinx.coroutines.Job r6 = (kotlinx.coroutines.Job) r6     // Catch:{ all -> 0x0046 }
            goto L_0x0049
        L_0x0046:
            r2 = move-exception
            goto L_0x00ad
        L_0x0048:
            r6 = r5
        L_0x0049:
            if (r6 == 0) goto L_0x0066
            boolean r9 = r6.isActive()     // Catch:{ all -> 0x0046 }
            if (r9 != 0) goto L_0x0066
            java.util.concurrent.CancellationException r6 = r6.getCancellationException()     // Catch:{ all -> 0x0046 }
            r10.cancelCompletedResult$kotlinx_coroutines_core(r7, r6)     // Catch:{ all -> 0x0046 }
            kotlin.Result$Companion r7 = kotlin.Result.Companion     // Catch:{ all -> 0x0046 }
            java.lang.Object r6 = kotlin.ResultKt.createFailure(r6)     // Catch:{ all -> 0x0046 }
            java.lang.Object r6 = kotlin.Result.m866constructorimpl(r6)     // Catch:{ all -> 0x0046 }
            r2.resumeWith(r6)     // Catch:{ all -> 0x0046 }
            goto L_0x0083
        L_0x0066:
            if (r8 == 0) goto L_0x0076
            kotlin.Result$Companion r6 = kotlin.Result.Companion     // Catch:{ all -> 0x0046 }
            java.lang.Object r6 = kotlin.ResultKt.createFailure(r8)     // Catch:{ all -> 0x0046 }
            java.lang.Object r6 = kotlin.Result.m866constructorimpl(r6)     // Catch:{ all -> 0x0046 }
            r2.resumeWith(r6)     // Catch:{ all -> 0x0046 }
            goto L_0x0083
        L_0x0076:
            kotlin.Result$Companion r6 = kotlin.Result.Companion     // Catch:{ all -> 0x0046 }
            java.lang.Object r6 = r10.getSuccessfulResult$kotlinx_coroutines_core(r7)     // Catch:{ all -> 0x0046 }
            java.lang.Object r6 = kotlin.Result.m866constructorimpl(r6)     // Catch:{ all -> 0x0046 }
            r2.resumeWith(r6)     // Catch:{ all -> 0x0046 }
        L_0x0083:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0046 }
            if (r4 == 0) goto L_0x008d
            boolean r2 = r4.clearThreadContext()     // Catch:{ all -> 0x0023 }
            if (r2 == 0) goto L_0x0090
        L_0x008d:
            kotlinx.coroutines.internal.ThreadContextKt.restoreThreadContext(r3, r1)     // Catch:{ all -> 0x0023 }
        L_0x0090:
            r0.afterTask()     // Catch:{ all -> 0x009a }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x009a }
            java.lang.Object r0 = kotlin.Result.m866constructorimpl(r0)     // Catch:{ all -> 0x009a }
            goto L_0x00a5
        L_0x009a:
            r0 = move-exception
            kotlin.Result$Companion r1 = kotlin.Result.Companion
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m866constructorimpl(r0)
        L_0x00a5:
            java.lang.Throwable r0 = kotlin.Result.m867exceptionOrNullimpl(r0)
            r10.handleFatalException$kotlinx_coroutines_core(r5, r0)
            goto L_0x00d7
        L_0x00ad:
            if (r4 == 0) goto L_0x00b5
            boolean r4 = r4.clearThreadContext()     // Catch:{ all -> 0x0023 }
            if (r4 == 0) goto L_0x00b8
        L_0x00b5:
            kotlinx.coroutines.internal.ThreadContextKt.restoreThreadContext(r3, r1)     // Catch:{ all -> 0x0023 }
        L_0x00b8:
            throw r2     // Catch:{ all -> 0x0023 }
        L_0x00b9:
            kotlin.Result$Companion r2 = kotlin.Result.Companion     // Catch:{ all -> 0x00c5 }
            r0.afterTask()     // Catch:{ all -> 0x00c5 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00c5 }
            java.lang.Object r0 = kotlin.Result.m866constructorimpl(r0)     // Catch:{ all -> 0x00c5 }
            goto L_0x00d0
        L_0x00c5:
            r0 = move-exception
            kotlin.Result$Companion r2 = kotlin.Result.Companion
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m866constructorimpl(r0)
        L_0x00d0:
            java.lang.Throwable r0 = kotlin.Result.m867exceptionOrNullimpl(r0)
            r10.handleFatalException$kotlinx_coroutines_core(r1, r0)
        L_0x00d7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.DispatchedTask.run():void");
    }
}
