package androidx.datastore.core;

import kotlin.jvm.internal.Ref$BooleanRef;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.sync.Mutex;

public final class DataStoreImpl$InitDataStore$doRun$initData$1$api$1 implements InitializerApi {
    final /* synthetic */ Ref$ObjectRef $currentData;
    final /* synthetic */ Ref$BooleanRef $initializationComplete;
    final /* synthetic */ Mutex $updateLock;
    final /* synthetic */ DataStoreImpl this$0;

    DataStoreImpl$InitDataStore$doRun$initData$1$api$1(Mutex mutex, Ref$BooleanRef ref$BooleanRef, Ref$ObjectRef ref$ObjectRef, DataStoreImpl dataStoreImpl) {
        this.$updateLock = mutex;
        this.$initializationComplete = ref$BooleanRef;
        this.$currentData = ref$ObjectRef;
        this.this$0 = dataStoreImpl;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009a A[Catch:{ all -> 0x00d8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00ba A[Catch:{ all -> 0x0056 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00d1 A[Catch:{ all -> 0x003b }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object updateData(kotlin.jvm.functions.Function2 r11, kotlin.coroutines.Continuation r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1
            if (r0 == 0) goto L_0x0013
            r0 = r12
            androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1 r0 = (androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1 r0 = new androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1
            r0.<init>(r10, r12)
        L_0x0018:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L_0x0076
            if (r2 == r5) goto L_0x005a
            if (r2 == r4) goto L_0x0046
            if (r2 != r3) goto L_0x003e
            java.lang.Object r11 = r0.L$2
            java.lang.Object r1 = r0.L$1
            kotlin.jvm.internal.Ref$ObjectRef r1 = (kotlin.jvm.internal.Ref$ObjectRef) r1
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.sync.Mutex r0 = (kotlinx.coroutines.sync.Mutex) r0
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x003b }
            goto L_0x00cd
        L_0x003b:
            r11 = move-exception
            goto L_0x00e3
        L_0x003e:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0046:
            java.lang.Object r11 = r0.L$2
            androidx.datastore.core.DataStoreImpl r11 = (androidx.datastore.core.DataStoreImpl) r11
            java.lang.Object r2 = r0.L$1
            kotlin.jvm.internal.Ref$ObjectRef r2 = (kotlin.jvm.internal.Ref$ObjectRef) r2
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.sync.Mutex r4 = (kotlinx.coroutines.sync.Mutex) r4
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x0056 }
            goto L_0x00b2
        L_0x0056:
            r11 = move-exception
            r0 = r4
            goto L_0x00e3
        L_0x005a:
            java.lang.Object r11 = r0.L$4
            androidx.datastore.core.DataStoreImpl r11 = (androidx.datastore.core.DataStoreImpl) r11
            java.lang.Object r2 = r0.L$3
            kotlin.jvm.internal.Ref$ObjectRef r2 = (kotlin.jvm.internal.Ref$ObjectRef) r2
            java.lang.Object r5 = r0.L$2
            kotlin.jvm.internal.Ref$BooleanRef r5 = (kotlin.jvm.internal.Ref$BooleanRef) r5
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.sync.Mutex r7 = (kotlinx.coroutines.sync.Mutex) r7
            java.lang.Object r8 = r0.L$0
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = r7
            r9 = r8
            r8 = r11
            r11 = r9
            goto L_0x0096
        L_0x0076:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlinx.coroutines.sync.Mutex r12 = r10.$updateLock
            kotlin.jvm.internal.Ref$BooleanRef r2 = r10.$initializationComplete
            kotlin.jvm.internal.Ref$ObjectRef r7 = r10.$currentData
            androidx.datastore.core.DataStoreImpl r8 = r10.this$0
            r0.L$0 = r11
            r0.L$1 = r12
            r0.L$2 = r2
            r0.L$3 = r7
            r0.L$4 = r8
            r0.label = r5
            java.lang.Object r5 = r12.lock(r6, r0)
            if (r5 != r1) goto L_0x0094
            return r1
        L_0x0094:
            r5 = r2
            r2 = r7
        L_0x0096:
            boolean r5 = r5.element     // Catch:{ all -> 0x00d8 }
            if (r5 != 0) goto L_0x00db
            java.lang.Object r5 = r2.element     // Catch:{ all -> 0x00d8 }
            r0.L$0 = r12     // Catch:{ all -> 0x00d8 }
            r0.L$1 = r2     // Catch:{ all -> 0x00d8 }
            r0.L$2 = r8     // Catch:{ all -> 0x00d8 }
            r0.L$3 = r6     // Catch:{ all -> 0x00d8 }
            r0.L$4 = r6     // Catch:{ all -> 0x00d8 }
            r0.label = r4     // Catch:{ all -> 0x00d8 }
            java.lang.Object r11 = r11.invoke(r5, r0)     // Catch:{ all -> 0x00d8 }
            if (r11 != r1) goto L_0x00af
            return r1
        L_0x00af:
            r4 = r12
            r12 = r11
            r11 = r8
        L_0x00b2:
            java.lang.Object r5 = r2.element     // Catch:{ all -> 0x0056 }
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r5)     // Catch:{ all -> 0x0056 }
            if (r5 != 0) goto L_0x00d1
            r0.L$0 = r4     // Catch:{ all -> 0x0056 }
            r0.L$1 = r2     // Catch:{ all -> 0x0056 }
            r0.L$2 = r12     // Catch:{ all -> 0x0056 }
            r0.label = r3     // Catch:{ all -> 0x0056 }
            r3 = 0
            java.lang.Object r11 = r11.writeData$datastore_core_release(r12, r3, r0)     // Catch:{ all -> 0x0056 }
            if (r11 != r1) goto L_0x00ca
            return r1
        L_0x00ca:
            r11 = r12
            r1 = r2
            r0 = r4
        L_0x00cd:
            r1.element = r11     // Catch:{ all -> 0x003b }
            r2 = r1
            goto L_0x00d2
        L_0x00d1:
            r0 = r4
        L_0x00d2:
            java.lang.Object r11 = r2.element     // Catch:{ all -> 0x003b }
            r0.unlock(r6)
            return r11
        L_0x00d8:
            r11 = move-exception
            r0 = r12
            goto L_0x00e3
        L_0x00db:
            java.lang.String r11 = "InitializerApi.updateData should not be called after initialization is complete."
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00d8 }
            r0.<init>(r11)     // Catch:{ all -> 0x00d8 }
            throw r0     // Catch:{ all -> 0x00d8 }
        L_0x00e3:
            r0.unlock(r6)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$initData$1$api$1.updateData(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
