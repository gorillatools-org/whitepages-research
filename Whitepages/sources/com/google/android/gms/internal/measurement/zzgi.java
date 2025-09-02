package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzgi extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzgi zzb;
    private int zzd;
    private zzmj zze = zzmd.zzcn();
    private zzmj zzf = zzmd.zzcn();
    private zzmj zzg = zzmd.zzcn();
    private boolean zzh;
    private zzmj zzi = zzmd.zzcn();

    static {
        zzgi zzgi = new zzgi();
        zzb = zzgi;
        zzmd.zzct(zzgi.class, zzgi);
    }

    private zzgi() {
    }

    public static zzgi zzb() {
        return zzb;
    }

    public final List zzc() {
        return this.zzg;
    }

    public final List zzd() {
        return this.zze;
    }

    public final List zze() {
        return this.zzf;
    }

    public final List zzf() {
        return this.zzi;
    }

    public final boolean zzg() {
        return this.zzh;
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
            return zzmd.zzcq(zzb, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0004\u0000\u0001\u001b\u0002\u001b\u0003\u001b\u0004ဇ\u0000\u0005\u001b", new Object[]{"zzd", "zze", zzfz.class, "zzf", zzgb.class, "zzg", zzgh.class, "zzh", "zzi", zzfz.class});
        } else if (i2 == 3) {
            return new zzgi();
        } else {
            if (i2 == 4) {
                return new zzfx((zzgz) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }
}
