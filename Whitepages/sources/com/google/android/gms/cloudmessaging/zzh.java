package com.google.android.gms.cloudmessaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public final /* synthetic */ class zzh implements Runnable {
    public final /* synthetic */ CloudMessagingReceiver zza;
    public final /* synthetic */ Intent zzb;
    public final /* synthetic */ Context zzc;
    public final /* synthetic */ boolean zzd;
    public final /* synthetic */ BroadcastReceiver.PendingResult zze;

    public /* synthetic */ zzh(CloudMessagingReceiver cloudMessagingReceiver, Intent intent, Context context, boolean z, BroadcastReceiver.PendingResult pendingResult) {
        this.zza = cloudMessagingReceiver;
        this.zzb = intent;
        this.zzc = context;
        this.zzd = z;
        this.zze = pendingResult;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc, this.zzd, this.zze);
    }
}
