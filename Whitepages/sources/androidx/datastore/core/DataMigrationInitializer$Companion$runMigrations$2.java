package androidx.datastore.core;

import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

final class DataMigrationInitializer$Companion$runMigrations$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ List $cleanUps;
    final /* synthetic */ List $migrations;
    /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataMigrationInitializer$Companion$runMigrations$2(List list, List list2, Continuation continuation) {
        super(2, continuation);
        this.$migrations = list;
        this.$cleanUps = list2;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        DataMigrationInitializer$Companion$runMigrations$2 dataMigrationInitializer$Companion$runMigrations$2 = new DataMigrationInitializer$Companion$runMigrations$2(this.$migrations, this.$cleanUps, continuation);
        dataMigrationInitializer$Companion$runMigrations$2.L$0 = obj;
        return dataMigrationInitializer$Companion$runMigrations$2;
    }

    public final Object invoke(Object obj, Continuation continuation) {
        return ((DataMigrationInitializer$Companion$runMigrations$2) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        List list;
        Iterator it;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            obj = this.L$0;
            list = this.$cleanUps;
            it = this.$migrations.iterator();
        } else if (i == 1) {
            Object obj2 = this.L$3;
            MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(this.L$2);
            Iterator it2 = (Iterator) this.L$1;
            List list2 = (List) this.L$0;
            ResultKt.throwOnFailure(obj);
            if (!((Boolean) obj).booleanValue()) {
                obj = obj2;
                it = it2;
                list = list2;
            } else {
                list2.add(new DataMigrationInitializer$Companion$runMigrations$2$1$1((DataMigration) null, (Continuation) null));
                this.L$0 = list2;
                this.L$1 = it2;
                this.L$2 = null;
                this.L$3 = null;
                this.label = 2;
                throw null;
            }
        } else if (i == 2) {
            it = (Iterator) this.L$1;
            list = (List) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (!it.hasNext()) {
            return obj;
        }
        MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
        this.L$0 = list;
        this.L$1 = it;
        this.L$2 = null;
        this.L$3 = obj;
        this.label = 1;
        throw null;
    }
}
