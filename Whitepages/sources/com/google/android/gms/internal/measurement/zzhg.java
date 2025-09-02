package com.google.android.gms.internal.measurement;

public final class zzhg extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzhg zzb;
    private int zzd;
    private boolean zze;
    private boolean zzf;
    private boolean zzg;
    private boolean zzh;
    private boolean zzi;
    private boolean zzj;
    private boolean zzk;

    static {
        zzhg zzhg = new zzhg();
        zzb = zzhg;
        zzmd.zzct(zzhg.class, zzhg);
    }

    private zzhg() {
    }

    public static zzhf zza() {
        return (zzhf) zzb.zzcg();
    }

    public static zzhg zzc() {
        return zzb;
    }

    static /* synthetic */ void zzd(zzhg zzhg, boolean z) {
        zzhg.zzd |= 32;
        zzhg.zzj = z;
    }

    static /* synthetic */ void zze(zzhg zzhg, boolean z) {
        zzhg.zzd |= 16;
        zzhg.zzi = z;
    }

    static /* synthetic */ void zzf(zzhg zzhg, boolean z) {
        zzhg.zzd |= 1;
        zzhg.zze = z;
    }

    static /* synthetic */ void zzg(zzhg zzhg, boolean z) {
        zzhg.zzd |= 64;
        zzhg.zzk = z;
    }

    static /* synthetic */ void zzh(zzhg zzhg, boolean z) {
        zzhg.zzd |= 2;
        zzhg.zzf = z;
    }

    static /* synthetic */ void zzi(zzhg zzhg, boolean z) {
        zzhg.zzd |= 4;
        zzhg.zzg = z;
    }

    static /* synthetic */ void zzj(zzhg zzhg, boolean z) {
        zzhg.zzd |= 8;
        zzhg.zzh = z;
    }

    public final boolean zzk() {
        return this.zzj;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzmd.zzcq(zzb, "\u0004\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ဇ\u0005\u0007ဇ\u0006", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        } else if (i2 == 3) {
            return new zzhg();
        } else {
            if (i2 == 4) {
                return new zzhf((zzip) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }

    public final boolean zzm() {
        return this.zzi;
    }

    public final boolean zzn() {
        return this.zze;
    }

    public final boolean zzo() {
        return this.zzk;
    }

    public final boolean zzp() {
        return this.zzf;
    }

    public final boolean zzq() {
        return this.zzg;
    }

    public final boolean zzr() {
        return this.zzh;
    }
}
