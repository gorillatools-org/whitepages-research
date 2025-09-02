package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzit extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzit zzb;
    private int zzd;
    private String zze = "";
    private zzmj zzf = zzmd.zzcn();

    static {
        zzit zzit = new zzit();
        zzb = zzit;
        zzmd.zzct(zzit.class, zzit);
    }

    private zzit() {
    }

    public final String zzb() {
        return this.zze;
    }

    public final List zzc() {
        return this.zzf;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzmd.zzcq(zzb, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001b", new Object[]{"zzd", "zze", "zzf", zziz.class});
        } else if (i2 == 3) {
            return new zzit();
        } else {
            if (i2 == 4) {
                return new zzis((zzja) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }
}
