package androidx.datastore.core;

import androidx.datastore.core.DataStoreImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

final class DataStoreImpl$incrementCollector$2$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ DataStoreImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataStoreImpl$incrementCollector$2$1(DataStoreImpl dataStoreImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = dataStoreImpl;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new DataStoreImpl$incrementCollector$2$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((DataStoreImpl$incrementCollector$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            DataStoreImpl.InitDataStore access$getReadAndInit$p = this.this$0.readAndInit;
            this.label = 1;
            if (access$getReadAndInit$p.awaitComplete(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Flow conflate = FlowKt.conflate(this.this$0.getCoordinator().getUpdateNotifications());
        final DataStoreImpl dataStoreImpl = this.this$0;
        AnonymousClass1 r1 = new FlowCollector() {
            public final Object emit(Unit unit, Continuation continuation) {
                if (dataStoreImpl.inMemoryCache.getCurrentState() instanceof Final) {
                    return Unit.INSTANCE;
                }
                Object access$readDataAndUpdateCache = dataStoreImpl.readDataAndUpdateCache(true, continuation);
                return access$readDataAndUpdateCache == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? access$readDataAndUpdateCache : Unit.INSTANCE;
            }
        };
        this.label = 2;
        if (conflate.collect(r1, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
