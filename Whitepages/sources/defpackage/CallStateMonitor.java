package defpackage;

import android.content.Context;
import android.telephony.TelephonyManager;
import androidx.core.content.ContextCompat;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: CallStateMonitor  reason: default package */
public final class CallStateMonitor {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final CallStateMonitor$callStateCallback$1 callStateCallback = new CallStateMonitor$callStateCallback$1(this);
    private final Context context;
    private final Function0 onCallEnded;
    private final TelephonyManager telephonyManager;

    public CallStateMonitor(Context context2, Function0 function0) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(function0, "onCallEnded");
        this.context = context2;
        this.onCallEnded = function0;
        Object systemService = context2.getSystemService("phone");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.telephony.TelephonyManager");
        this.telephonyManager = (TelephonyManager) systemService;
    }

    public final Function0 getOnCallEnded() {
        return this.onCallEnded;
    }

    /* renamed from: CallStateMonitor$Companion */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final void start() {
        if (ContextCompat.checkSelfPermission(this.context, "android.permission.READ_PHONE_STATE") == 0) {
            this.telephonyManager.registerTelephonyCallback(this.context.getMainExecutor(), CallStateMonitor$$ExternalSyntheticApiModelOutline1.m(this.callStateCallback));
        }
    }

    public final void stop() {
        this.telephonyManager.unregisterTelephonyCallback(CallStateMonitor$$ExternalSyntheticApiModelOutline1.m(this.callStateCallback));
    }
}
