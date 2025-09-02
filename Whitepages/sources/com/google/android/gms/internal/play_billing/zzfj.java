package com.google.android.gms.internal.play_billing;

import java.util.Iterator;

final class zzfj implements Iterator {
    final Iterator zza;
    final /* synthetic */ zzfk zzb;

    zzfj(zzfk zzfk) {
        this.zzb = zzfk;
        this.zza = zzfk.zza.iterator();
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.zza.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
