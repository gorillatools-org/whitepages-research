package com.google.android.gms.internal.p001authapi;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.AuthorizationRequest;

/* renamed from: com.google.android.gms.internal.auth-api.zbj  reason: invalid package */
public final class zbj extends zba implements IInterface {
    zbj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.identity.internal.IAuthorizationService");
    }

    public final void zbc(zbi zbi, AuthorizationRequest authorizationRequest) throws RemoteException {
        Parcel zba = zba();
        zbc.zbd(zba, zbi);
        zbc.zbc(zba, authorizationRequest);
        zbb(1, zba);
    }
}
