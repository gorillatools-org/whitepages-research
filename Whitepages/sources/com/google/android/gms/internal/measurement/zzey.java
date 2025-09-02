package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzey extends zzeu {
    final /* synthetic */ Activity zza;
    final /* synthetic */ zzfe zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzey(zzfe zzfe, Activity activity) {
        super(zzfe.zza, true);
        this.zza = activity;
        this.zzb = zzfe;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzcv) Preconditions.checkNotNull(this.zzb.zza.zzj)).onActivityStartedByScionActivityInfo(zzdj.zza(this.zza), this.zzi);
    }
}
