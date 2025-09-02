package com.google.android.gms.internal.measurement;

public final class zzim extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzim zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;

    static {
        zzim zzim = new zzim();
        zzb = zzim;
        zzmd.zzct(zzim.class, zzim);
    }

    private zzim() {
    }

    public static zzif zza() {
        return (zzif) zzb.zzcg();
    }

    public static zzim zzd() {
        return zzb;
    }

    static /* synthetic */ void zze(zzim zzim, zzih zzih) {
        zzim.zzf = zzih.zza();
        zzim.zzd |= 2;
    }

    static /* synthetic */ void zzh(zzim zzim, int i) {
        zzim.zzg = i - 1;
        zzim.zzd |= 4;
    }

    static /* synthetic */ void zzi(zzim zzim, int i) {
        zzim.zze = i - 1;
        zzim.zzd |= 1;
    }

    public final zzih zzb() {
        zzih zzb2 = zzih.zzb(this.zzf);
        return zzb2 == null ? zzih.CLIENT_UPLOAD_ELIGIBILITY_UNKNOWN : zzb2;
    }

    public final int zzf() {
        int zza = zzij.zza(this.zzg);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }

    public final int zzg() {
        int zza = zzil.zza(this.zze);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzmd.zzcq(zzb, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003᠌\u0002", new Object[]{"zzd", "zze", zzik.zza, "zzf", zzig.zza, "zzg", zzii.zza});
        } else if (i2 == 3) {
            return new zzim();
        } else {
            if (i2 == 4) {
                return new zzif((zzip) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }
}
