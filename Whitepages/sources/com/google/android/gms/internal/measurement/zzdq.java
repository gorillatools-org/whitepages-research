package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzdq extends zzeu {
    final /* synthetic */ zzdj zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ zzff zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdq(zzff zzff, zzdj zzdj, String str, String str2) {
        super(zzff, true);
        this.zza = zzdj;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = zzff;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzcv) Preconditions.checkNotNull(this.zzd.zzj)).setCurrentScreenByScionActivityInfo(this.zza, this.zzb, this.zzc, this.zzh);
    }
}
