package com.google.android.gms.internal.measurement;

public final class zzio extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzio zzb;
    private int zzd;
    private long zze;
    private String zzf = "";
    private String zzg = "";
    private long zzh;
    private float zzi;
    private double zzj;

    static {
        zzio zzio = new zzio();
        zzb = zzio;
        zzmd.zzct(zzio.class, zzio);
    }

    private zzio() {
    }

    public static zzin zze() {
        return (zzin) zzb.zzcg();
    }

    static /* synthetic */ void zzi(zzio zzio) {
        zzio.zzd &= -33;
        zzio.zzj = 0.0d;
    }

    static /* synthetic */ void zzj(zzio zzio) {
        zzio.zzd &= -9;
        zzio.zzh = 0;
    }

    static /* synthetic */ void zzk(zzio zzio) {
        zzio.zzd &= -5;
        zzio.zzg = zzb.zzg;
    }

    static /* synthetic */ void zzm(zzio zzio, double d) {
        zzio.zzd |= 32;
        zzio.zzj = d;
    }

    static /* synthetic */ void zzn(zzio zzio, long j) {
        zzio.zzd |= 8;
        zzio.zzh = j;
    }

    static /* synthetic */ void zzo(zzio zzio, String str) {
        str.getClass();
        zzio.zzd |= 2;
        zzio.zzf = str;
    }

    static /* synthetic */ void zzp(zzio zzio, long j) {
        zzio.zzd |= 1;
        zzio.zze = j;
    }

    static /* synthetic */ void zzq(zzio zzio, String str) {
        str.getClass();
        zzio.zzd |= 4;
        zzio.zzg = str;
    }

    public final double zza() {
        return this.zzj;
    }

    public final float zzb() {
        return this.zzi;
    }

    public final long zzc() {
        return this.zzh;
    }

    public final long zzd() {
        return this.zze;
    }

    public final String zzg() {
        return this.zzf;
    }

    public final String zzh() {
        return this.zzg;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzmd.zzcq(zzb, "\u0004\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005ခ\u0004\u0006က\u0005", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
        } else if (i2 == 3) {
            return new zzio();
        } else {
            if (i2 == 4) {
                return new zzin((zzip) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }

    public final boolean zzr() {
        return (this.zzd & 32) != 0;
    }

    public final boolean zzs() {
        return (this.zzd & 16) != 0;
    }

    public final boolean zzt() {
        return (this.zzd & 8) != 0;
    }

    public final boolean zzu() {
        return (this.zzd & 1) != 0;
    }

    public final boolean zzv() {
        return (this.zzd & 4) != 0;
    }
}
