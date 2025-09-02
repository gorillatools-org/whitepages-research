package androidx.datastore.core;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

final class DataStoreImpl$data$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ DataStoreImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataStoreImpl$data$1(DataStoreImpl dataStoreImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = dataStoreImpl;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        DataStoreImpl$data$1 dataStoreImpl$data$1 = new DataStoreImpl$data$1(this.this$0, continuation);
        dataStoreImpl$data$1.L$0 = obj;
        return dataStoreImpl$data$1;
    }

    public final Object invoke(FlowCollector flowCollector, Continuation continuation) {
        return ((DataStoreImpl$data$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00bc A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0032
            if (r1 == r4) goto L_0x002a
            if (r1 == r3) goto L_0x001e
            if (r1 != r2) goto L_0x0016
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x00bd
        L_0x0016:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x001e:
            java.lang.Object r1 = r8.L$1
            androidx.datastore.core.State r1 = (androidx.datastore.core.State) r1
            java.lang.Object r3 = r8.L$0
            kotlinx.coroutines.flow.FlowCollector r3 = (kotlinx.coroutines.flow.FlowCollector) r3
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0066
        L_0x002a:
            java.lang.Object r1 = r8.L$0
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x004a
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r9)
            java.lang.Object r9 = r8.L$0
            kotlinx.coroutines.flow.FlowCollector r9 = (kotlinx.coroutines.flow.FlowCollector) r9
            androidx.datastore.core.DataStoreImpl r1 = r8.this$0
            r8.L$0 = r9
            r8.label = r4
            r4 = 0
            java.lang.Object r1 = r1.readState(r4, r8)
            if (r1 != r0) goto L_0x0047
            return r0
        L_0x0047:
            r7 = r1
            r1 = r9
            r9 = r7
        L_0x004a:
            androidx.datastore.core.State r9 = (androidx.datastore.core.State) r9
            boolean r4 = r9 instanceof androidx.datastore.core.Data
            if (r4 == 0) goto L_0x0069
            r4 = r9
            androidx.datastore.core.Data r4 = (androidx.datastore.core.Data) r4
            java.lang.Object r4 = r4.getValue()
            r8.L$0 = r1
            r8.L$1 = r9
            r8.label = r3
            java.lang.Object r3 = r1.emit(r4, r8)
            if (r3 != r0) goto L_0x0064
            return r0
        L_0x0064:
            r3 = r1
            r1 = r9
        L_0x0066:
            r9 = r1
            r1 = r3
            goto L_0x0078
        L_0x0069:
            boolean r3 = r9 instanceof androidx.datastore.core.UnInitialized
            if (r3 != 0) goto L_0x00c7
            boolean r3 = r9 instanceof androidx.datastore.core.ReadException
            if (r3 != 0) goto L_0x00c0
            boolean r3 = r9 instanceof androidx.datastore.core.Final
            if (r3 == 0) goto L_0x0078
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L_0x0078:
            androidx.datastore.core.DataStoreImpl r3 = r8.this$0
            androidx.datastore.core.DataStoreInMemoryCache r3 = r3.inMemoryCache
            kotlinx.coroutines.flow.Flow r3 = r3.getFlow()
            androidx.datastore.core.DataStoreImpl$data$1$1 r4 = new androidx.datastore.core.DataStoreImpl$data$1$1
            androidx.datastore.core.DataStoreImpl r5 = r8.this$0
            r6 = 0
            r4.<init>(r5, r6)
            kotlinx.coroutines.flow.Flow r3 = kotlinx.coroutines.flow.FlowKt.onStart(r3, r4)
            androidx.datastore.core.DataStoreImpl$data$1$2 r4 = new androidx.datastore.core.DataStoreImpl$data$1$2
            r4.<init>(r6)
            kotlinx.coroutines.flow.Flow r3 = kotlinx.coroutines.flow.FlowKt.takeWhile(r3, r4)
            androidx.datastore.core.DataStoreImpl$data$1$3 r4 = new androidx.datastore.core.DataStoreImpl$data$1$3
            r4.<init>(r9, r6)
            kotlinx.coroutines.flow.Flow r9 = kotlinx.coroutines.flow.FlowKt.dropWhile(r3, r4)
            androidx.datastore.core.DataStoreImpl$data$1$invokeSuspend$$inlined$map$1 r3 = new androidx.datastore.core.DataStoreImpl$data$1$invokeSuspend$$inlined$map$1
            r3.<init>(r9)
            androidx.datastore.core.DataStoreImpl$data$1$5 r9 = new androidx.datastore.core.DataStoreImpl$data$1$5
            androidx.datastore.core.DataStoreImpl r4 = r8.this$0
            r9.<init>(r4, r6)
            kotlinx.coroutines.flow.Flow r9 = kotlinx.coroutines.flow.FlowKt.onCompletion(r3, r9)
            r8.L$0 = r6
            r8.L$1 = r6
            r8.label = r2
            java.lang.Object r9 = kotlinx.coroutines.flow.FlowKt.emitAll((kotlinx.coroutines.flow.FlowCollector) r1, (kotlinx.coroutines.flow.Flow) r9, (kotlin.coroutines.Continuation) r8)
            if (r9 != r0) goto L_0x00bd
            return r0
        L_0x00bd:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L_0x00c0:
            androidx.datastore.core.ReadException r9 = (androidx.datastore.core.ReadException) r9
            java.lang.Throwable r9 = r9.getReadException()
            throw r9
        L_0x00c7:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "This is a bug in DataStore. Please file a bug at: https://issuetracker.google.com/issues/new?component=907884&template=1466542"
            r9.<init>(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreImpl$data$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
