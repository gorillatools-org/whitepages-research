package com.google.android.gms.internal.measurement;

public final class zzfp extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzfp zzb;
    private int zzd;
    private int zze;
    private boolean zzf;
    private String zzg = "";
    private String zzh = "";
    private String zzi = "";

    static {
        zzfp zzfp = new zzfp();
        zzb = zzfp;
        zzmd.zzct(zzfp.class, zzfp);
    }

    private zzfp() {
    }

    public static zzfp zzb() {
        return zzb;
    }

    public final String zzc() {
        return this.zzg;
    }

    public final String zzd() {
        return this.zzi;
    }

    public final String zze() {
        return this.zzh;
    }

    public final boolean zzf() {
        return this.zzf;
    }

    public final boolean zzg() {
        return (this.zzd & 1) != 0;
    }

    public final boolean zzh() {
        return (this.zzd & 4) != 0;
    }

    public final boolean zzi() {
        return (this.zzd & 2) != 0;
    }

    public final boolean zzj() {
        return (this.zzd & 16) != 0;
    }

    public final boolean zzk() {
        return (this.zzd & 8) != 0;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzmd.zzcq(zzb, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001᠌\u0000\u0002ဇ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004", new Object[]{"zzd", "zze", zzfn.zza, "zzf", "zzg", "zzh", "zzi"});
        } else if (i2 == 3) {
            return new zzfp();
        } else {
            if (i2 == 4) {
                return new zzfm((zzfw) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }

    public final int zzm() {
        int zza = zzfo.zza(this.zze);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }
}
