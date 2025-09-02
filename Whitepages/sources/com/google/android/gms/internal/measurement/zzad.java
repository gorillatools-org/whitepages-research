package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzad implements Iterator {
    final /* synthetic */ zzae zza;
    private int zzb = 0;

    zzad(zzae zzae) {
        this.zza = zzae;
    }

    public final boolean hasNext() {
        return this.zzb < this.zza.zzc();
    }

    public final /* bridge */ /* synthetic */ Object next() {
        zzae zzae = this.zza;
        if (this.zzb < zzae.zzc()) {
            int i = this.zzb;
            this.zzb = i + 1;
            return zzae.zze(i);
        }
        int i2 = this.zzb;
        throw new NoSuchElementException("Out of bounds index: " + i2);
    }
}
