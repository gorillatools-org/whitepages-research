package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzgo extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzgo zzb;
    private int zzd;
    private long zze;
    private String zzf = "";
    private int zzg;
    private zzmj zzh = zzmd.zzcn();
    private zzmj zzi = zzmd.zzcn();
    /* access modifiers changed from: private */
    public zzmj zzj = zzmd.zzcn();
    private String zzk = "";
    private boolean zzl;
    /* access modifiers changed from: private */
    public zzmj zzm = zzmd.zzcn();
    private zzmj zzn = zzmd.zzcn();
    private String zzo = "";
    private String zzp = "";
    private zzgi zzq;
    private zzgs zzr;
    private zzgy zzs;
    private zzgu zzt;
    private zzgq zzu;

    static {
        zzgo zzgo = new zzgo();
        zzb = zzgo;
        zzmd.zzct(zzgo.class, zzgo);
    }

    private zzgo() {
    }

    public static zzgn zzf() {
        return (zzgn) zzb.zzcg();
    }

    public static zzgo zzh() {
        return zzb;
    }

    static /* synthetic */ void zzs(zzgo zzgo, int i, zzgm zzgm) {
        zzgm.getClass();
        zzmj zzmj = zzgo.zzi;
        if (!zzmj.zzc()) {
            zzgo.zzi = zzmd.zzco(zzmj);
        }
        zzgo.zzi.set(i, zzgm);
    }

    public final int zza() {
        return this.zzm.size();
    }

    public final int zzb() {
        return this.zzi.size();
    }

    public final long zzc() {
        return this.zze;
    }

    public final zzgi zzd() {
        zzgi zzgi = this.zzq;
        return zzgi == null ? zzgi.zzb() : zzgi;
    }

    public final zzgm zze(int i) {
        return (zzgm) this.zzi.get(i);
    }

    public final zzgy zzi() {
        zzgy zzgy = this.zzs;
        return zzgy == null ? zzgy.zzc() : zzgy;
    }

    public final String zzj() {
        return this.zzf;
    }

    public final String zzk() {
        return this.zzo;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzmd.zzcq(zzb, "\u0004\u0011\u0000\u0001\u0001\u0013\u0011\u0000\u0005\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003င\u0002\u0004\u001b\u0005\u001b\u0006\u001b\u0007ဈ\u0003\bဇ\u0004\t\u001b\n\u001b\u000bဈ\u0005\u000eဈ\u0006\u000fဉ\u0007\u0010ဉ\b\u0011ဉ\t\u0012ဉ\n\u0013ဉ\u000b", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", zzgw.class, "zzi", zzgm.class, "zzj", zzfh.class, "zzk", "zzl", "zzm", zziv.class, "zzn", zzgk.class, "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu"});
        } else if (i2 == 3) {
            return new zzgo();
        } else {
            if (i2 == 4) {
                return new zzgn((zzgz) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }

    public final List zzm() {
        return this.zzj;
    }

    public final List zzn() {
        return this.zzn;
    }

    public final List zzo() {
        return this.zzm;
    }

    public final List zzp() {
        return this.zzh;
    }

    public final boolean zzt() {
        return (this.zzd & 128) != 0;
    }

    public final boolean zzu() {
        return (this.zzd & 2) != 0;
    }

    public final boolean zzv() {
        return (this.zzd & 512) != 0;
    }

    public final boolean zzw() {
        return (this.zzd & 1) != 0;
    }
}
