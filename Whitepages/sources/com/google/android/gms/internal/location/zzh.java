package com.google.android.gms.internal.location;

import android.os.DeadObjectException;

final class zzh implements zzbg<zzam> {
    final /* synthetic */ zzi zza;

    zzh(zzi zzi) {
        this.zza = zzi;
    }

    public final zzam zza() throws DeadObjectException {
        return (zzam) this.zza.getService();
    }
}
