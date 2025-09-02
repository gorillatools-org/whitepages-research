package com.google.android.gms.internal.measurement;

public final class zzgq extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzgq zzb;
    private zzmj zzd = zzmd.zzcn();

    static {
        zzgq zzgq = new zzgq();
        zzb = zzgq;
        zzmd.zzct(zzgq.class, zzgq);
    }

    private zzgq() {
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzmd.zzcq(zzb, "\u0004\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", new Object[]{"zzd"});
        } else if (i2 == 3) {
            return new zzgq();
        } else {
            if (i2 == 4) {
                return new zzgp((zzgz) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }
}
