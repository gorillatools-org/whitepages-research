package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;

final class zzaq implements ListenerHolder.Notifier<LocationCallback> {
    final /* synthetic */ LocationAvailability zza;

    zzaq(zzar zzar, LocationAvailability locationAvailability) {
        this.zza = locationAvailability;
    }

    public final /* bridge */ /* synthetic */ void notifyListener(Object obj) {
        ((LocationCallback) obj).onLocationAvailability(this.zza);
    }

    public final void onNotifyListenerFailed() {
    }
}
