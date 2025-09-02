package androidx.datastore.core;

import androidx.datastore.core.Message;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

final class DataStoreImpl$updateData$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function2 $transform;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DataStoreImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataStoreImpl$updateData$2(DataStoreImpl dataStoreImpl, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = dataStoreImpl;
        this.$transform = function2;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        DataStoreImpl$updateData$2 dataStoreImpl$updateData$2 = new DataStoreImpl$updateData$2(this.this$0, this.$transform, continuation);
        dataStoreImpl$updateData$2.L$0 = obj;
        return dataStoreImpl$updateData$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((DataStoreImpl$updateData$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CompletableDeferred CompletableDeferred$default = CompletableDeferredKt.CompletableDeferred$default((Job) null, 1, (Object) null);
            this.this$0.writeActor.offer(new Message.Update(this.$transform, CompletableDeferred$default, this.this$0.inMemoryCache.getCurrentState(), ((CoroutineScope) this.L$0).getCoroutineContext()));
            this.label = 1;
            obj = CompletableDeferred$default.await(this);
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
