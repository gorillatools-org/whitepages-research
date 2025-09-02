package com.google.android.gms.internal.play_billing;

public final class zzhh extends zzcs implements zzed {
    /* access modifiers changed from: private */
    public static final zzhh zzb;
    private int zzd;
    private zzcz zze = zzcs.zzo();
    private int zzf;
    private String zzg = "";

    static {
        zzhh zzhh = new zzhh();
        zzb = zzhh;
        zzcs.zzt(zzhh.class, zzhh);
    }

    private zzhh() {
    }

    /* access modifiers changed from: protected */
    public final Object zzx(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzcs.zzq(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001\u001a\u0002င\u0000\u0003ဈ\u0001", new Object[]{"zzd", "zze", "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzhh();
        } else {
            if (i2 == 4) {
                return new zzhg((zzhf) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
