package com.google.android.gms.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzar implements RemoteCall {
    private final PendingIntent zza;

    zzar(PendingIntent pendingIntent) {
        this.zza = pendingIntent;
    }

    public final void accept(Object obj, Object obj2) {
        ((zzaz) obj).zzx(this.zza, new zzat((TaskCompletionSource) obj2));
    }
}
