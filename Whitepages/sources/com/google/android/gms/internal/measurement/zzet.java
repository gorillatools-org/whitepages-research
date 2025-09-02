package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzet extends zzeu {
    final /* synthetic */ Long zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ Bundle zzd;
    final /* synthetic */ boolean zze;
    final /* synthetic */ boolean zzf;
    final /* synthetic */ zzff zzg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzet(zzff zzff, Long l, String str, String str2, Bundle bundle, boolean z, boolean z2) {
        super(zzff, true);
        this.zza = l;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = bundle;
        this.zze = z;
        this.zzf = z2;
        this.zzg = zzff;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        long longValue;
        Long l = this.zza;
        if (l == null) {
            longValue = this.zzh;
        } else {
            longValue = l.longValue();
        }
        ((zzcv) Preconditions.checkNotNull(this.zzg.zzj)).logEvent(this.zzb, this.zzc, this.zzd, this.zze, this.zzf, longValue);
    }
}
