package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzei extends zzeu {
    final /* synthetic */ Bundle zza;
    final /* synthetic */ zzcs zzb;
    final /* synthetic */ zzff zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzei(zzff zzff, Bundle bundle, zzcs zzcs) {
        super(zzff, true);
        this.zza = bundle;
        this.zzb = zzcs;
        this.zzc = zzff;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzcv) Preconditions.checkNotNull(this.zzc.zzj)).performAction(this.zza, this.zzb, this.zzh);
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zzb.zze((Bundle) null);
    }
}
