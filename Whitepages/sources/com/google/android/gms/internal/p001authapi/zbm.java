package com.google.android.gms.internal.p001authapi;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenRequest;
import com.google.android.gms.auth.api.identity.SavePasswordRequest;

/* renamed from: com.google.android.gms.internal.auth-api.zbm  reason: invalid package */
public final class zbm extends zba implements IInterface {
    zbm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.identity.internal.ICredentialSavingService");
    }

    public final void zbc(zbs zbs, SaveAccountLinkingTokenRequest saveAccountLinkingTokenRequest) throws RemoteException {
        Parcel zba = zba();
        zbc.zbd(zba, zbs);
        zbc.zbc(zba, saveAccountLinkingTokenRequest);
        zbb(1, zba);
    }

    public final void zbd(zbu zbu, SavePasswordRequest savePasswordRequest) throws RemoteException {
        Parcel zba = zba();
        zbc.zbd(zba, zbu);
        zbc.zbc(zba, savePasswordRequest);
        zbb(2, zba);
    }
}
