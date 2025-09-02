package com.facebook.appevents.gps.pa;

import android.os.Bundle;
import android.os.OutcomeReceiver;
import android.util.Log;
import com.facebook.appevents.gps.GpsDebugLogger;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

public final class PACustomAudienceClient$joinCustomAudienceImpl$callback$1 implements OutcomeReceiver {
    PACustomAudienceClient$joinCustomAudienceImpl$callback$1() {
    }

    public void onResult(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "result");
        Log.i(PACustomAudienceClient.access$getTAG$p(), "Successfully joined custom audience");
        GpsDebugLogger access$getGpsDebugLogger$p = PACustomAudienceClient.access$getGpsDebugLogger$p();
        if (access$getGpsDebugLogger$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("gpsDebugLogger");
            access$getGpsDebugLogger$p = null;
        }
        access$getGpsDebugLogger$p.log("gps_pa_succeed", (Bundle) null);
    }

    public void onError(Exception exc) {
        Intrinsics.checkNotNullParameter(exc, "error");
        Log.e(PACustomAudienceClient.access$getTAG$p(), exc.toString());
        GpsDebugLogger access$getGpsDebugLogger$p = PACustomAudienceClient.access$getGpsDebugLogger$p();
        if (access$getGpsDebugLogger$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("gpsDebugLogger");
            access$getGpsDebugLogger$p = null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("gps_pa_failed_reason", exc.toString());
        Unit unit = Unit.INSTANCE;
        access$getGpsDebugLogger$p.log("gps_pa_failed", bundle);
    }
}
