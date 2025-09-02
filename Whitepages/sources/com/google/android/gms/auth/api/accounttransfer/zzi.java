package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzau;
import com.google.android.gms.internal.auth.zzav;

final class zzi extends zzn {
    final /* synthetic */ zzav zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzi(AccountTransferClient accountTransferClient, int i, zzav zzav) {
        super(1610);
        this.zza = zzav;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzau zzau) throws RemoteException {
        zzau.zzf(this.zzc, this.zza);
    }
}
