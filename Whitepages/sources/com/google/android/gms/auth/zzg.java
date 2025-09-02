package com.google.android.gms.auth;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;

public final /* synthetic */ class zzg implements zzk {
    public final /* synthetic */ Account zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ Bundle zzc;
    public final /* synthetic */ Context zzd;

    public /* synthetic */ zzg(Account account, String str, Bundle bundle, Context context) {
        this.zza = account;
        this.zzb = str;
        this.zzc = bundle;
        this.zzd = context;
    }

    public final Object zza(IBinder iBinder) {
        return zzl.zzb(this.zza, this.zzb, this.zzc, this.zzd, iBinder);
    }
}
