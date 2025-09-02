package com.google.android.gms.internal.measurement;

public final class zzho extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzho zzb;
    private int zzd;
    private String zze = "";
    private long zzf;

    static {
        zzho zzho = new zzho();
        zzb = zzho;
        zzmd.zzct(zzho.class, zzho);
    }

    private zzho() {
    }

    public static zzhn zza() {
        return (zzhn) zzb.zzcg();
    }

    static /* synthetic */ void zzc(zzho zzho, long j) {
        zzho.zzd |= 2;
        zzho.zzf = j;
    }

    static /* synthetic */ void zzd(zzho zzho, String str) {
        str.getClass();
        zzho.zzd |= 1;
        zzho.zze = str;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzmd.zzcq(zzb, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (i2 == 3) {
            return new zzho();
        } else {
            if (i2 == 4) {
                return new zzhn((zzip) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }
}
