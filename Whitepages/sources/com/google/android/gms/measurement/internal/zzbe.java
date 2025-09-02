package com.google.android.gms.measurement.internal;

import java.util.Iterator;

final class zzbe implements Iterator {
    final Iterator zza;
    final /* synthetic */ zzbf zzb;

    zzbe(zzbf zzbf) {
        this.zzb = zzbf;
        this.zza = zzbf.zza.keySet().iterator();
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }

    /* renamed from: zza */
    public final String next() {
        return (String) this.zza.next();
    }
}
