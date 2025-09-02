package com.google.android.gms.internal.measurement;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzeq extends zzeu {
    final /* synthetic */ Intent zza;
    final /* synthetic */ zzff zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzeq(zzff zzff, Intent intent) {
        super(zzff, true);
        this.zza = intent;
        this.zzb = zzff;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzcv) Preconditions.checkNotNull(this.zzb.zzj)).setSgtmDebugInfo(this.zza);
    }
}
