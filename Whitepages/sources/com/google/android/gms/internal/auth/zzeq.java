package com.google.android.gms.internal.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

final class zzeq {
    private static final zzeq zzb = new zzeq(true);
    final zzgv zza = new zzgl(16);
    private boolean zzc;
    private boolean zzd;

    private zzeq() {
    }

    public static zzeq zza() {
        throw null;
    }

    private static final void zzd(zzep zzep, Object obj) {
        boolean z;
        zzho zzb2 = zzep.zzb();
        byte[] bArr = zzfa.zzd;
        obj.getClass();
        zzho zzho = zzho.DOUBLE;
        zzhp zzhp = zzhp.INT;
        switch (zzb2.zza().ordinal()) {
            case 0:
                z = obj instanceof Integer;
                break;
            case 1:
                z = obj instanceof Long;
                break;
            case 2:
                z = obj instanceof Float;
                break;
            case 3:
                z = obj instanceof Double;
                break;
            case 4:
                z = obj instanceof Boolean;
                break;
            case 5:
                z = obj instanceof String;
                break;
            case 6:
                if ((obj instanceof zzef) || (obj instanceof byte[])) {
                    return;
                }
            case 7:
                if ((obj instanceof Integer) || (obj instanceof zzex)) {
                    return;
                }
            case 8:
                if ((obj instanceof zzfx) || (obj instanceof zzfc)) {
                    return;
                }
        }
        if (z) {
            return;
        }
        throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", new Object[]{Integer.valueOf(zzep.zza()), zzep.zzb().zza(), obj.getClass().getName()}));
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzeq zzeq = new zzeq();
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry zzg = this.zza.zzg(i);
            zzeq.zzc((zzep) zzg.getKey(), zzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzc()) {
            zzeq.zzc((zzep) entry.getKey(), entry.getValue());
        }
        zzeq.zzd = this.zzd;
        return zzeq;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzeq)) {
            return false;
        }
        return this.zza.equals(((zzeq) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final void zzb() {
        if (!this.zzc) {
            for (int i = 0; i < this.zza.zzb(); i++) {
                Map.Entry zzg = this.zza.zzg(i);
                if (zzg.getValue() instanceof zzev) {
                    ((zzev) zzg.getValue()).zzi();
                }
            }
            this.zza.zza();
            this.zzc = true;
        }
    }

    public final void zzc(zzep zzep, Object obj) {
        if (!zzep.zzc()) {
            zzd(zzep, obj);
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                zzd(zzep, arrayList.get(i));
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzfc) {
            this.zzd = true;
        }
        this.zza.put(zzep, obj);
    }

    private zzeq(boolean z) {
        zzb();
        zzb();
    }
}
