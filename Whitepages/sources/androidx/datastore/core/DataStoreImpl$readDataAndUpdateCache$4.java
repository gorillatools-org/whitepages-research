package androidx.datastore.core;

import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

final class DataStoreImpl$readDataAndUpdateCache$4 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $cachedVersion;
    Object L$0;
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ DataStoreImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataStoreImpl$readDataAndUpdateCache$4(DataStoreImpl dataStoreImpl, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = dataStoreImpl;
        this.$cachedVersion = i;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        DataStoreImpl$readDataAndUpdateCache$4 dataStoreImpl$readDataAndUpdateCache$4 = new DataStoreImpl$readDataAndUpdateCache$4(this.this$0, this.$cachedVersion, continuation);
        dataStoreImpl$readDataAndUpdateCache$4.Z$0 = ((Boolean) obj).booleanValue();
        return dataStoreImpl$readDataAndUpdateCache$4;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke(((Boolean) obj).booleanValue(), (Continuation) obj2);
    }

    public final Object invoke(boolean z, Continuation continuation) {
        return ((DataStoreImpl$readDataAndUpdateCache$4) create(Boolean.valueOf(z), continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        State state;
        boolean z;
        int i;
        Throwable th;
        boolean z2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            z = this.Z$0;
            DataStoreImpl dataStoreImpl = this.this$0;
            this.Z$0 = z;
            this.label = 1;
            obj = dataStoreImpl.readDataOrHandleCorruption(z, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 == 1) {
            z = this.Z$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th2) {
                if (z) {
                    InterProcessCoordinator access$getCoordinator = this.this$0.getCoordinator();
                    this.L$0 = th2;
                    this.Z$0 = z;
                    this.label = 2;
                    Object version = access$getCoordinator.getVersion(this);
                    if (version == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    z2 = z;
                    th = th2;
                    obj = version;
                } else {
                    boolean z3 = z;
                    th = th2;
                    i = this.$cachedVersion;
                    z2 = z3;
                }
            }
        } else if (i2 == 2) {
            z2 = this.Z$0;
            th = (Throwable) this.L$0;
            ResultKt.throwOnFailure(obj);
            i = ((Number) obj).intValue();
            ReadException readException = new ReadException(th, i);
            z = z2;
            state = readException;
            return TuplesKt.to(state, Boxing.boxBoolean(z));
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        state = (State) obj;
        return TuplesKt.to(state, Boxing.boxBoolean(z));
    }
}
