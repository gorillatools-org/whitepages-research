package com.google.android.gms.location;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

final /* synthetic */ class zzas implements RemoteCall {
    private final List zza;

    zzas(List list) {
        this.zza = list;
    }

    public final void accept(Object obj, Object obj2) {
        ((zzaz) obj).zzy(this.zza, new zzat((TaskCompletionSource) obj2));
    }
}
