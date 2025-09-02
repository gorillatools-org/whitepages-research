package com.google.android.gms.internal.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jspecify.annotations.NullMarked;

@NullMarked
public final class zzaa {
    /* access modifiers changed from: private */
    public final zzr zza;
    /* access modifiers changed from: private */
    public final boolean zzb;
    private final zzx zzc;

    private zzaa(zzx zzx, boolean z, zzr zzr, int i) {
        this.zzc = zzx;
        this.zzb = z;
        this.zza = zzr;
    }

    public static zzaa zzc(zzr zzr) {
        return new zzaa(new zzx(zzr), false, zzq.zza, Integer.MAX_VALUE);
    }

    /* access modifiers changed from: private */
    public final Iterator zzh(CharSequence charSequence) {
        zzx zzx = this.zzc;
        return new zzw(zzx, this, charSequence, zzx.zza);
    }

    public final zzaa zzb() {
        return new zzaa(this.zzc, true, this.zza, Integer.MAX_VALUE);
    }

    public final Iterable zzd(CharSequence charSequence) {
        return new zzy(this, charSequence);
    }

    public final List zzf(CharSequence charSequence) {
        charSequence.getClass();
        Iterator zzh = zzh(charSequence);
        ArrayList arrayList = new ArrayList();
        while (zzh.hasNext()) {
            arrayList.add((String) zzh.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
