package com.google.android.gms.internal.play_billing;

import java.util.Iterator;
import java.util.Map;

final class zzaq extends zzam {
    private final transient zzal zza;
    /* access modifiers changed from: private */
    public final transient Object[] zzb;
    /* access modifiers changed from: private */
    public final transient int zzc;

    zzaq(zzal zzal, Object[] objArr, int i, int i2) {
        this.zza = zzal;
        this.zzb = objArr;
        this.zzc = i2;
    }

    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value == null || !value.equals(this.zza.get(key))) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final /* synthetic */ Iterator iterator() {
        return zzd().listIterator(0);
    }

    public final int size() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        return zzd().zza(objArr, 0);
    }

    public final zzav zze() {
        return zzd().listIterator(0);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzf() {
        throw null;
    }

    /* access modifiers changed from: package-private */
    public final zzai zzh() {
        return new zzap(this);
    }
}
