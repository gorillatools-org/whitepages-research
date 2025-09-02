package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzau;
import com.google.android.gms.internal.auth.zzax;

final class zze extends zzl {
    final /* synthetic */ zzax zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zze(AccountTransferClient accountTransferClient, int i, zzax zzax) {
        super(1607, (zzk) null);
        this.zza = zzax;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzau zzau) throws RemoteException {
        zzau.zzg(new zzd(this, this), this.zza);
    }
}
