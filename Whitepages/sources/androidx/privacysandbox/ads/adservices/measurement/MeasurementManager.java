package androidx.privacysandbox.ads.adservices.measurement;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.InputEvent;
import androidx.privacysandbox.ads.adservices.internal.AdServicesInfo;
import androidx.privacysandbox.ads.adservices.internal.BackCompatManager;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class MeasurementManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public abstract Object deleteRegistrations(DeletionRequest deletionRequest, Continuation continuation);

    public abstract Object getMeasurementApiStatus(Continuation continuation);

    public abstract Object registerSource(Uri uri, InputEvent inputEvent, Continuation continuation);

    public abstract Object registerSource(SourceRegistrationRequest sourceRegistrationRequest, Continuation continuation);

    public abstract Object registerTrigger(Uri uri, Continuation continuation);

    public abstract Object registerWebSource(WebSourceRegistrationRequest webSourceRegistrationRequest, Continuation continuation);

    public abstract Object registerWebTrigger(WebTriggerRegistrationRequest webTriggerRegistrationRequest, Continuation continuation);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MeasurementManager obtain(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            StringBuilder sb = new StringBuilder();
            sb.append("AdServicesInfo.version=");
            AdServicesInfo adServicesInfo = AdServicesInfo.INSTANCE;
            sb.append(adServicesInfo.adServicesVersion());
            Log.d("MeasurementManager", sb.toString());
            if (adServicesInfo.adServicesVersion() >= 5) {
                return new MeasurementManagerApi33Ext5Impl(context);
            }
            if (adServicesInfo.extServicesVersionS() >= 9) {
                return (MeasurementManager) BackCompatManager.INSTANCE.getManager(context, "MeasurementManager", new MeasurementManager$Companion$obtain$1(context));
            }
            return null;
        }
    }
}
