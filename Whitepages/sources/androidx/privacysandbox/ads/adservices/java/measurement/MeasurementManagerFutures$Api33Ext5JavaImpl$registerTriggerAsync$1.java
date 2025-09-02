package androidx.privacysandbox.ads.adservices.java.measurement;

import android.net.Uri;
import androidx.privacysandbox.ads.adservices.java.measurement.MeasurementManagerFutures;
import androidx.privacysandbox.ads.adservices.measurement.MeasurementManager;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

final class MeasurementManagerFutures$Api33Ext5JavaImpl$registerTriggerAsync$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Uri $trigger;
    int label;
    final /* synthetic */ MeasurementManagerFutures.Api33Ext5JavaImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MeasurementManagerFutures$Api33Ext5JavaImpl$registerTriggerAsync$1(MeasurementManagerFutures.Api33Ext5JavaImpl api33Ext5JavaImpl, Uri uri, Continuation continuation) {
        super(2, continuation);
        this.this$0 = api33Ext5JavaImpl;
        this.$trigger = uri;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new MeasurementManagerFutures$Api33Ext5JavaImpl$registerTriggerAsync$1(this.this$0, this.$trigger, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((MeasurementManagerFutures$Api33Ext5JavaImpl$registerTriggerAsync$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MeasurementManager access$getMMeasurementManager$p = this.this$0.mMeasurementManager;
            Uri uri = this.$trigger;
            this.label = 1;
            if (access$getMMeasurementManager$p.registerTrigger(uri, this) == coroutine_suspended) {
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
