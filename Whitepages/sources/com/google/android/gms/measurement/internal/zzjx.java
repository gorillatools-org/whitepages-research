package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.util.EnumMap;
import java.util.Map;

public final class zzjx {
    public static final zzjx zza = new zzjx((Boolean) null, (Boolean) null, 100);
    private final EnumMap zzb;
    private final int zzc;

    public zzjx(Boolean bool, Boolean bool2, int i) {
        EnumMap enumMap = new EnumMap(zzjw.class);
        this.zzb = enumMap;
        enumMap.put(zzjw.AD_STORAGE, zzh((Boolean) null));
        enumMap.put(zzjw.ANALYTICS_STORAGE, zzh((Boolean) null));
        this.zzc = i;
    }

    static char zza(zzju zzju) {
        if (zzju == null) {
            return '-';
        }
        int ordinal = zzju.ordinal();
        if (ordinal == 1) {
            return '+';
        }
        if (ordinal != 2) {
            return ordinal != 3 ? '-' : '1';
        }
        return '0';
    }

    static zzju zzd(String str) {
        if (str == null) {
            return zzju.UNINITIALIZED;
        }
        if (str.equals("granted")) {
            return zzju.GRANTED;
        }
        if (str.equals("denied")) {
            return zzju.DENIED;
        }
        return zzju.UNINITIALIZED;
    }

    static zzju zzh(Boolean bool) {
        if (bool == null) {
            return zzju.UNINITIALIZED;
        }
        if (bool.booleanValue()) {
            return zzju.GRANTED;
        }
        return zzju.DENIED;
    }

    public static zzjx zzi(Bundle bundle, int i) {
        if (bundle == null) {
            return new zzjx((Boolean) null, (Boolean) null, i);
        }
        EnumMap enumMap = new EnumMap(zzjw.class);
        for (zzjw zzjw : zzjv.STORAGE.zzd) {
            enumMap.put(zzjw, zzd(bundle.getString(zzjw.zze)));
        }
        return new zzjx(enumMap, i);
    }

    public static zzjx zzj(zzju zzju, zzju zzju2, int i) {
        EnumMap enumMap = new EnumMap(zzjw.class);
        enumMap.put(zzjw.AD_STORAGE, zzju);
        enumMap.put(zzjw.ANALYTICS_STORAGE, zzju2);
        return new zzjx(enumMap, -10);
    }

    public static zzjx zzk(String str, int i) {
        EnumMap enumMap = new EnumMap(zzjw.class);
        zzjw[] zzb2 = zzjv.STORAGE.zzb();
        for (int i2 = 0; i2 < zzb2.length; i2++) {
            String str2 = str == null ? "" : str;
            zzjw zzjw = zzb2[i2];
            int i3 = i2 + 2;
            if (i3 < str2.length()) {
                enumMap.put(zzjw, zzg(str2.charAt(i3)));
            } else {
                enumMap.put(zzjw, zzju.UNINITIALIZED);
            }
        }
        return new zzjx(enumMap, i);
    }

    static String zzn(int i) {
        return i != -30 ? i != -20 ? i != -10 ? i != 0 ? i != 30 ? i != 90 ? i != 100 ? "OTHER" : "UNKNOWN" : "REMOTE_CONFIG" : "1P_INIT" : "1P_API" : "MANIFEST" : "API" : "TCF";
    }

    static String zzo(zzju zzju) {
        int ordinal = zzju.ordinal();
        if (ordinal == 2) {
            return "denied";
        }
        if (ordinal != 3) {
            return null;
        }
        return "granted";
    }

