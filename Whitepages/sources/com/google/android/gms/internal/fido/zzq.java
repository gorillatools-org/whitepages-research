package com.google.android.gms.internal.fido;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public abstract class zzq extends zzb implements zzr {
    public zzq() {
        super("com.google.android.gms.fido.fido2.internal.regular.IFido2AppCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zzc.zzc(parcel);
        zzb((Status) zzc.zza(parcel, Status.CREATOR), (PendingIntent) zzc.zza(parcel, PendingIntent.CREATOR));
        return true;
    }
}
