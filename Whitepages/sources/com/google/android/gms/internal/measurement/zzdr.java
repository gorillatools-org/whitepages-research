package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzdr extends zzeu {
    final /* synthetic */ Boolean zza;
    final /* synthetic */ zzff zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdr(zzff zzff, Boolean bool) {
        super(zzff, true);
        this.zza = bool;
        this.zzb = zzff;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzcv) Preconditions.checkNotNull(this.zzb.zzj)).setMeasurementEnabled(this.zza.booleanValue(), this.zzh);
    }
}
