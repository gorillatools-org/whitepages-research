package com.google.android.gms.internal.auth;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.api.accounttransfer.DeviceMetaData;
import com.google.android.gms.auth.api.accounttransfer.zzo;
import com.google.android.gms.auth.api.accounttransfer.zzw;
import com.google.android.gms.common.api.Status;

public interface zzat extends IInterface {
    void zzb(byte[] bArr) throws RemoteException;

    void zzc(DeviceMetaData deviceMetaData) throws RemoteException;

    void zzd(Status status) throws RemoteException;

    void zze() throws RemoteException;

    void zzf(Status status, zzw zzw) throws RemoteException;

    void zzg(Status status, zzo zzo) throws RemoteException;

    void zzh(Status status) throws RemoteException;
}
