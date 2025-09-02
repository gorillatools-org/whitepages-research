package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzdo extends zzeu {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzcs zzc;
    final /* synthetic */ zzff zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdo(zzff zzff, String str, String str2, zzcs zzcs) {
        super(zzff, true);
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzcs;
        this.zzd = zzff;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzcv) Preconditions.checkNotNull(this.zzd.zzj)).getConditionalUserProperties(this.zza, this.zzb, this.zzc);
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zzc.zze((Bundle) null);
    }
}
