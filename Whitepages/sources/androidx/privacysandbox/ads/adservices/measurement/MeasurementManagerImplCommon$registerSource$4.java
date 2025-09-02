package androidx.privacysandbox.ads.adservices.measurement;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

final class MeasurementManagerImplCommon$registerSource$4 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MeasurementManagerImplCommon this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MeasurementManagerImplCommon$registerSource$4(SourceRegistrationRequest sourceRegistrationRequest, MeasurementManagerImplCommon measurementManagerImplCommon, Continuation continuation) {
        super(2, continuation);
        this.this$0 = measurementManagerImplCommon;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        MeasurementManagerImplCommon$registerSource$4 measurementManagerImplCommon$registerSource$4 = new MeasurementManagerImplCommon$registerSource$4((SourceRegistrationRequest) null, this.this$0, continuation);
        measurementManagerImplCommon$registerSource$4.L$0 = obj;
        return measurementManagerImplCommon$registerSource$4;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((MeasurementManagerImplCommon$registerSource$4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        throw null;
    }
}
