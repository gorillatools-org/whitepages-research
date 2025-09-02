package androidx.datastore.core;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

final class DataStoreImpl$readState$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $requireLock;
    int label;
    final /* synthetic */ DataStoreImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataStoreImpl$readState$2(DataStoreImpl dataStoreImpl, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = dataStoreImpl;
        this.$requireLock = z;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new DataStoreImpl$readState$2(this.this$0, this.$requireLock, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((DataStoreImpl$readState$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.this$0.inMemoryCache.getCurrentState() instanceof Final) {
                return this.this$0.inMemoryCache.getCurrentState();
            }
            DataStoreImpl dataStoreImpl = this.this$0;
            this.label = 1;
            if (dataStoreImpl.readAndInitOrPropagateAndThrowFailure(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th) {
                return new ReadException(th, -1);
            }
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return (State) obj;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        DataStoreImpl dataStoreImpl2 = this.this$0;
        boolean z = this.$requireLock;
        this.label = 2;
        obj = dataStoreImpl2.readDataAndUpdateCache(z, this);
        if (obj == coroutine_suspended) {
            return coroutine_suspended;
        }
        return (State) obj;
    }
}
