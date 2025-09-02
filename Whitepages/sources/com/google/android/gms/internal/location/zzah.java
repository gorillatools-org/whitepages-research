package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzah extends zzb implements zzai {
    public zzah() {
        super("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzb((zzaa) zzc.zzb(parcel, zzaa.CREATOR));
        } else if (i != 2) {
            return false;
        } else {
            zzc();
        }
        return true;
    }
}
