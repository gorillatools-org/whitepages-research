package com.google.android.gms.internal.p001authapi;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.auth.api.identity.zba;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;

/* renamed from: com.google.android.gms.internal.auth-api.zbx  reason: invalid package */
final class zbx extends Api.AbstractClientBuilder {
    zbx() {
    }

    public final /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        return new zbf(context, looper, (zba) obj, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }
}
