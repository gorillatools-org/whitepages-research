package androidx.datastore.core;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

final class DataStoreImpl$transformAndWrite$2$newData$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Data $curData;
    final /* synthetic */ Function2 $transform;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataStoreImpl$transformAndWrite$2$newData$1(Function2 function2, Data data, Continuation continuation) {
        super(2, continuation);
        this.$transform = function2;
        this.$curData = data;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new DataStoreImpl$transformAndWrite$2$newData$1(this.$transform, this.$curData, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((DataStoreImpl$transformAndWrite$2$newData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Function2 function2 = this.$transform;
            Object value = this.$curData.getValue();
            this.label = 1;
            obj = function2.invoke(value, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
