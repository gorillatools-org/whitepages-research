package androidx.datastore.core;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

public abstract class RunOnce {
    private final CompletableDeferred didRun = CompletableDeferredKt.CompletableDeferred$default((Job) null, 1, (Object) null);
    private final Mutex runMutex = MutexKt.Mutex$default(false, 1, (Object) null);

    /* access modifiers changed from: protected */
    public abstract Object doRun(Continuation continuation);

    public final Object awaitComplete(Continuation continuation) {
        Object await = this.didRun.await(continuation);
        return await == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? await : Unit.INSTANCE;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0072 A[Catch:{ all -> 0x0078 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x007c A[SYNTHETIC, Splitter:B:33:0x007c] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object runIfNeeded(kotlin.coroutines.Continuation r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof androidx.datastore.core.RunOnce$runIfNeeded$1
            if (r0 == 0) goto L_0x0013
            r0 = r7
            androidx.datastore.core.RunOnce$runIfNeeded$1 r0 = (androidx.datastore.core.RunOnce$runIfNeeded$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            androidx.datastore.core.RunOnce$runIfNeeded$1 r0 = new androidx.datastore.core.RunOnce$runIfNeeded$1
            r0.<init>(r6, r7)
        L_0x0018:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x004c
            if (r2 == r4) goto L_0x003f
            if (r2 != r3) goto L_0x0037
            java.lang.Object r1 = r0.L$1
            kotlinx.coroutines.sync.Mutex r1 = (kotlinx.coroutines.sync.Mutex) r1
            java.lang.Object r0 = r0.L$0
            androidx.datastore.core.RunOnce r0 = (androidx.datastore.core.RunOnce) r0
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ all -> 0x0035 }
            goto L_0x008b
        L_0x0035:
            r7 = move-exception
            goto L_0x0096
        L_0x0037:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x003f:
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            java.lang.Object r4 = r0.L$0
            androidx.datastore.core.RunOnce r4 = (androidx.datastore.core.RunOnce) r4
            kotlin.ResultKt.throwOnFailure(r7)
            r7 = r2
            goto L_0x006a
        L_0x004c:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlinx.coroutines.CompletableDeferred r7 = r6.didRun
            boolean r7 = r7.isCompleted()
            if (r7 == 0) goto L_0x005a
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L_0x005a:
            kotlinx.coroutines.sync.Mutex r7 = r6.runMutex
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r4
            java.lang.Object r2 = r7.lock(r5, r0)
            if (r2 != r1) goto L_0x0069
            return r1
        L_0x0069:
            r4 = r6
        L_0x006a:
            kotlinx.coroutines.CompletableDeferred r2 = r4.didRun     // Catch:{ all -> 0x0078 }
            boolean r2 = r2.isCompleted()     // Catch:{ all -> 0x0078 }
            if (r2 == 0) goto L_0x007c
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0078 }
            r7.unlock(r5)
            return r0
        L_0x0078:
            r0 = move-exception
            r1 = r7
            r7 = r0
            goto L_0x0096
        L_0x007c:
            r0.L$0 = r4     // Catch:{ all -> 0x0078 }
            r0.L$1 = r7     // Catch:{ all -> 0x0078 }
            r0.label = r3     // Catch:{ all -> 0x0078 }
            java.lang.Object r0 = r4.doRun(r0)     // Catch:{ all -> 0x0078 }
            if (r0 != r1) goto L_0x0089
            return r1
        L_0x0089:
            r1 = r7
            r0 = r4
        L_0x008b:
            kotlinx.coroutines.CompletableDeferred r7 = r0.didRun     // Catch:{ all -> 0x0035 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0035 }
            r7.complete(r0)     // Catch:{ all -> 0x0035 }
            r1.unlock(r5)
            return r0
        L_0x0096:
            r1.unlock(r5)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.RunOnce.runIfNeeded(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
