package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.internal.p001authapi.zbas;

public final class zbe extends GmsClient {
    private final GoogleSignInOptions zba;

    public zbe(Context context, Looper looper, ClientSettings clientSettings, GoogleSignInOptions googleSignInOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 91, clientSettings, connectionCallbacks, onConnectionFailedListener);
        GoogleSignInOptions.Builder builder;
        if (googleSignInOptions != null) {
            builder = new GoogleSignInOptions.Builder(googleSignInOptions);
        } else {
            builder = new GoogleSignInOptions.Builder();
        }
        builder.setLogSessionId(zbas.zba());
        if (!clientSettings.getAllRequestedScopes().isEmpty()) {
            for (Scope requestScopes : clientSettings.getAllRequestedScopes()) {
                builder.requestScopes(requestScopes, new Scope[0]);
            }
        }
        this.zba = builder.build();
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.api.signin.internal.ISignInService");
        return queryLocalInterface instanceof zbs ? (zbs) queryLocalInterface : new zbs(iBinder);
    }

    public final int getMinApkVersion() {
        return GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    /* access modifiers changed from: protected */
    public final String getServiceDescriptor() {
        return "com.google.android.gms.auth.api.signin.internal.ISignInService";
    }

    public final Intent getSignInIntent() {
        return zbm.zbc(getContext(), this.zba);
    }

    /* access modifiers changed from: protected */
    public final String getStartServiceAction() {
        return "com.google.android.gms.auth.api.signin.service.START";
    }

    public final boolean providesSignIn() {
        return true;
    }

    public final GoogleSignInOptions zba() {
        return this.zba;
    }
}
