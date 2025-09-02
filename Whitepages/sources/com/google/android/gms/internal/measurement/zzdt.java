package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzdt extends zzeu {
    final /* synthetic */ zzff zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdt(zzff zzff) {
        super(zzff, true);
        this.zza = zzff;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzcv) Preconditions.checkNotNull(this.zza.zzj)).resetAnalyticsData(this.zzh);
    }
}
