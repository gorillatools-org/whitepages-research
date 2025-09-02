package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzej extends zzeu {
    final /* synthetic */ String zza;
    final /* synthetic */ zzcs zzb;
    final /* synthetic */ zzff zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzej(zzff zzff, String str, zzcs zzcs) {
        super(zzff, true);
        this.zza = str;
        this.zzb = zzcs;
        this.zzc = zzff;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzcv) Preconditions.checkNotNull(this.zzc.zzj)).getMaxUserProperties(this.zza, this.zzb);
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zzb.zze((Bundle) null);
    }
}
