package com.google.android.gms.internal.play_billing;

import java.util.Iterator;

final class zzar extends zzam {
    private final transient zzal zza;
    private final transient zzai zzb;

    zzar(zzal zzal, zzai zzai) {
        this.zza = zzal;
        this.zzb = zzai;
    }

    public final boolean contains(Object obj) {
        return this.zza.get(obj) != null;
    }

    public final /* synthetic */ Iterator iterator() {
        return this.zzb.listIterator(0);
    }

    public final int size() {
        return this.zza.size();
    }

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        return this.zzb.zza(objArr, 0);
    }

    public final zzai zzd() {
        return this.zzb;
    }

    public final zzav zze() {
        return this.zzb.listIterator(0);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzf() {
        throw null;
    }
}
