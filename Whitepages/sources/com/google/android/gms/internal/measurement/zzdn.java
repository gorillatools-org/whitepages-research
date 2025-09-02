package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzdn extends zzeu {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ Bundle zzc;
    final /* synthetic */ zzff zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdn(zzff zzff, String str, String str2, Bundle bundle) {
        super(zzff, true);
        this.zza = str;
        this.zzb = str2;
        this.zzc = bundle;
        this.zzd = zzff;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzcv) Preconditions.checkNotNull(this.zzd.zzj)).clearConditionalUserProperty(this.zza, this.zzb, this.zzc);
    }
}
