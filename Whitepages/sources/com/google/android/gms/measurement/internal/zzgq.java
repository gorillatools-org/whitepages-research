package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzbn;
import com.google.android.gms.internal.measurement.zzbo;

public abstract class zzgq extends zzbn implements zzgr {
    public zzgq() {
        super("com.google.android.gms.measurement.internal.IUploadBatchesCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 2) {
            return false;
        }
        zzbo.zzc(parcel);
        zze((zzpe) zzbo.zza(parcel, zzpe.CREATOR));
        return true;
    }
}
