package com.google.android.gms.internal.play_billing;

public final class zzge extends zzcs implements zzed {
    /* access modifiers changed from: private */
    public static final zzge zzb;
    private int zzd;
    private int zze = 0;
    private Object zzf;
    private int zzg;

    static {
        zzge zzge = new zzge();
        zzb = zzge;
        zzcs.zzt(zzge.class, zzge);
    }

    private zzge() {
    }

    static /* synthetic */ void zzA(zzge zzge, zzhb zzhb) {
        zzhb.getClass();
        zzge.zzf = zzhb;
        zzge.zze = 2;
    }

    static /* synthetic */ void zzB(zzge zzge, int i) {
        zzge.zzg = i - 1;
        zzge.zzd |= 1;
    }

    public static zzgd zzy() {
        return (zzgd) zzb.zzg();
    }

    /* access modifiers changed from: protected */
    public final Object zzx(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzcs.zzq(zzb, "\u0001\u0003\u0001\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002<\u0000\u0003<\u0000", new Object[]{"zzf", "zze", "zzd", "zzg", zzgb.zza, zzhb.class, zzho.class});
        } else if (i2 == 3) {
            return new zzge();
        } else {
            if (i2 == 4) {
                return new zzgd((zzgc) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
