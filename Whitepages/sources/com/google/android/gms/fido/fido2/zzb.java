package com.google.android.gms.fido.fido2;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.fido.zzp;
import com.google.android.gms.internal.fido.zzs;
import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class zzb implements RemoteCall {
    public final /* synthetic */ Fido2ApiClient zza;

    public /* synthetic */ zzb(Fido2ApiClient fido2ApiClient) {
        this.zza = fido2ApiClient;
    }

    public final void accept(Object obj, Object obj2) {
        ((zzs) ((zzp) obj).getService()).zze(new zzj(this.zza, (TaskCompletionSource) obj2));
    }
}
