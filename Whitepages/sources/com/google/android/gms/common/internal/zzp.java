package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.HashMap;
import java.util.Map;

final class zzp implements ServiceConnection, zzt {
    final /* synthetic */ zzs zza;
    private final Map zzb = new HashMap();
    private int zzc = 2;
    private boolean zzd;
    private IBinder zze;
    private final zzo zzf;
    private ComponentName zzg;

    public zzp(zzs zzs, zzo zzo) {
        this.zza = zzs;
        this.zzf = zzo;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:9|10|11|12|13|14|15) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x005e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* bridge */ /* synthetic */ com.google.android.gms.common.ConnectionResult zzd(com.google.android.gms.common.internal.zzp r9, java.lang.String r10, java.util.concurrent.Executor r11) {
        /*
            com.google.android.gms.common.internal.zzo r0 = r9.zzf     // Catch:{ zzaj -> 0x006a }
            com.google.android.gms.common.internal.zzs r1 = r9.zza     // Catch:{ zzaj -> 0x006a }
            android.content.Context r1 = r1.zzc     // Catch:{ zzaj -> 0x006a }
            android.content.Intent r5 = r0.zzb(r1)     // Catch:{ zzaj -> 0x006a }
            r0 = 3
            r9.zzc = r0
            android.os.StrictMode$VmPolicy r0 = com.google.android.gms.common.util.zzc.zza()
            com.google.android.gms.common.internal.zzs r1 = r9.zza     // Catch:{ all -> 0x004c }
            com.google.android.gms.common.stats.ConnectionTracker r2 = r1.zzf     // Catch:{ all -> 0x004c }
            android.content.Context r3 = r1.zzc     // Catch:{ all -> 0x004c }
            r7 = 4225(0x1081, float:5.92E-42)
            r4 = r10
            r6 = r9
            r8 = r11
            boolean r10 = r2.zza(r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x004c }
            r9.zzd = r10     // Catch:{ all -> 0x004c }
            if (r10 == 0) goto L_0x004e
            com.google.android.gms.common.internal.zzs r10 = r9.zza     // Catch:{ all -> 0x004c }
            android.os.Handler r10 = r10.zzd     // Catch:{ all -> 0x004c }
            com.google.android.gms.common.internal.zzo r11 = r9.zzf     // Catch:{ all -> 0x004c }
            r1 = 1
            android.os.Message r10 = r10.obtainMessage(r1, r11)     // Catch:{ all -> 0x004c }
            com.google.android.gms.common.internal.zzs r11 = r9.zza     // Catch:{ all -> 0x004c }
            android.os.Handler r11 = r11.zzd     // Catch:{ all -> 0x004c }
            com.google.android.gms.common.internal.zzs r9 = r9.zza     // Catch:{ all -> 0x004c }
            long r1 = r9.zzh     // Catch:{ all -> 0x004c }
            r11.sendMessageDelayed(r10, r1)     // Catch:{ all -> 0x004c }
            com.google.android.gms.common.ConnectionResult r9 = com.google.android.gms.common.ConnectionResult.RESULT_SUCCESS     // Catch:{ all -> 0x004c }
        L_0x0048:
            android.os.StrictMode.setVmPolicy(r0)
            goto L_0x006d
        L_0x004c:
            r9 = move-exception
            goto L_0x0066
        L_0x004e:
            r10 = 2
            r9.zzc = r10     // Catch:{ all -> 0x004c }
            com.google.android.gms.common.internal.zzs r10 = r9.zza     // Catch:{ IllegalArgumentException -> 0x005e }
            com.google.android.gms.common.stats.ConnectionTracker r11 = r10.zzf     // Catch:{ IllegalArgumentException -> 0x005e }
            android.content.Context r10 = r10.zzc     // Catch:{ IllegalArgumentException -> 0x005e }
            r11.unbindService(r10, r9)     // Catch:{ IllegalArgumentException -> 0x005e }
        L_0x005e:
            com.google.android.gms.common.ConnectionResult r9 = new com.google.android.gms.common.ConnectionResult     // Catch:{ all -> 0x004c }
            r10 = 16
            r9.<init>(r10)     // Catch:{ all -> 0x004c }
            goto L_0x0048
        L_0x0066:
            android.os.StrictMode.setVmPolicy(r0)
            throw r9
        L_0x006a:
            r9 = move-exception
            com.google.android.gms.common.ConnectionResult r9 = r9.zza
        L_0x006d:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzp.zzd(com.google.android.gms.common.internal.zzp, java.lang.String, java.util.concurrent.Executor):com.google.android.gms.common.ConnectionResult");
    }

    public final void onBindingDied(ComponentName componentName) {
        onServiceDisconnected(componentName);
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.zza.zzb) {
            try {
                this.zza.zzd.removeMessages(1, this.zzf);
                this.zze = iBinder;
                this.zzg = componentName;
                for (ServiceConnection onServiceConnected : this.zzb.values()) {
                    onServiceConnected.onServiceConnected(componentName, iBinder);
                }
                this.zzc = 1;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        synchronized (this.zza.zzb) {
            try {
                this.zza.zzd.removeMessages(1, this.zzf);
                this.zze = null;
                this.zzg = componentName;
                for (ServiceConnection onServiceDisconnected : this.zzb.values()) {
                    onServiceDisconnected.onServiceDisconnected(componentName);
                }
                this.zzc = 2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final int zza() {
        return this.zzc;
    }

    public final ComponentName zzb() {
        return this.zzg;
    }

    public final IBinder zzc() {
        return this.zze;
    }

    public final void zze(ServiceConnection serviceConnection, ServiceConnection serviceConnection2, String str) {
        this.zzb.put(serviceConnection, serviceConnection2);
    }

    public final void zzf(ServiceConnection serviceConnection, String str) {
        this.zzb.remove(serviceConnection);
    }

    public final void zzg(String str) {
        this.zza.zzd.removeMessages(1, this.zzf);
        zzs zzs = this.zza;
        zzs.zzf.unbindService(zzs.zzc, this);
        this.zzd = false;
        this.zzc = 2;
    }

    public final boolean zzh(ServiceConnection serviceConnection) {
        return this.zzb.containsKey(serviceConnection);
    }

    public final boolean zzi() {
        return this.zzb.isEmpty();
    }

    public final boolean zzj() {
        return this.zzd;
    }
}
