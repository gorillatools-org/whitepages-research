package com.google.android.gms.internal.auth;

import java.io.IOException;

final class zzgb implements zzgi {
    private final zzfx zza;
    private final zzgz zzb;
    private final zzem zzc;

    private zzgb(zzgz zzgz, zzem zzem, zzfx zzfx) {
        this.zzb = zzgz;
        this.zzc = zzem;
        this.zza = zzfx;
    }

    static zzgb zzb(zzgz zzgz, zzem zzem, zzfx zzfx) {
        return new zzgb(zzgz, zzem, zzfx);
    }

    public final int zza(Object obj) {
        return this.zzb.zzb(obj).hashCode();
    }

    public final Object zzd() {
        zzfx zzfx = this.zza;
        if (zzfx instanceof zzev) {
            return ((zzev) zzfx).zzc();
        }
        return ((zzet) ((zzev) zzfx).zzn(5, (Object) null, (Object) null)).zzd();
    }

    public final void zze(Object obj) {
        this.zzb.zze(obj);
        this.zzc.zzb(obj);
    }

    public final void zzf(Object obj, Object obj2) {
        zzgk.zzd(this.zzb, obj, obj2);
    }

    public final void zzg(Object obj, byte[] bArr, int i, int i2, zzdt zzdt) throws IOException {
        zzev zzev = (zzev) obj;
        if (zzev.zzc == zzha.zza()) {
            zzev.zzc = zzha.zzd();
        }
        zzeu zzeu = (zzeu) obj;
        throw null;
    }

    public final boolean zzh(Object obj, Object obj2) {
        return this.zzb.zzb(obj).equals(this.zzb.zzb(obj2));
    }

    public final boolean zzi(Object obj) {
        this.zzc.zza(obj);
        throw null;
    }
}
