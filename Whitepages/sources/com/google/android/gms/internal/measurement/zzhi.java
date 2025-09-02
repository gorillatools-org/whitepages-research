package com.google.android.gms.internal.measurement;

public final class zzhi extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzhi zzb;
    private int zzd;
    private int zze;
    private zzic zzf;
    private zzic zzg;
    private boolean zzh;

    static {
        zzhi zzhi = new zzhi();
        zzb = zzhi;
        zzmd.zzct(zzhi.class, zzhi);
    }

    private zzhi() {
    }

    public static zzhh zzb() {
        return (zzhh) zzb.zzcg();
    }

    static /* synthetic */ void zzf(zzhi zzhi, int i) {
        zzhi.zzd |= 1;
        zzhi.zze = i;
    }

    static /* synthetic */ void zzg(zzhi zzhi, zzic zzic) {
        zzic.getClass();
        zzhi.zzf = zzic;
        zzhi.zzd |= 2;
    }

    static /* synthetic */ void zzh(zzhi zzhi, boolean z) {
        zzhi.zzd |= 8;
        zzhi.zzh = z;
    }

    static /* synthetic */ void zzi(zzhi zzhi, zzic zzic) {
        zzhi.zzg = zzic;
        zzhi.zzd |= 4;
    }

    public final int zza() {
        return this.zze;
    }

    public final zzic zzd() {
        zzic zzic = this.zzf;
        return zzic == null ? zzic.zzg() : zzic;
    }

    public final zzic zze() {
        zzic zzic = this.zzg;
        return zzic == null ? zzic.zzg() : zzic;
    }

    public final boolean zzj() {
        return this.zzh;
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
            return zzmd.zzcq(zzb, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဇ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
        } else if (i2 == 3) {
            return new zzhi();
        } else {
            if (i2 == 4) {
                return new zzhh((zzip) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }

    public final boolean zzm() {
        return (this.zzd & 8) != 0;
    }

    public final boolean zzn() {
        return (this.zzd & 4) != 0;
    }
}
