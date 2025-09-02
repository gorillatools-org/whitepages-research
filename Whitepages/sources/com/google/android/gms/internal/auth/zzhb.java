package com.google.android.gms.internal.auth;

final class zzhb extends zzgz {
    zzhb() {
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ Object zza(Object obj) {
        zzev zzev = (zzev) obj;
        zzha zzha = zzev.zzc;
        if (zzha != zzha.zza()) {
            return zzha;
        }
        zzha zzd = zzha.zzd();
        zzev.zzc = zzd;
        return zzd;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzb(Object obj) {
        return ((zzev) obj).zzc;
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ Object zzc(Object obj, Object obj2) {
        if (zzha.zza().equals(obj2)) {
            return obj;
        }
        if (zzha.zza().equals(obj)) {
            return zzha.zzc((zzha) obj, (zzha) obj2);
        }
        ((zzha) obj).zzb((zzha) obj2);
        return obj;
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ void zzd(Object obj, int i, long j) {
        ((zzha) obj).zzh(i << 3, Long.valueOf(j));
    }

    /* access modifiers changed from: package-private */
    public final void zze(Object obj) {
        ((zzev) obj).zzc.zzf();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzf(Object obj, Object obj2) {
        ((zzev) obj).zzc = (zzha) obj2;
    }
}
