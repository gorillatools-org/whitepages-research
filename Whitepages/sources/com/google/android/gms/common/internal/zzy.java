package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zza;

public final class zzy extends zza implements zzaa {
    zzy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.ICertData");
    }

    public final int zzc() throws RemoteException {
        Parcel zzB = zzB(2, zza());
        int readInt = zzB.readInt();
        zzB.recycle();
        return readInt;
    }

    public final IObjectWrapper zzd() throws RemoteException {
        Parcel zzB = zzB(1, zza());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zzB.readStrongBinder());
        zzB.recycle();
        return asInterface;
    }
}
