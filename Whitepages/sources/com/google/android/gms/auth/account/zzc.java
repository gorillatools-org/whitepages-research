package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.auth.zza;

public final class zzc extends zza implements zze {
    zzc(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.account.IWorkAccountService");
    }

    public final void zzd(zzb zzb, String str) throws RemoteException {
        Parcel zza = zza();
        com.google.android.gms.internal.auth.zzc.zzd(zza, zzb);
        zza.writeString(str);
        zzc(2, zza);
    }

    public final void zze(zzb zzb, Account account) throws RemoteException {
        Parcel zza = zza();
        com.google.android.gms.internal.auth.zzc.zzd(zza, zzb);
        com.google.android.gms.internal.auth.zzc.zzc(zza, account);
        zzc(3, zza);
    }

    public final void zzf(boolean z) throws RemoteException {
        Parcel zza = zza();
        int i = com.google.android.gms.internal.auth.zzc.zza;
        zza.writeInt(z ? 1 : 0);
        zzc(1, zza);
    }
}
