package com.google.android.gms.internal.measurement;

public final class zzgy extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzgy zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private String zzg = "";
    private int zzh;
    private String zzi = "";

    static {
        zzgy zzgy = new zzgy();
        zzb = zzgy;
        zzmd.zzct(zzgy.class, zzgy);
    }

    private zzgy() {
    }

    public static zzgy zzc() {
        return zzb;
    }

    public final int zza() {
        return this.zzh;
    }

    public final String zzd() {
        return this.zzf;
    }

    public final String zze() {
        return this.zzi;
    }

    public final String zzf() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzmd.zzcq(zzb, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004င\u0003\u0005ဈ\u0004", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
        } else if (i2 == 3) {
            return new zzgy();
        } else {
            if (i2 == 4) {
                return new zzgx((zzgz) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }
}
