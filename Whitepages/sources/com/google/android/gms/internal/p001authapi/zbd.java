package com.google.android.gms.internal.p001authapi;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.auth.api.zbc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;

/* renamed from: com.google.android.gms.internal.auth-api.zbd  reason: invalid package */
public final class zbd extends GmsClient {
    private final com.google.android.gms.auth.api.zbd zba;

    public zbd(Context context, Looper looper, ClientSettings clientSettings, com.google.android.gms.auth.api.zbd zbd, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 68, clientSettings, connectionCallbacks, onConnectionFailedListener);
        zbc zbc = new zbc(zbd == null ? com.google.android.gms.auth.api.zbd.zba : zbd);
        zbc.zba(zbas.zba());
        this.zba = new com.google.android.gms.auth.api.zbd(zbc);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
        return queryLocalInterface instanceof zbe ? (zbe) queryLocalInterface : new zbe(iBinder);
    }

    /* access modifiers changed from: protected */
    public final Bundle getGetServiceRequestExtraArgs() {
        return this.zba.zba();
    }

    public final int getMinApkVersion() {
        return 12800000;
    }

    /* access modifiers changed from: protected */
    public final String getServiceDescriptor() {
        return "com.google.android.gms.auth.api.credentials.internal.ICredentialsService";
    }

    /* access modifiers changed from: protected */
    public final String getStartServiceAction() {
        return "com.google.android.gms.auth.api.credentials.service.START";
    }
}
