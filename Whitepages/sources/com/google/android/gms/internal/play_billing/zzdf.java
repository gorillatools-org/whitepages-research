package com.google.android.gms.internal.play_billing;

import java.util.Map;

final class zzdf implements Map.Entry {
    private final Map.Entry zza;

    /* synthetic */ zzdf(Map.Entry entry, zzde zzde) {
        this.zza = entry;
    }

    public final Object getKey() {
        return this.zza.getKey();
    }

    public final Object getValue() {
        if (((zzdh) this.zza.getValue()) == null) {
            return null;
        }
        throw null;
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzec) {
            return ((zzdh) this.zza.getValue()).zzc((zzec) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }

    public final zzdh zza() {
        return (zzdh) this.zza.getValue();
    }
}
