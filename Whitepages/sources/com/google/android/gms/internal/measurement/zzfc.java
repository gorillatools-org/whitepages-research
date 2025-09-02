package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzfc extends zzeu {
    final /* synthetic */ Activity zza;
    final /* synthetic */ zzcs zzb;
    final /* synthetic */ zzfe zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfc(zzfe zzfe, Activity activity, zzcs zzcs) {
        super(zzfe.zza, true);
        this.zza = activity;
        this.zzb = zzcs;
        this.zzc = zzfe;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzcv) Preconditions.checkNotNull(this.zzc.zza.zzj)).onActivitySaveInstanceStateByScionActivityInfo(zzdj.zza(this.zza), this.zzb, this.zzi);
    }
}
