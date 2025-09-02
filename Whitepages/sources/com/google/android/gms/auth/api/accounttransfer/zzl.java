package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.auth.zzap;
import com.google.android.gms.internal.auth.zzau;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzl extends TaskApiCall {
    protected TaskCompletionSource zzb;

    /* synthetic */ zzl(int i, zzk zzk) {
        super((Feature[]) null, false, i);
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzb = taskCompletionSource;
        zza((zzau) ((zzap) anyClient).getService());
    }

    /* access modifiers changed from: protected */
    public abstract void zza(zzau zzau) throws RemoteException;
}
