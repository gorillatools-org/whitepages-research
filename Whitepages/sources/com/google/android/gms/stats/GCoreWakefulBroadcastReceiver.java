package com.google.android.gms.stats;

import android.content.Context;
import android.content.Intent;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;

@ShowFirstParty
@KeepForSdk
public abstract class GCoreWakefulBroadcastReceiver extends WakefulBroadcastReceiver {
    @KeepForSdk
    public static boolean completeWakefulIntent(Context context, Intent intent) {
        if (intent == null) {
            return false;
        }
        return WakefulBroadcastReceiver.completeWakefulIntent(intent);
    }
}
