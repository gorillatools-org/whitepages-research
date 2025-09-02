package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.proxy.ProxyResponse;

final class zzbp extends zzbd {
    final /* synthetic */ zzbq zza;

    zzbp(zzbq zzbq) {
        this.zza = zzbq;
    }

    public final void zzb(ProxyResponse proxyResponse) {
        this.zza.setResult(new zzbu(proxyResponse));
    }
}
