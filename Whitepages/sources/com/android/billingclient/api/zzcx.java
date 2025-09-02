package com.android.billingclient.api;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;

public final /* synthetic */ class zzcx implements ActivityResultCallback {
    public final /* synthetic */ ProxyBillingActivityV2 zza;

    public /* synthetic */ zzcx(ProxyBillingActivityV2 proxyBillingActivityV2) {
        this.zza = proxyBillingActivityV2;
    }

    public final void onActivityResult(Object obj) {
        this.zza.zzb((ActivityResult) obj);
    }
}
