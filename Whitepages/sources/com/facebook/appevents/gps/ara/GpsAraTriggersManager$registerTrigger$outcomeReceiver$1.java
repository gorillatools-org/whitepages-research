package com.facebook.appevents.gps.ara;

import android.os.Bundle;
import android.os.OutcomeReceiver;
import android.util.Log;
import com.facebook.appevents.gps.GpsDebugLogger;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

public final class GpsAraTriggersManager$registerTrigger$outcomeReceiver$1 implements OutcomeReceiver {
    GpsAraTriggersManager$registerTrigger$outcomeReceiver$1() {
    }

    public void onResult(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "result");
        Log.d(GpsAraTriggersManager.access$getTAG$p(), "OUTCOME_RECEIVER_TRIGGER_SUCCESS");
        GpsDebugLogger access$getGpsDebugLogger$p = GpsAraTriggersManager.access$getGpsDebugLogger$p();
        if (access$getGpsDebugLogger$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("gpsDebugLogger");
            access$getGpsDebugLogger$p = null;
        }
        access$getGpsDebugLogger$p.log("gps_ara_succeed", (Bundle) null);
    }

    public void onError(Exception exc) {
        Intrinsics.checkNotNullParameter(exc, "error");
        Log.d(GpsAraTriggersManager.access$getTAG$p(), "OUTCOME_RECEIVER_TRIGGER_FAILURE");
        GpsDebugLogger access$getGpsDebugLogger$p = GpsAraTriggersManager.access$getGpsDebugLogger$p();
        if (access$getGpsDebugLogger$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("gpsDebugLogger");
            access$getGpsDebugLogger$p = null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("gps_ara_failed_reason", exc.toString());
        Unit unit = Unit.INSTANCE;
        access$getGpsDebugLogger$p.log("gps_ara_failed", bundle);
    }
}
