package com.google.android.gms.fido.fido2;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions;
import com.google.android.gms.internal.fido.zzp;
import com.google.android.gms.internal.fido.zzs;
import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class zze implements RemoteCall {
    public final /* synthetic */ Fido2ApiClient zza;
    public final /* synthetic */ PublicKeyCredentialRequestOptions zzb;

    public /* synthetic */ zze(Fido2ApiClient fido2ApiClient, PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions) {
        this.zza = fido2ApiClient;
        this.zzb = publicKeyCredentialRequestOptions;
    }

    public final void accept(Object obj, Object obj2) {
        Fido2ApiClient fido2ApiClient = this.zza;
        PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions = this.zzb;
        ((zzs) ((zzp) obj).getService()).zzd(new zzg(fido2ApiClient, (TaskCompletionSource) obj2), publicKeyCredentialRequestOptions);
    }
}
