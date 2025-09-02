package com.google.android.gms.internal.auth;

final class zzfj extends zzfl {
    private zzfj() {
        super((zzfk) null);
    }

    /* synthetic */ zzfj(zzfi zzfi) {
        super((zzfk) null);
    }

    /* access modifiers changed from: package-private */
    public final void zza(Object obj, long j) {
        ((zzez) zzhj.zzf(obj, j)).zzb();
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Object obj, Object obj2, long j) {
        zzez zzez = (zzez) zzhj.zzf(obj, j);
        zzez zzez2 = (zzez) zzhj.zzf(obj2, j);
        int size = zzez.size();
        int size2 = zzez2.size();
        if (size > 0 && size2 > 0) {
            if (!zzez.zzc()) {
                zzez = zzez.zzd(size2 + size);
            }
            zzez.addAll(zzez2);
        }
        if (size > 0) {
            zzez2 = zzez;
        }
        zzhj.zzp(obj, j, zzez2);
    }
}
