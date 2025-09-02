package com.google.android.gms.internal.measurement;

public final class zzgu extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzgu zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";

    static {
        zzgu zzgu = new zzgu();
        zzb = zzgu;
        zzmd.zzct(zzgu.class, zzgu);
    }

    private zzgu() {
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzmd.zzcq(zzb, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (i2 == 3) {
            return new zzgu();
        } else {
            if (i2 == 4) {
                return new zzgt((zzgz) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }
}
