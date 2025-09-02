package com.google.android.gms.internal.play_billing;

import java.util.Set;

public abstract class zzam extends zzaf implements Set {
    private transient zzai zza;

    zzam() {
    }

    public final boolean equals(Object obj) {
        if (obj == this || obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size() && containsAll(set)) {
                    return true;
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public final int hashCode() {
        return zzau.zza(this);
    }

    public zzai zzd() {
        zzai zzai = this.zza;
        if (zzai != null) {
            return zzai;
        }
        zzai zzh = zzh();
        this.zza = zzh;
        return zzh;
    }

    /* renamed from: zze */
    public abstract zzav iterator();

    /* access modifiers changed from: package-private */
    public zzai zzh() {
        Object[] array = toArray();
        int i = zzai.zzd;
        return zzai.zzi(array, array.length);
    }
}
