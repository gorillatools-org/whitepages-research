package androidx.datastore.core;

import androidx.datastore.core.DataStoreImpl;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

final class DataStoreImpl$InitDataStore$doRun$initData$1 extends SuspendLambda implements Function1 {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    final /* synthetic */ DataStoreImpl this$0;
    final /* synthetic */ DataStoreImpl.InitDataStore this$1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataStoreImpl$InitDataStore$doRun$initData$1(DataStoreImpl dataStoreImpl, DataStoreImpl.InitDataStore initDataStore, Continuation continuation) {
        super(1, continuation);
        this.this$0 = dataStoreImpl;
        this.this$1 = initDataStore;
    }

    public final Continuation create(Continuation continuation) {
        return new DataStoreImpl$InitDataStore$doRun$initData$1(this.this$0, this.this$1, continuation);
    }

    public final Object invoke(Continuation continuation) {
        return ((DataStoreImpl$InitDataStore$doRun$initData$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00e6 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x010d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x010e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            r2 = 0
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            r7 = 0
            if (r1 == 0) goto L_0x0064
            if (r1 == r6) goto L_0x0050
            if (r1 == r5) goto L_0x0038
            if (r1 == r4) goto L_0x0027
            if (r1 != r3) goto L_0x001f
            int r0 = r12.I$0
            java.lang.Object r1 = r12.L$0
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x010f
        L_0x001f:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L_0x0027:
            java.lang.Object r1 = r12.L$2
            kotlinx.coroutines.sync.Mutex r1 = (kotlinx.coroutines.sync.Mutex) r1
            java.lang.Object r4 = r12.L$1
            kotlin.jvm.internal.Ref$ObjectRef r4 = (kotlin.jvm.internal.Ref$ObjectRef) r4
            java.lang.Object r5 = r12.L$0
            kotlin.jvm.internal.Ref$BooleanRef r5 = (kotlin.jvm.internal.Ref$BooleanRef) r5
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x00e8
        L_0x0038:
            java.lang.Object r1 = r12.L$4
            java.util.Iterator r1 = (java.util.Iterator) r1
            java.lang.Object r8 = r12.L$3
            androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$initData$1$api$1 r8 = (androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$initData$1$api$1) r8
            java.lang.Object r9 = r12.L$2
            kotlin.jvm.internal.Ref$ObjectRef r9 = (kotlin.jvm.internal.Ref$ObjectRef) r9
            java.lang.Object r10 = r12.L$1
            kotlin.jvm.internal.Ref$BooleanRef r10 = (kotlin.jvm.internal.Ref$BooleanRef) r10
            java.lang.Object r11 = r12.L$0
            kotlinx.coroutines.sync.Mutex r11 = (kotlinx.coroutines.sync.Mutex) r11
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x00aa
        L_0x0050:
            java.lang.Object r1 = r12.L$3
            kotlin.jvm.internal.Ref$ObjectRef r1 = (kotlin.jvm.internal.Ref$ObjectRef) r1
            java.lang.Object r8 = r12.L$2
            kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref$ObjectRef) r8
            java.lang.Object r9 = r12.L$1
            kotlin.jvm.internal.Ref$BooleanRef r9 = (kotlin.jvm.internal.Ref$BooleanRef) r9
            java.lang.Object r10 = r12.L$0
            kotlinx.coroutines.sync.Mutex r10 = (kotlinx.coroutines.sync.Mutex) r10
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x0089
        L_0x0064:
            kotlin.ResultKt.throwOnFailure(r13)
            kotlinx.coroutines.sync.Mutex r10 = kotlinx.coroutines.sync.MutexKt.Mutex$default(r2, r6, r7)
            kotlin.jvm.internal.Ref$BooleanRef r9 = new kotlin.jvm.internal.Ref$BooleanRef
            r9.<init>()
            kotlin.jvm.internal.Ref$ObjectRef r1 = new kotlin.jvm.internal.Ref$ObjectRef
            r1.<init>()
            androidx.datastore.core.DataStoreImpl r13 = r12.this$0
            r12.L$0 = r10
            r12.L$1 = r9
            r12.L$2 = r1
            r12.L$3 = r1
            r12.label = r6
            java.lang.Object r13 = r13.readDataOrHandleCorruption(r6, r12)
            if (r13 != r0) goto L_0x0088
            return r0
        L_0x0088:
            r8 = r1
        L_0x0089:
            androidx.datastore.core.Data r13 = (androidx.datastore.core.Data) r13
            java.lang.Object r13 = r13.getValue()
            r1.element = r13
            androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$initData$1$api$1 r13 = new androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$initData$1$api$1
            androidx.datastore.core.DataStoreImpl r1 = r12.this$0
            r13.<init>(r10, r9, r8, r1)
            androidx.datastore.core.DataStoreImpl$InitDataStore r1 = r12.this$1
            java.util.List r1 = r1.initTasks
            if (r1 == 0) goto L_0x00cd
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r1 = r1.iterator()
            r11 = r10
            r10 = r9
            r9 = r8
            r8 = r13
        L_0x00aa:
            boolean r13 = r1.hasNext()
            if (r13 == 0) goto L_0x00c9
            java.lang.Object r13 = r1.next()
            kotlin.jvm.functions.Function2 r13 = (kotlin.jvm.functions.Function2) r13
            r12.L$0 = r11
            r12.L$1 = r10
            r12.L$2 = r9
            r12.L$3 = r8
            r12.L$4 = r1
            r12.label = r5
            java.lang.Object r13 = r13.invoke(r8, r12)
            if (r13 != r0) goto L_0x00aa
            return r0
        L_0x00c9:
            r8 = r9
            r5 = r10
            r1 = r11
            goto L_0x00cf
        L_0x00cd:
            r5 = r9
            r1 = r10
        L_0x00cf:
            androidx.datastore.core.DataStoreImpl$InitDataStore r13 = r12.this$1
            r13.initTasks = r7
            r12.L$0 = r5
            r12.L$1 = r8
            r12.L$2 = r1
            r12.L$3 = r7
            r12.L$4 = r7
            r12.label = r4
            java.lang.Object r13 = r1.lock(r7, r12)
            if (r13 != r0) goto L_0x00e7
            return r0
        L_0x00e7:
            r4 = r8
        L_0x00e8:
            r5.element = r6     // Catch:{ all -> 0x011b }
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x011b }
            r1.unlock(r7)
            java.lang.Object r1 = r4.element
            if (r1 == 0) goto L_0x00f7
            int r2 = r1.hashCode()
        L_0x00f7:
            androidx.datastore.core.DataStoreImpl r13 = r12.this$0
            androidx.datastore.core.InterProcessCoordinator r13 = r13.getCoordinator()
            r12.L$0 = r1
            r12.L$1 = r7
            r12.L$2 = r7
            r12.I$0 = r2
            r12.label = r3
            java.lang.Object r13 = r13.getVersion(r12)
            if (r13 != r0) goto L_0x010e
            return r0
        L_0x010e:
            r0 = r2
        L_0x010f:
            java.lang.Number r13 = (java.lang.Number) r13
            int r13 = r13.intValue()
            androidx.datastore.core.Data r2 = new androidx.datastore.core.Data
            r2.<init>(r1, r0, r13)
            return r2
        L_0x011b:
            r13 = move-exception
            r1.unlock(r7)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$initData$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
