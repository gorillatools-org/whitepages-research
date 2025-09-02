package kotlinx.coroutines.internal;

import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

public abstract class DispatchedContinuationKt {
    public static final Symbol REUSABLE_CLAIMED = new Symbol("REUSABLE_CLAIMED");
    /* access modifiers changed from: private */
    public static final Symbol UNDEFINED = new Symbol("UNDEFINED");

    public static /* synthetic */ void resumeCancellableWith$default(Continuation continuation, Object obj, Function1 function1, int i, Object obj2) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        resumeCancellableWith(continuation, obj, function1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x008d, code lost:
        if (r8.clearThreadContext() != false) goto L_0x008f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a3, code lost:
        if (r8.clearThreadContext() != false) goto L_0x00a5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void resumeCancellableWith(kotlin.coroutines.Continuation r6, java.lang.Object r7, kotlin.jvm.functions.Function1 r8) {
        /*
            boolean r0 = r6 instanceof kotlinx.coroutines.internal.DispatchedContinuation
            if (r0 == 0) goto L_0x00b2
            kotlinx.coroutines.internal.DispatchedContinuation r6 = (kotlinx.coroutines.internal.DispatchedContinuation) r6
            java.lang.Object r8 = kotlinx.coroutines.CompletionStateKt.toState((java.lang.Object) r7, (kotlin.jvm.functions.Function1) r8)
            kotlinx.coroutines.CoroutineDispatcher r0 = r6.dispatcher
            kotlin.coroutines.CoroutineContext r1 = r6.getContext()
            boolean r0 = r0.isDispatchNeeded(r1)
            r1 = 1
            if (r0 == 0) goto L_0x0026
            r6._state = r8
            r6.resumeMode = r1
            kotlinx.coroutines.CoroutineDispatcher r7 = r6.dispatcher
            kotlin.coroutines.CoroutineContext r8 = r6.getContext()
            r7.dispatch(r8, r6)
            goto L_0x00b5
        L_0x0026:
            kotlinx.coroutines.ThreadLocalEventLoop r0 = kotlinx.coroutines.ThreadLocalEventLoop.INSTANCE
            kotlinx.coroutines.EventLoop r0 = r0.getEventLoop$kotlinx_coroutines_core()
            boolean r2 = r0.isUnconfinedLoopActive()
            if (r2 == 0) goto L_0x003b
            r6._state = r8
            r6.resumeMode = r1
            r0.dispatchUnconfined(r6)
            goto L_0x00b5
        L_0x003b:
            r0.incrementUseCount(r1)
            r2 = 0
            kotlin.coroutines.CoroutineContext r3 = r6.getContext()     // Catch:{ all -> 0x0068 }
            kotlinx.coroutines.Job$Key r4 = kotlinx.coroutines.Job.Key     // Catch:{ all -> 0x0068 }
            kotlin.coroutines.CoroutineContext$Element r3 = r3.get(r4)     // Catch:{ all -> 0x0068 }
            kotlinx.coroutines.Job r3 = (kotlinx.coroutines.Job) r3     // Catch:{ all -> 0x0068 }
            if (r3 == 0) goto L_0x006a
            boolean r4 = r3.isActive()     // Catch:{ all -> 0x0068 }
            if (r4 != 0) goto L_0x006a
            java.util.concurrent.CancellationException r7 = r3.getCancellationException()     // Catch:{ all -> 0x0068 }
            r6.cancelCompletedResult$kotlinx_coroutines_core(r8, r7)     // Catch:{ all -> 0x0068 }
            kotlin.Result$Companion r8 = kotlin.Result.Companion     // Catch:{ all -> 0x0068 }
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r7)     // Catch:{ all -> 0x0068 }
            java.lang.Object r7 = kotlin.Result.m866constructorimpl(r7)     // Catch:{ all -> 0x0068 }
            r6.resumeWith(r7)     // Catch:{ all -> 0x0068 }
            goto L_0x0092
        L_0x0068:
            r7 = move-exception
            goto L_0x00a9
        L_0x006a:
            kotlin.coroutines.Continuation r8 = r6.continuation     // Catch:{ all -> 0x0068 }
            java.lang.Object r3 = r6.countOrElement     // Catch:{ all -> 0x0068 }
            kotlin.coroutines.CoroutineContext r4 = r8.getContext()     // Catch:{ all -> 0x0068 }
            java.lang.Object r3 = kotlinx.coroutines.internal.ThreadContextKt.updateThreadContext(r4, r3)     // Catch:{ all -> 0x0068 }
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.internal.ThreadContextKt.NO_THREAD_ELEMENTS     // Catch:{ all -> 0x0068 }
            if (r3 == r5) goto L_0x007f
            kotlinx.coroutines.UndispatchedCoroutine r8 = kotlinx.coroutines.CoroutineContextKt.updateUndispatchedCompletion(r8, r4, r3)     // Catch:{ all -> 0x0068 }
            goto L_0x0080
        L_0x007f:
            r8 = r2
        L_0x0080:
            kotlin.coroutines.Continuation r5 = r6.continuation     // Catch:{ all -> 0x009c }
            r5.resumeWith(r7)     // Catch:{ all -> 0x009c }
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x009c }
            if (r8 == 0) goto L_0x008f
            boolean r7 = r8.clearThreadContext()     // Catch:{ all -> 0x0068 }
            if (r7 == 0) goto L_0x0092
        L_0x008f:
            kotlinx.coroutines.internal.ThreadContextKt.restoreThreadContext(r4, r3)     // Catch:{ all -> 0x0068 }
        L_0x0092:
            boolean r7 = r0.processUnconfinedEvent()     // Catch:{ all -> 0x0068 }
            if (r7 != 0) goto L_0x0092
        L_0x0098:
            r0.decrementUseCount(r1)
            goto L_0x00b5
        L_0x009c:
            r7 = move-exception
            if (r8 == 0) goto L_0x00a5
            boolean r8 = r8.clearThreadContext()     // Catch:{ all -> 0x0068 }
            if (r8 == 0) goto L_0x00a8
        L_0x00a5:
            kotlinx.coroutines.internal.ThreadContextKt.restoreThreadContext(r4, r3)     // Catch:{ all -> 0x0068 }
        L_0x00a8:
            throw r7     // Catch:{ all -> 0x0068 }
        L_0x00a9:
            r6.handleFatalException$kotlinx_coroutines_core(r7, r2)     // Catch:{ all -> 0x00ad }
            goto L_0x0098
        L_0x00ad:
            r6 = move-exception
            r0.decrementUseCount(r1)
            throw r6
        L_0x00b2:
            r6.resumeWith(r7)
        L_0x00b5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.DispatchedContinuationKt.resumeCancellableWith(kotlin.coroutines.Continuation, java.lang.Object, kotlin.jvm.functions.Function1):void");
    }
}
