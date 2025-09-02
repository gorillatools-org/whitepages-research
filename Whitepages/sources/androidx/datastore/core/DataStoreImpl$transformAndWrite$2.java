package androidx.datastore.core;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

final class DataStoreImpl$transformAndWrite$2 extends SuspendLambda implements Function1 {
    final /* synthetic */ CoroutineContext $callerContext;
    final /* synthetic */ Function2 $transform;
    Object L$0;
    int label;
    final /* synthetic */ DataStoreImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataStoreImpl$transformAndWrite$2(DataStoreImpl dataStoreImpl, CoroutineContext coroutineContext, Function2 function2, Continuation continuation) {
        super(1, continuation);
        this.this$0 = dataStoreImpl;
        this.$callerContext = coroutineContext;
        this.$transform = function2;
    }

    public final Continuation create(Continuation continuation) {
        return new DataStoreImpl$transformAndWrite$2(this.this$0, this.$callerContext, this.$transform, continuation);
    }

    public final Object invoke(Continuation continuation) {
        return ((DataStoreImpl$transformAndWrite$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x002b
            if (r1 == r4) goto L_0x0027
            if (r1 == r3) goto L_0x001f
            if (r1 != r2) goto L_0x0017
            java.lang.Object r0 = r8.L$0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x006c
        L_0x0017:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x001f:
            java.lang.Object r1 = r8.L$0
            androidx.datastore.core.Data r1 = (androidx.datastore.core.Data) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0051
        L_0x0027:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0039
        L_0x002b:
            kotlin.ResultKt.throwOnFailure(r9)
            androidx.datastore.core.DataStoreImpl r9 = r8.this$0
            r8.label = r4
            java.lang.Object r9 = r9.readDataOrHandleCorruption(r4, r8)
            if (r9 != r0) goto L_0x0039
            return r0
        L_0x0039:
            r1 = r9
            androidx.datastore.core.Data r1 = (androidx.datastore.core.Data) r1
            kotlin.coroutines.CoroutineContext r9 = r8.$callerContext
            androidx.datastore.core.DataStoreImpl$transformAndWrite$2$newData$1 r5 = new androidx.datastore.core.DataStoreImpl$transformAndWrite$2$newData$1
            kotlin.jvm.functions.Function2 r6 = r8.$transform
            r7 = 0
            r5.<init>(r6, r1, r7)
            r8.L$0 = r1
            r8.label = r3
            java.lang.Object r9 = kotlinx.coroutines.BuildersKt.withContext(r9, r5, r8)
            if (r9 != r0) goto L_0x0051
            return r0
        L_0x0051:
            r1.checkHashCode()
            java.lang.Object r1 = r1.getValue()
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r9)
            if (r1 != 0) goto L_0x006d
            androidx.datastore.core.DataStoreImpl r1 = r8.this$0
            r8.L$0 = r9
            r8.label = r2
            java.lang.Object r1 = r1.writeData$datastore_core_release(r9, r4, r8)
            if (r1 != r0) goto L_0x006b
            return r0
        L_0x006b:
            r0 = r9
        L_0x006c:
            r9 = r0
        L_0x006d:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreImpl$transformAndWrite$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
