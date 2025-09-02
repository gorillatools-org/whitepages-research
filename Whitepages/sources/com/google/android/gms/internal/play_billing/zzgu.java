package com.google.android.gms.internal.play_billing;

public final class zzgu extends zzcs implements zzed {
    /* access modifiers changed from: private */
    public static final zzgu zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private int zzg;

    static {
        zzgu zzgu = new zzgu();
        zzb = zzgu;
        zzcs.zzt(zzgu.class, zzgu);
    }

    private zzgu() {
    }

    static /* synthetic */ void zzA(zzgu zzgu, String str) {
        str.getClass();
        zzgu.zzd |= 1;
        zzgu.zze = str;
    }

    static /* synthetic */ void zzB(zzgu zzgu, String str) {
        str.getClass();
        zzgu.zzd |= 2;
        zzgu.zzf = str;
    }

    static /* synthetic */ void zzC(zzgu zzgu, int i) {
        zzgu.zzd |= 4;
        zzgu.zzg = i;
    }

    public static zzgt zzy() {
        return (zzgt) zzb.zzg();
    }

    /* access modifiers changed from: protected */
    public final Object zzx(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzcs.zzq(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003င\u0002", new Object[]{"zzd", "zze", "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzgu();
        } else {
            if (i2 == 4) {
                return new zzgt((zzgs) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
