package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public final class zzba {
    public static final zzba zza = new zzba((Boolean) null, 100, (Boolean) null, (String) null);
    private final int zzb;
    private final String zzc = zzl();
    private final Boolean zzd;
    private final String zze;
    private final EnumMap zzf;

    zzba(Boolean bool, int i, Boolean bool2, String str) {
        EnumMap enumMap = new EnumMap(zzjw.class);
        this.zzf = enumMap;
        enumMap.put(zzjw.AD_USER_DATA, zzjx.zzh(bool));
        this.zzb = i;
        this.zzd = bool2;
        this.zze = str;
    }

    public static zzba zzc(Bundle bundle, int i) {
        Boolean bool = null;
        if (bundle == null) {
            return new zzba((Boolean) null, i, (Boolean) null, (String) null);
        }
        EnumMap enumMap = new EnumMap(zzjw.class);
        for (zzjw zzjw : zzjv.DMA.zzb()) {
            enumMap.put(zzjw, zzjx.zzd(bundle.getString(zzjw.zze)));
        }
        if (bundle.containsKey("is_dma_region")) {
            bool = Boolean.valueOf(bundle.getString("is_dma_region"));
        }
        return new zzba(enumMap, i, bool, bundle.getString("cps_display_str"));
    }

    static zzba zzd(zzju zzju, int i) {
        EnumMap enumMap = new EnumMap(zzjw.class);
        enumMap.put(zzjw.AD_USER_DATA, zzju);
        return new zzba(enumMap, -10, (Boolean) null, (String) null);
    }

    public static zzba zze(String str) {
        if (str == null || str.length() <= 0) {
            return zza;
        }
        String[] split = str.split(":");
        int parseInt = Integer.parseInt(split[0]);
        EnumMap enumMap = new EnumMap(zzjw.class);
        zzjw[] zzb2 = zzjv.DMA.zzb();
        int length = zzb2.length;
        int i = 1;
        int i2 = 0;
        while (i2 < length) {
            enumMap.put(zzb2[i2], zzjx.zzg(split[i].charAt(0)));
            i2++;
            i++;
        }
        return new zzba(enumMap, parseInt, (Boolean) null, (String) null);
    }

    public static Boolean zzg(Bundle bundle) {
        zzju zzd2;
        if (bundle == null || (zzd2 = zzjx.zzd(bundle.getString("ad_personalization"))) == null) {
            return null;
        }
        int ordinal = zzd2.ordinal();
        if (ordinal == 2) {
            return Boolean.FALSE;
        }
        if (ordinal != 3) {
            return null;
        }
        return Boolean.TRUE;
    }

    private final String zzl() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.zzb);
        for (zzjw zzjw : zzjv.DMA.zzb()) {
            sb.append(":");
            sb.append(zzjx.zza((zzju) this.zzf.get(zzjw)));
        }
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzba)) {
            return false;
        }
        zzba zzba = (zzba) obj;
        if (!this.zzc.equalsIgnoreCase(zzba.zzc) || !Objects.equals(this.zzd, zzba.zzd)) {
            return false;
        }
        return Objects.equals(this.zze, zzba.zze);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("source=");
        sb.append(zzjx.zzn(this.zzb));
        for (zzjw zzjw : zzjv.DMA.zzb()) {
            sb.append(",");
            sb.append(zzjw.zze);
            sb.append("=");
            zzju zzju = (zzju) this.zzf.get(zzjw);
            if (zzju == null) {
                sb.append("uninitialized");
            } else {
                int ordinal = zzju.ordinal();
                if (ordinal == 0) {
                    sb.append("uninitialized");
                } else if (ordinal == 1) {
                    sb.append("eu_consent_policy");
                } else if (ordinal == 2) {
                    sb.append("denied");
                } else if (ordinal == 3) {
                    sb.append("granted");
                }
            }
        }
        Boolean bool = this.zzd;
        if (bool != null) {
            sb.append(",isDmaRegion=");
            sb.append(bool);
        }
        String str = this.zze;
        if (str != null) {
            sb.append(",cpsDisplayStr=");
            sb.append(str);
        }
        return sb.toString();
    }

    public final int zza() {
        return this.zzb;
    }

    public final Bundle zzb() {
        Bundle bundle = new Bundle();
        for (Map.Entry entry : this.zzf.entrySet()) {
            String zzo = zzjx.zzo((zzju) entry.getValue());
            if (zzo != null) {
                bundle.putString(((zzjw) entry.getKey()).zze, zzo);
            }
        }
        Boolean bool = this.zzd;
        if (bool != null) {
            bundle.putString("is_dma_region", bool.toString());
        }
        String str = this.zze;
        if (str != null) {
            bundle.putString("cps_display_str", str);
        }
        return bundle;
    }

    public final zzju zzf() {
        zzju zzju = (zzju) this.zzf.get(zzjw.AD_USER_DATA);
        return zzju == null ? zzju.UNINITIALIZED : zzju;
    }

    public final Boolean zzh() {
        return this.zzd;
    }

    public final String zzi() {
        return this.zze;
    }

    public final String zzj() {
        return this.zzc;
    }

    public final boolean zzk() {
        for (zzju zzju : this.zzf.values()) {
            if (zzju != zzju.UNINITIALIZED) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i;
        int i2;
        Boolean bool = this.zzd;
        if (bool == null) {
            i = 3;
        } else {
            i = true != bool.booleanValue() ? 13 : 7;
        }
        String str = this.zze;
        if (str == null) {
            i2 = 17;
        } else {
            i2 = str.hashCode();
        }
        return this.zzc.hashCode() + (i * 29) + (i2 * 137);
    }

    private zzba(EnumMap enumMap, int i, Boolean bool, String str) {
        EnumMap enumMap2 = new EnumMap(zzjw.class);
        this.zzf = enumMap2;
        enumMap2.putAll(enumMap);
        this.zzb = i;
        this.zzd = bool;
        this.zze = str;
    }
}
