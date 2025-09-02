package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzdl extends zzeu {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ Object zzc;
    final /* synthetic */ boolean zzd;
    final /* synthetic */ zzff zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdl(zzff zzff, String str, String str2, Object obj, boolean z) {
        super(zzff, true);
        this.zza = str;
        this.zzb = str2;
        this.zzc = obj;
        this.zzd = z;
        this.zze = zzff;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzcv) Preconditions.checkNotNull(this.zze.zzj)).setUserProperty(this.zza, this.zzb, ObjectWrapper.wrap(this.zzc), this.zzd, this.zzh);
    }
}
