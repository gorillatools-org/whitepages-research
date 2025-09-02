package androidx.privacysandbox.ads.adservices.java.measurement;

import android.content.Context;
import android.net.Uri;
import android.view.InputEvent;
import androidx.privacysandbox.ads.adservices.java.internal.CoroutineAdapterKt;
import androidx.privacysandbox.ads.adservices.measurement.DeletionRequest;
import androidx.privacysandbox.ads.adservices.measurement.MeasurementManager;
import androidx.privacysandbox.ads.adservices.measurement.SourceRegistrationRequest;
import androidx.privacysandbox.ads.adservices.measurement.WebSourceRegistrationRequest;
import androidx.privacysandbox.ads.adservices.measurement.WebTriggerRegistrationRequest;
import com.google.common.util.concurrent.ListenableFuture;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;

public abstract class MeasurementManagerFutures {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public static final MeasurementManagerFutures from(Context context) {
        return Companion.from(context);
    }

    public abstract ListenableFuture getMeasurementApiStatusAsync();

    public abstract ListenableFuture registerTriggerAsync(Uri uri);

    private static final class Api33Ext5JavaImpl extends MeasurementManagerFutures {
        /* access modifiers changed from: private */
        public final MeasurementManager mMeasurementManager;

        public Api33Ext5JavaImpl(MeasurementManager measurementManager) {
            Intrinsics.checkNotNullParameter(measurementManager, "mMeasurementManager");
            this.mMeasurementManager = measurementManager;
        }

        public ListenableFuture deleteRegistrationsAsync(DeletionRequest deletionRequest) {
            Intrinsics.checkNotNullParameter(deletionRequest, "deletionRequest");
            return CoroutineAdapterKt.asListenableFuture$default(BuildersKt__Builders_commonKt.async$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), (CoroutineContext) null, (CoroutineStart) null, new MeasurementManagerFutures$Api33Ext5JavaImpl$deleteRegistrationsAsync$1(this, deletionRequest, (Continuation) null), 3, (Object) null), (Object) null, 1, (Object) null);
        }

        public ListenableFuture registerSourceAsync(Uri uri, InputEvent inputEvent) {
            Intrinsics.checkNotNullParameter(uri, "attributionSource");
            return CoroutineAdapterKt.asListenableFuture$default(BuildersKt__Builders_commonKt.async$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), (CoroutineContext) null, (CoroutineStart) null, new MeasurementManagerFutures$Api33Ext5JavaImpl$registerSourceAsync$1(this, uri, inputEvent, (Continuation) null), 3, (Object) null), (Object) null, 1, (Object) null);
        }

        public ListenableFuture registerSourceAsync(SourceRegistrationRequest sourceRegistrationRequest) {
            Intrinsics.checkNotNullParameter(sourceRegistrationRequest, "request");
            return CoroutineAdapterKt.asListenableFuture$default(BuildersKt__Builders_commonKt.async$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), (CoroutineContext) null, (CoroutineStart) null, new MeasurementManagerFutures$Api33Ext5JavaImpl$registerSourceAsync$2(this, sourceRegistrationRequest, (Continuation) null), 3, (Object) null), (Object) null, 1, (Object) null);
        }

        public ListenableFuture registerTriggerAsync(Uri uri) {
            Intrinsics.checkNotNullParameter(uri, "trigger");
            return CoroutineAdapterKt.asListenableFuture$default(BuildersKt__Builders_commonKt.async$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), (CoroutineContext) null, (CoroutineStart) null, new MeasurementManagerFutures$Api33Ext5JavaImpl$registerTriggerAsync$1(this, uri, (Continuation) null), 3, (Object) null), (Object) null, 1, (Object) null);
        }

        public ListenableFuture registerWebSourceAsync(WebSourceRegistrationRequest webSourceRegistrationRequest) {
            Intrinsics.checkNotNullParameter(webSourceRegistrationRequest, "request");
            return CoroutineAdapterKt.asListenableFuture$default(BuildersKt__Builders_commonKt.async$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), (CoroutineContext) null, (CoroutineStart) null, new MeasurementManagerFutures$Api33Ext5JavaImpl$registerWebSourceAsync$1(this, webSourceRegistrationRequest, (Continuation) null), 3, (Object) null), (Object) null, 1, (Object) null);
        }

        public ListenableFuture registerWebTriggerAsync(WebTriggerRegistrationRequest webTriggerRegistrationRequest) {
            Intrinsics.checkNotNullParameter(webTriggerRegistrationRequest, "request");
            return CoroutineAdapterKt.asListenableFuture$default(BuildersKt__Builders_commonKt.async$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), (CoroutineContext) null, (CoroutineStart) null, new MeasurementManagerFutures$Api33Ext5JavaImpl$registerWebTriggerAsync$1(this, webTriggerRegistrationRequest, (Continuation) null), 3, (Object) null), (Object) null, 1, (Object) null);
        }

        public ListenableFuture getMeasurementApiStatusAsync() {
            return CoroutineAdapterKt.asListenableFuture$default(BuildersKt__Builders_commonKt.async$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), (CoroutineContext) null, (CoroutineStart) null, new MeasurementManagerFutures$Api33Ext5JavaImpl$getMeasurementApiStatusAsync$1(this, (Continuation) null), 3, (Object) null), (Object) null, 1, (Object) null);
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MeasurementManagerFutures from(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            MeasurementManager obtain = MeasurementManager.Companion.obtain(context);
            if (obtain != null) {
                return new Api33Ext5JavaImpl(obtain);
            }
            return null;
        }
    }
}
