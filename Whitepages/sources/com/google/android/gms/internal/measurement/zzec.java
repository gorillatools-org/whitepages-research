package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzec extends zzeu {
    final /* synthetic */ zzcs zza;
    final /* synthetic */ zzff zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzec(zzff zzff, zzcs zzcs) {
        super(zzff, true);
        this.zza = zzcs;
        this.zzb = zzff;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzcv) Preconditions.checkNotNull(this.zzb.zzj)).generateEventId(this.zza);
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zza.zze((Bundle) null);
    }
}
