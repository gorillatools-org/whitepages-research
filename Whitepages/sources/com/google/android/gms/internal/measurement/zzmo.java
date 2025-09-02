package com.google.android.gms.internal.measurement;

import java.util.Map;

final class zzmo implements Map.Entry {
    private final Map.Entry zza;

    /* synthetic */ zzmo(Map.Entry entry, zzmq zzmq) {
        this.zza = entry;
    }

    public final Object getKey() {
        return this.zza.getKey();
    }

    public final Object getValue() {
        if (((zzmr) this.zza.getValue()) == null) {
            return null;
        }
        throw null;
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zznh) {
            return ((zzmr) this.zza.getValue()).zzc((zznh) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }

    public final zzmr zza() {
        return (zzmr) this.zza.getValue();
    }
}
