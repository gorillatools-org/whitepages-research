package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zziv extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zziv zzb;
    private int zzd;
    private zzmj zze = zzmd.zzcn();
    private zzir zzf;

    static {
        zziv zziv = new zziv();
        zzb = zziv;
        zzmd.zzct(zziv.class, zziv);
    }

    private zziv() {
    }

    public final zzir zza() {
        zzir zzir = this.zzf;
        return zzir == null ? zzir.zzc() : zzir;
    }

    public final List zzc() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzmd.zzcq(zzb, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002ဉ\u0000", new Object[]{"zzd", "zze", zziz.class, "zzf"});
        } else if (i2 == 3) {
            return new zziv();
        } else {
            if (i2 == 4) {
                return new zziu((zzja) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }
}
