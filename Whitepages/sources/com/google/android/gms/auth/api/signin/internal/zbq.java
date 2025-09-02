package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.p001authapi.zbb;
import com.google.android.gms.internal.p001authapi.zbc;
import com.salesforce.marketingcloud.analytics.stats.b;

public abstract class zbq extends zbb implements zbr {
    public zbq() {
        super("com.google.android.gms.auth.api.signin.internal.ISignInCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean zba(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 101:
                zbc.zbb(parcel);
                zbd((GoogleSignInAccount) zbc.zba(parcel, GoogleSignInAccount.CREATOR), (Status) zbc.zba(parcel, Status.CREATOR));
                break;
            case 102:
                zbc.zbb(parcel);
                zbc((Status) zbc.zba(parcel, Status.CREATOR));
                break;
            case b.i /*103*/:
                zbc.zbb(parcel);
                zbb((Status) zbc.zba(parcel, Status.CREATOR));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
