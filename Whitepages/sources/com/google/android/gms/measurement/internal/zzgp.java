package com.google.android.gms.measurement.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzbm;
import com.google.android.gms.internal.measurement.zzbo;

public final class zzgp extends zzbm implements zzgr {
    zzgp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.internal.IUploadBatchesCallback");
    }

    public final void zze(zzpe zzpe) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzpe);
        zzd(2, zza);
    }
}
