package com.google.android.play.core.review.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

final class zzs implements ServiceConnection {
    final /* synthetic */ zzt zza;

    /* synthetic */ zzs(zzt zzt, zzr zzr) {
        this.zza = zzt;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.zza.zzc.zzd("ServiceConnectionImpl.onServiceConnected(%s)", componentName);
        zzt zzt = this.zza;
        zzt.zzc().post(new zzp(this, iBinder));
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.zza.zzc.zzd("ServiceConnectionImpl.onServiceDisconnected(%s)", componentName);
        zzt zzt = this.zza;
        zzt.zzc().post(new zzq(this));
    }
}
