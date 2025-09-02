package com.google.android.gms.measurement.internal;

import java.util.EnumMap;

final class zzao {
    private final EnumMap zza;

    zzao() {
        this.zza = new EnumMap(zzjw.class);
    }

    public static zzao zzb(String str) {
        EnumMap enumMap = new EnumMap(zzjw.class);
        if (str.length() >= zzjw.values().length) {
            int i = 0;
            if (str.charAt(0) == '1') {
                zzjw[] values = zzjw.values();
                int length = values.length;
                int i2 = 1;
                while (i < length) {
                    enumMap.put(values[i], zzan.zzb(str.charAt(i2)));
                    i++;
                    i2++;
                }
                return new zzao(enumMap);
            }
        }
        return new zzao();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("1");
        for (zzjw zzjw : zzjw.values()) {
            zzan zzan = (zzan) this.zza.get(zzjw);
            if (zzan == null) {
                zzan = zzan.UNSET;
            }
            sb.append(zzan.zzl);
        }
        return sb.toString();
    }

    public final zzan zza(zzjw zzjw) {
        zzan zzan = (zzan) this.zza.get(zzjw);
        return zzan == null ? zzan.UNSET : zzan;
    }

    public final void zzc(zzjw zzjw, int i) {
        zzan zzan = zzan.UNSET;
        if (i != -30) {
            if (i != -20) {
                if (i == -10) {
                    zzan = zzan.MANIFEST;
                } else if (i != 0) {
                    if (i == 30) {
                        zzan = zzan.INITIALIZATION;
                    }
                }
            }
            zzan = zzan.API;
        } else {
            zzan = zzan.TCF;
        }
        this.zza.put(zzjw, zzan);
    }

    public final void zzd(zzjw zzjw, zzan zzan) {
        this.zza.put(zzjw, zzan);
    }

    private zzao(EnumMap enumMap) {
        EnumMap enumMap2 = new EnumMap(zzjw.class);
        this.zza = enumMap2;
        enumMap2.putAll(enumMap);
    }
}
