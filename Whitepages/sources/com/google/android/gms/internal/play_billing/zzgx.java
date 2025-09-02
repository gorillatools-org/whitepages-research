package com.google.android.gms.internal.play_billing;

public final class zzgx extends zzcs implements zzed {
    /* access modifiers changed from: private */
    public static final zzgx zzb;
    private int zzd;
    private boolean zze;
    private boolean zzf;

    static {
        zzgx zzgx = new zzgx();
        zzb = zzgx;
        zzcs.zzt(zzgx.class, zzgx);
    }

    private zzgx() {
    }

    /* access modifiers changed from: protected */
    public final Object zzx(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzcs.zzq(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (i2 == 3) {
            return new zzgx();
        } else {
            if (i2 == 4) {
                return new zzgw((zzgv) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
