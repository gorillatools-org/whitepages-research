package com.google.android.gms.internal.play_billing;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public abstract class zzal implements Map, Serializable {
    private transient zzam zza;
    private transient zzam zzb;
    private transient zzaf zzc;

    zzal() {
    }

    public static zzal zzc(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        zzad.zza("com.android.vending.billing.PURCHASES_UPDATED", obj2);
        zzad.zza("com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED", obj4);
        zzad.zza("com.android.vending.billing.ALTERNATIVE_BILLING", obj6);
        return zzat.zzg(3, new Object[]{"com.android.vending.billing.PURCHASES_UPDATED", obj2, "com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED", obj4, "com.android.vending.billing.ALTERNATIVE_BILLING", obj6}, (zzak) null);
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public final boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    public final boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        return entrySet().equals(((Map) obj).entrySet());
    }

    public abstract Object get(Object obj);

    public final Object getOrDefault(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    public final int hashCode() {
        return zzau.zza(entrySet());
    }

    public final boolean isEmpty() {
        return size() == 0;
    }

    public final /* bridge */ /* synthetic */ Set keySet() {
        zzam zzam = this.zzb;
        if (zzam != null) {
            return zzam;
        }
        zzam zze = zze();
        this.zzb = zze;
        return zze;
    }

    @Deprecated
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final Object remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        if (size >= 0) {
            StringBuilder sb = new StringBuilder((int) Math.min(((long) size) * 8, 1073741824));
            sb.append('{');
            boolean z = true;
            for (Map.Entry entry : entrySet()) {
                if (!z) {
                    sb.append(", ");
                }
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
                z = false;
            }
            sb.append('}');
            return sb.toString();
        }
        throw new IllegalArgumentException("size cannot be negative but was: " + size);
    }

    /* access modifiers changed from: package-private */
    public abstract zzaf zza();

    /* renamed from: zzb */
    public final zzaf values() {
        zzaf zzaf = this.zzc;
        if (zzaf != null) {
            return zzaf;
        }
        zzaf zza2 = zza();
        this.zzc = zza2;
        return zza2;
    }

    /* access modifiers changed from: package-private */
    public abstract zzam zzd();

    /* access modifiers changed from: package-private */
    public abstract zzam zze();

    /* renamed from: zzf */
    public final zzam entrySet() {
        zzam zzam = this.zza;
        if (zzam != null) {
            return zzam;
        }
        zzam zzd = zzd();
        this.zza = zzd;
        return zzd;
    }
}
