package com.google.android.gms.fido.fido2;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions;
import com.google.android.gms.internal.fido.zzp;
import com.google.android.gms.internal.fido.zzs;
import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class zzd implements RemoteCall {
    public final /* synthetic */ Fido2ApiClient zza;
    public final /* synthetic */ PublicKeyCredentialCreationOptions zzb;

    public /* synthetic */ zzd(Fido2ApiClient fido2ApiClient, PublicKeyCredentialCreationOptions publicKeyCredentialCreationOptions) {
        this.zza = fido2ApiClient;
        this.zzb = publicKeyCredentialCreationOptions;
    }

    public final void accept(Object obj, Object obj2) {
        Fido2ApiClient fido2ApiClient = this.zza;
        PublicKeyCredentialCreationOptions publicKeyCredentialCreationOptions = this.zzb;
        ((zzs) ((zzp) obj).getService()).zzc(new zzh(fido2ApiClient, (TaskCompletionSource) obj2), publicKeyCredentialCreationOptions);
    }
}
