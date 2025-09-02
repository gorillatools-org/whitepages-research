package com.google.android.gms.fido.u2f;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fido.u2f.api.common.RegisterRequestParams;
import com.google.android.gms.internal.fido.zzw;
import com.google.android.gms.internal.fido.zzy;
import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class zzb implements RemoteCall {
    public final /* synthetic */ U2fApiClient zza;
    public final /* synthetic */ RegisterRequestParams zzb;

    public /* synthetic */ zzb(U2fApiClient u2fApiClient, RegisterRequestParams registerRequestParams) {
        this.zza = u2fApiClient;
        this.zzb = registerRequestParams;
    }

    public final void accept(Object obj, Object obj2) {
        U2fApiClient u2fApiClient = this.zza;
        RegisterRequestParams registerRequestParams = this.zzb;
        ((zzw) ((zzy) obj).getService()).zzc(new zzc(u2fApiClient, (TaskCompletionSource) obj2), registerRequestParams);
    }
}
