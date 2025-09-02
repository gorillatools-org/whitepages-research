package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzfv extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzfv zzb;
    private int zzd;
    private int zze;
    private String zzf = "";
    private boolean zzg;
    private zzmj zzh = zzmd.zzcn();

    static {
        zzfv zzfv = new zzfv();
        zzb = zzfv;
        zzmd.zzct(zzfv.class, zzfv);
    }

    private zzfv() {
    }

    public static zzfv zzc() {
        return zzb;
    }

    public final int zza() {
        return this.zzh.size();
    }

    public final String zzd() {
        return this.zzf;
    }

    public final List zze() {
        return this.zzh;
    }

    public final boolean zzf() {
        return this.zzg;
    }

    public final boolean zzg() {
        return (this.zzd & 4) != 0;
    }

    public final boolean zzh() {
        return (this.zzd & 2) != 0;
    }

    public final boolean zzi() {
        return (this.zzd & 1) != 0;
    }

    public final int zzj() {
        int zza = zzfu.zza(this.zze);
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
            return zzmd.zzcq(zzb, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001᠌\u0000\u0002ဈ\u0001\u0003ဇ\u0002\u0004\u001a", new Object[]{"zzd", "zze", zzft.zza, "zzf", "zzg", "zzh"});
        } else if (i2 == 3) {
            return new zzfv();
        } else {
            if (i2 == 4) {
                return new zzfs((zzfw) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }
}
