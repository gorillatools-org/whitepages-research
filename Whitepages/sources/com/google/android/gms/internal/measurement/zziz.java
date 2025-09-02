package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zziz extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zziz zzb;
    private int zzd;
    private int zze;
    private zzmj zzf = zzmd.zzcn();
    private String zzg = "";
    private String zzh = "";
    private boolean zzi;
    private double zzj;

    static {
        zziz zziz = new zziz();
        zzb = zziz;
        zzmd.zzct(zziz.class, zziz);
    }

    private zziz() {
    }

    public final double zza() {
        return this.zzj;
    }

    public final String zzc() {
        return this.zzg;
    }

    public final String zzd() {
        return this.zzh;
    }

    public final List zze() {
        return this.zzf;
    }

    public final boolean zzf() {
        return this.zzi;
    }

    public final boolean zzg() {
        return (this.zzd & 8) != 0;
    }

    public final boolean zzh() {
        return (this.zzd & 16) != 0;
    }

    public final boolean zzi() {
        return (this.zzd & 4) != 0;
    }

    public final int zzj() {
        int zza = zziy.zza(this.zze);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzmd.zzcq(zzb, "\u0004\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001᠌\u0000\u0002\u001b\u0003ဈ\u0001\u0004ဈ\u0002\u0005ဇ\u0003\u0006က\u0004", new Object[]{"zzd", "zze", zzix.zza, "zzf", zziz.class, "zzg", "zzh", "zzi", "zzj"});
        } else if (i2 == 3) {
            return new zziz();
        } else {
            if (i2 == 4) {
                return new zziw((zzja) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }
}
