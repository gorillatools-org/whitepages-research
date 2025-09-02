package com.google.android.gms.measurement.internal;

import com.google.android.gms.tasks.OnFailureListener;

public final /* synthetic */ class zzgz implements OnFailureListener {
    public final /* synthetic */ zzha zza;
    public final /* synthetic */ long zzb;

    public /* synthetic */ zzgz(zzha zzha, long j) {
        this.zza = zzha;
        this.zzb = j;
    }

    public final void onFailure(Exception exc) {
        this.zza.zzd.set(this.zzb);
    }
}
