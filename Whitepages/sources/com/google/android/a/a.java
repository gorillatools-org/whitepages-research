package com.google.android.a;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public abstract class a implements IInterface {
    private final IBinder a;
    private final String b = "com.google.android.finsky.externalreferrer.IGetInstallReferrerService";

    protected a(IBinder iBinder) {
        this.a = iBinder;
    }

    /* access modifiers changed from: protected */
    public final Parcel a() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.b);
        return obtain;
    }

    public final IBinder asBinder() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public final Parcel b(Parcel parcel) {
        parcel = Parcel.obtain();
        try {
            this.a.transact(1, parcel, parcel, 0);
            parcel.readException();
            return parcel;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            parcel.recycle();
        }
    }
}
