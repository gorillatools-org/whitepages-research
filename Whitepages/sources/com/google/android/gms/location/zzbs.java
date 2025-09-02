package com.google.android.gms.location;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzbs implements RemoteCall {
    private final LocationSettingsRequest zza;

    zzbs(LocationSettingsRequest locationSettingsRequest) {
        this.zza = locationSettingsRequest;
    }

    public final void accept(Object obj, Object obj2) {
        ((zzaz) obj).zzL(this.zza, new zzbt((TaskCompletionSource) obj2), (String) null);
    }
}
