package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;

@KeepForSdk
public interface OnConnectionFailedListener {
    @ShowFirstParty
    @KeepForSdk
    void onConnectionFailed(ConnectionResult connectionResult);
}
