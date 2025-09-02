package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class zzbk implements RemoteCall {
    public final /* synthetic */ zzbo zza;

    public /* synthetic */ zzbk(zzbo zzbo) {
        this.zza = zzbo;
    }

    public final void accept(Object obj, Object obj2) {
        ((zzbh) ((zzbe) obj).getService()).zzd(new zzbn(this.zza, (TaskCompletionSource) obj2));
    }
}
