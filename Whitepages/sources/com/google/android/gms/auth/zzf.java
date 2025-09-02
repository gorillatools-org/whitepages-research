package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import com.google.android.gms.internal.auth.zze;
import java.io.IOException;

public final /* synthetic */ class zzf implements zzk {
    public final /* synthetic */ Account zza;

    public /* synthetic */ zzf(Account account) {
        this.zza = account;
    }

    public final Object zza(IBinder iBinder) {
        Bundle zzf = zze.zzb(iBinder).zzf(this.zza);
        if (zzf != null) {
            return zzf;
        }
        throw new IOException("Service call returned null.");
    }
}
