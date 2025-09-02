package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzhq extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzhq zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private long zzg;
    private float zzh;
    private double zzi;
    /* access modifiers changed from: private */
    public zzmj zzj = zzmd.zzcn();

    static {
        zzhq zzhq = new zzhq();
        zzb = zzhq;
        zzmd.zzct(zzhq.class, zzhq);
    }

    private zzhq() {
    }

    public static zzhp zze() {
        return (zzhp) zzb.zzcg();
    }

    static /* synthetic */ void zzj(zzhq zzhq, Iterable iterable) {
        zzhq.zzz();
        zzko.zzcc(iterable, zzhq.zzj);
    }

    static /* synthetic */ void zzk(zzhq zzhq, zzhq zzhq2) {
        zzhq2.getClass();
        zzhq.zzz();
        zzhq.zzj.add(zzhq2);
    }

    static /* synthetic */ void zzm(zzhq zzhq) {
        zzhq.zzd &= -17;
        zzhq.zzi = 0.0d;
    }

    static /* synthetic */ void zzn(zzhq zzhq) {
        zzhq.zzd &= -5;
        zzhq.zzg = 0;
    }

    static /* synthetic */ void zzp(zzhq zzhq) {
        zzhq.zzd &= -3;
        zzhq.zzf = zzb.zzf;
    }

    static /* synthetic */ void zzq(zzhq zzhq, double d) {
        zzhq.zzd |= 16;
        zzhq.zzi = d;
    }

    static /* synthetic */ void zzr(zzhq zzhq, long j) {
        zzhq.zzd |= 4;
        zzhq.zzg = j;
    }

    static /* synthetic */ void zzs(zzhq zzhq, String str) {
        str.getClass();
        zzhq.zzd |= 1;
        zzhq.zze = str;
    }

    static /* synthetic */ void zzt(zzhq zzhq, String str) {
        str.getClass();
        zzhq.zzd |= 2;
        zzhq.zzf = str;
    }

    private final void zzz() {
        zzmj zzmj = this.zzj;
        if (!zzmj.zzc()) {
            this.zzj = zzmd.zzco(zzmj);
        }
    }

    public final double zza() {
        return this.zzi;
    }

    public final float zzb() {
        return this.zzh;
    }

    public final int zzc() {
        return this.zzj.size();
    }

    public final long zzd() {
        return this.zzg;
    }

    public final String zzg() {
        return this.zze;
    }

    public final String zzh() {
        return this.zzf;
    }

    public final List zzi() {
        return this.zzj;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzmd.zzcq(zzb, "\u0004\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဂ\u0002\u0004ခ\u0003\u0005က\u0004\u0006\u001b", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", zzhq.class});
        } else if (i2 == 3) {
            return new zzhq();
        } else {
            if (i2 == 4) {
                return new zzhp((zzip) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }

    public final boolean zzu() {
        return (this.zzd & 16) != 0;
    }

    public final boolean zzv() {
        return (this.zzd & 8) != 0;
    }

    public final boolean zzw() {
        return (this.zzd & 4) != 0;
    }

    public final boolean zzx() {
        return (this.zzd & 1) != 0;
    }

    public final boolean zzy() {
        return (this.zzd & 2) != 0;
    }
}
