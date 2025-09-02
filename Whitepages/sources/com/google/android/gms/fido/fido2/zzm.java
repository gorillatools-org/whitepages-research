package com.google.android.gms.fido.fido2;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fido.fido2.api.common.BrowserPublicKeyCredentialRequestOptions;
import com.google.android.gms.internal.fido.zzk;
import com.google.android.gms.internal.fido.zzn;
import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class zzm implements RemoteCall {
    public final /* synthetic */ Fido2PrivilegedApiClient zza;
    public final /* synthetic */ BrowserPublicKeyCredentialRequestOptions zzb;

    public /* synthetic */ zzm(Fido2PrivilegedApiClient fido2PrivilegedApiClient, BrowserPublicKeyCredentialRequestOptions browserPublicKeyCredentialRequestOptions) {
        this.zza = fido2PrivilegedApiClient;
        this.zzb = browserPublicKeyCredentialRequestOptions;
    }

    public final void accept(Object obj, Object obj2) {
        Fido2PrivilegedApiClient fido2PrivilegedApiClient = this.zza;
        BrowserPublicKeyCredentialRequestOptions browserPublicKeyCredentialRequestOptions = this.zzb;
        ((zzn) ((zzk) obj).getService()).zze(new zzr(fido2PrivilegedApiClient, (TaskCompletionSource) obj2), browserPublicKeyCredentialRequestOptions);
    }
}
