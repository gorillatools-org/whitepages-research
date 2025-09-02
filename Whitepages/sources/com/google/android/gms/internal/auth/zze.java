package com.google.android.gms.internal.auth;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zze extends zzb implements zzf {
    public static zzf zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.auth.IAuthManagerService");
        return queryLocalInterface instanceof zzf ? (zzf) queryLocalInterface : new zzd(iBinder);
    }
}
