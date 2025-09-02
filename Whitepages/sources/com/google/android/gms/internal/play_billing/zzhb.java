package com.google.android.gms.internal.play_billing;

public final class zzhb extends zzcs implements zzed {
    /* access modifiers changed from: private */
    public static final zzhb zzb;
    private int zzd;
    private int zze;

    static {
        zzhb zzhb = new zzhb();
        zzb = zzhb;
        zzcs.zzt(zzhb.class, zzhb);
    }

    private zzhb() {
    }

    static /* synthetic */ void zzA(zzhb zzhb, int i) {
        zzhb.zze = i - 1;
        zzhb.zzd |= 1;
    }

    public static zzgz zzy() {
        return (zzgz) zzb.zzg();
    }

    /* access modifiers changed from: protected */
    public final Object zzx(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzcs.zzq(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001᠌\u0000", new Object[]{"zzd", "zze", zzha.zza});
        } else if (i2 == 3) {
            return new zzhb();
        } else {
            if (i2 == 4) {
                return new zzgz((zzgy) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
