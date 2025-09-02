package com.google.android.gms.location;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzw implements RemoteCall {
    static final RemoteCall zza = new zzw();

    private zzw() {
    }

    public final void accept(Object obj, Object obj2) {
        ((zzaz) obj).zzK(new zzao((TaskCompletionSource) obj2));
    }
}
