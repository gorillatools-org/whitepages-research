package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;

final class BlockingCoroutine extends AbstractCoroutine {
    private final Thread blockedThread;
    private final EventLoop eventLoop;

    /* access modifiers changed from: protected */
    public boolean isScopedCoroutine() {
        return true;
    }

    public BlockingCoroutine(CoroutineContext coroutineContext, Thread thread, EventLoop eventLoop2) {
        super(coroutineContext, true, true);
        this.blockedThread = thread;
        this.eventLoop = eventLoop2;
    }

    /* access modifiers changed from: protected */
    public void afterCompletion(Object obj) {
        if (!Intrinsics.areEqual((Object) Thread.currentThread(), (Object) this.blockedThread)) {
            Thread thread = this.blockedThread;
            AbstractTimeSourceKt.getTimeSource();
            LockSupport.unpark(thread);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: kotlinx.coroutines.CompletedExceptionally} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object joinBlocking() {
        /*
            r6 = this;
            kotlinx.coroutines.AbstractTimeSourceKt.getTimeSource()
            kotlinx.coroutines.EventLoop r0 = r6.eventLoop     // Catch:{ all -> 0x000e }
            r1 = 1
            r2 = 0
            r3 = 0
            if (r0 == 0) goto L_0x0010
            kotlinx.coroutines.EventLoop.incrementUseCount$default(r0, r2, r1, r3)     // Catch:{ all -> 0x000e }
            goto L_0x0010
        L_0x000e:
            r0 = move-exception
            goto L_0x0063
        L_0x0010:
            boolean r0 = java.lang.Thread.interrupted()     // Catch:{ all -> 0x001f }
            if (r0 != 0) goto L_0x0052
            kotlinx.coroutines.EventLoop r0 = r6.eventLoop     // Catch:{ all -> 0x001f }
            if (r0 == 0) goto L_0x0021
            long r4 = r0.processNextEvent()     // Catch:{ all -> 0x001f }
            goto L_0x0026
        L_0x001f:
            r0 = move-exception
            goto L_0x005b
        L_0x0021:
            r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
        L_0x0026:
            boolean r0 = r6.isCompleted()     // Catch:{ all -> 0x001f }
            if (r0 != 0) goto L_0x0033
            kotlinx.coroutines.AbstractTimeSourceKt.getTimeSource()     // Catch:{ all -> 0x001f }
            java.util.concurrent.locks.LockSupport.parkNanos(r6, r4)     // Catch:{ all -> 0x001f }
            goto L_0x0010
        L_0x0033:
            kotlinx.coroutines.EventLoop r0 = r6.eventLoop     // Catch:{ all -> 0x000e }
            if (r0 == 0) goto L_0x003a
            kotlinx.coroutines.EventLoop.decrementUseCount$default(r0, r2, r1, r3)     // Catch:{ all -> 0x000e }
        L_0x003a:
            kotlinx.coroutines.AbstractTimeSourceKt.getTimeSource()
            java.lang.Object r0 = r6.getState$kotlinx_coroutines_core()
            java.lang.Object r0 = kotlinx.coroutines.JobSupportKt.unboxState(r0)
            boolean r1 = r0 instanceof kotlinx.coroutines.CompletedExceptionally
            if (r1 == 0) goto L_0x004c
            r3 = r0
            kotlinx.coroutines.CompletedExceptionally r3 = (kotlinx.coroutines.CompletedExceptionally) r3
        L_0x004c:
            if (r3 != 0) goto L_0x004f
            return r0
        L_0x004f:
            java.lang.Throwable r0 = r3.cause
            throw r0
        L_0x0052:
            java.lang.InterruptedException r0 = new java.lang.InterruptedException     // Catch:{ all -> 0x001f }
            r0.<init>()     // Catch:{ all -> 0x001f }
            r6.cancelCoroutine(r0)     // Catch:{ all -> 0x001f }
            throw r0     // Catch:{ all -> 0x001f }
        L_0x005b:
            kotlinx.coroutines.EventLoop r4 = r6.eventLoop     // Catch:{ all -> 0x000e }
            if (r4 == 0) goto L_0x0062
            kotlinx.coroutines.EventLoop.decrementUseCount$default(r4, r2, r1, r3)     // Catch:{ all -> 0x000e }
        L_0x0062:
            throw r0     // Catch:{ all -> 0x000e }
        L_0x0063:
            kotlinx.coroutines.AbstractTimeSourceKt.getTimeSource()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.BlockingCoroutine.joinBlocking():java.lang.Object");
    }
}
