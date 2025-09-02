package com.google.android.gms.internal.auth;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

@Deprecated
public final class zzhe extends AbstractList implements RandomAccess, zzff {
    /* access modifiers changed from: private */
    public final zzff zza;

    public zzhe(zzff zzff) {
        this.zza = zzff;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        return ((zzfe) this.zza).get(i);
    }

    public final Iterator iterator() {
        return new zzhd(this);
    }

    public final ListIterator listIterator(int i) {
        return new zzhc(this, i);
    }

    public final int size() {
        return this.zza.size();
    }

    public final zzff zze() {
        return this;
    }

    public final List zzg() {
        return this.zza.zzg();
    }
}
