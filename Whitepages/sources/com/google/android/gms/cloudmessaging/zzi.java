package com.google.android.gms.cloudmessaging;

import android.os.IBinder;
import android.os.RemoteException;

public final /* synthetic */ class zzi implements Runnable {
    public final /* synthetic */ zzp zza;
    public final /* synthetic */ IBinder zzb;

    public /* synthetic */ zzi(zzp zzp, IBinder iBinder) {
        this.zza = zzp;
        this.zzb = iBinder;
    }

    public final void run() {
        zzp zzp = this.zza;
        IBinder iBinder = this.zzb;
        synchronized (zzp) {
            if (iBinder == null) {
                zzp.zza(0, "Null service connection");
                return;
            }
            try {
                zzp.zzc = new zzq(iBinder);
                zzp.zza = 2;
                zzp.zzc();
            } catch (RemoteException e) {
                zzp.zza(0, e.getMessage());
            }
        }
    }
}
