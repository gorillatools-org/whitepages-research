package com.google.android.gms.internal.measurement;

public final class zzia extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzia zzb;
    private int zzd;
    private int zze = 1;
    private zzmj zzf = zzmd.zzcn();

    static {
        zzia zzia = new zzia();
        zzb = zzia;
        zzmd.zzct(zzia.class, zzia);
    }

    private zzia() {
    }

    public static zzhy zza() {
        return (zzhy) zzb.zzcg();
    }

    static /* synthetic */ void zzc(zzia zzia, zzho zzho) {
        zzho.getClass();
        zzmj zzmj = zzia.zzf;
        if (!zzmj.zzc()) {
            zzia.zzf = zzmd.zzco(zzmj);
        }
        zzia.zzf.add(zzho);
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzmd.zzcq(zzb, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001᠌\u0000\u0002\u001b", new Object[]{"zzd", "zze", zzhz.zza, "zzf", zzho.class});
        } else if (i2 == 3) {
            return new zzia();
        } else {
            if (i2 == 4) {
                return new zzhy((zzip) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }
}
