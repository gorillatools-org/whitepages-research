package com.google.android.gms.auth.api;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.proxy.ProxyClient;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.internal.auth.zzbo;
import com.google.android.gms.internal.auth.zzbt;

@ShowFirstParty
@KeepForSdk
public final class AuthProxy {
    @ShowFirstParty
    @KeepForSdk
    public static final Api<AuthProxyOptions> API;
    @ShowFirstParty
    @KeepForSdk
    public static final ProxyApi ProxyApi = new zzbt();
    public static final Api.ClientKey zza;
    private static final Api.AbstractClientBuilder zzb;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zza = clientKey;
        zza zza2 = new zza();
        zzb = zza2;
        API = new Api<>("Auth.PROXY_API", zza2, clientKey);
    }

    @KeepForSdk
    public static ProxyClient getClient(Activity activity, AuthProxyOptions authProxyOptions) {
        return new zzbo(activity, authProxyOptions);
    }

    @KeepForSdk
    public static ProxyClient getClient(Context context, AuthProxyOptions authProxyOptions) {
        return new zzbo(context, authProxyOptions);
    }
}
