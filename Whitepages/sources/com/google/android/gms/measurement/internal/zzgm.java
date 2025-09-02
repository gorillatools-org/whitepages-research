package com.google.android.gms.measurement.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzbm;
import java.util.List;

public final class zzgm extends zzbm implements zzgo {
    zzgm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.internal.ITriggerUrisCallback");
    }

    public final void zze(List list) throws RemoteException {
        Parcel zza = zza();
        zza.writeTypedList(list);
        zzd(2, zza);
    }
}
