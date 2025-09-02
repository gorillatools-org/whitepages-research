package com.google.android.gms.cloudmessaging;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.internal.cloudmessaging.zze;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class zzv {
    private static zzv zza;
    /* access modifiers changed from: private */
    public final Context zzb;
    /* access modifiers changed from: private */
    public final ScheduledExecutorService zzc;
    private zzp zzd = new zzp(this, (zzo) null);
    private int zze = 1;

    zzv(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.zzc = scheduledExecutorService;
        this.zzb = context.getApplicationContext();
    }

    public static synchronized zzv zzb(Context context) {
        zzv zzv;
        synchronized (zzv.class) {
            try {
                if (zza == null) {
                    zze.zza();
                    zza = new zzv(context, Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(1, new NamedThreadFactory("MessengerIpcClient"))));
                }
                zzv = zza;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return zzv;
    }

    private final synchronized int zzf() {
        int i;
        i = this.zze;
        this.zze = i + 1;
        return i;
    }

    private final synchronized Task zzg(zzs zzs) {
        try {
            if (Log.isLoggable("MessengerIpcClient", 3)) {
                Log.d("MessengerIpcClient", "Queueing ".concat(zzs.toString()));
            }
            if (!this.zzd.zzg(zzs)) {
                zzp zzp = new zzp(this, (zzo) null);
                this.zzd = zzp;
                zzp.zzg(zzs);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return zzs.zzb.getTask();
    }

    public final Task zzc(int i, Bundle bundle) {
        return zzg(new zzr(zzf(), i, bundle));
    }

    public final Task zzd(int i, Bundle bundle) {
        return zzg(new zzu(zzf(), i, bundle));
    }
}
