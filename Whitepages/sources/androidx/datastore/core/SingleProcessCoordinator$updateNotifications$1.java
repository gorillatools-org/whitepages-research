package androidx.datastore.core;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

final class SingleProcessCoordinator$updateNotifications$1 extends SuspendLambda implements Function2 {
    int label;

    SingleProcessCoordinator$updateNotifications$1(Continuation continuation) {
        super(2, continuation);
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new SingleProcessCoordinator$updateNotifications$1(continuation);
    }

    public final Object invoke(FlowCollector flowCollector, Continuation continuation) {
        return ((SingleProcessCoordinator$updateNotifications$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
