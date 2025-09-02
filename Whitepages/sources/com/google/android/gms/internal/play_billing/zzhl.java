package com.google.android.gms.internal.play_billing;

public final class zzhl extends zzcs implements zzed {
    /* access modifiers changed from: private */
    public static final zzhl zzb;
    private int zzd;
    private int zze;

    static {
        zzhl zzhl = new zzhl();
        zzb = zzhl;
        zzcs.zzt(zzhl.class, zzhl);
    }

    private zzhl() {
    }

    public static zzhl zzz() {
        return zzb;
    }

    /* access modifiers changed from: protected */
    public final Object zzx(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzcs.zzq(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001᠌\u0000", new Object[]{"zzd", "zze", zzhk.zza});
        } else if (i2 == 3) {
            return new zzhl();
        } else {
            if (i2 == 4) {
                return new zzhj((zzhi) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
