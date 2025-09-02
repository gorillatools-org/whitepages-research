package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzdp extends zzeu {
    final /* synthetic */ String zza;
    final /* synthetic */ zzff zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdp(zzff zzff, String str) {
        super(zzff, true);
        this.zza = str;
        this.zzb = zzff;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzcv) Preconditions.checkNotNull(this.zzb.zzj)).setUserId(this.zza, this.zzh);
    }
}
