package com.google.android.gms.common.internal;

import android.util.Log;

public abstract class zzc {
    private Object zza;
    private boolean zzb = false;
    final /* synthetic */ BaseGmsClient zzd;

    public zzc(BaseGmsClient baseGmsClient, Object obj) {
        this.zzd = baseGmsClient;
        this.zza = obj;
    }

    /* access modifiers changed from: protected */
    public abstract void zza(Object obj);

    /* access modifiers changed from: protected */
    public abstract void zzc();

    public final void zze() {
        Object obj;
        synchronized (this) {
            try {
                obj = this.zza;
                if (this.zzb) {
                    String obj2 = toString();
                    Log.w("GmsClient", "Callback proxy " + obj2 + " being reused. This is not safe.");
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (obj != null) {
            zza(obj);
        }
        synchronized (this) {
            this.zzb = true;
        }
        zzg();
    }

    public final void zzf() {
        synchronized (this) {
            this.zza = null;
        }
    }

    public final void zzg() {
        zzf();
        synchronized (this.zzd.zzt) {
            this.zzd.zzt.remove(this);
        }
    }
}
