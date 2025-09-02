package com.google.android.gms.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzf implements RemoteCall {
    private final ActivityTransitionRequest zza;
    private final PendingIntent zzb;

    zzf(ActivityTransitionRequest activityTransitionRequest, PendingIntent pendingIntent) {
        this.zza = activityTransitionRequest;
        this.zzb = pendingIntent;
    }

    public final void accept(Object obj, Object obj2) {
        ((zzaz) obj).zzr(this.zza, this.zzb, new zzj((TaskCompletionSource) obj2));
    }
}
