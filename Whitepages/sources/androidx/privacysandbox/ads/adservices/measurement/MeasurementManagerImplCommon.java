package androidx.privacysandbox.ads.adservices.measurement;

import android.adservices.measurement.MeasurementManager;
import android.net.Uri;
import android.view.InputEvent;
import androidx.core.os.OutcomeReceiverKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScopeKt;

public abstract class MeasurementManagerImplCommon extends MeasurementManager {
    private final MeasurementManager mMeasurementManager;

    public Object deleteRegistrations(DeletionRequest deletionRequest, Continuation continuation) {
        return deleteRegistrations$suspendImpl(this, deletionRequest, continuation);
    }

    public Object getMeasurementApiStatus(Continuation continuation) {
        return getMeasurementApiStatus$suspendImpl(this, continuation);
    }

    public Object registerSource(Uri uri, InputEvent inputEvent, Continuation continuation) {
        return registerSource$suspendImpl(this, uri, inputEvent, continuation);
    }

    public Object registerSource(SourceRegistrationRequest sourceRegistrationRequest, Continuation continuation) {
        return registerSource$suspendImpl(this, sourceRegistrationRequest, continuation);
    }

    public Object registerTrigger(Uri uri, Continuation continuation) {
        return registerTrigger$suspendImpl(this, uri, continuation);
    }

    public Object registerWebSource(WebSourceRegistrationRequest webSourceRegistrationRequest, Continuation continuation) {
        return registerWebSource$suspendImpl(this, webSourceRegistrationRequest, continuation);
    }

    public Object registerWebTrigger(WebTriggerRegistrationRequest webTriggerRegistrationRequest, Continuation continuation) {
        return registerWebTrigger$suspendImpl(this, webTriggerRegistrationRequest, continuation);
    }

    /* access modifiers changed from: protected */
    public final MeasurementManager getMMeasurementManager() {
        return this.mMeasurementManager;
    }

    public MeasurementManagerImplCommon(MeasurementManager measurementManager) {
        Intrinsics.checkNotNullParameter(measurementManager, "mMeasurementManager");
        this.mMeasurementManager = measurementManager;
    }

    static /* synthetic */ Object registerSource$suspendImpl(MeasurementManagerImplCommon measurementManagerImplCommon, SourceRegistrationRequest sourceRegistrationRequest, Continuation continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new MeasurementManagerImplCommon$registerSource$4(sourceRegistrationRequest, measurementManagerImplCommon, (Continuation) null), continuation);
        return coroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutineScope : Unit.INSTANCE;
    }

    static /* synthetic */ Object deleteRegistrations$suspendImpl(MeasurementManagerImplCommon measurementManagerImplCommon, DeletionRequest deletionRequest, Continuation continuation) {
        new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1).initCancellability();
        measurementManagerImplCommon.getMMeasurementManager();
        throw null;
    }

    static /* synthetic */ Object getMeasurementApiStatus$suspendImpl(MeasurementManagerImplCommon measurementManagerImplCommon, Continuation continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        measurementManagerImplCommon.getMMeasurementManager().getMeasurementApiStatus(new MeasurementManagerImplCommon$$ExternalSyntheticLambda3(), OutcomeReceiverKt.asOutcomeReceiver(cancellableContinuationImpl));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    static /* synthetic */ Object registerSource$suspendImpl(MeasurementManagerImplCommon measurementManagerImplCommon, Uri uri, InputEvent inputEvent, Continuation continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        measurementManagerImplCommon.getMMeasurementManager().registerSource(uri, inputEvent, new MeasurementManagerImplCommon$$ExternalSyntheticLambda3(), OutcomeReceiverKt.asOutcomeReceiver(cancellableContinuationImpl));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return result;
        }
        return Unit.INSTANCE;
    }

    static /* synthetic */ Object registerTrigger$suspendImpl(MeasurementManagerImplCommon measurementManagerImplCommon, Uri uri, Continuation continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        measurementManagerImplCommon.getMMeasurementManager().registerTrigger(uri, new MeasurementManagerImplCommon$$ExternalSyntheticLambda3(), OutcomeReceiverKt.asOutcomeReceiver(cancellableContinuationImpl));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return result;
        }
        return Unit.INSTANCE;
    }

    static /* synthetic */ Object registerWebSource$suspendImpl(MeasurementManagerImplCommon measurementManagerImplCommon, WebSourceRegistrationRequest webSourceRegistrationRequest, Continuation continuation) {
        new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1).initCancellability();
        measurementManagerImplCommon.getMMeasurementManager();
        throw null;
    }

    static /* synthetic */ Object registerWebTrigger$suspendImpl(MeasurementManagerImplCommon measurementManagerImplCommon, WebTriggerRegistrationRequest webTriggerRegistrationRequest, Continuation continuation) {
        new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1).initCancellability();
        measurementManagerImplCommon.getMMeasurementManager();
        throw null;
    }
}
