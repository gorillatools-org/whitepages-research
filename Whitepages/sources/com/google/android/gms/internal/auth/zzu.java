package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class zzu implements RemoteCall {
    public final /* synthetic */ zzab zza;
    public final /* synthetic */ AccountChangeEventsRequest zzb;

    public /* synthetic */ zzu(zzab zzab, AccountChangeEventsRequest accountChangeEventsRequest) {
        this.zza = zzab;
        this.zzb = accountChangeEventsRequest;
    }

    public final void accept(Object obj, Object obj2) {
        zzab zzab = this.zza;
        AccountChangeEventsRequest accountChangeEventsRequest = this.zzb;
        ((zzp) ((zzi) obj).getService()).zze(new zzz(zzab, (TaskCompletionSource) obj2), accountChangeEventsRequest);
    }
}
