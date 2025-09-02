package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzdz extends zzeu {
    final /* synthetic */ Runnable zza;
    final /* synthetic */ zzff zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdz(zzff zzff, Runnable runnable) {
        super(zzff, true);
        this.zza = runnable;
        this.zzb = zzff;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzcv) Preconditions.checkNotNull(this.zzb.zzj)).retrieveAndUploadBatches(new zzdy(this, this.zza));
    }
}
