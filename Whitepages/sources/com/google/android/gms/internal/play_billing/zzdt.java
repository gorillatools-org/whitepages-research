package com.google.android.gms.internal.play_billing;

final class zzdt implements zzea {
    private final zzea[] zza;

    zzdt(zzea... zzeaArr) {
        this.zza = zzeaArr;
    }

    public final zzdz zzb(Class cls) {
        for (int i = 0; i < 2; i++) {
            zzea zzea = this.zza[i];
            if (zzea.zzc(cls)) {
                return zzea.zzb(cls);
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
