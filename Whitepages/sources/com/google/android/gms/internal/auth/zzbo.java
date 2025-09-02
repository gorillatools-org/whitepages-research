package com.google.android.gms.internal.auth;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.auth.api.AuthProxy;
import com.google.android.gms.auth.api.AuthProxyOptions;
import com.google.android.gms.auth.api.proxy.ProxyClient;
import com.google.android.gms.auth.api.proxy.ProxyRequest;
import com.google.android.gms.auth.api.proxy.ProxyResponse;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;

public final class zzbo extends GoogleApi implements ProxyClient {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzbo(Activity activity, AuthProxyOptions authProxyOptions) {
        super(activity, AuthProxy.API, authProxyOptions == null ? AuthProxyOptions.zza : authProxyOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public final Task<String> getSpatulaHeader() {
        return doRead(TaskApiCall.builder().run(new zzbk(this)).setMethodKey(1520).build());
    }

    public final Task<ProxyResponse> performProxyRequest(ProxyRequest proxyRequest) {
        return doWrite(TaskApiCall.builder().run(new zzbl(this, proxyRequest)).setMethodKey(1518).build());
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzbo(Context context, AuthProxyOptions authProxyOptions) {
        super(context, AuthProxy.API, authProxyOptions == null ? AuthProxyOptions.zza : authProxyOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
