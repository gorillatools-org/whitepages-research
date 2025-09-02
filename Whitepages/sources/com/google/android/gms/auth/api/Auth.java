package com.google.android.gms.auth.api;

import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.zbd;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.ShowFirstParty;

public final class Auth {
    public static final Api<GoogleSignInOptions> GOOGLE_SIGN_IN_API;
    public static final GoogleSignInApi GoogleSignInApi = new zbd();
    @ShowFirstParty
    @KeepForSdk
    @Deprecated
    public static final Api<AuthProxyOptions> PROXY_API = AuthProxy.API;
    @ShowFirstParty
    @KeepForSdk
    @Deprecated
    public static final ProxyApi ProxyApi = AuthProxy.ProxyApi;
    public static final Api.ClientKey zba;
    public static final Api.ClientKey zbb;
    private static final Api.AbstractClientBuilder zbc;
    private static final Api.AbstractClientBuilder zbd;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zba = clientKey;
        Api.ClientKey clientKey2 = new Api.ClientKey();
        zbb = clientKey2;
        zba zba2 = new zba();
        zbc = zba2;
        zbb zbb2 = new zbb();
        zbd = zbb2;
        new Api("Auth.CREDENTIALS_API", zba2, clientKey);
        GOOGLE_SIGN_IN_API = new Api<>("Auth.GOOGLE_SIGN_IN_API", zbb2, clientKey2);
    }

    private Auth() {
    }
}
