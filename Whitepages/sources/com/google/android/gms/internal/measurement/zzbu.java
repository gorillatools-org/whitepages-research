package com.google.android.gms.internal.measurement;

final class zzbu extends zzcb {
    private String zza;
    private byte zzb;
    private int zzc;
    private int zzd;

    zzbu() {
    }

    public final zzcb zza(String str) {
        this.zza = "";
        return this;
    }

    public final zzcb zzb(boolean z) {
        this.zzb = 1;
        return this;
    }

    public final zzcc zzc() {
        if (this.zzb == 1 && this.zza != null && this.zzc != 0 && this.zzd != 0) {
            return new zzbw(this.zza, false, this.zzc, (zzbs) null, (zzbt) null, this.zzd, (zzbv) null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" fileOwner");
        }
        if (this.zzb == 0) {
            sb.append(" hasDifferentDmaOwner");
        }
        if (this.zzc == 0) {
            sb.append(" fileChecks");
        }
        if (this.zzd == 0) {
            sb.append(" filePurpose");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }

    /* access modifiers changed from: package-private */
    public final zzcb zzd(int i) {
        this.zzc = i;
        return this;
    }

    public final zzcb zze(int i) {
        this.zzd = 1;
        return this;
    }
}
