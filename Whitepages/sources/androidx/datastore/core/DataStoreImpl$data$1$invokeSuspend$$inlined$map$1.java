package androidx.datastore.core;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

public final class DataStoreImpl$data$1$invokeSuspend$$inlined$map$1 implements Flow {
    final /* synthetic */ Flow $this_unsafeTransform$inlined;

    public DataStoreImpl$data$1$invokeSuspend$$inlined$map$1(Flow flow) {
        this.$this_unsafeTransform$inlined = flow;
    }

    public Object collect(final FlowCollector flowCollector, Continuation continuation) {
        Object collect = this.$this_unsafeTransform$inlined.collect(new FlowCollector() {
            /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                /*
                    r4 = this;
                    boolean r0 = r6 instanceof androidx.datastore.core.DataStoreImpl$data$1$invokeSuspend$$inlined$map$1.AnonymousClass2.AnonymousClass1
                    if (r0 == 0) goto L_0x0013
                    r0 = r6
                    androidx.datastore.core.DataStoreImpl$data$1$invokeSuspend$$inlined$map$1$2$1 r0 = (androidx.datastore.core.DataStoreImpl$data$1$invokeSuspend$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L_0x0013
                    int r1 = r1 - r2
                    r0.label = r1
                    goto L_0x0018
                L_0x0013:
                    androidx.datastore.core.DataStoreImpl$data$1$invokeSuspend$$inlined$map$1$2$1 r0 = new androidx.datastore.core.DataStoreImpl$data$1$invokeSuspend$$inlined$map$1$2$1
                    r0.<init>(r4, r6)
                L_0x0018:
                    java.lang.Object r6 = r0.result
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.label
                    r3 = 1
                    if (r2 == 0) goto L_0x0031
                    if (r2 != r3) goto L_0x0029
                    kotlin.ResultKt.throwOnFailure(r6)
                    goto L_0x004f
                L_0x0029:
                    java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                    java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                    r5.<init>(r6)
                    throw r5
                L_0x0031:
                    kotlin.ResultKt.throwOnFailure(r6)
                    kotlinx.coroutines.flow.FlowCollector r6 = r3
                    androidx.datastore.core.State r5 = (androidx.datastore.core.State) r5
                    boolean r2 = r5 instanceof androidx.datastore.core.ReadException
                    if (r2 != 0) goto L_0x0069
                    boolean r2 = r5 instanceof androidx.datastore.core.Data
                    if (r2 == 0) goto L_0x0052
                    androidx.datastore.core.Data r5 = (androidx.datastore.core.Data) r5
                    java.lang.Object r5 = r5.getValue()
                    r0.label = r3
                    java.lang.Object r5 = r6.emit(r5, r0)
                    if (r5 != r1) goto L_0x004f
                    return r1
                L_0x004f:
                    kotlin.Unit r5 = kotlin.Unit.INSTANCE
                    return r5
                L_0x0052:
                    boolean r6 = r5 instanceof androidx.datastore.core.Final
                    if (r6 == 0) goto L_0x0057
                    goto L_0x0059
                L_0x0057:
                    boolean r3 = r5 instanceof androidx.datastore.core.UnInitialized
                L_0x0059:
                    if (r3 == 0) goto L_0x0063
                    java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                    java.lang.String r6 = "This is a bug in DataStore. Please file a bug at: https://issuetracker.google.com/issues/new?component=907884&template=1466542"
                    r5.<init>(r6)
                    throw r5
                L_0x0063:
                    kotlin.NoWhenBranchMatchedException r5 = new kotlin.NoWhenBranchMatchedException
                    r5.<init>()
                    throw r5
                L_0x0069:
                    androidx.datastore.core.ReadException r5 = (androidx.datastore.core.ReadException) r5
                    java.lang.Throwable r5 = r5.getReadException()
                    throw r5
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreImpl$data$1$invokeSuspend$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }, continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }
}
