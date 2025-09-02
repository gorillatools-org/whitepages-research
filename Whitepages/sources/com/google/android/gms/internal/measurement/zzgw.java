package com.google.android.gms.internal.measurement;

public final class zzgw extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzgw zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";

    static {
        zzgw zzgw = new zzgw();
        zzb = zzgw;
        zzmd.zzct(zzgw.class, zzgw);
    }

    private zzgw() {
    }

    public final String zzb() {
        return this.zze;
    }

    public final String zzc() {
        return this.zzf;
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
            return new zzgw();
        } else {
            if (i2 == 4) {
                return new zzgv((zzgz) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }
}
