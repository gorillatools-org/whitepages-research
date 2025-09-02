package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;

final class zaaz implements GoogleApiClient.ConnectionCallbacks {
    final /* synthetic */ AtomicReference zaa;
    final /* synthetic */ StatusPendingResult zab;
    final /* synthetic */ zabe zac;

    zaaz(zabe zabe, AtomicReference atomicReference, StatusPendingResult statusPendingResult) {
        this.zac = zabe;
        this.zaa = atomicReference;
        this.zab = statusPendingResult;
    }

    public final void onConnected(Bundle bundle) {
        StatusPendingResult statusPendingResult = this.zab;
        this.zac.zam((GoogleApiClient) Preconditions.checkNotNull((GoogleApiClient) this.zaa.get()), statusPendingResult, true);
    }

    public final void onConnectionSuspended(int i) {
    }
}
