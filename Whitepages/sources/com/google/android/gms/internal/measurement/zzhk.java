package com.google.android.gms.internal.measurement;

public final class zzhk extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzhk zzb;
    private int zzd;
    private int zze;
    private long zzf;

    static {
        zzhk zzhk = new zzhk();
        zzb = zzhk;
        zzmd.zzct(zzhk.class, zzhk);
    }

    private zzhk() {
    }

    public static zzhj zzc() {
        return (zzhj) zzb.zzcg();
    }

    static /* synthetic */ void zze(zzhk zzhk, long j) {
        zzhk.zzd |= 2;
        zzhk.zzf = j;
    }

    static /* synthetic */ void zzf(zzhk zzhk, int i) {
        zzhk.zzd |= 1;
        zzhk.zze = i;
    }

    public final int zza() {
        return this.zze;
    }

    public final long zzb() {
        return this.zzf;
    }

    public final boolean zzg() {
        return (this.zzd & 2) != 0;
    }

    public final boolean zzh() {
        return (this.zzd & 1) != 0;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzmd.zzcq(zzb, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002ဂ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (i2 == 3) {
            return new zzhk();
        } else {
            if (i2 == 4) {
                return new zzhj((zzip) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }
}
