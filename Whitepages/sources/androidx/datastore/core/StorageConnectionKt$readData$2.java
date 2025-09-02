package androidx.datastore.core;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

final class StorageConnectionKt$readData$2 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    int label;

    StorageConnectionKt$readData$2(Continuation continuation) {
        super(3, continuation);
    }

    public final Object invoke(ReadScope readScope, boolean z, Continuation continuation) {
        StorageConnectionKt$readData$2 storageConnectionKt$readData$2 = new StorageConnectionKt$readData$2(continuation);
        storageConnectionKt$readData$2.L$0 = readScope;
        return storageConnectionKt$readData$2.invokeSuspend(Unit.INSTANCE);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke((ReadScope) obj, ((Boolean) obj2).booleanValue(), (Continuation) obj3);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = ((ReadScope) this.L$0).readData(this);
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
