package com.google.android.gms.internal.play_billing;

public final class zzhe extends zzcs implements zzed {
    /* access modifiers changed from: private */
    public static final zzhe zzb;
    private int zzd;
    private int zze = 0;
    private Object zzf;
    private zzgu zzg;
    private zzgx zzh;

    static {
        zzhe zzhe = new zzhe();
        zzb = zzhe;
        zzcs.zzt(zzhe.class, zzhe);
    }

    private zzhe() {
    }

    static /* synthetic */ void zzA(zzhe zzhe, zzhl zzhl) {
        zzhe.zzf = zzhl;
        zzhe.zze = 4;
    }

    static /* synthetic */ void zzB(zzhe zzhe, zzgu zzgu) {
        zzgu.getClass();
        zzhe.zzg = zzgu;
        zzhe.zzd |= 1;
    }

    static /* synthetic */ void zzC(zzhe zzhe, zzga zzga) {
        zzhe.zzf = zzga;
        zzhe.zze = 2;
    }

    static /* synthetic */ void zzD(zzhe zzhe, zzge zzge) {
        zzhe.zzf = zzge;
        zzhe.zze = 3;
    }

    public static zzhd zzy() {
        return (zzhd) zzb.zzg();
    }

    /* access modifiers changed from: protected */
    public final Object zzx(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzcs.zzq(zzb, "\u0001\u0006\u0001\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဉ\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000\u0005<\u0000\u0006ဉ\u0001", new Object[]{"zzf", "zze", "zzd", "zzg", zzga.class, zzge.class, zzhl.class, zzgr.class, "zzh"});
        } else if (i2 == 3) {
            return new zzhe();
        } else {
            if (i2 == 4) {
                return new zzhd((zzhc) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
