package androidx.datastore.core;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

final class DataStoreImpl$doWithWriteFileLock$3 extends SuspendLambda implements Function1 {
    final /* synthetic */ Function1 $block;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataStoreImpl$doWithWriteFileLock$3(Function1 function1, Continuation continuation) {
        super(1, continuation);
        this.$block = function1;
    }

    public final Continuation create(Continuation continuation) {
        return new DataStoreImpl$doWithWriteFileLock$3(this.$block, continuation);
    }

    public final Object invoke(Continuation continuation) {
        return ((DataStoreImpl$doWithWriteFileLock$3) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Function1 function1 = this.$block;
            this.label = 1;
            obj = function1.invoke(this);
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
