package com.google.android.gms.internal.measurement;

public final class zzhc extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzhc zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private String zzg = "";
    private long zzh;
    private String zzi = "";
    private String zzj = "";
    private String zzk = "";
    private long zzl;

    static {
        zzhc zzhc = new zzhc();
        zzb = zzhc;
        zzmd.zzct(zzhc.class, zzhc);
    }

    private zzhc() {
    }

    public static zzhb zzc() {
        return (zzhb) zzb.zzcg();
    }

    public static zzhc zze() {
        return zzb;
    }

    static /* synthetic */ void zzm(zzhc zzhc) {
        zzhc.zzd &= -5;
        zzhc.zzg = zzb.zzg;
    }

    static /* synthetic */ void zzn(zzhc zzhc) {
        zzhc.zzd &= -3;
        zzhc.zzf = zzb.zzf;
    }

    static /* synthetic */ void zzo(zzhc zzhc) {
        zzhc.zzd &= -2;
        zzhc.zze = zzb.zze;
    }

    static /* synthetic */ void zzp(zzhc zzhc) {
        zzhc.zzd &= -65;
        zzhc.zzk = zzb.zzk;
    }

    static /* synthetic */ void zzq(zzhc zzhc) {
        zzhc.zzd &= -33;
        zzhc.zzj = zzb.zzj;
    }

    static /* synthetic */ void zzr(zzhc zzhc) {
        zzhc.zzd &= -17;
        zzhc.zzi = zzb.zzi;
    }

    static /* synthetic */ void zzs(zzhc zzhc, String str) {
        zzhc.zzd |= 4;
        zzhc.zzg = str;
    }

    static /* synthetic */ void zzt(zzhc zzhc, String str) {
        zzhc.zzd |= 2;
        zzhc.zzf = str;
    }

    static /* synthetic */ void zzu(zzhc zzhc, String str) {
        zzhc.zzd |= 1;
        zzhc.zze = str;
    }

    static /* synthetic */ void zzv(zzhc zzhc, long j) {
        zzhc.zzd |= 8;
        zzhc.zzh = j;
    }

    static /* synthetic */ void zzw(zzhc zzhc, long j) {
        zzhc.zzd |= 128;
        zzhc.zzl = j;
    }

    static /* synthetic */ void zzx(zzhc zzhc, String str) {
        zzhc.zzd |= 64;
        zzhc.zzk = str;
    }

    static /* synthetic */ void zzy(zzhc zzhc, String str) {
        zzhc.zzd |= 32;
        zzhc.zzj = str;
    }

    static /* synthetic */ void zzz(zzhc zzhc, String str) {
        zzhc.zzd |= 16;
        zzhc.zzi = str;
    }

    public final boolean zzA() {
        return (this.zzd & 4) != 0;
    }

    public final boolean zzB() {
        return (this.zzd & 2) != 0;
    }

    public final boolean zzC() {
        return (this.zzd & 1) != 0;
    }

    public final boolean zzD() {
        return (this.zzd & 8) != 0;
    }

    public final boolean zzE() {
        return (this.zzd & 128) != 0;
    }

    public final boolean zzF() {
        return (this.zzd & 64) != 0;
    }

    public final boolean zzG() {
        return (this.zzd & 32) != 0;
    }

    public final boolean zzH() {
        return (this.zzd & 16) != 0;
    }

    public final long zza() {
        return this.zzh;
    }

    public final long zzb() {
        return this.zzl;
    }

    public final String zzf() {
        return this.zzg;
    }

    public final String zzg() {
        return this.zzf;
    }

    public final String zzh() {
        return this.zze;
    }

    public final String zzi() {
        return this.zzk;
    }

    public final String zzj() {
        return this.zzj;
    }

    public final String zzk() {
        return this.zzi;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzmd.zzcq(zzb, "\u0004\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006\bဂ\u0007", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
        } else if (i2 == 3) {
            return new zzhc();
        } else {
            if (i2 == 4) {
                return new zzhb((zzip) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }
}
