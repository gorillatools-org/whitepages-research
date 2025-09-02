package com.google.android.gms.location;

import android.location.Location;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzai implements RemoteCall {
    private final Location zza;

    zzai(Location location) {
        this.zza = location;
    }

    public final void accept(Object obj, Object obj2) {
        ((zzaz) obj).zzJ(this.zza);
        ((TaskCompletionSource) obj2).setResult(null);
    }
}
