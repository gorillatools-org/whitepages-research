package com.google.android.gms.internal.play_billing;

public final class zzho extends zzcs implements zzed {
    /* access modifiers changed from: private */
    public static final zzho zzb;
    private int zzd;
    private int zze;

    static {
        zzho zzho = new zzho();
        zzb = zzho;
        zzcs.zzt(zzho.class, zzho);
    }

    private zzho() {
    }

    /* access modifiers changed from: protected */
    public final Object zzx(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzcs.zzq(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001င\u0000", new Object[]{"zzd", "zze"});
        } else if (i2 == 3) {
            return new zzho();
        } else {
            if (i2 == 4) {
                return new zzhn((zzhm) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
