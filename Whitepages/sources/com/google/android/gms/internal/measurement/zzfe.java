package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

final class zzfe implements Application.ActivityLifecycleCallbacks {
    final /* synthetic */ zzff zza;

    zzfe(zzff zzff) {
        this.zza = zzff;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        this.zza.zzW(new zzex(this, bundle, activity));
    }

    public final void onActivityDestroyed(Activity activity) {
        this.zza.zzW(new zzfd(this, activity));
    }

    public final void onActivityPaused(Activity activity) {
        this.zza.zzW(new zzfa(this, activity));
    }

    public final void onActivityResumed(Activity activity) {
        this.zza.zzW(new zzez(this, activity));
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zzcs zzcs = new zzcs();
        this.zza.zzW(new zzfc(this, activity, zzcs));
        Bundle zzb = zzcs.zzb(50);
        if (zzb != null) {
            bundle.putAll(zzb);
        }
    }

    public final void onActivityStarted(Activity activity) {
        this.zza.zzW(new zzey(this, activity));
    }

    public final void onActivityStopped(Activity activity) {
        this.zza.zzW(new zzfb(this, activity));
    }
}
