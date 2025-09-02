package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzic extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzic zzb;
    /* access modifiers changed from: private */
    public zzmi zzd = zzmd.zzcl();
    /* access modifiers changed from: private */
    public zzmi zze = zzmd.zzcl();
    /* access modifiers changed from: private */
    public zzmj zzf = zzmd.zzcn();
    /* access modifiers changed from: private */
    public zzmj zzg = zzmd.zzcn();

    static {
        zzic zzic = new zzic();
        zzb = zzic;
        zzmd.zzct(zzic.class, zzic);
    }

    private zzic() {
    }

    public static zzib zze() {
        return (zzib) zzb.zzcg();
    }

    public static zzic zzg() {
        return zzb;
    }

    static /* synthetic */ void zzm(zzic zzic, Iterable iterable) {
        zzmj zzmj = zzic.zzf;
        if (!zzmj.zzc()) {
            zzic.zzf = zzmd.zzco(zzmj);
        }
        zzko.zzcc(iterable, zzic.zzf);
    }

    static /* synthetic */ void zzn(zzic zzic, Iterable iterable) {
        zzmi zzmi = zzic.zze;
        if (!zzmi.zzc()) {
            zzic.zze = zzmd.zzcm(zzmi);
        }
        zzko.zzcc(iterable, zzic.zze);
    }

    static /* synthetic */ void zzo(zzic zzic, Iterable iterable) {
        zzmj zzmj = zzic.zzg;
        if (!zzmj.zzc()) {
            zzic.zzg = zzmd.zzco(zzmj);
        }
        zzko.zzcc(iterable, zzic.zzg);
    }

    static /* synthetic */ void zzp(zzic zzic, Iterable iterable) {
        zzmi zzmi = zzic.zzd;
        if (!zzmi.zzc()) {
            zzic.zzd = zzmd.zzcm(zzmi);
        }
        zzko.zzcc(iterable, zzic.zzd);
    }

    public final int zza() {
        return this.zzf.size();
    }

    public final int zzb() {
        return this.zze.size();
    }

    public final int zzc() {
        return this.zzg.size();
    }

    public final int zzd() {
        return this.zzd.size();
    }

    public final List zzh() {
        return this.zzf;
    }

    public final List zzi() {
        return this.zze;
    }

    public final List zzj() {
        return this.zzg;
    }

    public final List zzk() {
        return this.zzd;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzmd.zzcq(zzb, "\u0004\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0004\u0000\u0001\u0015\u0002\u0015\u0003\u001b\u0004\u001b", new Object[]{"zzd", "zze", "zzf", zzhk.class, "zzg", zzie.class});
        } else if (i2 == 3) {
            return new zzic();
        } else {
            if (i2 == 4) {
                return new zzib((zzip) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }
}
