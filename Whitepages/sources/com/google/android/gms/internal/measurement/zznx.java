package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

final class zznx implements Iterator {
    final /* synthetic */ zzoa zza;
    private int zzb = -1;
    private boolean zzc;
    private Iterator zzd;

    /* synthetic */ zznx(zzoa zzoa, zznz zznz) {
        this.zza = zzoa;
    }

    private final Iterator zza() {
        if (this.zzd == null) {
            this.zzd = this.zza.zzc.entrySet().iterator();
        }
        return this.zzd;
    }

    public final boolean hasNext() {
        int i = this.zzb + 1;
        zzoa zzoa = this.zza;
        if (i < zzoa.zzb) {
            return true;
        }
        if (!zzoa.zzc.isEmpty()) {
            return zza().hasNext();
        }
        return false;
    }

    public final /* bridge */ /* synthetic */ Object next() {
        this.zzc = true;
        int i = this.zzb + 1;
        this.zzb = i;
        zzoa zzoa = this.zza;
        if (i < zzoa.zzb) {
            return (zznw) zzoa.zza[i];
        }
        return (Map.Entry) zza().next();
    }

    public final void remove() {
        if (this.zzc) {
            this.zzc = false;
            zzoa zzoa = this.zza;
            zzoa.zzo();
            int i = this.zzb;
            if (i < zzoa.zzb) {
                this.zzb = i - 1;
                Object unused = zzoa.zzm(i);
                return;
            }
            zza().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }
}
