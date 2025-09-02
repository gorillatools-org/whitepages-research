package androidx.datastore.core;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

public final class SingleProcessCoordinator implements InterProcessCoordinator {
    private final String filePath;
    private final Mutex mutex = MutexKt.Mutex$default(false, 1, (Object) null);
    private final Flow updateNotifications = FlowKt.flow(new SingleProcessCoordinator$updateNotifications$1((Continuation) null));
    private final AtomicInt version = new AtomicInt(0);

    public SingleProcessCoordinator(String str) {
        Intrinsics.checkNotNullParameter(str, "filePath");
        this.filePath = str;
    }

    public Flow getUpdateNotifications() {
        return this.updateNotifications;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0067 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object lock(kotlin.jvm.functions.Function1 r8, kotlin.coroutines.Continuation r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof androidx.datastore.core.SingleProcessCoordinator$lock$1
            if (r0 == 0) goto L_0x0013
            r0 = r9
            androidx.datastore.core.SingleProcessCoordinator$lock$1 r0 = (androidx.datastore.core.SingleProcessCoordinator$lock$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            androidx.datastore.core.SingleProcessCoordinator$lock$1 r0 = new androidx.datastore.core.SingleProcessCoordinator$lock$1
            r0.<init>(r7, r9)
        L_0x0018:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x0049
            if (r2 == r4) goto L_0x003b
            if (r2 != r3) goto L_0x0033
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.sync.Mutex r8 = (kotlinx.coroutines.sync.Mutex) r8
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ all -> 0x0031 }
            goto L_0x006b
        L_0x0031:
            r9 = move-exception
            goto L_0x0073
        L_0x0033:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x003b:
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.sync.Mutex r8 = (kotlinx.coroutines.sync.Mutex) r8
            java.lang.Object r2 = r0.L$0
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r8
            r8 = r2
            goto L_0x005b
        L_0x0049:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.sync.Mutex r9 = r7.mutex
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r4
            java.lang.Object r2 = r9.lock(r5, r0)
            if (r2 != r1) goto L_0x005b
            return r1
        L_0x005b:
            r0.L$0 = r9     // Catch:{ all -> 0x006f }
            r0.L$1 = r5     // Catch:{ all -> 0x006f }
            r0.label = r3     // Catch:{ all -> 0x006f }
            java.lang.Object r8 = r8.invoke(r0)     // Catch:{ all -> 0x006f }
            if (r8 != r1) goto L_0x0068
            return r1
        L_0x0068:
            r6 = r9
            r9 = r8
            r8 = r6
        L_0x006b:
            r8.unlock(r5)
            return r9
        L_0x006f:
            r8 = move-exception
            r6 = r9
            r9 = r8
            r8 = r6
        L_0x0073:
            r8.unlock(r5)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.SingleProcessCoordinator.lock(kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object tryLock(kotlin.jvm.functions.Function2 r7, kotlin.coroutines.Continuation r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof androidx.datastore.core.SingleProcessCoordinator$tryLock$1
            if (r0 == 0) goto L_0x0013
            r0 = r8
            androidx.datastore.core.SingleProcessCoordinator$tryLock$1 r0 = (androidx.datastore.core.SingleProcessCoordinator$tryLock$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            androidx.datastore.core.SingleProcessCoordinator$tryLock$1 r0 = new androidx.datastore.core.SingleProcessCoordinator$tryLock$1
            r0.<init>(r6, r8)
        L_0x0018:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            boolean r7 = r0.Z$0
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.sync.Mutex r0 = (kotlinx.coroutines.sync.Mutex) r0
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0030 }
            goto L_0x0057
        L_0x0030:
            r8 = move-exception
            goto L_0x0061
        L_0x0032:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.sync.Mutex r8 = r6.mutex
            boolean r2 = r8.tryLock(r4)
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)     // Catch:{ all -> 0x005d }
            r0.L$0 = r8     // Catch:{ all -> 0x005d }
            r0.Z$0 = r2     // Catch:{ all -> 0x005d }
            r0.label = r3     // Catch:{ all -> 0x005d }
            java.lang.Object r7 = r7.invoke(r5, r0)     // Catch:{ all -> 0x005d }
            if (r7 != r1) goto L_0x0054
            return r1
        L_0x0054:
            r0 = r8
            r8 = r7
            r7 = r2
        L_0x0057:
            if (r7 == 0) goto L_0x005c
            r0.unlock(r4)
        L_0x005c:
            return r8
        L_0x005d:
            r7 = move-exception
            r0 = r8
            r8 = r7
            r7 = r2
        L_0x0061:
            if (r7 == 0) goto L_0x0066
            r0.unlock(r4)
        L_0x0066:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.SingleProcessCoordinator.tryLock(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public Object getVersion(Continuation continuation) {
        return Boxing.boxInt(this.version.get());
    }

    public Object incrementAndGetVersion(Continuation continuation) {
        return Boxing.boxInt(this.version.incrementAndGet());
    }
}
