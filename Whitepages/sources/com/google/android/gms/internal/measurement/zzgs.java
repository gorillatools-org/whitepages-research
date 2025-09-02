package com.google.android.gms.internal.measurement;

public final class zzgs extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzgs zzb;
    private int zzd;
    private int zze = 14;
    private int zzf = 11;
    private int zzg = 60;

    static {
        zzgs zzgs = new zzgs();
        zzb = zzgs;
        zzmd.zzct(zzgs.class, zzgs);
    }

    private zzgs() {
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzmd.zzcq(zzb, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002", new Object[]{"zzd", "zze", "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzgs();
        } else {
            if (i2 == 4) {
                return new zzgr((zzgz) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }
}
