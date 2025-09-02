package androidx.datastore.core;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

final class DataMigrationInitializer$Companion$runMigrations$2$1$1 extends SuspendLambda implements Function1 {
    int label;

    DataMigrationInitializer$Companion$runMigrations$2$1$1(DataMigration dataMigration, Continuation continuation) {
        super(1, continuation);
    }

    public final Continuation create(Continuation continuation) {
        return new DataMigrationInitializer$Companion$runMigrations$2$1$1((DataMigration) null, continuation);
    }

    public final Object invoke(Continuation continuation) {
        return ((DataMigrationInitializer$Companion$runMigrations$2$1$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            throw null;
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
