package com.google.android.gms.location;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzah implements RemoteCall {
    private final boolean zza;

    zzah(boolean z) {
        this.zza = z;
    }

    public final void accept(Object obj, Object obj2) {
        ((zzaz) obj).zzI(this.zza);
        ((TaskCompletionSource) obj2).setResult(null);
    }
}
