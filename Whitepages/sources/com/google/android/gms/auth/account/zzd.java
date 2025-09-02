package com.google.android.gms.auth.account;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.internal.auth.zzb;

public abstract class zzd extends zzb implements zze {
    public static zze zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.account.IWorkAccountService");
        return queryLocalInterface instanceof zze ? (zze) queryLocalInterface : new zzc(iBinder);
    }
}
