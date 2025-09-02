package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzer extends zzeu {
    final /* synthetic */ zzew zza;
    final /* synthetic */ zzff zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzer(zzff zzff, zzew zzew) {
        super(zzff, true);
        this.zza = zzew;
        this.zzb = zzff;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzcv) Preconditions.checkNotNull(this.zzb.zzj)).registerOnMeasurementEventListener(this.zza);
    }
}
