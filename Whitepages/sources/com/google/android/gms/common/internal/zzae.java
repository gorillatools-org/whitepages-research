package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.zzo;
import com.google.android.gms.common.zzq;
import com.google.android.gms.common.zzs;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zza;
import com.google.android.gms.internal.common.zzc;

public final class zzae extends zza implements zzag {
    zzae(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IGoogleCertificatesApi");
    }

    public final zzq zze(zzo zzo) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, zzo);
        Parcel zzB = zzB(6, zza);
        zzq zzq = (zzq) zzc.zza(zzB, zzq.CREATOR);
        zzB.recycle();
        return zzq;
    }

    public final zzq zzf(zzo zzo) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, zzo);
        Parcel zzB = zzB(8, zza);
        zzq zzq = (zzq) zzc.zza(zzB, zzq.CREATOR);
        zzB.recycle();
        return zzq;
    }

    public final boolean zzg() throws RemoteException {
        Parcel zzB = zzB(9, zza());
        boolean zzf = zzc.zzf(zzB);
        zzB.recycle();
        return zzf;
    }

    public final boolean zzh(zzs zzs, IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, zzs);
        zzc.zze(zza, iObjectWrapper);
        Parcel zzB = zzB(5, zza);
        boolean zzf = zzc.zzf(zzB);
        zzB.recycle();
        return zzf;
    }

    public final boolean zzi() throws RemoteException {
        Parcel zzB = zzB(7, zza());
        boolean zzf = zzc.zzf(zzB);
        zzB.recycle();
        return zzf;
    }
}
