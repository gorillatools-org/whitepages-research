package com.wpconnect;

import android.content.Intent;
import android.net.Uri;
import android.telecom.Call;
import android.telecom.CallScreeningService;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CallScreeningService extends android.telecom.CallScreeningService {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "CallScreening";

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public void onScreenCall(Call.Details details) {
        Uri handle;
        String schemeSpecificPart;
        Intrinsics.checkNotNullParameter(details, "callDetails");
        if (details.getCallDirection() == 0 && (handle = details.getHandle()) != null && (schemeSpecificPart = handle.getSchemeSpecificPart()) != null) {
            Intent intent = new Intent(this, CallerIdOverlayService.class);
            intent.putExtra("phoneNumber", schemeSpecificPart);
            intent.setAction("com.wpconnect.INCOMING_CALL");
            startService(intent);
            respondToCall(details, new CallScreeningService.CallResponse.Builder().setDisallowCall(false).setRejectCall(false).build());
        }
    }
}
