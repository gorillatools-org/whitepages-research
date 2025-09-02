package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;

@KeepForSdk
public interface ConnectionCallbacks {
    @ShowFirstParty
    @KeepForSdk
    void onConnected(Bundle bundle);

    @ShowFirstParty
    @KeepForSdk
    void onConnectionSuspended(int i);
}
