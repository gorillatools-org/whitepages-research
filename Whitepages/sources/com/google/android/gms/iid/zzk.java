package com.google.android.gms.iid;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public final class zzk implements ServiceConnection {
    private final Intent zzbp;
    private final ScheduledExecutorService zzbq;
    private final Queue<zzg> zzbr;
    private zzi zzbs;
    private boolean zzbt;
    private final Context zzl;

    public zzk(Context context, String str) {
        this(context, str, new ScheduledThreadPoolExecutor(0, new NamedThreadFactory("EnhancedIntentService")));
    }

    private zzk(Context context, String str, ScheduledExecutorService scheduledExecutorService) {
        this.zzbr = new ArrayDeque();
        this.zzbt = false;
        Context applicationContext = context.getApplicationContext();
        this.zzl = applicationContext;
        this.zzbp = new Intent(str).setPackage(applicationContext.getPackageName());
        this.zzbq = scheduledExecutorService;
    }

    public final synchronized void zzd(Intent intent, BroadcastReceiver.PendingResult pendingResult) {
        try {
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "new intent queued in the bind-strategy delivery");
            }
            this.zzbr.add(new zzg(intent, pendingResult, this.zzbq));
            zzm();
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a6, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized void zzm() {
        /*
            r6 = this;
            monitor-enter(r6)
            java.lang.String r0 = "EnhancedIntentService"
            r1 = 3
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x0012 }
            if (r0 == 0) goto L_0x0015
            java.lang.String r0 = "EnhancedIntentService"
            java.lang.String r2 = "flush queue called"
            android.util.Log.d(r0, r2)     // Catch:{ all -> 0x0012 }
            goto L_0x0015
        L_0x0012:
            r0 = move-exception
            goto L_0x00a9
        L_0x0015:
            java.util.Queue<com.google.android.gms.iid.zzg> r0 = r6.zzbr     // Catch:{ all -> 0x0012 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0012 }
            if (r0 != 0) goto L_0x00a7
            java.lang.String r0 = "EnhancedIntentService"
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x0012 }
            if (r0 == 0) goto L_0x002c
            java.lang.String r0 = "EnhancedIntentService"
            java.lang.String r2 = "found intent to be delivered"
            android.util.Log.d(r0, r2)     // Catch:{ all -> 0x0012 }
        L_0x002c:
            com.google.android.gms.iid.zzi r0 = r6.zzbs     // Catch:{ all -> 0x0012 }
            if (r0 == 0) goto L_0x0053
            boolean r0 = r0.isBinderAlive()     // Catch:{ all -> 0x0012 }
            if (r0 == 0) goto L_0x0053
            java.lang.String r0 = "EnhancedIntentService"
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x0012 }
            if (r0 == 0) goto L_0x0045
            java.lang.String r0 = "EnhancedIntentService"
            java.lang.String r2 = "binder is alive, sending the intent."
            android.util.Log.d(r0, r2)     // Catch:{ all -> 0x0012 }
        L_0x0045:
            java.util.Queue<com.google.android.gms.iid.zzg> r0 = r6.zzbr     // Catch:{ all -> 0x0012 }
            java.lang.Object r0 = r0.poll()     // Catch:{ all -> 0x0012 }
            com.google.android.gms.iid.zzg r0 = (com.google.android.gms.iid.zzg) r0     // Catch:{ all -> 0x0012 }
            com.google.android.gms.iid.zzi r2 = r6.zzbs     // Catch:{ all -> 0x0012 }
            r2.zzd((com.google.android.gms.iid.zzg) r0)     // Catch:{ all -> 0x0012 }
            goto L_0x0015
        L_0x0053:
            java.lang.String r0 = "EnhancedIntentService"
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x0012 }
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0078
            java.lang.String r0 = "EnhancedIntentService"
            boolean r3 = r6.zzbt     // Catch:{ all -> 0x0012 }
            r3 = r3 ^ r2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0012 }
            r5 = 39
            r4.<init>(r5)     // Catch:{ all -> 0x0012 }
            java.lang.String r5 = "binder is dead. start connection? "
            r4.append(r5)     // Catch:{ all -> 0x0012 }
            r4.append(r3)     // Catch:{ all -> 0x0012 }
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x0012 }
            android.util.Log.d(r0, r3)     // Catch:{ all -> 0x0012 }
        L_0x0078:
            boolean r0 = r6.zzbt     // Catch:{ all -> 0x0012 }
            if (r0 != 0) goto L_0x00a5
            r6.zzbt = r2     // Catch:{ all -> 0x0012 }
            com.google.android.gms.common.stats.ConnectionTracker r0 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()     // Catch:{ SecurityException -> 0x0098 }
            android.content.Context r2 = r6.zzl     // Catch:{ SecurityException -> 0x0098 }
            android.content.Intent r3 = r6.zzbp     // Catch:{ SecurityException -> 0x0098 }
            r4 = 65
            boolean r0 = r0.bindService(r2, r3, r6, r4)     // Catch:{ SecurityException -> 0x0098 }
            if (r0 == 0) goto L_0x0090
            monitor-exit(r6)
            return
        L_0x0090:
            java.lang.String r0 = "EnhancedIntentService"
            java.lang.String r2 = "binding to the service failed"
            android.util.Log.e(r0, r2)     // Catch:{ SecurityException -> 0x0098 }
            goto L_0x00a0
        L_0x0098:
            r0 = move-exception
            java.lang.String r2 = "EnhancedIntentService"
            java.lang.String r3 = "Exception while binding the service"
            android.util.Log.e(r2, r3, r0)     // Catch:{ all -> 0x0012 }
        L_0x00a0:
            r6.zzbt = r1     // Catch:{ all -> 0x0012 }
            r6.zzn()     // Catch:{ all -> 0x0012 }
        L_0x00a5:
            monitor-exit(r6)
            return
        L_0x00a7:
            monitor-exit(r6)
            return
        L_0x00a9:
            monitor-exit(r6)     // Catch:{ all -> 0x0012 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.iid.zzk.zzm():void");
    }

    private final void zzn() {
        while (!this.zzbr.isEmpty()) {
            this.zzbr.poll().finish();
        }
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this) {
            try {
                this.zzbt = false;
                this.zzbs = (zzi) iBinder;
                if (Log.isLoggable("EnhancedIntentService", 3)) {
                    String valueOf = String.valueOf(componentName);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 20);
                    sb.append("onServiceConnected: ");
                    sb.append(valueOf);
                    Log.d("EnhancedIntentService", sb.toString());
                }
                if (iBinder == null) {
                    Log.e("EnhancedIntentService", "Null service connection");
                    zzn();
                } else {
                    zzm();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            String valueOf = String.valueOf(componentName);
            StringBuilder sb = new StringBuilder(valueOf.length() + 23);
            sb.append("onServiceDisconnected: ");
            sb.append(valueOf);
            Log.d("EnhancedIntentService", sb.toString());
        }
        zzm();
    }
}
