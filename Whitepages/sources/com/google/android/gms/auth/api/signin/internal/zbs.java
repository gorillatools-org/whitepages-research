package com.google.android.gms.auth.api.signin.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.internal.p001authapi.zba;
import com.google.android.gms.internal.p001authapi.zbc;
import com.salesforce.marketingcloud.analytics.stats.b;

public final class zbs extends zba implements IInterface {
    zbs(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.signin.internal.ISignInService");
    }

    public final void zbc(zbr zbr, GoogleSignInOptions googleSignInOptions) throws RemoteException {
        Parcel zba = zba();
        zbc.zbd(zba, zbr);
        zbc.zbc(zba, googleSignInOptions);
        zbb(b.i, zba);
    }

    public final void zbd(zbr zbr, GoogleSignInOptions googleSignInOptions) throws RemoteException {
        Parcel zba = zba();
        zbc.zbd(zba, zbr);
        zbc.zbc(zba, googleSignInOptions);
        zbb(102, zba);
    }

    public final void zbe(zbr zbr, GoogleSignInOptions googleSignInOptions) throws RemoteException {
        Parcel zba = zba();
        zbc.zbd(zba, zbr);
        zbc.zbc(zba, googleSignInOptions);
        zbb(101, zba);
    }
}
