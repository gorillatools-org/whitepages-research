package com.google.android.gms.internal.measurement;

final class zzmy implements zznf {
    private final zznf[] zza;

    zzmy(zznf... zznfArr) {
        this.zza = zznfArr;
    }

    public final zzne zzb(Class cls) {
        for (int i = 0; i < 2; i++) {
            zznf zznf = this.zza[i];
            if (zznf.zzc(cls)) {
                return zznf.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(cls.getName()));
    }

    public final boolean zzc(Class cls) {
        for (int i = 0; i < 2; i++) {
            if (this.zza[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
