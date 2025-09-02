package com.google.android.gms.fido.fido2;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fido.fido2.api.common.BrowserPublicKeyCredentialCreationOptions;
import com.google.android.gms.internal.fido.zzk;
import com.google.android.gms.internal.fido.zzn;
import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class zzo implements RemoteCall {
    public final /* synthetic */ Fido2PrivilegedApiClient zza;
    public final /* synthetic */ BrowserPublicKeyCredentialCreationOptions zzb;

    public /* synthetic */ zzo(Fido2PrivilegedApiClient fido2PrivilegedApiClient, BrowserPublicKeyCredentialCreationOptions browserPublicKeyCredentialCreationOptions) {
        this.zza = fido2PrivilegedApiClient;
        this.zzb = browserPublicKeyCredentialCreationOptions;
    }

    public final void accept(Object obj, Object obj2) {
        Fido2PrivilegedApiClient fido2PrivilegedApiClient = this.zza;
        BrowserPublicKeyCredentialCreationOptions browserPublicKeyCredentialCreationOptions = this.zzb;
        ((zzn) ((zzk) obj).getService()).zzd(new zzs(fido2PrivilegedApiClient, (TaskCompletionSource) obj2), browserPublicKeyCredentialCreationOptions);
    }
}
