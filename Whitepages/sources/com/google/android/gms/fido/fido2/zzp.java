package com.google.android.gms.fido.fido2;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.fido.zzk;
import com.google.android.gms.internal.fido.zzn;
import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class zzp implements RemoteCall {
    public final /* synthetic */ Fido2PrivilegedApiClient zza;

    public /* synthetic */ zzp(Fido2PrivilegedApiClient fido2PrivilegedApiClient) {
        this.zza = fido2PrivilegedApiClient;
    }

    public final void accept(Object obj, Object obj2) {
        ((zzn) ((zzk) obj).getService()).zzf(new zzu(this.zza, (TaskCompletionSource) obj2));
    }
}
