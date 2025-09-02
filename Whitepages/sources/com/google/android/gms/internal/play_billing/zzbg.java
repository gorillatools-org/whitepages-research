package com.google.android.gms.internal.play_billing;

import java.util.NoSuchElementException;

final class zzbg extends zzbi {
    final /* synthetic */ zzbq zza;
    private int zzb = 0;
    private final int zzc;

    zzbg(zzbq zzbq) {
        this.zza = zzbq;
        this.zzc = zzbq.zzd();
    }

    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    public final byte zza() {
        int i = this.zzb;
        if (i < this.zzc) {
            this.zzb = i + 1;
            return this.zza.zzb(i);
        }
        throw new NoSuchElementException();
    }
}
