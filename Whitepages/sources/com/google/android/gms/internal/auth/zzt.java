package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class zzt implements RemoteCall {
    public final /* synthetic */ zzab zza;
    public final /* synthetic */ zzbw zzb;

    public /* synthetic */ zzt(zzab zzab, zzbw zzbw) {
        this.zza = zzab;
        this.zzb = zzbw;
    }

    public final void accept(Object obj, Object obj2) {
        zzab zzab = this.zza;
        ((zzp) ((zzi) obj).getService()).zzd(new zzx(zzab, (TaskCompletionSource) obj2), this.zzb);
    }
}
