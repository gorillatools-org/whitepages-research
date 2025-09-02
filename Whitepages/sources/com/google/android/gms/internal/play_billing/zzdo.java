package com.google.android.gms.internal.play_billing;

final class zzdo extends zzdq {
    private zzdo() {
        throw null;
    }

    /* synthetic */ zzdo(zzdn zzdn) {
        super((zzdp) null);
    }

    /* access modifiers changed from: package-private */
    public final void zza(Object obj, long j) {
        ((zzcz) zzfp.zzf(obj, j)).zzb();
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Object obj, Object obj2, long j) {
        zzcz zzcz = (zzcz) zzfp.zzf(obj, j);
        zzcz zzcz2 = (zzcz) zzfp.zzf(obj2, j);
        int size = zzcz.size();
        int size2 = zzcz2.size();
        if (size > 0 && size2 > 0) {
            if (!zzcz.zzc()) {
                zzcz = zzcz.zzd(size2 + size);
            }
            zzcz.addAll(zzcz2);
        }
        if (size > 0) {
            zzcz2 = zzcz;
        }
        zzfp.zzs(obj, j, zzcz2);
    }
}
