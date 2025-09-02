package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzem extends zzeu {
    final /* synthetic */ zzcs zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ zzff zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzem(zzff zzff, zzcs zzcs, int i) {
        super(zzff, true);
        this.zza = zzcs;
        this.zzb = i;
        this.zzc = zzff;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzcv) Preconditions.checkNotNull(this.zzc.zzj)).getTestFlag(this.zza, this.zzb);
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zza.zze((Bundle) null);
    }
}
