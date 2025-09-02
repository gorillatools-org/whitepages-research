package com.google.android.gms.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.location.zzb;
import com.google.android.gms.internal.location.zzc;

public abstract class zzaz extends zzb implements zzba {
    public zzaz() {
        super("com.google.android.gms.location.ILocationCallback");
    }

    public static zzba zzb(IBinder iBinder) {
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.ILocationCallback");
        if (queryLocalInterface instanceof zzba) {
            return (zzba) queryLocalInterface;
        }
        return new zzay(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzd((LocationResult) zzc.zzb(parcel, LocationResult.CREATOR));
        } else if (i != 2) {
            return false;
        } else {
            zze((LocationAvailability) zzc.zzb(parcel, LocationAvailability.CREATOR));
        }
        return true;
    }
}