    public static boolean zzs(int i, int i2) {
        int i3 = -30;
        if (i == -20) {
            if (i2 == -30) {
                return true;
            }
            i = -20;
        }
        if (i != -30) {
            i3 = i;
        } else if (i2 == -20) {
            return true;
        }
        return i3 == i2 || i < i2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzjx)) {
            return false;
        }
        zzjx zzjx = (zzjx) obj;
        for (zzjw zzjw : zzjv.STORAGE.zzd) {
            if (this.zzb.get(zzjw) != zzjx.zzb.get(zzjw)) {
                return false;
            }
        }
        if (this.zzc == zzjx.zzc) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzc * 17;
        for (zzju hashCode : this.zzb.values()) {
            i = (i * 31) + hashCode.hashCode();
        }
        return i;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("source=");
        sb.append(zzn(this.zzc));
        for (zzjw zzjw : zzjv.STORAGE.zzd) {
            sb.append(",");
            sb.append(zzjw.zze);
            sb.append("=");
            zzju zzju = (zzju) this.zzb.get(zzjw);
            if (zzju == null) {
                zzju = zzju.UNINITIALIZED;
            }
            sb.append(zzju);
        }
        return sb.toString();
    }

    public final int zzb() {
        return this.zzc;
    }

    public final Bundle zzc() {
        Bundle bundle = new Bundle();
        for (Map.Entry entry : this.zzb.entrySet()) {
            String zzo = zzo((zzju) entry.getValue());
            if (zzo != null) {
                bundle.putString(((zzjw) entry.getKey()).zze, zzo);
            }
        }
        return bundle;
    }

    public final zzju zze() {
        zzju zzju = (zzju) this.zzb.get(zzjw.AD_STORAGE);
        return zzju == null ? zzju.UNINITIALIZED : zzju;
    }

    public final zzju zzf() {
        zzju zzju = (zzju) this.zzb.get(zzjw.ANALYTICS_STORAGE);
        return zzju == null ? zzju.UNINITIALIZED : zzju;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0047 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzjx zzl(com.google.android.gms.measurement.internal.zzjx r9) {
        /*
            r8 = this;
            java.util.EnumMap r0 = new java.util.EnumMap
            java.lang.Class<com.google.android.gms.measurement.internal.zzjw> r1 = com.google.android.gms.measurement.internal.zzjw.class
            r0.<init>(r1)
            com.google.android.gms.measurement.internal.zzjv r1 = com.google.android.gms.measurement.internal.zzjv.STORAGE
            com.google.android.gms.measurement.internal.zzjw[] r1 = r1.zzd
            int r2 = r1.length
            r3 = 0
        L_0x000f:
            if (r3 >= r2) goto L_0x004a
            r4 = r1[r3]
            java.util.EnumMap r5 = r8.zzb
            java.lang.Object r5 = r5.get(r4)
            com.google.android.gms.measurement.internal.zzju r5 = (com.google.android.gms.measurement.internal.zzju) r5
            java.util.EnumMap r6 = r9.zzb
            java.lang.Object r6 = r6.get(r4)
            com.google.android.gms.measurement.internal.zzju r6 = (com.google.android.gms.measurement.internal.zzju) r6
            if (r5 != 0) goto L_0x0026
            goto L_0x0033
        L_0x0026:
            if (r6 == 0) goto L_0x0042
            com.google.android.gms.measurement.internal.zzju r7 = com.google.android.gms.measurement.internal.zzju.UNINITIALIZED
            if (r5 != r7) goto L_0x002d
            goto L_0x0033
        L_0x002d:
            if (r6 == r7) goto L_0x0042
            com.google.android.gms.measurement.internal.zzju r7 = com.google.android.gms.measurement.internal.zzju.POLICY
            if (r5 != r7) goto L_0x0035
        L_0x0033:
            r5 = r6
            goto L_0x0042
        L_0x0035:
            if (r6 == r7) goto L_0x0042
            com.google.android.gms.measurement.internal.zzju r7 = com.google.android.gms.measurement.internal.zzju.DENIED
            if (r5 == r7) goto L_0x0041
            if (r6 != r7) goto L_0x003e
            goto L_0x0041
        L_0x003e:
            com.google.android.gms.measurement.internal.zzju r5 = com.google.android.gms.measurement.internal.zzju.GRANTED
            goto L_0x0042
        L_0x0041:
            r5 = r7
        L_0x0042:
            if (r5 == 0) goto L_0x0047
            r0.put(r4, r5)
        L_0x0047:
            int r3 = r3 + 1
            goto L_0x000f
        L_0x004a:
            com.google.android.gms.measurement.internal.zzjx r9 = new com.google.android.gms.measurement.internal.zzjx
            r1 = 100
            r9.<init>(r0, r1)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzjx.zzl(com.google.android.gms.measurement.internal.zzjx):com.google.android.gms.measurement.internal.zzjx");
    }

    public final zzjx zzm(zzjx zzjx) {
        EnumMap enumMap = new EnumMap(zzjw.class);
        for (zzjw zzjw : zzjv.STORAGE.zzd) {
            zzju zzju = (zzju) this.zzb.get(zzjw);
            if (zzju == zzju.UNINITIALIZED) {
                zzju = (zzju) zzjx.zzb.get(zzjw);
            }
            if (zzju != null) {
                enumMap.put(zzjw, zzju);
            }
        }
        return new zzjx(enumMap, this.zzc);
    }

    public final String zzp() {
        int ordinal;
        StringBuilder sb = new StringBuilder("G1");
        for (zzjw zzjw : zzjv.STORAGE.zzb()) {
            zzju zzju = (zzju) this.zzb.get(zzjw);
            char c = '-';
            if (!(zzju == null || (ordinal = zzju.ordinal()) == 0)) {
                if (ordinal != 1) {
                    if (ordinal == 2) {
                        c = '0';
                    } else if (ordinal != 3) {
                    }
                }
                c = '1';
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public final String zzq() {
        StringBuilder sb = new StringBuilder("G1");
        for (zzjw zzjw : zzjv.STORAGE.zzb()) {
            sb.append(zza((zzju) this.zzb.get(zzjw)));
        }
        return sb.toString();
    }

    public final boolean zzr(zzjw zzjw) {
        return ((zzju) this.zzb.get(zzjw)) != zzju.DENIED;
    }

    public final boolean zzt() {
        for (zzju zzju : this.zzb.values()) {
            if (zzju != zzju.UNINITIALIZED) {
                return true;
            }
        }
        return false;
    }

    public final boolean zzu(zzjx zzjx) {
        EnumMap enumMap = this.zzb;
        for (zzjw zzjw : (zzjw[]) enumMap.keySet().toArray(new zzjw[0])) {
            zzju zzju = (zzju) enumMap.get(zzjw);
            zzju zzju2 = (zzju) zzjx.zzb.get(zzjw);
            zzju zzju3 = zzju.DENIED;
            if (zzju == zzju3 && zzju2 != zzju3) {
                return true;
            }
        }
        return false;
    }

    private zzjx(EnumMap enumMap, int i) {
        EnumMap enumMap2 = new EnumMap(zzjw.class);
        this.zzb = enumMap2;
        enumMap2.putAll(enumMap);
        this.zzc = i;
    }

    static zzju zzg(char c) {
        if (c == '+') {
            return zzju.POLICY;
        }
        if (c == '0') {
            return zzju.DENIED;
        }
        if (c != '1') {
            return zzju.UNINITIALIZED;
        }
        return zzju.GRANTED;
    }
}
