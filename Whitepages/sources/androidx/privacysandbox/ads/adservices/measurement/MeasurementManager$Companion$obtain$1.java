package androidx.privacysandbox.ads.adservices.measurement;

import android.content.Context;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class MeasurementManager$Companion$obtain$1 extends Lambda implements Function1 {
    final /* synthetic */ Context $context;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MeasurementManager$Companion$obtain$1(Context context) {
        super(1);
        this.$context = context;
    }

    public final MeasurementManagerApi31Ext9Impl invoke(Context context) {
        Intrinsics.checkNotNullParameter(context, "it");
        return new MeasurementManagerApi31Ext9Impl(this.$context);
    }
}
