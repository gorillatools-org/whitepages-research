package androidx.datastore.core;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

final class DataStoreImpl$readDataOrHandleCorruption$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $preLockVersion;
    Object L$0;
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ DataStoreImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataStoreImpl$readDataOrHandleCorruption$2(DataStoreImpl dataStoreImpl, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = dataStoreImpl;
        this.$preLockVersion = i;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        DataStoreImpl$readDataOrHandleCorruption$2 dataStoreImpl$readDataOrHandleCorruption$2 = new DataStoreImpl$readDataOrHandleCorruption$2(this.this$0, this.$preLockVersion, continuation);
        dataStoreImpl$readDataOrHandleCorruption$2.Z$0 = ((Boolean) obj).booleanValue();
        return dataStoreImpl$readDataOrHandleCorruption$2;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke(((Boolean) obj).booleanValue(), (Continuation) obj2);
    }

    public final Object invoke(boolean z, Continuation continuation) {
        return ((DataStoreImpl$readDataOrHandleCorruption$2) create(Boolean.valueOf(z), continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x005e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0022
            if (r1 == r3) goto L_0x001c
            if (r1 != r2) goto L_0x0014
            java.lang.Object r0 = r5.L$0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0049
        L_0x0014:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x001c:
            boolean r1 = r5.Z$0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0034
        L_0x0022:
            kotlin.ResultKt.throwOnFailure(r6)
            boolean r1 = r5.Z$0
            androidx.datastore.core.DataStoreImpl r6 = r5.this$0
            r5.Z$0 = r1
            r5.label = r3
            java.lang.Object r6 = r6.readDataFromFileOrDefault(r5)
            if (r6 != r0) goto L_0x0034
            return r0
        L_0x0034:
            if (r1 == 0) goto L_0x0050
            androidx.datastore.core.DataStoreImpl r1 = r5.this$0
            androidx.datastore.core.InterProcessCoordinator r1 = r1.getCoordinator()
            r5.L$0 = r6
            r5.label = r2
            java.lang.Object r1 = r1.getVersion(r5)
            if (r1 != r0) goto L_0x0047
            return r0
        L_0x0047:
            r0 = r6
            r6 = r1
        L_0x0049:
            java.lang.Number r6 = (java.lang.Number) r6
            int r6 = r6.intValue()
            goto L_0x0055
        L_0x0050:
            int r0 = r5.$preLockVersion
            r4 = r0
            r0 = r6
            r6 = r4
        L_0x0055:
            androidx.datastore.core.Data r1 = new androidx.datastore.core.Data
            if (r0 == 0) goto L_0x005e
            int r2 = r0.hashCode()
            goto L_0x005f
        L_0x005e:
            r2 = 0
        L_0x005f:
            r1.<init>(r0, r2, r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
