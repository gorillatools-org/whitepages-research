package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzie extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzie zzb;
    private int zzd;
    private int zze;
    private zzmi zzf = zzmd.zzcl();

    static {
        zzie zzie = new zzie();
        zzb = zzie;
        zzmd.zzct(zzie.class, zzie);
    }

    private zzie() {
    }

    public static zzid zzd() {
        return (zzid) zzb.zzcg();
    }

    static /* synthetic */ void zzg(zzie zzie, Iterable iterable) {
        zzmi zzmi = zzie.zzf;
        if (!zzmi.zzc()) {
            zzie.zzf = zzmd.zzcm(zzmi);
        }
        zzko.zzcc(iterable, zzie.zzf);
    }

    static /* synthetic */ void zzh(zzie zzie, int i) {
        zzie.zzd |= 1;
        zzie.zze = i;
    }

    public final int zza() {
        return this.zzf.size();
    }

    public final int zzb() {
        return this.zze;
    }

    public final long zzc(int i) {
        return this.zzf.zza(i);
    }

    public final List zzf() {
        return this.zzf;
    }

    public final boolean zzi() {
        return (this.zzd & 1) != 0;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzmd.zzcq(zzb, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001င\u0000\u0002\u0014", new Object[]{"zzd", "zze", "zzf"});
        } else if (i2 == 3) {
            return new zzie();
        } else {
            if (i2 == 4) {
                return new zzid((zzip) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }
}
