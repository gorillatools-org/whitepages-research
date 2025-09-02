package com.google.android.gms.internal.fido;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;

final class zzas extends zzat {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzat zzc;

    zzas(zzat zzat, int i, int i2) {
        this.zzc = zzat;
        this.zza = i;
        this.zzb = i2;
    }

    public final Object get(int i) {
        zzam.zza(i, this.zzb, FirebaseAnalytics.Param.INDEX);
        return this.zzc.get(i + this.zza);
    }

    public final int size() {
        return this.zzb;
    }

    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    /* access modifiers changed from: package-private */
    public final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    /* access modifiers changed from: package-private */
    public final Object[] zze() {
        return this.zzc.zze();
    }

    public final zzat zzf(int i, int i2) {
        zzam.zze(i, i2, this.zzb);
        zzat zzat = this.zzc;
        int i3 = this.zza;
        return zzat.subList(i + i3, i2 + i3);
    }
}
