package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzda extends zzbn implements zzdb {
    public zzda() {
        super("com.google.android.gms.measurement.api.internal.IDynamiteUploadBatchesCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 2) {
            return false;
        }
        zze();
        return true;
    }
}
