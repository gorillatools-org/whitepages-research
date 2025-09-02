package com.google.android.gms.auth;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.auth.zze;
import java.io.IOException;

final class zzi implements zzk {
    final /* synthetic */ AccountChangeEventsRequest zza;

    zzi(AccountChangeEventsRequest accountChangeEventsRequest) {
        this.zza = accountChangeEventsRequest;
    }

    public final /* bridge */ /* synthetic */ Object zza(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
        AccountChangeEventsResponse zzh = zze.zzb(iBinder).zzh(this.zza);
        Object unused = zzl.zzj(zzh);
        return zzh.getEvents();
    }
}
