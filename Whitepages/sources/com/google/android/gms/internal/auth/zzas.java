package com.google.android.gms.internal.auth;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.accounttransfer.DeviceMetaData;
import com.google.android.gms.auth.api.accounttransfer.zzo;
import com.google.android.gms.auth.api.accounttransfer.zzw;
import com.google.android.gms.common.api.Status;

public abstract class zzas extends zzb implements zzat {
    public zzas() {
        super("com.google.android.gms.auth.api.accounttransfer.internal.IAccountTransferCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zzc.zzb(parcel);
                zzh((Status) zzc.zza(parcel, Status.CREATOR));
                return true;
            case 2:
                zzc.zzb(parcel);
                zzf((Status) zzc.zza(parcel, Status.CREATOR), (zzw) zzc.zza(parcel, zzw.CREATOR));
                return true;
            case 3:
                zzc.zzb(parcel);
                zzg((Status) zzc.zza(parcel, Status.CREATOR), (zzo) zzc.zza(parcel, zzo.CREATOR));
                return true;
            case 4:
                zze();
                return true;
            case 5:
                zzc.zzb(parcel);
                zzd((Status) zzc.zza(parcel, Status.CREATOR));
                return true;
            case 6:
                byte[] createByteArray = parcel.createByteArray();
                zzc.zzb(parcel);
                zzb(createByteArray);
                return true;
            case 7:
                zzc.zzb(parcel);
                zzc((DeviceMetaData) zzc.zza(parcel, DeviceMetaData.CREATOR));
                return true;
            default:
                return false;
        }
    }
}
