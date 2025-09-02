package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

final class zzmp implements Iterator {
    private final Iterator zza;

    public zzmp(Iterator it) {
        this.zza = it;
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final /* bridge */ /* synthetic */ Object next() {
        Map.Entry entry = (Map.Entry) this.zza.next();
        return entry.getValue() instanceof zzmr ? new zzmo(entry, (zzmq) null) : entry;
    }

    public final void remove() {
        this.zza.remove();
    }
}
