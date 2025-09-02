package com.google.android.play.core.review.internal;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.core.review.zze;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzt {
    private static final Map zza = new HashMap();
    /* access modifiers changed from: private */
    public final Context zzb;
    /* access modifiers changed from: private */
    public final zzi zzc;
    private final String zzd;
    /* access modifiers changed from: private */
    public final List zze = new ArrayList();
    private final Set zzf = new HashSet();
    private final Object zzg = new Object();
    /* access modifiers changed from: private */
    public boolean zzh;
    private final Intent zzi;
    private final WeakReference zzj;
    private final IBinder.DeathRecipient zzk = new zzl(this);
    private final AtomicInteger zzl = new AtomicInteger(0);
    /* access modifiers changed from: private */
    public ServiceConnection zzm;
    /* access modifiers changed from: private */
    public IInterface zzn;
    private final zze zzo;

    public zzt(Context context, zzi zzi2, String str, Intent intent, zze zze2, zzo zzo2, byte[] bArr) {
        this.zzb = context;
        this.zzc = zzi2;
        this.zzd = "com.google.android.finsky.inappreviewservice.InAppReviewService";
        this.zzi = intent;
        this.zzo = zze2;
        this.zzj = new WeakReference((Object) null);
    }

    public static /* synthetic */ void zzh(zzt zzt) {
        zzt.zzc.zzd("reportBinderDeath", new Object[0]);
        MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(zzt.zzj.get());
        zzt.zzc.zzd("%s : Binder has died.", zzt.zzd);
        for (zzj zzc2 : zzt.zze) {
            zzc2.zzc(zzt.zzs());
        }
        zzt.zze.clear();
        zzt.zzt();
    }

    static /* bridge */ /* synthetic */ void zzn(zzt zzt) {
        zzt.zzc.zzd("linkToDeath", new Object[0]);
        try {
            zzt.zzn.asBinder().linkToDeath(zzt.zzk, 0);
        } catch (RemoteException e) {
            zzt.zzc.zzc(e, "linkToDeath failed", new Object[0]);
        }
    }

    static /* bridge */ /* synthetic */ void zzo(zzt zzt) {
        zzt.zzc.zzd("unlinkToDeath", new Object[0]);
        zzt.zzn.asBinder().unlinkToDeath(zzt.zzk, 0);
    }

    /* access modifiers changed from: private */
    public final void zzt() {
        synchronized (this.zzg) {
            try {
                for (TaskCompletionSource trySetException : this.zzf) {
                    trySetException.trySetException(zzs());
                }
                this.zzf.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final Handler zzc() {
        Handler handler;
        Map map = zza;
        synchronized (map) {
            try {
                if (!map.containsKey(this.zzd)) {
                    HandlerThread handlerThread = new HandlerThread(this.zzd, 10);
                    handlerThread.start();
                    map.put(this.zzd, new Handler(handlerThread.getLooper()));
                }
                handler = (Handler) map.get(this.zzd);
            } catch (Throwable th) {
                throw th;
            }
        }
        return handler;
    }

    public final IInterface zze() {
        return this.zzn;
    }

    public final void zzp(zzj zzj2, TaskCompletionSource taskCompletionSource) {
        synchronized (this.zzg) {
            this.zzf.add(taskCompletionSource);
            taskCompletionSource.getTask().addOnCompleteListener(new zzk(this, taskCompletionSource));
        }
        synchronized (this.zzg) {
            try {
                if (this.zzl.getAndIncrement() > 0) {
                    this.zzc.zza("Already connected to the service.", new Object[0]);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        zzc().post(new zzm(this, zzj2.zzb(), zzj2));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzq(TaskCompletionSource taskCompletionSource, Task task) {
        synchronized (this.zzg) {
            this.zzf.remove(taskCompletionSource);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002b, code lost:
        zzc().post(new com.google.android.play.core.review.internal.zzn(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0037, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzr(com.google.android.gms.tasks.TaskCompletionSource r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.zzg
            monitor-enter(r0)
            java.util.Set r1 = r3.zzf     // Catch:{ all -> 0x003a }
            r1.remove(r4)     // Catch:{ all -> 0x003a }
            monitor-exit(r0)     // Catch:{ all -> 0x003a }
            java.lang.Object r4 = r3.zzg
            monitor-enter(r4)
            java.util.concurrent.atomic.AtomicInteger r0 = r3.zzl     // Catch:{ all -> 0x0028 }
            int r0 = r0.get()     // Catch:{ all -> 0x0028 }
            if (r0 <= 0) goto L_0x002a
            java.util.concurrent.atomic.AtomicInteger r0 = r3.zzl     // Catch:{ all -> 0x0028 }
            int r0 = r0.decrementAndGet()     // Catch:{ all -> 0x0028 }
            if (r0 <= 0) goto L_0x002a
            com.google.android.play.core.review.internal.zzi r0 = r3.zzc     // Catch:{ all -> 0x0028 }
            java.lang.String r1 = "Leaving the connection open for other ongoing calls."
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0028 }
            r0.zzd(r1, r2)     // Catch:{ all -> 0x0028 }
            monitor-exit(r4)     // Catch:{ all -> 0x0028 }
            return
        L_0x0028:
            r0 = move-exception
            goto L_0x0038
        L_0x002a:
            monitor-exit(r4)     // Catch:{ all -> 0x0028 }
            com.google.android.play.core.review.internal.zzn r4 = new com.google.android.play.core.review.internal.zzn
            r4.<init>(r3)
            android.os.Handler r0 = r3.zzc()
            r0.post(r4)
            return
        L_0x0038:
            monitor-exit(r4)     // Catch:{ all -> 0x0028 }
            throw r0
        L_0x003a:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003a }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.review.internal.zzt.zzr(com.google.android.gms.tasks.TaskCompletionSource):void");
    }

    private final RemoteException zzs() {
        return new RemoteException(String.valueOf(this.zzd).concat(" : Binder has died."));
    }

    static /* bridge */ /* synthetic */ void zzm(zzt zzt, zzj zzj2) {
        if (zzt.zzn == null && !zzt.zzh) {
            zzt.zzc.zzd("Initiate binding to the service.", new Object[0]);
            zzt.zze.add(zzj2);
            zzs zzs = new zzs(zzt, (zzr) null);
            zzt.zzm = zzs;
            zzt.zzh = true;
            if (!zzt.zzb.bindService(zzt.zzi, zzs, 1)) {
                zzt.zzc.zzd("Failed to bind to the service.", new Object[0]);
                zzt.zzh = false;
                for (zzj zzc2 : zzt.zze) {
                    zzc2.zzc(new zzu());
                }
                zzt.zze.clear();
            }
        } else if (zzt.zzh) {
            zzt.zzc.zzd("Waiting to bind to the service.", new Object[0]);
            zzt.zze.add(zzj2);
        } else {
            zzj2.run();
        }
    }
}
