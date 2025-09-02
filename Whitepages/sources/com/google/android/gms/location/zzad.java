package com.google.android.gms.location;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzad implements RemoteCall {
    static final RemoteCall zza = new zzad();

    private zzad() {
    }

    public final void accept(Object obj, Object obj2) {
        ((TaskCompletionSource) obj2).setResult(((zzaz) obj).zzA());
    }
}
