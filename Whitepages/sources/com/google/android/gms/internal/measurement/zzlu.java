package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzlu {
    private static final zzlu zzb = new zzlu(true);
    final zzoa zza = new zznv();
    private boolean zzc;
    private boolean zzd;

    private zzlu() {
    }

    static int zza(zzop zzop, int i, Object obj) {
        zzlk.zzz(i << 3);
        if (zzop.GROUP == null) {
            byte[] bArr = zzmk.zzb;
            if (((zznh) obj) instanceof zzkp) {
                throw null;
            }
        }
        zzoq zzoq = zzoq.INT;
        throw null;
    }

    public static int zzb(zzlt zzlt, Object obj) {
        zzop zzb2 = zzlt.zzb();
        int zza2 = zzlt.zza();
        if (!zzlt.zze()) {
            return zza(zzb2, zza2, obj);
        }
        List list = (List) obj;
        int size = list.size();
        if (!zzlt.zzd()) {
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zza(zzb2, zza2, list.get(i2));
            }
            return i;
        } else if (list.isEmpty()) {
            return 0;
        } else {
            if (size <= 0) {
                return zzlk.zzz(zza2 << 3) + zzlk.zzz(0);
            }
            list.get(0);
            zzop zzop = zzop.DOUBLE;
            zzoq zzoq = zzoq.INT;
            throw null;
        }
    }

    public static zzlu zzd() {
        return zzb;
    }

    private static boolean zzi(Map.Entry entry) {
        zzlt zzlt = (zzlt) entry.getKey();
        if (zzlt.zzc() != zzoq.MESSAGE) {
            return true;
        }
        if (!zzlt.zze()) {
            return zzj(entry.getValue());
        }
        List list = (List) entry.getValue();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!zzj(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean zzj(Object obj) {
        if (obj instanceof zzni) {
            return ((zzni) obj).zzcD();
        }
        if (obj instanceof zzmr) {
            return true;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }

    private static final int zzk(Map.Entry entry) {
        int i;
        int zzz;
        int zzz2;
        int zzcf;
        int zzz3;
        zzlt zzlt = (zzlt) entry.getKey();
        Object value = entry.getValue();
        if (zzlt.zzc() != zzoq.MESSAGE || zzlt.zze() || zzlt.zzd()) {
            return zzb(zzlt, value);
        }
        if (value instanceof zzmr) {
            int zza2 = ((zzlt) entry.getKey()).zza();
            int zzz4 = zzlk.zzz(8);
            i = zzz4 + zzz4;
            zzz = zzlk.zzz(16) + zzlk.zzz(zza2);
            zzz2 = zzlk.zzz(24);
            zzcf = ((zzmr) value).zza();
            zzz3 = zzlk.zzz(zzcf);
        } else {
            int zza3 = ((zzlt) entry.getKey()).zza();
            int zzz5 = zzlk.zzz(8);
            i = zzz5 + zzz5;
            zzz = zzlk.zzz(16) + zzlk.zzz(zza3);
            zzz2 = zzlk.zzz(24);
            zzcf = ((zznh) value).zzcf();
            zzz3 = zzlk.zzz(zzcf);
        }
        return i + zzz + zzz2 + zzz3 + zzcf;
    }

    private static final void zzl(zzlt zzlt, Object obj) {
        boolean z;
        zzop zzb2 = zzlt.zzb();
        byte[] bArr = zzmk.zzb;
        obj.getClass();
        zzop zzop = zzop.DOUBLE;
        zzoq zzoq = zzoq.INT;
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
                if ((obj instanceof zzld) || (obj instanceof byte[])) {
                    return;
                }
            case 7:
                if ((obj instanceof Integer) || (obj instanceof zzmf)) {
                    return;
                }
            case 8:
                if ((obj instanceof zznh) || (obj instanceof zzmr)) {
                    return;
                }
        }
        if (z) {
            return;
        }
        throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", new Object[]{Integer.valueOf(zzlt.zza()), zzlt.zzb().zza(), obj.getClass().getName()}));
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzlu zzlu = new zzlu();
        zzoa zzoa = this.zza;
        int zzc2 = zzoa.zzc();
        for (int i = 0; i < zzc2; i++) {
            Map.Entry zzg = zzoa.zzg(i);
            zzlu.zzg((zzlt) ((zznw) zzg).zza(), zzg.getValue());
        }
        for (Map.Entry entry : zzoa.zzd()) {
            zzlu.zzg((zzlt) entry.getKey(), entry.getValue());
        }
        zzlu.zzd = this.zzd;
        return zzlu;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzlu)) {
            return false;
        }
        return this.zza.equals(((zzlu) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final int zzc() {
        zzoa zzoa = this.zza;
        int zzc2 = zzoa.zzc();
        int i = 0;
        for (int i2 = 0; i2 < zzc2; i2++) {
            i += zzk(zzoa.zzg(i2));
        }
        for (Map.Entry zzk : zzoa.zzd()) {
            i += zzk(zzk);
        }
        return i;
    }

    public final Iterator zze() {
        zzoa zzoa = this.zza;
        if (zzoa.isEmpty()) {
            return Collections.emptyIterator();
        }
        if (this.zzd) {
            return new zzmp(zzoa.entrySet().iterator());
        }
        return zzoa.entrySet().iterator();
    }

    public final void zzf() {
        if (!this.zzc) {
            zzoa zzoa = this.zza;
            int zzc2 = zzoa.zzc();
            for (int i = 0; i < zzc2; i++) {
                Object value = zzoa.zzg(i).getValue();
                if (value instanceof zzmd) {
                    ((zzmd) value).zzcr();
                }
            }
            for (Map.Entry value2 : zzoa.zzd()) {
                Object value3 = value2.getValue();
                if (value3 instanceof zzmd) {
                    ((zzmd) value3).zzcr();
                }
            }
            zzoa.zza();
            this.zzc = true;
        }
    }

    public final void zzg(zzlt zzlt, Object obj) {
        if (!zzlt.zze()) {
            zzl(zzlt, obj);
        } else if (obj instanceof List) {
            List list = (List) obj;
            int size = list.size();
            ArrayList arrayList = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                Object obj2 = list.get(i);
                zzl(zzlt, obj2);
                arrayList.add(obj2);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzmr) {
            this.zzd = true;
        }
        this.zza.put(zzlt, obj);
    }

    public final boolean zzh() {
        zzoa zzoa = this.zza;
        int zzc2 = zzoa.zzc();
        for (int i = 0; i < zzc2; i++) {
            if (!zzi(zzoa.zzg(i))) {
                return false;
            }
        }
        for (Map.Entry zzi : zzoa.zzd()) {
            if (!zzi(zzi)) {
                return false;
            }
        }
        return true;
    }

    private zzlu(boolean z) {
        zzf();
        zzf();
    }
}
