package com.google.android.gms.internal.play_billing;

public final class zzga extends zzcs implements zzed {
    /* access modifiers changed from: private */
    public static final zzga zzb;
    private int zzd;
    private int zze = 0;
    private Object zzf;
    private int zzg;
    private zzgk zzh;

    static {
        zzga zzga = new zzga();
        zzb = zzga;
        zzcs.zzt(zzga.class, zzga);
    }

    private zzga() {
    }

    public static zzga zzA(byte[] bArr, zzcd zzcd) throws zzdc {
        return (zzga) zzcs.zzm(zzb, bArr, zzcd);
    }

    static /* synthetic */ void zzB(zzga zzga, zzgk zzgk) {
        zzgk.getClass();
        zzga.zzh = zzgk;
        zzga.zzd |= 2;
    }

    static /* synthetic */ void zzC(zzga zzga, zzhb zzhb) {
        zzhb.getClass();
        zzga.zzf = zzhb;
        zzga.zze = 4;
    }

    static /* synthetic */ void zzD(zzga zzga, int i) {
        zzga.zzg = i - 1;
        zzga.zzd |= 1;
    }

    public static zzfz zzy() {
        return (zzfz) zzb.zzg();
    }

    /* access modifiers changed from: protected */
    public final Object zzx(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzcs.zzq(zzb, "\u0001\u0003\u0001\u0001\u0001\u0004\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002ဉ\u0001\u0004<\u0000", new Object[]{"zzf", "zze", "zzd", "zzg", zzgb.zza, "zzh", zzhb.class});
        } else if (i2 == 3) {
            return new zzga();
        } else {
            if (i2 == 4) {
                return new zzfz((zzfy) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
