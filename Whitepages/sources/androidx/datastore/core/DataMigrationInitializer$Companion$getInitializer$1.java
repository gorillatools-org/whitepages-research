package androidx.datastore.core;

import androidx.datastore.core.DataMigrationInitializer;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

final class DataMigrationInitializer$Companion$getInitializer$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ List $migrations;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataMigrationInitializer$Companion$getInitializer$1(List list, Continuation continuation) {
        super(2, continuation);
        this.$migrations = list;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        DataMigrationInitializer$Companion$getInitializer$1 dataMigrationInitializer$Companion$getInitializer$1 = new DataMigrationInitializer$Companion$getInitializer$1(this.$migrations, continuation);
        dataMigrationInitializer$Companion$getInitializer$1.L$0 = obj;
        return dataMigrationInitializer$Companion$getInitializer$1;
    }

    public final Object invoke(InitializerApi initializerApi, Continuation continuation) {
        return ((DataMigrationInitializer$Companion$getInitializer$1) create(initializerApi, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            DataMigrationInitializer.Companion companion = DataMigrationInitializer.Companion;
            List list = this.$migrations;
            this.label = 1;
            if (companion.runMigrations(list, (InitializerApi) this.L$0, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
