package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzaq;
import com.google.android.gms.internal.auth.zzau;

final class zzg extends zzl {
    final /* synthetic */ zzaq zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzg(AccountTransferClient accountTransferClient, int i, zzaq zzaq) {
        super(1608, (zzk) null);
        this.zza = zzaq;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzau zzau) throws RemoteException {
        zzau.zzd(new zzf(this, this), this.zza);
    }
}
