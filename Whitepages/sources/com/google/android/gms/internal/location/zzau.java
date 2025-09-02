package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.zzbc;

final class zzau extends zzbc {
    private final ListenerHolder<LocationListener> zza;

    zzau(ListenerHolder<LocationListener> listenerHolder) {
        this.zza = listenerHolder;
    }

    public final synchronized void zzc() {
        this.zza.clear();
    }

    public final synchronized void zzd(Location location) {
        this.zza.notifyListener(new zzat(this, location));
    }
}
