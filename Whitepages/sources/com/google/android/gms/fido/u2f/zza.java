package com.google.android.gms.fido.u2f;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fido.u2f.api.common.SignRequestParams;
import com.google.android.gms.internal.fido.zzw;
import com.google.android.gms.internal.fido.zzy;
import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class zza implements RemoteCall {
    public final /* synthetic */ U2fApiClient zza;
    public final /* synthetic */ SignRequestParams zzb;

    public /* synthetic */ zza(U2fApiClient u2fApiClient, SignRequestParams signRequestParams) {
        this.zza = u2fApiClient;
        this.zzb = signRequestParams;
    }

    public final void accept(Object obj, Object obj2) {
        U2fApiClient u2fApiClient = this.zza;
        SignRequestParams signRequestParams = this.zzb;
        ((zzw) ((zzy) obj).getService()).zzd(new zzd(u2fApiClient, (TaskCompletionSource) obj2), signRequestParams);
    }
}
