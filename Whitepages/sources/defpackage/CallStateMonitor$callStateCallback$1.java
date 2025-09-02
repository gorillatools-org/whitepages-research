package defpackage;

import android.telephony.TelephonyCallback;

/* renamed from: CallStateMonitor$callStateCallback$1  reason: default package */
public final class CallStateMonitor$callStateCallback$1 extends TelephonyCallback implements TelephonyCallback.CallStateListener {
    final /* synthetic */ CallStateMonitor this$0;

    CallStateMonitor$callStateCallback$1(CallStateMonitor callStateMonitor) {
        this.this$0 = callStateMonitor;
    }

    public void onCallStateChanged(int i) {
        if (i == 0) {
            this.this$0.getOnCallEnded().invoke();
        } else if (i == 2) {
            this.this$0.getOnCallEnded().invoke();
        }
    }
}
