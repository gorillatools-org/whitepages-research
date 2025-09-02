package com.google.android.gms.internal.fido;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;

public final class zzj extends Api.AbstractClientBuilder {
    public final /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Api.ApiOptions.NoOptions noOptions = (Api.ApiOptions.NoOptions) obj;
        return new zzk(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }
}
