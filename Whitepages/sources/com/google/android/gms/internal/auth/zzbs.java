package com.google.android.gms.internal.auth;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzbs extends zzbj {
    zzbs(zzbt zzbt, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final void zza(Context context, zzbh zzbh) throws RemoteException {
        zzbh.zzd(new zzbr(this));
    }
}
