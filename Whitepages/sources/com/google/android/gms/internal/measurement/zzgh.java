package com.google.android.gms.internal.measurement;

public final class zzgh extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzgh zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";

    static {
        zzgh zzgh = new zzgh();
        zzb = zzgh;
        zzmd.zzct(zzgh.class, zzgh);
    }

    private zzgh() {
    }

    public final String zzb() {
        return this.zze;
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
            return new zzgh();
        } else {
            if (i2 == 4) {
                return new zzgg((zzgz) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }
}
