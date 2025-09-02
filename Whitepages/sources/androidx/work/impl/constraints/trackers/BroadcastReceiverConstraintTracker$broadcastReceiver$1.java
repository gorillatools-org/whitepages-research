package androidx.work.impl.constraints.trackers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kotlin.jvm.internal.Intrinsics;

public final class BroadcastReceiverConstraintTracker$broadcastReceiver$1 extends BroadcastReceiver {
    final /* synthetic */ BroadcastReceiverConstraintTracker this$0;

    BroadcastReceiverConstraintTracker$broadcastReceiver$1(BroadcastReceiverConstraintTracker broadcastReceiverConstraintTracker) {
        this.this$0 = broadcastReceiverConstraintTracker;
    }

    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        this.this$0.onBroadcastReceive(intent);
    }
}
