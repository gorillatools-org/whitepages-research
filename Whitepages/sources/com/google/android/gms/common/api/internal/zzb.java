package com.google.android.gms.common.api.internal;

import android.os.Bundle;

final class zzb implements Runnable {
    final /* synthetic */ LifecycleCallback zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzc zzc;

    zzb(zzc zzc2, LifecycleCallback lifecycleCallback, String str) {
        this.zza = lifecycleCallback;
        this.zzb = str;
        this.zzc = zzc2;
    }

    public final void run() {
        Bundle bundle;
        zzc zzc2 = this.zzc;
        if (zzc2.zzb > 0) {
            LifecycleCallback lifecycleCallback = this.zza;
            if (zzc2.zzc != null) {
                bundle = zzc2.zzc.getBundle(this.zzb);
            } else {
                bundle = null;
            }
            lifecycleCallback.onCreate(bundle);
        }
        if (this.zzc.zzb >= 2) {
            this.zza.onStart();
        }
        if (this.zzc.zzb >= 3) {
            this.zza.onResume();
        }
        if (this.zzc.zzb >= 4) {
            this.zza.onStop();
        }
        if (this.zzc.zzb >= 5) {
            this.zza.onDestroy();
        }
    }
}
