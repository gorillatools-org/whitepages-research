package com.google.android.gms.internal.play_billing;

public final class zzgk extends zzcs implements zzed {
    /* access modifiers changed from: private */
    public static final zzgk zzb;
    private int zzd;
    private int zze;
    private String zzf = "";
    private int zzg;
    private String zzh = "";

    static {
        zzgk zzgk = new zzgk();
        zzb = zzgk;
        zzcs.zzt(zzgk.class, zzgk);
    }

    private zzgk() {
    }

    static /* synthetic */ void zzA(zzgk zzgk, int i) {
        zzgk.zzd |= 1;
        zzgk.zze = i;
    }

    static /* synthetic */ void zzB(zzgk zzgk, String str) {
        str.getClass();
        zzgk.zzd |= 2;
        zzgk.zzf = str;
    }

    static /* synthetic */ void zzC(zzgk zzgk, String str) {
        zzgk.zzd |= 8;
        zzgk.zzh = str;
    }

    static /* synthetic */ void zzD(zzgk zzgk, int i) {
        zzgk.zzg = i - 1;
        zzgk.zzd |= 4;
    }

    public static zzgg zzy() {
        return (zzgg) zzb.zzg();
    }

    /* access modifiers changed from: protected */
    public final Object zzx(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzcs.zzq(zzb, "\u0001\u0004\u0000\u0001\u0001\u0005\u0004\u0000\u0000\u0000\u0001င\u0000\u0002ဈ\u0001\u0004᠌\u0002\u0005ဈ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", zzgi.zza, "zzh"});
        } else if (i2 == 3) {
            return new zzgk();
        } else {
            if (i2 == 4) {
                return new zzgg((zzgf) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
