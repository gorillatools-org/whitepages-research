package com.google.android.gms.internal.measurement;

public final class zzfl extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzfl zzb;
    private int zzd;
    private zzfv zze;
    private zzfp zzf;
    private boolean zzg;
    private String zzh = "";

    static {
        zzfl zzfl = new zzfl();
        zzb = zzfl;
        zzmd.zzct(zzfl.class, zzfl);
    }

    private zzfl() {
    }

    public static zzfl zzb() {
        return zzb;
    }

    static /* synthetic */ void zzf(zzfl zzfl, String str) {
        zzfl.zzd |= 8;
        zzfl.zzh = str;
    }

    public final zzfp zzc() {
        zzfp zzfp = this.zzf;
        return zzfp == null ? zzfp.zzb() : zzfp;
    }

    public final zzfv zzd() {
        zzfv zzfv = this.zze;
        return zzfv == null ? zzfv.zzc() : zzfv;
    }

    public final String zze() {
        return this.zzh;
    }

    public final boolean zzg() {
        return this.zzg;
    }

    public final boolean zzh() {
        return (this.zzd & 4) != 0;
    }

    public final boolean zzi() {
        return (this.zzd & 2) != 0;
    }

    public final boolean zzj() {
        return (this.zzd & 8) != 0;
    }

    public final boolean zzk() {
        return (this.zzd & 1) != 0;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzmd.zzcq(zzb, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဇ\u0002\u0004ဈ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
        } else if (i2 == 3) {
            return new zzfl();
        } else {
            if (i2 == 4) {
                return new zzfk((zzfw) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }
}
