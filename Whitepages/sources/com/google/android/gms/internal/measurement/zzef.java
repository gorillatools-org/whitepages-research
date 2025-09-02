package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzef extends zzeu {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ boolean zzc;
    final /* synthetic */ zzcs zzd;
    final /* synthetic */ zzff zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzef(zzff zzff, String str, String str2, boolean z, zzcs zzcs) {
        super(zzff, true);
        this.zza = str;
        this.zzb = str2;
        this.zzc = z;
        this.zzd = zzcs;
        this.zze = zzff;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzcv) Preconditions.checkNotNull(this.zze.zzj)).getUserProperties(this.zza, this.zzb, this.zzc, this.zzd);
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zzd.zze((Bundle) null);
    }
}
