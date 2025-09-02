package androidx.datastore.core;

import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

final class DataStoreImpl$readDataAndUpdateCache$3 extends SuspendLambda implements Function1 {
    Object L$0;
    int label;
    final /* synthetic */ DataStoreImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataStoreImpl$readDataAndUpdateCache$3(DataStoreImpl dataStoreImpl, Continuation continuation) {
        super(1, continuation);
        this.this$0 = dataStoreImpl;
    }

    public final Continuation create(Continuation continuation) {
        return new DataStoreImpl$readDataAndUpdateCache$3(this.this$0, continuation);
    }

    public final Object invoke(Continuation continuation) {
        return ((DataStoreImpl$readDataAndUpdateCache$3) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        State state;
        Throwable th;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            DataStoreImpl dataStoreImpl = this.this$0;
            this.label = 1;
            obj = dataStoreImpl.readDataOrHandleCorruption(true, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th2) {
                InterProcessCoordinator access$getCoordinator = this.this$0.getCoordinator();
                this.L$0 = th2;
                this.label = 2;
                Object version = access$getCoordinator.getVersion(this);
                if (version == coroutine_suspended) {
                    return coroutine_suspended;
                }
                th = th2;
                obj = version;
            }
        } else if (i == 2) {
            th = (Throwable) this.L$0;
            ResultKt.throwOnFailure(obj);
            state = new ReadException(th, ((Number) obj).intValue());
            return TuplesKt.to(state, Boxing.boxBoolean(true));
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        state = (State) obj;
        return TuplesKt.to(state, Boxing.boxBoolean(true));
    }
}
