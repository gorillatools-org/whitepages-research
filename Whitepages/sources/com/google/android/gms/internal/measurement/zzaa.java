package com.google.android.gms.internal.measurement;

import com.google.common.collect.ImmutableSet;
import java.util.HashMap;
import java.util.Map;

public final class zzaa {
    private static final ImmutableSet zza = ImmutableSet.of("_syn", "_err", "_el");
    private String zzb;
    private final long zzc;
    private final Map zzd;

    public zzaa(String str, long j, Map map) {
        this.zzb = str;
        this.zzc = j;
        HashMap hashMap = new HashMap();
        this.zzd = hashMap;
        if (map != null) {
            hashMap.putAll(map);
        }
    }

    public static Object zzd(String str, Object obj, Object obj2) {
        if (zza.contains(str) && (obj2 instanceof Double)) {
            return Long.valueOf(Math.round(((Double) obj2).doubleValue()));
        }
        if (str.startsWith("_")) {
            if (!(obj instanceof String) && obj != null) {
                return obj;
            }
        } else if (!(obj instanceof Double)) {
            if (obj instanceof Long) {
                return Long.valueOf(Math.round(((Double) obj2).doubleValue()));
            }
            if (obj instanceof String) {
                return obj2.toString();
            }
        }
        return obj2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzaa)) {
            return false;
        }
        zzaa zzaa = (zzaa) obj;
        if (this.zzc == zzaa.zzc && this.zzb.equals(zzaa.zzb)) {
            return this.zzd.equals(zzaa.zzd);
        }
        return false;
    }

    public final int hashCode() {
        long j = this.zzc;
        return (((this.zzb.hashCode() * 31) + ((int) (j ^ (j >>> 32)))) * 31) + this.zzd.hashCode();
    }

    public final String toString() {
        String str = this.zzb;
        String obj = this.zzd.toString();
        return "Event{name='" + str + "', timestamp=" + this.zzc + ", params=" + obj + "}";
    }

    public final long zza() {
        return this.zzc;
    }

    /* renamed from: zzb */
    public final zzaa clone() {
        return new zzaa(this.zzb, this.zzc, new HashMap(this.zzd));
    }

    public final Object zzc(String str) {
        Map map = this.zzd;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        return null;
    }

    public final String zze() {
        return this.zzb;
    }

    public final Map zzf() {
        return this.zzd;
    }

    public final void zzg(String str) {
        this.zzb = str;
    }

    public final void zzh(String str, Object obj) {
        if (obj == null) {
            this.zzd.remove(str);
            return;
        }
        Map map = this.zzd;
        map.put(str, zzd(str, map.get(str), obj));
    }
}
