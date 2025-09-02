package com.google.android.gms.internal.measurement;

public final class zzqq implements zzqp {
    public static final zzki zza;
    public static final zzki zzb;
    public static final zzki zzc;
    public static final zzki zzd;
    public static final zzki zze;
    public static final zzki zzf;

    static {
        zzkf zza2 = new zzkf(zzjx.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zzf("measurement.test.boolean_flag", false);
        zzb = zza2.zzd("measurement.test.cached_long_flag", -1);
        zzc = zza2.zzc("measurement.test.double_flag", -3.0d);
        zzd = zza2.zzd("measurement.test.int_flag", -2);
        zze = zza2.zzd("measurement.test.long_flag", -1);
        zzf = zza2.zze("measurement.test.string_flag", "---");
    }

    public final double zza() {
        return ((Double) zzc.zzb()).doubleValue();
    }

    public final long zzb() {
        return ((Long) zzb.zzb()).longValue();
    }

    public final long zzc() {
        return ((Long) zzd.zzb()).longValue();
    }

    public final long zzd() {
        return ((Long) zze.zzb()).longValue();
    }

    public final String zze() {
        return (String) zzf.zzb();
    }

    public final boolean zzf() {
        return ((Boolean) zza.zzb()).booleanValue();
    }
}
