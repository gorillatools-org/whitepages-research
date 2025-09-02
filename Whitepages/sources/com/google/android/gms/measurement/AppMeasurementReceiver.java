package com.google.android.gms.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.measurement.internal.zzhx;

public final class AppMeasurementReceiver extends WakefulBroadcastReceiver implements zzhx.zza {
    private zzhx zza;

    public BroadcastReceiver.PendingResult doGoAsync() {
        return goAsync();
    }

    public void doStartService(Context context, Intent intent) {
        WakefulBroadcastReceiver.startWakefulService(context, intent);
    }

    public void onReceive(Context context, Intent intent) {
        if (this.zza == null) {
            this.zza = new zzhx(this);
        }
        this.zza.zza(context, intent);
    }
}
