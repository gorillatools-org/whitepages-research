package com.google.android.gms.fido.fido2;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.fido.zzn;
import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class zzk implements RemoteCall {
    public final /* synthetic */ Fido2PrivilegedApiClient zza;
    public final /* synthetic */ String zzb;

    public /* synthetic */ zzk(Fido2PrivilegedApiClient fido2PrivilegedApiClient, String str) {
        this.zza = fido2PrivilegedApiClient;
        this.zzb = str;
    }

    public final void accept(Object obj, Object obj2) {
        Fido2PrivilegedApiClient fido2PrivilegedApiClient = this.zza;
        String str = this.zzb;
        ((zzn) ((com.google.android.gms.internal.fido.zzk) obj).getService()).zzc(new zzv(fido2PrivilegedApiClient, (TaskCompletionSource) obj2), str);
    }
}
