package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationCallback;

final class zzn extends zzx {
    final /* synthetic */ LocationCallback zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzn(zzz zzz, GoogleApiClient googleApiClient, LocationCallback locationCallback) {
        super(googleApiClient);
        this.zza = locationCallback;
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzaz) anyClient).zzH(ListenerHolders.createListenerKey(this.zza, LocationCallback.class.getSimpleName()), new zzy(this));
    }
}
