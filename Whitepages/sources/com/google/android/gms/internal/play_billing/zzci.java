package com.google.android.gms.internal.play_billing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzci {
    private static final zzci zzb = new zzci(true);
    final zzfb zza = new zzer(16);
    private boolean zzc;
    private boolean zzd;

    private zzci() {
    }

    static int zza(zzfv zzfv, int i, Object obj) {
        zzby.zzw(i << 3);
        if (zzfv.GROUP == null) {
            byte[] bArr = zzda.zzd;
            if (((zzec) obj) instanceof zzaz) {
                throw null;
            }
        }
        zzfw zzfw = zzfw.INT;
        throw null;
    }

    public static int zzb(zzch zzch, Object obj) {
        zzfv zzd2 = zzch.zzd();
        int zza2 = zzch.zza();
        if (!zzch.zzg()) {
            return zza(zzd2, zza2, obj);
        }
        List<Object> list = (List) obj;
        int i = 0;
        if (!zzch.zzf()) {
            for (Object zza3 : list) {
                i += zza(zzd2, zza2, zza3);
            }
            return i;
        } else if (list.isEmpty()) {
            return 0;
        } else {
            Iterator it = list.iterator();
            if (!it.hasNext()) {
                return zzby.zzw(zza2 << 3) + zzby.zzw(0);
            }
            it.next();
            zzfv zzfv = zzfv.DOUBLE;
            zzfw zzfw = zzfw.INT;
            throw null;
        }
    }

    public static zzci zzd() {
        return zzb;
    }

    private static Object zzk(Object obj) {
        if (obj instanceof zzeh) {
            return ((zzeh) obj).zzd();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    private final void zzl(Map.Entry entry) {
        Object obj;
        zzch zzch = (zzch) entry.getKey();
        Object value = entry.getValue();
        boolean z = value instanceof zzdh;
        if (zzch.zzg()) {
            if (!z) {
                Object zze = zze(zzch);
                if (zze == null) {
                    zze = new ArrayList();
                }
                for (Object zzk : (List) value) {
                    ((List) zze).add(zzk(zzk));
                }
                this.zza.put(zzch, zze);
                return;
            }
            throw new IllegalStateException("Lazy fields can not be repeated");
        } else if (zzch.zze() == zzfw.MESSAGE) {
            Object zze2 = zze(zzch);
            if (zze2 == null) {
                this.zza.put(zzch, zzk(value));
                if (z) {
                    this.zzd = true;
                }
            } else if (!z) {
                if (zze2 instanceof zzeh) {
                    obj = zzch.zzc((zzeh) zze2, (zzeh) value);
                } else {
                    obj = zzch.zzb(((zzec) zze2).zzF(), (zzec) value).zzf();
                }
                this.zza.put(zzch, obj);
            } else {
                throw null;
            }
        } else if (!z) {
            this.zza.put(zzch, zzk(value));
        } else {
            throw new IllegalStateException("Lazy fields must be message-valued");
        }
    }

    private static boolean zzm(Map.Entry entry) {
        zzch zzch = (zzch) entry.getKey();
        if (zzch.zze() != zzfw.MESSAGE) {
            return true;
        }
        if (!zzch.zzg()) {
            return zzn(entry.getValue());
        }
        for (Object zzn : (List) entry.getValue()) {
            if (!zzn(zzn)) {
                return false;
            }
        }
        return true;
    }

    private static boolean zzn(Object obj) {
        if (obj instanceof zzed) {
            return ((zzed) obj).zzk();
        }
        if (obj instanceof zzdh) {
            return true;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }

    private static final int zzo(Map.Entry entry) {
        int i;
        int zzw;
        int zzw2;
        int zzf;
        int zzw3;
        zzch zzch = (zzch) entry.getKey();
        Object value = entry.getValue();
        if (zzch.zze() != zzfw.MESSAGE || zzch.zzg() || zzch.zzf()) {
            return zzb(zzch, value);
        }
        if (value instanceof zzdh) {
            int zza2 = ((zzch) entry.getKey()).zza();
            int zzw4 = zzby.zzw(8);
            i = zzw4 + zzw4;
            zzw = zzby.zzw(16) + zzby.zzw(zza2);
            zzw2 = zzby.zzw(24);
            zzf = ((zzdh) value).zza();
            zzw3 = zzby.zzw(zzf);
        } else {
            int zza3 = ((zzch) entry.getKey()).zza();
            int zzw5 = zzby.zzw(8);
            i = zzw5 + zzw5;
            zzw = zzby.zzw(16) + zzby.zzw(zza3);
            zzw2 = zzby.zzw(24);
            zzf = ((zzec) value).zzf();
            zzw3 = zzby.zzw(zzf);
        }
        return i + zzw + zzw2 + zzw3 + zzf;
    }

    private static final void zzp(zzch zzch, Object obj) {
        boolean z;
        zzfv zzd2 = zzch.zzd();
        byte[] bArr = zzda.zzd;
        obj.getClass();
        zzfv zzfv = zzfv.DOUBLE;
        zzfw zzfw = zzfw.INT;
        switch (zzd2.zza().ordinal()) {
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
                if ((obj instanceof zzbq) || (obj instanceof byte[])) {
                    return;
                }
            case 7:
                if ((obj instanceof Integer) || (obj instanceof zzcu)) {
                    return;
                }
            case 8:
                if ((obj instanceof zzec) || (obj instanceof zzdh)) {
                    return;
                }
        }
        if (z) {
            return;
        }
        throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", new Object[]{Integer.valueOf(zzch.zza()), zzch.zzd().zza(), obj.getClass().getName()}));
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzci zzci = new zzci();
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry zzg = this.zza.zzg(i);
            zzci.zzi((zzch) zzg.getKey(), zzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzc()) {
            zzci.zzi((zzch) entry.getKey(), entry.getValue());
        }
        zzci.zzd = this.zzd;
        return zzci;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzci)) {
            return false;
        }
        return this.zza.equals(((zzci) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final int zzc() {
        int i = 0;
        for (int i2 = 0; i2 < this.zza.zzb(); i2++) {
            i += zzo(this.zza.zzg(i2));
        }
        for (Map.Entry zzo : this.zza.zzc()) {
            i += zzo(zzo);
        }
        return i;
    }

    public final Object zze(zzch zzch) {
        Object obj = this.zza.get(zzch);
        if (!(obj instanceof zzdh)) {
            return obj;
        }
        throw null;
    }

    public final Iterator zzf() {
        if (this.zzd) {
            return new zzdg(this.zza.entrySet().iterator());
        }
        return this.zza.entrySet().iterator();
    }

    public final void zzg() {
        if (!this.zzc) {
            for (int i = 0; i < this.zza.zzb(); i++) {
                Map.Entry zzg = this.zza.zzg(i);
                if (zzg.getValue() instanceof zzcs) {
                    ((zzcs) zzg.getValue()).zzr();
                }
            }
            this.zza.zza();
            this.zzc = true;
        }
    }

    public final void zzh(zzci zzci) {
        for (int i = 0; i < zzci.zza.zzb(); i++) {
            zzl(zzci.zza.zzg(i));
        }
        for (Map.Entry zzl : zzci.zza.zzc()) {
            zzl(zzl);
        }
    }

    public final void zzi(zzch zzch, Object obj) {
        if (!zzch.zzg()) {
            zzp(zzch, obj);
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                zzp(zzch, arrayList.get(i));
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzdh) {
            this.zzd = true;
        }
        this.zza.put(zzch, obj);
    }

    public final boolean zzj() {
        for (int i = 0; i < this.zza.zzb(); i++) {
            if (!zzm(this.zza.zzg(i))) {
                return false;
            }
        }
        for (Map.Entry zzm : this.zza.zzc()) {
            if (!zzm(zzm)) {
                return false;
            }
        }
        return true;
    }

    private zzci(boolean z) {
        zzg();
        zzg();
    }
}
