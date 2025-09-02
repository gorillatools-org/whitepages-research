package com.google.android.gms.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.internal.location.zzba;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzaf implements RemoteCall {
    private final FusedLocationProviderClient zza;
    private final zzba zzb;
    private final PendingIntent zzc;

    zzaf(FusedLocationProviderClient fusedLocationProviderClient, zzba zzba, PendingIntent pendingIntent) {
        this.zza = fusedLocationProviderClient;
        this.zzb = zzba;
        this.zzc = pendingIntent;
    }

    public final void accept(Object obj, Object obj2) {
        this.zza.zza(this.zzb, this.zzc, (zzaz) obj, (TaskCompletionSource) obj2);
    }
}
