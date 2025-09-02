package com.google.android.gms.internal.location;

final class zzbq<E> extends zzbo<E> {
    private final zzbs<E> zza;

    zzbq(zzbs<E> zzbs, int i) {
        super(zzbs.size(), i);
        this.zza = zzbs;
    }

    /* access modifiers changed from: protected */
    public final E zza(int i) {
        return this.zza.get(i);
    }
}
