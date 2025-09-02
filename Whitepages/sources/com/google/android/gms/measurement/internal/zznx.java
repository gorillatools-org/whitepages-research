package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;

public final class zznx implements ServiceConnection, BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    final /* synthetic */ zzny zza;
    /* access modifiers changed from: private */
    public volatile boolean zzb;
    private volatile zzgy zzc;

    protected zznx(zzny zzny) {
        this.zza = zzny;
    }

    public final void onConnected(Bundle bundle) {
        this.zza.zzu.zzaX().zzn();
        synchronized (this) {
            try {
                Preconditions.checkNotNull(this.zzc);
                this.zza.zzu.zzaX().zzq(new zzns(this, (zzgl) this.zzc.getService()));
            } catch (DeadObjectException | IllegalStateException unused) {
                this.zzc = null;
                this.zzb = false;
            }
        }
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        zzny zzny = this.zza;
        zzny.zzu.zzaX().zzn();
        zzhe zzl = zzny.zzu.zzl();
        if (zzl != null) {
            zzl.zzk().zzb("Service connection failed", connectionResult);
        }
        synchronized (this) {
            this.zzb = false;
            this.zzc = null;
        }
        this.zza.zzu.zzaX().zzq(new zznw(this, connectionResult));
    }

    public final void onConnectionSuspended(int i) {
        zzio zzio = this.zza.zzu;
        zzio.zzaX().zzn();
        zzio.zzaW().zzd().zza("Service connection suspended");
        zzio.zzaX().zzq(new zznt(this));
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:20|21) */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r3.zza.zzu.zzaW().zze().zza("Service connect failed to get IMeasurementService");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x006c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onServiceConnected(android.content.ComponentName r4, android.os.IBinder r5) {
        /*
            r3 = this;
            com.google.android.gms.measurement.internal.zzny r4 = r3.zza
            com.google.android.gms.measurement.internal.zzio r4 = r4.zzu
            com.google.android.gms.measurement.internal.zzil r4 = r4.zzaX()
            r4.zzn()
            monitor-enter(r3)
            r4 = 0
            if (r5 != 0) goto L_0x0027
            r3.zzb = r4     // Catch:{ all -> 0x0024 }
            com.google.android.gms.measurement.internal.zzny r4 = r3.zza     // Catch:{ all -> 0x0024 }
            com.google.android.gms.measurement.internal.zzio r4 = r4.zzu     // Catch:{ all -> 0x0024 }
            com.google.android.gms.measurement.internal.zzhe r4 = r4.zzaW()     // Catch:{ all -> 0x0024 }
            com.google.android.gms.measurement.internal.zzhc r4 = r4.zze()     // Catch:{ all -> 0x0024 }
            java.lang.String r5 = "Service connected with null binder"
            r4.zza(r5)     // Catch:{ all -> 0x0024 }
            monitor-exit(r3)     // Catch:{ all -> 0x0024 }
            return
        L_0x0024:
            r4 = move-exception
            goto L_0x00a7
        L_0x0027:
            r0 = 0
            java.lang.String r1 = r5.getInterfaceDescriptor()     // Catch:{ RemoteException -> 0x006c }
            java.lang.String r2 = "com.google.android.gms.measurement.internal.IMeasurementService"
            boolean r2 = r2.equals(r1)     // Catch:{ RemoteException -> 0x006c }
            if (r2 == 0) goto L_0x005a
            java.lang.String r1 = "com.google.android.gms.measurement.internal.IMeasurementService"
            android.os.IInterface r1 = r5.queryLocalInterface(r1)     // Catch:{ RemoteException -> 0x006c }
            boolean r2 = r1 instanceof com.google.android.gms.measurement.internal.zzgl     // Catch:{ RemoteException -> 0x006c }
            if (r2 == 0) goto L_0x0042
            com.google.android.gms.measurement.internal.zzgl r1 = (com.google.android.gms.measurement.internal.zzgl) r1     // Catch:{ RemoteException -> 0x006c }
        L_0x0040:
            r0 = r1
            goto L_0x0048
        L_0x0042:
            com.google.android.gms.measurement.internal.zzgj r1 = new com.google.android.gms.measurement.internal.zzgj     // Catch:{ RemoteException -> 0x006c }
            r1.<init>(r5)     // Catch:{ RemoteException -> 0x006c }
            goto L_0x0040
        L_0x0048:
            com.google.android.gms.measurement.internal.zzny r5 = r3.zza     // Catch:{ RemoteException -> 0x006c }
            com.google.android.gms.measurement.internal.zzio r5 = r5.zzu     // Catch:{ RemoteException -> 0x006c }
            com.google.android.gms.measurement.internal.zzhe r5 = r5.zzaW()     // Catch:{ RemoteException -> 0x006c }
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zzj()     // Catch:{ RemoteException -> 0x006c }
            java.lang.String r1 = "Bound to IMeasurementService interface"
            r5.zza(r1)     // Catch:{ RemoteException -> 0x006c }
            goto L_0x007d
        L_0x005a:
            com.google.android.gms.measurement.internal.zzny r5 = r3.zza     // Catch:{ RemoteException -> 0x006c }
            com.google.android.gms.measurement.internal.zzio r5 = r5.zzu     // Catch:{ RemoteException -> 0x006c }
            com.google.android.gms.measurement.internal.zzhe r5 = r5.zzaW()     // Catch:{ RemoteException -> 0x006c }
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zze()     // Catch:{ RemoteException -> 0x006c }
            java.lang.String r2 = "Got binder with a wrong descriptor"
            r5.zzb(r2, r1)     // Catch:{ RemoteException -> 0x006c }
            goto L_0x007d
        L_0x006c:
            com.google.android.gms.measurement.internal.zzny r5 = r3.zza     // Catch:{ all -> 0x0024 }
            com.google.android.gms.measurement.internal.zzio r5 = r5.zzu     // Catch:{ all -> 0x0024 }
            com.google.android.gms.measurement.internal.zzhe r5 = r5.zzaW()     // Catch:{ all -> 0x0024 }
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zze()     // Catch:{ all -> 0x0024 }
            java.lang.String r1 = "Service connect failed to get IMeasurementService"
            r5.zza(r1)     // Catch:{ all -> 0x0024 }
        L_0x007d:
            if (r0 != 0) goto L_0x0095
            r3.zzb = r4     // Catch:{ all -> 0x0024 }
            com.google.android.gms.common.stats.ConnectionTracker r4 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()     // Catch:{ IllegalArgumentException -> 0x00a5 }
            com.google.android.gms.measurement.internal.zzny r5 = r3.zza     // Catch:{ IllegalArgumentException -> 0x00a5 }
            com.google.android.gms.measurement.internal.zzio r0 = r5.zzu     // Catch:{ IllegalArgumentException -> 0x00a5 }
            android.content.Context r0 = r0.zzaT()     // Catch:{ IllegalArgumentException -> 0x00a5 }
            com.google.android.gms.measurement.internal.zznx r5 = r5.zza     // Catch:{ IllegalArgumentException -> 0x00a5 }
            r4.unbindService(r0, r5)     // Catch:{ IllegalArgumentException -> 0x00a5 }
            goto L_0x00a5
        L_0x0095:
            com.google.android.gms.measurement.internal.zzny r4 = r3.zza     // Catch:{ all -> 0x0024 }
            com.google.android.gms.measurement.internal.zzio r4 = r4.zzu     // Catch:{ all -> 0x0024 }
            com.google.android.gms.measurement.internal.zzil r4 = r4.zzaX()     // Catch:{ all -> 0x0024 }
            com.google.android.gms.measurement.internal.zznq r5 = new com.google.android.gms.measurement.internal.zznq     // Catch:{ all -> 0x0024 }
            r5.<init>(r3, r0)     // Catch:{ all -> 0x0024 }
            r4.zzq(r5)     // Catch:{ all -> 0x0024 }
        L_0x00a5:
            monitor-exit(r3)     // Catch:{ all -> 0x0024 }
            return
        L_0x00a7:
            monitor-exit(r3)     // Catch:{ all -> 0x0024 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznx.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        zzio zzio = this.zza.zzu;
        zzio.zzaX().zzn();
        zzio.zzaW().zzd().zza("Service disconnected");
        zzio.zzaX().zzq(new zznr(this, componentName));
    }

    public final void zzb(Intent intent) {
        zzny zzny = this.zza;
        zzny.zzg();
        Context zzaT = zzny.zzu.zzaT();
        ConnectionTracker instance = ConnectionTracker.getInstance();
        synchronized (this) {
            try {
                if (this.zzb) {
                    this.zza.zzu.zzaW().zzj().zza("Connection attempt already in progress");
                    return;
                }
                zzny zzny2 = this.zza;
                zzny2.zzu.zzaW().zzj().zza("Using local app measurement service");
                this.zzb = true;
                instance.bindService(zzaT, intent, zzny2.zza, 129);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void zzc() {
        zzny zzny = this.zza;
        zzny.zzg();
        Context zzaT = zzny.zzu.zzaT();
        synchronized (this) {
            try {
                if (this.zzb) {
                    this.zza.zzu.zzaW().zzj().zza("Connection attempt already in progress");
                } else if (this.zzc == null || (!this.zzc.isConnecting() && !this.zzc.isConnected())) {
                    this.zzc = new zzgy(zzaT, Looper.getMainLooper(), this, this);
                    this.zza.zzu.zzaW().zzj().zza("Connecting to remote service");
                    this.zzb = true;
                    Preconditions.checkNotNull(this.zzc);
                    this.zzc.checkAvailabilityAndConnect();
                } else {
                    this.zza.zzu.zzaW().zzj().zza("Already awaiting connection attempt");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void zzd() {
        if (this.zzc != null && (this.zzc.isConnected() || this.zzc.isConnecting())) {
            this.zzc.disconnect();
        }
        this.zzc = null;
    }
}
