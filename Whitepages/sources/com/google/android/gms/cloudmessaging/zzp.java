package com.google.android.gms.cloudmessaging;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.cloudmessaging.zzf;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

final class zzp implements ServiceConnection {
    int zza = 0;
    final Messenger zzb = new Messenger(new zzf(Looper.getMainLooper(), new zzm(this)));
    zzq zzc;
    final Queue zzd = new ArrayDeque();
    final SparseArray zze = new SparseArray();
    final /* synthetic */ zzv zzf;

    /* synthetic */ zzp(zzv zzv, zzo zzo) {
        this.zzf = zzv;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        this.zzf.zzc.execute(new zzi(this, iBinder));
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        this.zzf.zzc.execute(new zzl(this));
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zza(int i, String str) {
        zzb(i, str, (Throwable) null);
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzb(int i, String str, Throwable th) {
        try {
            if (Log.isLoggable("MessengerIpcClient", 3)) {
                Log.d("MessengerIpcClient", "Disconnected: ".concat(String.valueOf(str)));
            }
            int i2 = this.zza;
            if (i2 == 0) {
                throw new IllegalStateException();
            } else if (i2 == 1 || i2 == 2) {
                if (Log.isLoggable("MessengerIpcClient", 2)) {
                    Log.v("MessengerIpcClient", "Unbinding service");
                }
                this.zza = 4;
                ConnectionTracker.getInstance().unbindService(this.zzf.zzb, this);
                zzt zzt = new zzt(i, str, th);
                for (zzs zzc2 : this.zzd) {
                    zzc2.zzc(zzt);
                }
                this.zzd.clear();
                for (int i3 = 0; i3 < this.zze.size(); i3++) {
                    ((zzs) this.zze.valueAt(i3)).zzc(zzt);
                }
                this.zze.clear();
            } else if (i2 == 3) {
                this.zza = 4;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzc() {
        this.zzf.zzc.execute(new zzj(this));
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzd() {
        if (this.zza == 1) {
            zza(1, "Timed out while binding");
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zze(int i) {
        zzs zzs = (zzs) this.zze.get(i);
        if (zzs != null) {
            Log.w("MessengerIpcClient", "Timing out request: " + i);
            this.zze.remove(i);
            zzs.zzc(new zzt(3, "Timed out waiting for response", (Throwable) null));
            zzf();
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzf() {
        /*
            r2 = this;
            monitor-enter(r2)
            int r0 = r2.zza     // Catch:{ all -> 0x0026 }
            r1 = 2
            if (r0 != r1) goto L_0x003a
            java.util.Queue r0 = r2.zzd     // Catch:{ all -> 0x0026 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x003a
            android.util.SparseArray r0 = r2.zze     // Catch:{ all -> 0x0026 }
            int r0 = r0.size()     // Catch:{ all -> 0x0026 }
            if (r0 != 0) goto L_0x003a
            java.lang.String r0 = "MessengerIpcClient"
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x0028
            java.lang.String r0 = "MessengerIpcClient"
            java.lang.String r1 = "Finished handling requests, unbinding"
            android.util.Log.v(r0, r1)     // Catch:{ all -> 0x0026 }
            goto L_0x0028
        L_0x0026:
            r0 = move-exception
            goto L_0x003c
        L_0x0028:
            r0 = 3
            r2.zza = r0     // Catch:{ all -> 0x0026 }
            com.google.android.gms.cloudmessaging.zzv r0 = r2.zzf     // Catch:{ all -> 0x0026 }
            com.google.android.gms.common.stats.ConnectionTracker r1 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()     // Catch:{ all -> 0x0026 }
            android.content.Context r0 = r0.zzb     // Catch:{ all -> 0x0026 }
            r1.unbindService(r0, r2)     // Catch:{ all -> 0x0026 }
            monitor-exit(r2)
            return
        L_0x003a:
            monitor-exit(r2)
            return
        L_0x003c:
            monitor-exit(r2)     // Catch:{ all -> 0x0026 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cloudmessaging.zzp.zzf():void");
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean zzg(zzs zzs) {
        int i = this.zza;
        if (i == 0) {
            this.zzd.add(zzs);
            Preconditions.checkState(this.zza == 0);
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Starting bind to GmsCore");
            }
            this.zza = 1;
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gms");
            try {
                if (!ConnectionTracker.getInstance().bindService(this.zzf.zzb, intent, this, 1)) {
                    zza(0, "Unable to bind to service");
                } else {
                    this.zzf.zzc.schedule(new zzk(this), 30, TimeUnit.SECONDS);
                }
            } catch (SecurityException e) {
                zzb(0, "Unable to bind to service", e);
            }
        } else if (i == 1) {
            this.zzd.add(zzs);
            return true;
        } else if (i != 2) {
            return false;
        } else {
            this.zzd.add(zzs);
            zzc();
            return true;
        }
        return true;
    }
}
