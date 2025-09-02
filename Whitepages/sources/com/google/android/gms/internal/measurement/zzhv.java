package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzhv extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzhv zzb;
    private int zzd;
    /* access modifiers changed from: private */
    public zzmj zze = zzmd.zzcn();
    private String zzf = "";
    private String zzg = "";
    private int zzh;

    static {
        zzhv zzhv = new zzhv();
        zzb = zzhv;
        zzmd.zzct(zzhv.class, zzhv);
    }

    private zzhv() {
    }

    public static zzht zzb() {
        return (zzht) zzb.zzcg();
    }

    public static zzht zzc(zzhv zzhv) {
        zzlz zzcg = zzb.zzcg();
        zzcg.zzaY(zzhv);
        return (zzht) zzcg;
    }

    static /* synthetic */ void zzi(zzhv zzhv, Iterable iterable) {
        zzhv.zzr();
        zzko.zzcc(iterable, zzhv.zze);
    }

    static /* synthetic */ void zzj(zzhv zzhv, zzhx zzhx) {
        zzhx.getClass();
        zzhv.zzr();
        zzhv.zze.add(zzhx);
    }

    static /* synthetic */ void zzm(zzhv zzhv, int i, zzhx zzhx) {
        zzhx.getClass();
        zzhv.zzr();
        zzhv.zze.set(i, zzhx);
    }

    static /* synthetic */ void zzn(zzhv zzhv, String str) {
        str.getClass();
        zzhv.zzd |= 1;
        zzhv.zzf = str;
    }

    static /* synthetic */ void zzo(zzhv zzhv, String str) {
        str.getClass();
        zzhv.zzd |= 2;
        zzhv.zzg = str;
    }

    private final void zzr() {
        zzmj zzmj = this.zze;
        if (!zzmj.zzc()) {
            this.zze = zzmd.zzco(zzmj);
        }
    }

    public final int zza() {
        return this.zze.size();
    }

    public final zzhx zze(int i) {
        return (zzhx) this.zze.get(i);
    }

    public final String zzf() {
        return this.zzf;
    }

    public final String zzg() {
        return this.zzg;
    }

    public final List zzh() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzmd.zzcq(zzb, "\u0004\u0004\u0000\u0001\u0001\t\u0004\u0000\u0001\u0000\u0001\u001b\u0007ဈ\u0000\bဈ\u0001\t᠌\u0002", new Object[]{"zzd", "zze", zzhx.class, "zzf", "zzg", "zzh", zzhu.zza});
        } else if (i2 == 3) {
            return new zzhv();
        } else {
            if (i2 == 4) {
                return new zzht((zzip) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }

    public final boolean zzp() {
        return (this.zzd & 1) != 0;
    }

    public final boolean zzq() {
        return (this.zzd & 2) != 0;
    }
}
